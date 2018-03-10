package com.acchain.community.activity.person;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.PersonInformationContract;
import com.acchain.community.presenter.PersonInformationPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.ChangePictureDialogFragment;
import com.acchain.community.window.LocationPickerViewDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.acchain.community.base.BasePresenter.singleAccountBean;

/**
 * function---- PersonInformationActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 07:20:03 (+0000).
 */
@Route(path = ARouterConst.Activity_PersonInformationActivity)
@RuntimePermissions
public class PersonInformationActivity extends BaseActivity<PersonInformationPresenter> implements PersonInformationContract.View, LineMenuView.LineMenuListener, ChangePictureDialogFragment.OnChangeHeadListener, TakePhoto.TakeResultListener, InvokeListener, LocationPickerViewDialog.onLocationPickerViewListener {
    /**
     * 需要跳转到的活动或界面
     */
    private static final String[] NAVIGATION_ACTIVITY = {
            null,//头像
            ARouterConst.Activity_QRAndCommandActivity,//我的二维码
            null,//微脉号
            ARouterConst.Activity_ChangeNickNameActivity,//昵称
            ARouterConst.Activity_ChangeSexActivity,//性别
            null,//ARouterConst.Activity_ChangeLocationActivity,// 地区功能
            ARouterConst.Activity_ShowCertificationResultActivity,// 实名认证,显示结果
            null,// 职业/名片,未设置则调往设置界面
            ARouterConst.Activity_ChangeDeliveryAddressActivity,//收货地址
    };

    @BindView(R.id.lmv_head)
    LineMenuView mLmvHead;
    @BindView(R.id.lmv_qr)
    LineMenuView mLmvQr;
    @BindView(R.id.lmv_mpn)
    LineMenuView mLmvMpn;
    @BindView(R.id.lmv_nick_name)
    LineMenuView mLmvNickName;
    @BindView(R.id.lmv_sex)
    LineMenuView mLmvSex;
    @BindView(R.id.lmv_location)
    LineMenuView mLmvLocation;
    @BindView(R.id.lmv_certification)
    LineMenuView mLmvCertification;
    @BindView(R.id.lmv_profession)
    LineMenuView mLmvProfession;
    @BindView(R.id.lmv_address)
    LineMenuView mLmvAddress;


    /**
     * takePhoto框架
     */
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;


    /**
     * 设置个人信息
     */
    private String currentProvince;
    private String currentCountry;


    @Override
    protected void onResume() {
        super.onResume();

        /*
        * 刷新头像
        * 会员号
        * 昵称
        * 性别
        * 地区
        * 真实姓名
        * 职业
        * */
        getAccountFinish(singleAccountBean);
        mLmvMpn.setBrief(singleAccountBean.getOnlyName());
        mLmvNickName.setBrief(singleAccountBean.getNickname());
        mLmvSex.setBrief(singleAccountBean.getSexStr());
        mLmvLocation.setBrief(singleAccountBean.getLocation());
        mLmvCertification.setBrief(singleAccountBean.getCertification());
        mLmvProfession.setBrief(singleAccountBean.getBusinessCardStr());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_person_information;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //takePhoto框架
        getTakePhoto();
        takePhoto.onCreate(savedInstanceState);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_person_information, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 隐私
        ARouter.getInstance().build(ARouterConst.Activity_PrivacyActivity).navigation();
        return true;
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        //名片可能会有动态变动
        if (v == mLmvProfession) {
            ARouter.getInstance()
                    .build(BasePresenter.singleAccountBean.getBusinessCard() == null ?
                            ARouterConst.Activity_EditProfessionCardActivity :
                            ARouterConst.Activity_CardDetailActivity)
                    .navigation();
            return;
        }

        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);

