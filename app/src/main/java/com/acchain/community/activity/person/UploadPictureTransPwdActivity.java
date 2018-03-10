package com.acchain.community.activity.person;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.TransPasswordStatus;
import com.acchain.community.contract.UploadPictureTransPwdContract;
import com.acchain.community.presenter.UploadPictureTransPwdPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.CancelConfirmDialogFragment;
import com.acchain.community.window.CardTypePickerViewDialog;
import com.acchain.community.window.ChangePictureDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.PermissionUtils;
import com.bumptech.glide.Glide;
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
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.acchain.community.util.Const.KEY_PHONE_NUMBER;
import static com.acchain.community.util.Const.KEY_TRANS_PWD_STATUS;

/**
 * function---- UploadPictureTransPwdActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 11:46:08 (+0000).
 */
@RuntimePermissions
@Route(path = ARouterConst.Activity_UploadPictureTransPwdActivity)
public class UploadPictureTransPwdActivity extends BaseActivity<UploadPictureTransPwdPresenter> implements UploadPictureTransPwdContract.View, TakePhoto.TakeResultListener, InvokeListener, ChangePictureDialogFragment.OnChangeHeadListener, CardTypePickerViewDialog.onCardTypePickerViewListener, CancelConfirmDialogFragment.OnOperateListener, LineMenuView.LineMenuListener {
    @BindView(R.id.fl_positive)
    FrameLayout mFlPositive;
    @BindView(R.id.fl_negative)
    FrameLayout mFlNegative;
    @BindView(R.id.fl_multi)
    FrameLayout mFlMulti;
    @BindView(R.id.iv_positive)
    ImageView mIvPositive;
    @BindView(R.id.iv_negative)
    ImageView mIvNegative;
    @BindView(R.id.iv_multi)
    ImageView mIvMulti;
    @BindView(R.id.lmv_card_type)
    LineMenuView mLmvCardType;
    @Autowired(required = true, name = KEY_PHONE_NUMBER)
    String phoneNumber;

    /**
     * 存储的图片位置
     * <p>
     * 默认为null
     * <p>
     * 如果都有值时,表示上传图片成功
     */
    private String picture_positive;
    private String picture_negative;
    private String picture_multiple;

    /**
     * 当前上传到文件的位置
     * 0:正
     * 1:反
     * 2:手持
     */
    private int currentFile;
    /**
     * 身份验证类型
     */
    private int cardType = -1;
    /**
     * takePhoto框架
     */
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_upload_picture_trans_pwd;
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
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mIvPositive.setImageDrawable(null);
        mIvNegative.setImageDrawable(null);
        mIvMulti.setImageDrawable(null);
        picture_multiple = picture_positive = picture_negative = null;
        showToast("图片已清除");
        return true;
    }

    @OnClick({R.id.fl_positive, R.id.fl_negative, R.id.fl_multi, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_positive:
                currentFile = 0;
                break;
            case R.id.fl_negative:
                currentFile = 1;
                break;
            case R.id.fl_multi:
                currentFile = 2;
                break;
            case R.id.bt_submit://选择图片成功
                if (cardType == -1) {
                    showToast("请选择证件类型");
                    return;
                }

                //信息填写完毕,则向后台发送请求,如果成功则跳转到结果界面
                if (TextUtils.isEmpty(picture_positive) ||
                        TextUtils.isEmpty(picture_negative) ||
                        TextUtils.isEmpty(picture_multiple)) {
                    showToast("请先上传必须的图片");
                } else {
                    mPresenter.resetPayPwdArtificial(DefaultPreferenceUtil.getInstance().getToken(), phoneNumber, picture_positive, picture_negative, picture_multiple, cardType);
                }
                return;
        }

        //上传图片
        new ChangePictureDialogFragment()
                .setShowFunction(true, true, false, true)
                .show(getSupportFragmentManager(), "upload_picture");
    }

    /**
     * 直接拍照
     */
    @Override
    public boolean onClickTakePhone(Dialog dialog) {
        UploadPictureTransPwdActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this, 0);
        return false;
    }

    /**
     * 从相册获取
     */
    @Override
    public boolean onClickFromAlbum(Dialog dialog) {
        UploadPictureTransPwdActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this, 1);
        return false;
    }

    /**
     * 还原默认设置:
     * 本类不实用
     */
    @Override
    public boolean onClickRestoreDefault(Dialog dialog) {
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
                File file = DefaultPreferenceUtil.getRandomFile(this, getString(R.string.password_directory), true);
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
        UploadPictureTransPwdActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationals(final PermissionRequest request) {
        showToast("您必须开启权限,才能进行之后的操作");
        request.proceed();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        showToast("权限被禁止,无法选择图片");
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

        //文件上传
        ImageView des = mIvPositive;
        switch (currentFile) {
            case 0:
                picture_positive = filePath;
                break;
            case 1:
                des = mIvNegative;
                picture_negative = filePath;
                break;
            case 2:
                des = mIvMulti;
                picture_multiple = filePath;
                break;
        }
        Glide.with(this)
                .load(filePath)
                .into(des);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        showToast("选择失败:" + msg);
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

    @Override
    public void onTypePickerDismiss() {

    }

    @Override
    public void onTypePickerFinish(int position, String type) {
        cardType = position;

        mLmvCardType.setBrief(type);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void resetPayPwdArtificialFinish() {
        //上传成功后,获取一次审核状态,如果为处理中,成功,或者失败,则调往结果显示界面
        mPresenter.getPayPwdArtificial(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getPayPwdArtificialFinish(TransPasswordStatus status) {
        super.getPayPwdArtificialFinish(status);

        //如果为待审核,已通过,未通过三种状态,则跳转到结果界面
        if (status != null &&
                status.getStatus() >= 0 &&
                status.getStatus() <= 2) {
            ARouter.getInstance()
                    .build(ARouterConst.Activity_ChangeTransResultActivity)
                    .withObject(KEY_TRANS_PWD_STATUS, status)
                    .navigation();
            finish();
        } else {
            new CancelConfirmDialogFragment()
                    .setTitle("抱歉!审核出现未知异常,请重新上传证件")
                    .setCancelText("放弃退出")
                    .setConfirmText("重新审核")
                    .setOnOperateListener(this)
                    .show(getSupportFragmentManager(), "restart_staff");
        }
    }


    @Override
    public boolean onCancel(Dialog dialog) {
        finish();
        return false;
    }

    @Override
    public boolean onConfirm(Dialog dialog) {
        ARouter.getInstance().build(ARouterConst.Activity_StaffServiceChangeTransActivity).navigation();
        finish();
        return false;
    }

    /**
     * 点击左侧文本
     *
     * @param v 被点击到的v;此时应该是左侧的TextView
     * @return 是否消费该点击事件, 如果返回true, 则performSelf将不会被调用
     */
    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    /**
     * @param v 被点击到的v;此时应该是右侧的TextView
     * @return 是否消费该点击事件, 如果返回true, 则performSelf将不会被调用
     */
    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    /**
     * @param v 被点击到的v;此时应该是该view自身:LineMenuView
     */
    @Override
    public void performSelf(LineMenuView v) {
        //选择证件类型
        if (v == mLmvCardType) {
            if (!CardTypePickerViewDialog.getInstance(this).setOnPickerViewListener(this).show()) {
                showToast("抱歉!无法选择证件");
            }
        }
    }
}