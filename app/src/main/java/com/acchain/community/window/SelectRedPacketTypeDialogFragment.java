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
import android.widget.TextView;

import com.acchain.community.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 2018/1/15
 * function : 设置头像弹出框
 *
 * @author ACChain
 */

public class SelectRedPacketTypeDialogFragment extends DialogFragment {

    private OnItemClickListener listener;
    private View rootView;
    private Unbinder unbinder;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //添加view
        rootView = inflater.inflate(R.layout.dialog_fragment_change_red_packet_type, null, false);
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
            window.setGravity(Gravity.BOTTOM);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setWindowAnimations(R.style.ActivityBottomViewAnimation);
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //如果activity实现了对应接口,则默认添加上点击事件
        if (getActivity() instanceof OnItemClickListener) {
            listener = (OnItemClickListener) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({ R.id.tv_recever, R.id.tv_send, R.id.tv_cancel})
    public void onViewClicked(View view) {
        if (listener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_recever:
                listener.onItemClickListener(this,((TextView)view).getText().toString(),0);
                break;
            case R.id.tv_send:
                listener.onItemClickListener(this,((TextView)view).getText().toString(),1);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    /**
     * 当点击弹出框中按钮时进行回调
     */
    public interface OnItemClickListener {
        void onItemClickListener(DialogFragment dialog, String clickItem,int index);
    }
}
