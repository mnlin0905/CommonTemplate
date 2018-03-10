package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.RedPacketDetailContract;
import com.acchain.community.presenter.RedPacketDetailPresenter;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_AMOUNT;
import static com.acchain.community.util.Const.KEY_CURRENCY_SHORT_NAME;
import static com.acchain.community.util.Const.KEY_HEAD_IMG;
import static com.acchain.community.util.Const.KEY_NIKE_NAME;
import static com.acchain.community.util.Const.KEY_REMARK;

/**
 * function---- RedPacketDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 08:58:11 (+0000).
 */
@Route(path = ARouterConst.Activity_RedPacketDetailActivity)
public class RedPacketDetailActivity extends BaseActivity<RedPacketDetailPresenter> implements RedPacketDetailContract.View {
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.red_title)
    TextView redTitle;
    @BindView(R.id.number)
    TextView number;
    @Autowired(name = KEY_HEAD_IMG)
    public String headimg;
    @Autowired(name = KEY_NIKE_NAME,required = true)
    public String nikeName;
    @Autowired(name = KEY_REMARK, required = true)
    public String remark;
    @Autowired(name = KEY_AMOUNT, required = true)
    public String amount;
    @Autowired(name = KEY_CURRENCY_SHORT_NAME, required = true)
    public String currency;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_red_packet_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ViewGroup contentFrameLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        contentFrameLayout.getChildAt(0).setFitsSystemWindows(false);
        Glide.with(this)
                .load(headimg)
                .apply(new RequestOptions().centerCrop().error(R.drawable.default_head_img).placeholder(R.drawable.default_head_img))
                .into(headImg);
        name.setText(nikeName+"的红包");
        number.setText(amount+" "+currency);
        redTitle.setText(remark);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide mSlide = new Slide();
            mSlide.setInterpolator(new AccelerateInterpolator());
            mSlide.setSlideEdge(Gravity.TOP);
            getWindow().setEnterTransition(mSlide);
            getWindow().setExitTransition(mSlide);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAfterTransition();
                    } else {
                        finish();
                    }
                }
            });
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_red_packet_record,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ARouter.getInstance().build(ARouterConst.Activity_RedPacketRecordActivity).navigation();
        return true;
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick(R.id.remark)
    public void onViewClicked() {
    }
}