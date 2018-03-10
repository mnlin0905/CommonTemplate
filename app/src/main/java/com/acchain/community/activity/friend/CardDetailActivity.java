package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.CardDetail;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.CardDetailContract;
import com.acchain.community.presenter.CardDetailPresenter;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.acchain.community.util.Const.KEY_HEAD_IMG;
import static com.acchain.community.util.Const.KEY_NIKE_NAME;
import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;
import static com.acchain.community.util.Const.KEY_TYPE_ADD_FRIEND_FROM;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_CARD;

/**
 * function---- CardDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/18 05:58:49 (+0000).
 */
@Route(path = ARouterConst.Activity_CardDetailActivity,extras = ARouterConst.FLAG_LOGIN)
public class CardDetailActivity extends BaseActivity<CardDetailPresenter> implements CardDetailContract.View {

    @BindView(R.id.headImg)
    CircleImageView ivHeadImg;
    @BindView(R.id.sex)
    ImageView sex;
    @BindView(R.id.nikeName)
    TextView tvNikeName;
    @BindView(R.id.job)
    TextView job;
    @BindView(R.id.unit)
    TextView unit;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.yewu)
    TextView yewu;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.unitInfo)
    TextView unitInfo;

    public String nikeName;
    public String headImg;
    public String userId;

    @Autowired(name = "cardId")
    public String cardId;
    private Menu menu;
    @Autowired(name = "isFriend")
    public boolean isFriend;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_card_detail, menu);
        this.menu = menu;
        if (isFriend) {
            MenuItem editMenu = menu.findItem(R.id.add);
            editMenu.setVisible(false);
        }
        mPresenter.loadCardDetail();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                showSnackbar("分享", "666", null);
                break;
            case R.id.add:
                if (StringUtils.isEmpty(userId)) {
                    return true;
                }
                ARouter.getInstance()
                        .build(ARouterConst.Activity_FriendAddActivity)
                        .withString(KEY_TYPE_ADD_FRIEND_FROM, TYPE_ADD_FRIEND_FROM_CARD)
                        .withString(KEY_NIKE_NAME, nikeName)
                        .withString(KEY_TARGET_FRIEND_ID, userId)
                        .withString(KEY_HEAD_IMG, headImg)
                        .navigation();
                break;
            case R.id.edit:
                ARouter.getInstance().build(ARouterConst.Activity_EditProfessionCardActivity).navigation();
                finish();
                break;
        }
        return true;
    }

    @Override
    public String getCardId() {
        return cardId;
    }

    /**
     * 显示自己的名片信息
     * @param cardDetail
     */
    @Override
    public void showCardDetail(GetAccountBean.BusinessCard cardDetail) {
        MenuItem item = menu.findItem(R.id.add);
        item.setVisible(false);
        MenuItem editMenu = menu.findItem(R.id.edit);
        editMenu.setVisible(true);
        Glide.with(this).load(BasePresenter.singleAccountBean.getImgSrc()).apply(new RequestOptions().centerCrop()).into(ivHeadImg);
        String nickname = BasePresenter.singleAccountBean.getNickname();
        tvNikeName.setText(nickname);
        job.setText(cardDetail.getPosition());
        unit.setText(cardDetail.getCompanyName());
        phone.setText(cardDetail.getPhone());
        yewu.setText(cardDetail.getMainBusiness());
        email.setText(cardDetail.getEmail());
        address.setText(cardDetail.getAddress());
        unitInfo.setText(cardDetail.getIntroduction());
    }

    /**
     * 显示别人的名片信息
     * @param cardDetail
     */
    @Override
    public void showCardDetail(CardDetail cardDetail) {
        Glide.with(this).load(cardDetail.getPhoto_img()).apply(new RequestOptions().centerCrop()).into(ivHeadImg);
        tvNikeName.setText(cardDetail.getNickname());
        job.setText(cardDetail.getPosition());
        unit.setText(cardDetail.getCompany_name());
        phone.setText(cardDetail.getPhone());
        yewu.setText(cardDetail.getMain_business());
        email.setText(cardDetail.getEmail());
        address.setText(cardDetail.getAddress());
        unitInfo.setText(cardDetail.getIntroduction());
//        unitInfo.setText(cardDetail.in);
        nikeName = cardDetail.getNickname();
        userId = String.valueOf(cardDetail.getMember_id());
        headImg = cardDetail.getPhoto_img();
    }
}