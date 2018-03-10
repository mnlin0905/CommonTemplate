package com.acchain.community.base;

import com.acchain.community.bean.AliPay;
import com.acchain.community.bean.CommitOrderRequest;
import com.acchain.community.bean.CommitOrderResponse;
import com.acchain.community.bean.CommonCart;
import com.acchain.community.bean.ConfirmAdoptOrder;
import com.acchain.community.bean.ConfirmCartAdoptOrder;
import com.acchain.community.bean.ConfirmCartPanicOrder;
import com.acchain.community.bean.ConfirmCartPreOrder;
import com.acchain.community.bean.ConfirmExerciseOrder;
import com.acchain.community.bean.ConfirmPanicOrder;
import com.acchain.community.bean.ConfirmPreOrder;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.bean.QueryPay;
import com.acchain.community.bean.WeiXinPay;
import com.acchain.community.interfaces.CommonHttpInterface;
import com.acchain.community.util.HttpListener;
import com.acchain.community.retrofit.HttpInterface;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.HTTPException;
import com.acchain.community.util.L;
import com.acchain.community.window.ProgressDialog;
import com.blankj.utilcode.util.ActivityUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能----基础中间键,mvp模式
 * <p>
 * Created by ACChain on 2017/9/22.
 */

public abstract class BasePresenter<T extends BaseView> implements CommonHttpInterface {
    /**
     * 网络成功返回,用0表示
     */
    protected static final int RESULT_OK = 0;
    /**
     * 会员信息
     * <p>
     * 设置默认值,放置空指针异常
     */
    public static GetAccountBean singleAccountBean = new GetAccountBean();
    protected T mView;
    /**
     * 网络请求对象
     */
    @Inject
    protected HttpInterface httpInterface;
    /**
     * 存储rxjava网络请求,用于取消事件
     * <p>
     * 管理网络的事件队列
     */
    private CompositeDisposable group;
    private int taskCount;

    /**
     * 网络请求成功回调接口
     * <p>
     * 如果有该callback,则网络请求成功后将执行该callback方法
     */
    public interface HttpCallback<CB> {
        /**
         * @param tag 回调时用于区分某个请求,tag为null则表示dataSet字段自身为null或异常
         */
        void run(CB tag);
    }

    /**
     * 如果加载框存在,则让其消失
     */
    private void disappearLoading() {
        //如果loading还在显示,则将弹出框隐去(如果还有其他任务,则不消失)
        if (taskCount <= 0) {
            //由于mview等原因可能无法关闭,但必须尝试关闭一次,否则可能导致弹出框一直在显示状态
            ProgressDialog.getInstance(mView.getBaseActivity()).dismiss();
        }
    }

    /**
     * 显示加载框
     *
     * @param loadingMessage 为null时不显示加载框
     */
    private void appearLoading(String loadingMessage) {
        //判断是否显示进度框
        if (loadingMessage == null) {
            return;
        }

        //如果activity不在栈顶,则不显示(同一个类加载器只会对同一个类加载一次)
        if (ActivityUtils.getTopActivity().getClass() != mView.getBaseActivity().getClass()) {
            return;
        }

        //如果baseActivity中还未添加loading碎片,则显示进度框
        ProgressDialog.getInstance(mView.getBaseActivity())
                .setMessage(loadingMessage)
                .show();
    }

    /**
     * 进行网络请求
     */
    @SafeVarargs
    protected final <BEAN extends Object> void requestHttp(Observable<BaseHttpBean<BEAN>> observable, Consumer<BaseHttpBean<BEAN>> onNextListener, Consumer<Throwable>... onErrorListener) {
        requestHttp(null, observable, onNextListener, onErrorListener);
    }

