package com.acchain.community.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.activity.other.MainActivity;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.PersonContract;
import com.acchain.community.presenter.PersonPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.acchain.community.base.BasePresenter.singleAccountBean;
import static com.acchain.community.util.Const.KEY_POSITION;

/**
 * function---- PersonFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:49:35 (+0000).
 */
@Route(path = ARouterConst.Fragment_PersonFragment)
public class PersonFragment extends BaseFragment<PersonPresenter> implements PersonContract.View, Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nikeName)
    TextView nikeName;
    @BindView(R.id.iv_smrz)
    ImageView ivSmrz;//实名认证图标
    @BindView(R.id.iv_tran_pwd)
    ImageView ivTranPwd;//是否设置交易密码
    @BindView(R.id.iv_phone)
    ImageView ivPhone;//是否绑定手机
    @BindView(R.id.iv_email)
    ImageView ivEmail;//是否绑定邮箱
    @BindView(R.id.headerImage)
    ImageView headerImage; //头像
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.tv_attention)
    TextView mTvAttention;
    @BindView(R.id.tv_foot)
    TextView mTvFoot;
    @BindView(R.id.tv_property)
    TextView mTvProperty;
    Unbinder unbinder;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_person;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //toolbar初始化
        mToolbar.inflateMenu(R.menu.menu_fragment_person);
        mToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting://设置界面
                ARouter.getInstance().build(ARouterConst.Activity_SoftSettingActivity).navigation();
                break;
            case R.id.action_message://消息界面
                ARouter.getInstance().build(ARouterConst.Activity_NewsActivity).navigation();
                break;
        }
        return true;
    }

    @OnClick({R.id.nikeName, R.id.iv_qr, R.id.headerImage, R.id.linear_spsc, R.id.linear_dpgz, R.id.linear_llzj, R.id.linear_cyzc, R.id.rela_my_tran, R.id.rela_all_order, R.id.linear_wait_pay, R.id.linear_wait_fh, R.id.linear_wait_sh, R.id.linear_wait_pj, R.id.linear_refund})
    public void onViewClicked(View view) {
        int orderFormPosition = 4;
        switch (view.getId()) {
            case R.id.iv_qr://二维码
                ARouter.getInstance().build(ARouterConst.Activity_QRAndCommandActivity).navigation();
                break;
            case R.id.nikeName://昵称
                //ARouter.getInstance().build(ARouterConst.Activity_LoginActivity).navigation();
                break;
            case R.id.headerImage://头像//登录
                if (!DefaultPreferenceUtil.getInstance().hasLogin()) {
                    ARouter.getInstance().build(ARouterConst.Activity_LoginActivity).navigation();
                }
                break;
            case R.id.linear_spsc://收藏
                ARouter.getInstance().build(ARouterConst.Activity_GoodsCollectionActivity).navigation();
                break;
            case R.id.linear_dpgz://关注
                showToast("店铺关注");
                break;
            case R.id.linear_llzj://流览足迹
                ARouter.getInstance().build(ARouterConst.Activity_FootHistoryActivity).navigation();
                break;
            case R.id.linear_cyzc://切换到钱包界面
                ((MainActivity) baseActivity).mBnveNavigation.setCurrentItem(1);
                break;
            case R.id.rela_my_tran://交易
                ARouter.getInstance().build(ARouterConst.Activity_TransactionRecordActivity).navigation();
                break;
            case R.id.rela_all_order://全部订单
                ARouter.getInstance().build(ARouterConst.Activity_OrderFormRecordActivity).navigation();
                break;
            case R.id.linear_wait_pay://等待付款
                orderFormPosition--;
            case R.id.linear_wait_fh://待发货
                orderFormPosition--;
            case R.id.linear_wait_sh://待收货
                orderFormPosition--;
            case R.id.linear_wait_pj://待评价
                ARouter.getInstance()
                        .build(ARouterConst.Activity_OrderFormRecordActivity)
                        .withInt(KEY_POSITION, orderFormPosition)
                        .navigation();
                break;
            case R.id.linear_refund://售后退款
                ARouter.getInstance().build(ARouterConst.Activity_RefundAftermarketActivity).navigation();
                break;
        }
    }

    /**
     * 更新最新的数据
     */
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    /**
     * 刷新界面中的数据
     */
    private void refreshData() {
        //刷新数据
        Glide.with(this)
                .load(singleAccountBean.getImgSrc())
                .apply(new RequestOptions().placeholder(R.drawable.default_head_img).circleCrop())
                .into(headerImage);
        nikeName.setText(singleAccountBean.getNickname());

        //图标
        ivSmrz.setImageResource(singleAccountBean.isVerifiedSuccess() ? R.drawable.smrz_1 : R.drawable.smrz_0);
        ivTranPwd.setImageResource(singleAccountBean.isPayPwd() ? R.drawable.tran_pwd_1 : R.drawable.tran_pwd_0);
        ivPhone.setImageResource(!TextUtils.isEmpty(singleAccountBean.getMobile()) ? R.drawable.phone_1 : R.drawable.phone_0);
        ivEmail.setImageResource(!TextUtils.isEmpty(singleAccountBean.getMobile()) ? R.drawable.email_1 : R.drawable.email_0);

        //资产足迹
        mTvCollect.setText(String.valueOf(singleAccountBean.getCollections()));
        mTvAttention.setText(String.valueOf(singleAccountBean.getAttentions()));
        mTvFoot.setText(String.valueOf(singleAccountBean.getBrowses()));
        mTvProperty.setText(String.valueOf(singleAccountBean.getAssets()));
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        if (accountBean == null) {
            return;
        }

        refreshData();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (singleAccountBean != null) {
            refreshData();
        }
    }

}