package com.acchain.community.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.FriendListAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.Friend;
import com.acchain.community.contract.ContactListContract;
import com.acchain.community.interfaces.OnFriendClickListener;
import com.acchain.community.manager.FriendManager;
import com.acchain.community.presenter.ContactListPresenter;
import com.acchain.community.util.CharacterParser;
import com.acchain.community.util.PinyinComparator;
import com.alibaba.android.arouter.facade.annotation.Route;

import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import io.rong.imkit.mention.SideBar;

/**
 * function---- ContactListFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 02:18:23 (+0000).
 */
@Route(path = ARouterConst.Fragment_ContactListFragment)
public class ContactListFragment extends BaseFragment<ContactListPresenter> implements ContactListContract.View {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.group_dialog)
    TextView groupDialog;
    @BindView(R.id.sidrbar)
    SideBar sidrbar;
    List<Friend> friends;
    private CharacterParser mCharacterParser;
    private PinyinComparator mPinyinComparator;
    private List<Friend> mFilteredFriendList;
    FriendListAdapter mFriendListAdapter;
    public OnFriendClickListener onFriendClickListener;
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FindMultiExecutor allFriendSync = FriendManager.getInstance().getAllFriendSync();
        if (null == allFriendSync) {
            return;
        }
        allFriendSync.listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                friends = (List<Friend>) t;
                Collections.sort(friends, mPinyinComparator);
                mFriendListAdapter.updateListView(friends);
            }
        });
        mFriendListAdapter = new FriendListAdapter(getActivity(),friends);
        listview.setAdapter(mFriendListAdapter);
        mFilteredFriendList = new ArrayList<>();
        mCharacterParser = CharacterParser.getInstance();
        mPinyinComparator = PinyinComparator.getInstance();
        //设置右侧触摸监听
        sidrbar.setTextView(groupDialog);
        sidrbar.setOnTouchingLetterChangedListener(s -> {
            //该字母首次出现的位置
            int position = mFriendListAdapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                listview.setSelection(position);
            }

        });
        listview.setOnItemClickListener((parent, view, position, id) -> {
            if (onFriendClickListener != null) {
                onFriendClickListener.onFriendClick((Friend) listview.getAdapter().getItem(position));
            }
        });
    }

    public void setOnFriendClickListener(OnFriendClickListener onFriendClickListener) {
        this.onFriendClickListener = onFriendClickListener;
    }

    public void setSearchEditText(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr 需要过滤的 String
     */
    public void filterData(String filterStr) {
        filterStr = filterStr.toUpperCase();
        List<Friend> filterDateList = new ArrayList<>();
        try {
            if (TextUtils.isEmpty(filterStr)) {
                filterDateList = friends;
            } else {
                filterDateList.clear();
                for (Friend friendModel : friends) {
                    String name = friendModel.getName();
                    String displayName = friendModel.getActualName();
                    if (!TextUtils.isEmpty(displayName)) {
                        if (name.contains(filterStr) || mCharacterParser.getSpelling(name).startsWith(filterStr) || displayName.contains(filterStr) || mCharacterParser.getSpelling(displayName).startsWith(filterStr)) {
                            filterDateList.add(friendModel);
                        }
                    } else {
                        if (name.contains(filterStr) || mCharacterParser.getSpelling(name).startsWith(filterStr)) {
                            filterDateList.add(friendModel);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, mPinyinComparator);
        mFilteredFriendList = filterDateList;
        mFriendListAdapter.updateListView(filterDateList);
    }
    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }
}