    /**
     * 进行网络请求
     *
     * @param loadingMessage  null mesons no loading
     * @param observable      observable
     * @param onNextListener  listener to deal result
     * @param onErrorListener any error(HTTPException or throwable)
     */
    @SafeVarargs
    protected final <BEAN extends Object> void requestHttp(String loadingMessage, Observable<BaseHttpBean<BEAN>> observable, Consumer<BaseHttpBean<BEAN>> onNextListener, Consumer<Throwable>... onErrorListener) {
        //onError回调
        Consumer<Throwable> onError = throwable -> {
            try {
                //如果不是数据异常,而是网络异常,则提示网络错误
                if(!(throwable instanceof HTTPException)){
                    mView.showToast("网络异常");
                }

                if (onErrorListener.length != 0) {
                    onErrorListener[0].accept(throwable);
                } else {
                    throwable.printStackTrace();
                }
            } catch (Exception e) {
                //统一处理各种未知的异常情况
                e.printStackTrace();
                mView.showToast("未知异常");
            } finally {
                taskCount--;
                disappearLoading();
            }
        };

        //onNext回调
        Consumer<BaseHttpBean<BEAN>> onNext = beanBaseHttpBean -> {
            //如果视图丢失,则不处理网络事件
            if (mView != null && beanBaseHttpBean != null) {
                if (beanBaseHttpBean.result == RESULT_OK) {
                    onNextListener.accept(beanBaseHttpBean);
                    taskCount--;
                    disappearLoading();
                    return;
                } else if (beanBaseHttpBean.result == 1007) {
                    DefaultPreferenceUtil.getInstance().setToken(null);
                    DefaultPreferenceUtil.getInstance().setLogin(false);
                    RxBus.getInstance().post(new BaseEvent(Const.SHOW_LOGIN_DIALOG, null));
                } else {
                    mView.showToast(beanBaseHttpBean.message);
                }
                onError.accept(new HTTPException("ERROR", beanBaseHttpBean));
            }
        };

        //设定请求关系
        taskCount++;
        addDisposable(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> appearLoading(loadingMessage))
                .subscribe(onNext, onError));
    }

    /**
     * 进行网络请求
     */
    protected <BEAN extends Object> void requestHttp(String loadingMessage,Observable<BaseHttpBean<BEAN>> observable, HttpListener<BaseHttpBean<BEAN>> listener) {
        requestHttp(loadingMessage,observable,
                beanBaseHttpBean -> listener.onSuccess(beanBaseHttpBean),
                (Consumer<Throwable>) throwable -> {
                    if (throwable instanceof HTTPException) {
                        listener.onFailed((HTTPException) throwable);
                    } else {
                        listener.onFailed(throwable);
                    }
                });
    }

    /**
     * 进行网络请求
     */
    protected <BEAN extends Object> void requestHttp(Observable<BaseHttpBean<BEAN>> observable, HttpListener<BaseHttpBean<BEAN>> listener) {
        requestHttp(observable,
                beanBaseHttpBean -> listener.onSuccess(beanBaseHttpBean),
                (Consumer<Throwable>) throwable -> {
                    if (throwable instanceof HTTPException) {
                        listener.onFailed((HTTPException) throwable);
                    } else {
                        listener.onFailed(throwable);
                    }
                });
    }

    /**
     * 移除rx监听
     */
    protected void removeDisposable() {
        if (group != null) {
            group.dispose();
        }
    }

    /**
     * 添加到监听组
     */
    public void addDisposable(Disposable disposable) {
        if (group == null) {
            group = new CompositeDisposable();
        }
        group.add(disposable);
    }

    /**
     * 获取邮箱验证码
     *
     * @param email 邮箱
     * @param tag   操作类型
     * @param code  图形验证码
     */
    @Override
    public void sendEmail(String email, String tag, String code) {
        requestHttp(httpInterface.sendEmail(email, tag, code),
                objectBaseHttpBean -> mView.sendEmailFinish());
    }

    /**
     * 获取短信验证码
     *
     * @param mobile 手机号
     * @param tag    操作类型
     * @param code   图形验证码
     */
    @Override
    public void sendMessage(String mobile, String tag, String code) {
        requestHttp(httpInterface.sendMessage(mobile, tag, code),
                objectBaseHttpBean -> mView.sendMessageFinish());
    }

    /**
     * 校验验证码
     *
     * @param username 手机号/邮箱
     * @param tag      操作类型
     * @param code     短信验证码
     */
    @Override
    public void checkCode(String username, String tag, String code) {
        requestHttp(httpInterface.checkCode(username, tag, code),
                objectBaseHttpBean -> mView.checkCodeFinish());
    }

    /**
     * 1.9 获取会员账户信息
     *
     * @param token token值
     */
    @Override
    public void getAccount(String token) {
        requestHttp(httpInterface.getAccount(token),
                getAccountBeanBaseHttpBean -> {
                    DefaultPreferenceUtil.getInstance().setMemberId(getAccountBeanBaseHttpBean.dataSet.getMemberId());
                    mView.getAccountFinish(singleAccountBean = getAccountBeanBaseHttpBean.dataSet);
                },
                (Consumer<Throwable>) throwable -> mView.getAccountFinish(singleAccountBean));
    }

    @Override
    public void setPersonalInfo(String token, int sex, String province, String city) {
        requestHttp("修改信息中...",httpInterface.setPersonalInfo(token, sex == 0 ? null : String.valueOf(sex), province, city),
                objectBaseHttpBean -> mView.setPersonalInfoFinish());
    }

    /**
     * 加入购物车前检查商品状态
     *
     * @param itemCount      商品购买数量
     * @param flashSaleRefId 如果是抢购，必须传这个参数，否则可以不用管
     * @param productType    0：抢购，1：预售，2：领养，3：行权
     * @param productSubId   抢购/预售/领养ID
     */
    @Override
    public void checkProductState(int itemCount, Integer flashSaleRefId, int productType, int productSubId, int productId, String productAttrValueIds, String adoptCodeId, String token) {
        requestHttp(httpInterface.checkProductState(itemCount, flashSaleRefId, productType, productSubId), new HttpListener<BaseHttpBean<CommonCart>>() {
            @Override
            public void onSuccess(BaseHttpBean<CommonCart> response) {
                if (response.dataSet.getState() == 0) {//检验成功
                    addCart(productType, productId, productSubId, itemCount, productAttrValueIds, flashSaleRefId, adoptCodeId, token);
                } else {
                    mView.onAddCartFinish(null);
                }
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onAddCartFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onAddCartFinish(null);
            }
        });
    }

    /*因为加入购物车之前都要访问检查购物车的接口，此处在基类里处理*/
    public void checkAndAddCart(int productType, int productId, int productSubId, int itemCount, String productAttrValueIds, Integer flashSaleRefId, String adoptCodeId, String token) {
        if (productType == 0) {//抢购
            checkProductState(itemCount, flashSaleRefId, productType, productSubId, productId, productAttrValueIds, adoptCodeId, token);
        } else if (productType == 1) {//预售
            checkProductState(itemCount, null, productType, productSubId, productId, productAttrValueIds, adoptCodeId, token);
        } else if (productType == 2) {//领养
            checkProductState(itemCount, null, productType, productSubId, productId, productAttrValueIds, adoptCodeId, token);
        }
    }

    /**
     * 加入购物车
     *
     * @param productType         商品类型0：抢购1：预售 2：领养
     * @param productId           主商品ID
     * @param productSubId        抢购/预售/领养ID
     * @param itemCount           商品购买数量
     * @param productAttrValueIds 商品详情对应attrValueId 如: (1,3,5)
     * @param flashSaleRefId      限购活动ID;商品类型为0时,必传 (非必传)
     * @param adoptCodeId         领养物品ID; 商品类型为2时,必传 (非必传)
     * @param token               会员ID,用户登录时必传 (非必传)
     */
    @Override
    public void addCart(int productType, int productId, int productSubId, int itemCount, String productAttrValueIds, Integer flashSaleRefId, String adoptCodeId, String token) {
        requestHttp(httpInterface.addCart(productType, productId, productSubId, itemCount, productAttrValueIds, flashSaleRefId, adoptCodeId, token), new HttpListener<BaseHttpBean<CommonCart>>() {
            @Override
            public void onSuccess(BaseHttpBean<CommonCart> response) {
                mView.onAddCartFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onAddCartFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onAddCartFinish(null);
            }
        });
    }

    /**
     * @param cardId              购物车ID
     * @param cartType            购物车类型0：抢购1：预售 2：领养
     * @param token               会员ID
     * @param itemCount           商品购买数量
     * @param productSubIds       抢购/预售/领养ID
     * @param productAttrValueIds 商品详情对应attrValueId如: (1,3,5)
     * @param flashSaleRefId      限购活动与商品关联表ID;商品类型为0时,必传（购物车获取）
     * @param adoptCodeIds        领养物品IDs(1,3,5); 商品类型为2时,必传
     * @param productId           主商品ID
     * @param type                类型，0--修改抢购的数量，1--修改抢购的属性，2--修改预购的数量，3--修改预购的属性，4--修改领养的标的
     */
    @Override
    public void editCart(int cardId, int cartType, String token, Integer itemCount, int productSubIds, String productAttrValueIds, Integer flashSaleRefId, String adoptCodeIds, int productId, int type) {
        requestHttp(httpInterface.editCart(cardId, cartType, token, itemCount, productSubIds, productAttrValueIds, flashSaleRefId, adoptCodeIds, productId), new HttpListener<BaseHttpBean<CommonCart>>() {
            @Override
            public void onSuccess(BaseHttpBean<CommonCart> response) {
                mView.onEditCartFinish(response.dataSet, type);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onEditCartFinish(null, type);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onEditCartFinish(null, type);
            }
        });
    }

    @Override
    public void deleteCart(String token, int cartType, String cardIds, String productSubIds, int type) {
        requestHttp(httpInterface.deleteCart(token, cartType, cardIds, productSubIds), new HttpListener<BaseHttpBean<CommonCart>>() {
            @Override
            public void onSuccess(BaseHttpBean<CommonCart> response) {
                mView.onDeleteCartFinish(response.dataSet, cartType, type);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onDeleteCartFinish(null, cartType, type);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onDeleteCartFinish(null, cartType, type);
            }
        });
    }

    /**
     * 确认订单,将三种订单写在一个方法里面
     *
     * @param token              用户登录标识
     * @param productType        商品类型 0：抢购  1：预售  2：领养  3：行权
     * @param itemCount          购买商品数量
     * @param productSubId       4种具体商品类型商品ID
     * @param flashSaleRefId     只有从抢购详情过来的才用传
     * @param cartIds            非必填，如果是购物车列表页面过来带ids
     * @param productAttrValeIds 商品详情对应attrValueId 如: (1,3,5)
     * @param adoptCodeIds       领养时对应的标的的id拼接 如：（1,3,5）
     * @param type               根据type的不同来返回不同的对象，0--抢购订单，1--购物车抢购订单，2--预售订单，3--购物车预售订单，4--领养订单，5--购物车领养订单,6--行权订单
     */
    @Override
    public void confirmOrder(String token, int productType, Integer itemCount, Integer productSubId, Integer flashSaleRefId, String cartIds, String productAttrValeIds, String adoptCodeIds, int type) {
        if (type == 0) {
            requestHttp(httpInterface.confirmPanicOrder(token, productType, itemCount, productSubId, flashSaleRefId, productAttrValeIds), new HttpListener<BaseHttpBean<ConfirmPanicOrder>>() {
                @Override
                public void onSuccess(BaseHttpBean<ConfirmPanicOrder> response) {
                    mView.onConfirmOrderFinish(response.dataSet, type);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onConfirmOrderFinish(null, type);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onConfirmOrderFinish(null, type);
                }
            });
        } else if (type == 1) {
            requestHttp(httpInterface.confirmCartPanicOrder(token, productType, cartIds), new HttpListener<BaseHttpBean<ConfirmCartPanicOrder>>() {
                @Override
                public void onSuccess(BaseHttpBean<ConfirmCartPanicOrder> response) {
                    mView.onConfirmOrderFinish(response.dataSet, type);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onConfirmOrderFinish(null, type);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onConfirmOrderFinish(null, type);
                }
            });
        } else if (type == 2) {
            requestHttp(httpInterface.confirmPreOrder(token, productType, itemCount, productSubId, productAttrValeIds), new HttpListener<BaseHttpBean<ConfirmPreOrder>>() {
                @Override
                public void onSuccess(BaseHttpBean<ConfirmPreOrder> response) {
                    mView.onConfirmOrderFinish(response.dataSet, type);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onConfirmOrderFinish(null, type);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onConfirmOrderFinish(null, type);
                }
            });
        } else if (type == 3) {
            requestHttp(httpInterface.confirmCartPreOrder(token, productType, cartIds), new HttpListener<BaseHttpBean<ConfirmCartPreOrder>>() {
                @Override
                public void onSuccess(BaseHttpBean<ConfirmCartPreOrder> response) {
                    mView.onConfirmOrderFinish(response.dataSet, type);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onConfirmOrderFinish(null, type);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onConfirmOrderFinish(null, type);
                }
            });
        } else if (type == 4) {
            requestHttp(httpInterface.confirmAdoptOrder(token, productType, itemCount, productSubId, adoptCodeIds), new HttpListener<BaseHttpBean<ConfirmAdoptOrder>>() {
                @Override
                public void onSuccess(BaseHttpBean<ConfirmAdoptOrder> response) {
                    mView.onConfirmOrderFinish(response.dataSet, type);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onConfirmOrderFinish(null, type);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onConfirmOrderFinish(null, type);
                }
            });
        } else if (type == 5) {
            requestHttp(httpInterface.confirmCartAdoptOrder(token, productType, cartIds), new HttpListener<BaseHttpBean<ConfirmCartAdoptOrder>>() {
                @Override
                public void onSuccess(BaseHttpBean<ConfirmCartAdoptOrder> response) {
                    mView.onConfirmOrderFinish(response.dataSet, type);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onConfirmOrderFinish(null, type);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onConfirmOrderFinish(null, type);
                }
            });
        } else if (type == 6) {
            requestHttp(httpInterface.confirmExerciseOrder(token, productType, itemCount, productSubId, productAttrValeIds), new HttpListener<BaseHttpBean<ConfirmExerciseOrder>>() {
                @Override
                public void onSuccess(BaseHttpBean<ConfirmExerciseOrder> response) {
                    mView.onConfirmOrderFinish(response.dataSet, type);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onConfirmOrderFinish(null, type);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onConfirmOrderFinish(null, type);
                }
            });
        }
    }

    /**
     * @param commitOrderRequests 订单对象集合
     * @param type                根据type的不同来返回不同的对象，0--抢购订单，1--购物车抢购订单，2--预售订单，3--购物车预售订单，4--领养订单，5--购物车领养订单
     */
    @Override
    public void commitOrder(List<CommitOrderRequest> commitOrderRequests, int type) {
        requestHttp("正在提交订单......",httpInterface.commitOrder(commitOrderRequests), new HttpListener<BaseHttpBean<CommitOrderResponse>>() {
            @Override
            public void onSuccess(BaseHttpBean<CommitOrderResponse> response) {
                mView.onCommitOrderFinish(response.dataSet, type);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onCommitOrderFinish(null, type);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onCommitOrderFinish(null, type);
            }
        });
    }

    @Override
    public void weiXinPay(String token, int productType, String orderCodes, int payType, int type) {
        requestHttp("获取预交易单号......",httpInterface.weiXinPay(token, productType, orderCodes, payType), new HttpListener<BaseHttpBean<WeiXinPay>>() {
            @Override
            public void onSuccess(BaseHttpBean<WeiXinPay> response) {
                mView.onWeiXinPayFinish(response.dataSet,type);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onWeiXinPayFinish(null,type);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onWeiXinPayFinish(null,type);
            }
        });
    }

    @Override
    public void aliPay(String token, int productType, String orderCodes, int payType, int type) {
        requestHttp(httpInterface.aliPay(token, productType, orderCodes, payType), new HttpListener<BaseHttpBean<AliPay>>() {
            @Override
            public void onSuccess(BaseHttpBean<AliPay> response) {
                mView.onAliPayFinish(response.dataSet,type);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onAliPayFinish(null,type);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onAliPayFinish(null,type);
            }
        });
    }

    @Override
    public void queryWechatPay(String token, int productType, String orderCodes, String outTradeNo) {
        requestHttp("查询支付结果......",httpInterface.queryWechatPay(token, productType, orderCodes, outTradeNo), new HttpListener<BaseHttpBean<QueryPay>>() {
            @Override
            public void onSuccess(BaseHttpBean<QueryPay> response) {
                mView.onQueryWechatPayFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onQueryWechatPayFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onQueryWechatPayFinish(null);
            }
        });
    }

    @Override
    public void queryAliPay(String token, int productType, String orderCodes, String outTradeNo,int type) {
        requestHttp("查询支付结果......",httpInterface.queryAliPay(token, productType, orderCodes, outTradeNo), new HttpListener<BaseHttpBean<QueryPay>>() {
            @Override
            public void onSuccess(BaseHttpBean<QueryPay> response) {
                mView.onQueryAliPayFinish(response.dataSet,type);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onQueryAliPayFinish(null,type);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onQueryAliPayFinish(null,type);
            }
        });
    }

    @Override
    public void updateAddress(String token, String name, String mobile, String province, String city, String area, String address, int addId, int defaultStatus) {
        requestHttp(httpInterface.updateAddress(token, name, mobile, province, city, area, address, addId, defaultStatus), objectBaseHttpBean -> mView.updateAddressFinish());
    }

    @Override
    public void deleteAddress(String token, int addId) {
        requestHttp(httpInterface.deleteAddress(token, addId), objectBaseHttpBean -> mView.deleteAddressFinish());
    }

    @Override
    public void updateEmailOrMobile(String token, String emailOrMobile, String payPwd, String code) {
        requestHttp(httpInterface.updateEmailOrMobile(token, emailOrMobile, payPwd, code), objectBaseHttpBean -> mView.deleteAddressFinish());
    }

    @Override
    public void setPayPwd(String token, String confPayPwd, String payPwd, String code) {
        requestHttp(httpInterface.setPayPwd(token, confPayPwd, payPwd, code), objectBaseHttpBean -> mView.setPayPwdFinish());
    }

    @Override
    public void changePayPwd(String token, String oldPwd, String newPwd) {
        requestHttp(httpInterface.changePayPwd(token, oldPwd, newPwd), objectBaseHttpBean -> mView.changePayPwdFinish());
    }

    @Override
    public void cancelArtificial(String token, int id) {
        requestHttp(httpInterface.cancelArtificial(token, id), objectBaseHttpBean -> mView.cancelArtificialFinish());
    }

    @Override
    public void getPayPwdArtificial(String token) {
        //这里不处理异常情况,避免与真实的null发生混淆
        requestHttp(httpInterface.getPayPwdArtificial(token),
                transPasswordStatusBaseHttpBean -> mView.getPayPwdArtificialFinish(transPasswordStatusBaseHttpBean.dataSet),
                (Consumer<Throwable>) throwable -> mView.showToast("申请状态异常"));
    }

    @Override
    public void addOrDeleteCollection(String token, int commodityId, int commodityType, int saleId) {
        requestHttp(httpInterface.addOrDeleteCollection(token, commodityId, commodityType, saleId), new HttpListener<BaseHttpBean<Object>>() {
            @Override
            public void onSuccess(BaseHttpBean<Object> response) {
                mView.onAddOrDeleteCollectFinish(commodityType, true);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onAddOrDeleteCollectFinish(commodityType, false);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onAddOrDeleteCollectFinish(commodityType, false);
            }
        });
    }

    @Override
    public void addUserFooter(String deviceId, String token, int commodityId, int commodityType, int saleId) {
        requestHttp(httpInterface.addUserFooter(deviceId, token, commodityId, commodityType, saleId), new HttpListener<BaseHttpBean<Object>>() {
            @Override
            public void onSuccess(BaseHttpBean<Object> response) {
                L.i("添加足迹成功");
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                L.i("添加足迹失败");
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                L.i("添加足迹失败");

            }
        });
    }

    @Override
    public void accountBlances(String token, String currency) {
        requestHttp(httpInterface.accountBlances(token, currency),
                accountBalanceBaseHttpBean -> mView.accountBlancesFinish(accountBalanceBaseHttpBean.dataSet),
                throwable -> mView.accountBlancesFinish(null));
    }

    @Override
    public void queryTransactionList(String token, String currency, String tag, String year, String month) {
        requestHttp(httpInterface.queryTransactionList(token, currency, tag, year, month)
                , transactionListBeanBaseHttpBean -> mView.queryTransactionListFinish(transactionListBeanBaseHttpBean.dataSet),
                throwable -> mView.queryTransactionListFinish(null));
    }
}
