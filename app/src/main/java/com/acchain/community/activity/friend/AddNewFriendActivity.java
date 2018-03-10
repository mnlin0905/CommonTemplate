package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.AddNewFriendContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.presenter.AddNewFriendPresenter;
import com.acchain.community.window.MyQrCodeDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- AddNewFriendActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 06:45:02 (+0000).
 */
@Route(path = ARouterConst.Activity_AddNewFriendActivity)
public class AddNewFriendActivity extends BaseActivity<AddNewFriendPresenter> implements AddNewFriendContract.View {

    @BindView(R.id.search)
    EditText mTvSearch;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_new_friend;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTvSearch.post(() -> mTvSearch.setBackground(new RoundRectShapeDrawable(mTvSearch.getWidth(), mTvSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.linear_qrcode, R.id.linear_koulin, R.id.linear_add_phone_contact, R.id.linear_saoyisao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_qrcode:
                MyQrCodeDialog myQrCodeDialog = new MyQrCodeDialog(this);
                myQrCodeDialog.show();
                break;
            case R.id.linear_koulin:
                break;
            case R.id.linear_add_phone_contact:
                ARouter.getInstance().build(ARouterConst.Activity_AddPhoneContactActivity).navigation();
                break;
            case R.id.linear_saoyisao:
                break;
        }
    }
}