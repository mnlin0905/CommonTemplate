package com.acchain.community.activity.person;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.QRAndCommandContract;
import com.acchain.community.presenter.QRAndCommandPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.window.ShareSaveQrDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.acchain.community.base.BasePresenter.singleAccountBean;

/**
 * function---- QRAndCommandActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 11:38:29 (+0000).
 */
@RuntimePermissions
@Route(path = ARouterConst.Activity_QRAndCommandActivity, extras = ARouterConst.FLAG_BUSINESS_CARD)
public class QRAndCommandActivity extends BaseActivity<QRAndCommandPresenter> implements QRAndCommandContract.View, ShareSaveQrDialogFragment.OnShareSaveQrListener {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_duty)
    TextView mTvDuty;
    @BindView(R.id.iv_qr)
    ImageView mIvQr;
    @BindView(R.id.ll_create_command)
    LinearLayout mLlCreateCommand;
    @BindView(R.id.tv_company)
    TextView mTvCompany;

    private Bitmap qrImage;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_qr_and_command;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化界面
        Glide.with(this)
                .load(singleAccountBean.getImgSrc())
                .apply(new RequestOptions().placeholder(R.drawable.default_head_img).circleCrop())
                .into(mIvIcon);
        mTvName.setText(singleAccountBean.getNickname());
        mTvDuty.setText(singleAccountBean.getBusinessCardStr());
        mTvCompany.setText(singleAccountBean.getBusinessCard().getCompanyName());

        mPresenter.getWeiCode(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new ShareSaveQrDialogFragment()
                .setOnShareSaveQrListener(this)
                .show(getSupportFragmentManager(), "share_save");
        return true;
    }


    @OnClick(R.id.ll_create_command)
    public void onViewClicked() {
        if (qrImage == null) {
            showToast("正在加载名片信息,请等待...");
            return;
        }
        //todo 生成微口令
        showToast("暂不支持");
    }

    @Override
    public void getWeiCodeFinish(String commandBean) {
        if (commandBean != null) {
            qrImage = CodeUtils.createImage(commandBean, mTvCompany.getMeasuredWidth(), mTvCompany.getMeasuredHeight(), ImageUtils.getBitmap(R.drawable.acc));
            mIvQr.setImageBitmap(qrImage);
        }
    }

    @Override
    public boolean onSaveQr(Dialog dialog) {
        if(qrImage!=null) {
            onSaveQrWithPermission();
        }else{
            showToast("未生成二维码图片,无法进行保存");
        }
        return false;
    }

    /**
     * 权限存在时,存储图片
     */
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void onSaveQrWithPermission(){
        try {
            File file = DefaultPreferenceUtil.getRandomFile(this, getString(R.string.qr_directory), true);
            if (file != null) {
                ImageUtils.save(qrImage, file, Bitmap.CompressFormat.JPEG);
                showSnackbar(String.format(Locale.CHINA, "文件存储成功,路径为:%s", file.getPath()), "查看图片", v -> {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setDataAndType(Uri.fromFile(file), "image/*");
                    startActivity(Intent.createChooser(intent, "请选择图片查看工具"));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            showToast("文件存储失败");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        QRAndCommandActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        showToast("开启存储权限才能保存图片");
        request.proceed();
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
        showSnackbar("存储 权限已被禁止,请前往软件权限管理界面手动打开", "去设置", v -> PermissionUtils.openAppSettings());
    }

    @Override
    public boolean onShareQr(Dialog dialog) {
        // TODO: 2018/1/29 跳到朋友界面进行分享
        showToast("暂不开启分享功能");
        return false;
    }
}