        //position是指在布局中出现的顺序
        performDeal(position);
    }

    /**
     * 如果不需要跳转揭秘那,则由本类类处理数据
     */
    private void performDeal(int position) {
        if (position < NAVIGATION_ACTIVITY.length && NAVIGATION_ACTIVITY[position] != null) {
            ARouter.getInstance().build(NAVIGATION_ACTIVITY[position]).navigation();
            return;
        }

        //在当前界面处理的逻辑
        switch (position) {
            case 0: //选择设置头像
                new ChangePictureDialogFragment()
                        .setShowFunction(true, true, true, true)
                        .show(getSupportFragmentManager(), "change_head");
                break;
            case 2://会员名不可修改,无操作
                break;
            case 5://地区,暂时设置为从列表中选择城市
                LocationPickerViewDialog.getInstance(this)
                        .setOnPickerViewListener(this)
                        .show(true, true);
                break;
        }
    }

    /**
     * 直接拍照
     */
    @Override
    public boolean onClickTakePhone(Dialog dialog) {
        PersonInformationActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this, 0);
        return false;
    }

    /**
     * 从相册获取
     */
    @Override
    public boolean onClickFromAlbum(Dialog dialog) {
        PersonInformationActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this, 1);
        return false;
    }

    /**
     * 还原默认设置
     */
    @Override
    public boolean onClickRestoreDefault(Dialog dialog) {
        mPresenter.setPhoto(ImageUtils.drawable2Bytes(getResources().getDrawable(R.drawable.default_head_img), Bitmap.CompressFormat.PNG));
        return false;
    }

    @Override
    public boolean onClickCancel(Dialog dialog) {
        return false;
    }

    /**
     * 如果调用该方法,需要先有权限
     *
     * @param type 0:photo;
     *             1:album;
     */
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void needsPermission(int type) {
        switch (type) {
            //拍照
            case 0: {
                File file = DefaultPreferenceUtil.getRandomFile(this, getString(R.string.head_directory), true);
                if (file != null) {
                    takePhoto.onPickFromCapture(Uri.fromFile(file));
                }
                break;
            }

            //相册
            case 1: {
                takePhoto.onPickFromGallery();
                break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);

        PersonInformationActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationals(final PermissionRequest request) {
        showToast("您必须开启权限,才能进行之后的操作");
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        showToast("权限被禁止,无法设置头像");
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
        showSnackbar("相机 和 存储 权限已被禁止,请前往软件权限管理界面手动打开", "去设置", v -> PermissionUtils.openAppSettings());
    }

    @Override
    public void takeSuccess(TResult result) {
        //获取uri
        String filePath = result.getImage().getOriginalPath();

        // 更换头像,获取到了操作后请求网络
        File file = new File(filePath);
        if (!file.exists()) {
            showToast("图片未选中");
            return;
        }
        mPresenter.setPhoto(file);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        showToast("头像更换失败:" + msg);
    }

    @Override
    public void takeCancel() {
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        CompressConfig config = new CompressConfig
                .Builder()
                //.enableReserveRaw(false)//设置不保存原图
                //.setMaxPixel(1024 * 100)//最大像素值
                .create();
        // 启用图片压缩(显示压缩进度框)
        takePhoto.onEnableCompress(config, true);

        TakePhotoOptions takePhotoOptions = new TakePhotoOptions
                .Builder()
                .setWithOwnGallery(false)//使用takePhoto自带相册
                .setCorrectImage(true) //纠正图片旋转角度
                .create();
        takePhoto.setTakePhotoOptions(takePhotoOptions);
        return takePhoto;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePhoto.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        takePhoto.onSaveInstanceState(outState);
    }

    /**
     * 头像设置成功,需要设置到原位置上
     */
    @Override
    public void setPhotoFinish() {
        //设置头像后,更改本地缓存
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        //加载头像
        Glide.with(this)
                .asDrawable()
                .apply(new RequestOptions().placeholder(R.drawable.default_head_img).circleCrop())
                .load(singleAccountBean.getImgSrc())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        showToast("头像加载失败");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mLmvHead.setBadge(resource);
                        return true;
                    }
                })
                .submit();

        //加载地区
        mLmvLocation.setBrief(accountBean.getLocation());
    }

    @Override
    public void onLocationPickerDismiss() {

    }

    @Override
    public void onLocationPickerFinish(String province, String city, String country) {
        mPresenter.setPersonalInfo(DefaultPreferenceUtil.getInstance().getToken(), 0, province, city);

        this.currentProvince = province;
        this.currentCountry = country;
    }

    @Override
    public void setPersonalInfoFinish() {
        super.setPersonalInfoFinish();
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }
}