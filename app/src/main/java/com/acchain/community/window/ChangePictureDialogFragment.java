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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 2018/1/15
 * function : 设置头像弹出框
 *
 * @author ACChain
 */

public class ChangePictureDialogFragment extends DialogFragment {


    @BindView(R.id.tv_take_photo)
    TextView mTvTakePhoto;
    @BindView(R.id.tv_from_album)
    TextView mTvFromAlbum;
    @BindView(R.id.tv_restore_default)
    TextView mTvRestoreDefault;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;

    private OnChangeHeadListener listener;
    private View rootView;
    private Unbinder unbinder;


    /**
     * 功能显示/隐藏
     */
    private boolean takePhoto;
    private boolean fromAlbum;
    private boolean restoreDefault;
    private boolean cancel;

    /**
     * 设置对一个的4个功能菜单是否显示
     * <p>
     * true表示显示
     */
    public ChangePictureDialogFragment setShowFunction(boolean takePhoto, boolean fromAlbum, boolean restoreDefault, boolean cancel) {
        this.takePhoto = takePhoto;
        this.fromAlbum = fromAlbum;
        this.restoreDefault = restoreDefault;
        this.cancel = cancel;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //添加view
        rootView = inflater.inflate(R.layout.dialog_fragment_change_head, null, false);
        unbinder = ButterKnife.bind(this, rootView);

        mTvTakePhoto.setVisibility(takePhoto ? View.VISIBLE : View.GONE);
        mTvFromAlbum.setVisibility(fromAlbum ? View.VISIBLE : View.GONE);
        mTvRestoreDefault.setVisibility(restoreDefault ? View.VISIBLE : View.GONE);
        mTvCancel.setVisibility(cancel ? View.VISIBLE : View.GONE);

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
        if (getActivity() instanceof OnChangeHeadListener) {
            listener = (OnChangeHeadListener) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public ChangePictureDialogFragment setOnChangeHeadListener(OnChangeHeadListener listener) {
        this.listener = listener;
        return this;
    }

    @OnClick({R.id.tv_take_photo, R.id.tv_from_album, R.id.tv_restore_default, R.id.tv_cancel})
    public void onViewClicked(View view) {
        if (listener == null) {
            return;
        }
        boolean consume = false;
        switch (view.getId()) {
            case R.id.tv_take_photo:
                consume = listener.onClickTakePhone(getDialog());
                break;
            case R.id.tv_from_album:
                consume = listener.onClickFromAlbum(getDialog());
                break;
            case R.id.tv_restore_default:
                consume = listener.onClickRestoreDefault(getDialog());
                break;
            case R.id.tv_cancel:
                consume = listener.onClickCancel(getDialog());
                break;
        }
        if (!consume) {
            getDialog().dismiss();
        }
    }

    /**
     * 当点击弹出框中按钮时进行回调
     */
    public interface OnChangeHeadListener {
        /**
         * 返回true表示不需要默认操作
         */
        boolean onClickTakePhone(Dialog dialog);

        boolean onClickFromAlbum(Dialog dialog);

        boolean onClickRestoreDefault(Dialog dialog);

        boolean onClickCancel(Dialog dialog);
    }
}
