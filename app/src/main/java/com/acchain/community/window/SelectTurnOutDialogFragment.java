package com.acchain.community.window;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.blankj.utilcode.util.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 2018/1/9
 * function : 转出功能;弹出框;选择转出方式
 *
 * @author ACChain
 */

public class SelectTurnOutDialogFragment extends DialogFragment {

    @BindView(R.id.tv_friends)
    TextView mTvFriends;
    @BindView(R.id.tv_platform)
    TextView mTvPlatform;
    Unbinder unbinder;
    @BindView(R.id.iv_close)
    ImageView mIvClose;

    private OnSelectTurnOutListener listener;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //添加view
        rootView = inflater.inflate(R.layout.dialog_fragment_select_turn_out, null, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.ActivityBottomView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setAttributes(attributes);
            window.setGravity(Gravity.CENTER);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
            //因为宽度不是MATCH_PARENT,所以即便高度设置未MATCH_PARENT也无法全屏显示
            window.setLayout((int) (ScreenUtils.getScreenWidth() * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(true);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //如果activity实现了对应接口,则默认添加上点击事件
        if (getActivity() instanceof OnSelectTurnOutListener) {
            listener = (OnSelectTurnOutListener) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_friends, R.id.tv_platform, R.id.iv_close})
    public void onViewClicked(View view) {
        if (listener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_friends:
                listener.onSelectFriends();
                break;
            case R.id.tv_platform:
                listener.onSelectPlatform();
                break;
            case R.id.iv_close:
                break;
        }
        dismiss();
    }

    public SelectTurnOutDialogFragment setOnSelectTurnOutListener(OnSelectTurnOutListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnSelectTurnOutListener {
        void onSelectFriends();

        void onSelectPlatform();
    }
}
