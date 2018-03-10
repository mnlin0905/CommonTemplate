package com.acchain.community.manager;

import com.acchain.community.base.BaseApplication;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.bean.Friend;
import com.acchain.community.events.LoginSuccess;
import com.acchain.community.events.RefreshFriendList;
import com.acchain.community.retrofit.HttpInterface;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.CharacterParser;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.blankj.utilcode.util.StringUtils;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;
import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rong.imkit.RongIM;

/**
 * Created by rsp on 2018/1/15.
 */

public class FriendManager {
    private static FriendManager friendManager;
    CharacterParser mCharacterParser;
    private Disposable subscribe;
    private Disposable subscribe2;
    private final String memberId;

    private FriendManager() {
        mCharacterParser = CharacterParser.getInstance();
        memberId = DefaultPreferenceUtil.getInstance().getMemberId();
        if (StringUtils.isEmpty(memberId)) {
            Logger.e("memberId为空 请登录后再试");
            return;
        }
        FindMultiExecutor allFriendSync = DataSupport.where("member_id = ?",memberId).findAsync(Friend.class);
        if (allFriendSync != null) {
            allFriendSync.listen(new FindMultiCallback() {
                @Override
                public <T> void onFinish(List<T> t) {
                    if (t == null || t.isEmpty()) {
                        //从网络获取好友列表
                        getAllFriendsForNetWork();
                    }
                }
            });
        }
        RxBus.getInstance().toObservable(RefreshFriendList.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RefreshFriendList>() {
            @Override
            public void accept(RefreshFriendList refreshFriendList) throws Exception {
                getAllFriendsForNetWork();
            }
        });
    }

    private void getAllFriendsForNetWork() {
        HttpInterface httpInterface = BaseApplication.getApplication().getHttpInterface();
        Observable<BaseHttpBean<List<Friend>>> newFriend = httpInterface.friendList(DefaultPreferenceUtil.getInstance().getToken(), null);
        subscribe = newFriend.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(friendBaseHttpBean -> {
                    if (friendBaseHttpBean.result == 1007) {//登录超时
                        subscribe2 = RxBus.getInstance().toObservable(LoginSuccess.class).observeOn(AndroidSchedulers.mainThread())
                                .subscribe(loginSuccess -> {
                                    getAllFriendsForNetWork();
                                    subscribe2.dispose();
                                });
                    }
                    List<Friend> dataSet = friendBaseHttpBean.dataSet;
                    String pinyin;
                    if (dataSet == null || dataSet.isEmpty()) {
                        subscribe.dispose();
                        return;
                    }
                    for (Friend friend : dataSet) {
                        pinyin = mCharacterParser.getSpelling(friend.getName());
                        String sortString;
                        if (!StringUtils.isEmpty(pinyin)) {
                            sortString = pinyin.substring(0, 1).toUpperCase();
                        } else {
                            sortString = "#";
                        }

                        // 正则表达式，判断首字母是否是英文字母
                        if (sortString.matches("[A-Z]")) {
                            friend.setLetters(sortString);
                        } else {
                            friend.setLetters("#");
                        }
                        RongIM.getInstance().refreshUserInfoCache(friend.getUserInfo());
                    }
                    DataSupport.saveAll(dataSet);

                    subscribe.dispose();
                }, throwable -> {
                    throwable.printStackTrace();
                    subscribe.dispose();
                });
    }

    public static synchronized FriendManager getInstance() {
        if (friendManager == null) {
            friendManager = new FriendManager();
            return friendManager;
        } else {
            return friendManager;
        }
    }

    public static Friend findFriendById(String userId) {
        List<Friend> friends = DataSupport.where("userId = ?", userId).find(Friend.class);
        if (friends != null && !friends.isEmpty()) {
            return friends.get(0);

        } else {
            return null;
        }
    }


    /**
     * 异步获取所有好友
     *
     * @return
     */
    public FindMultiExecutor getAllFriendSync() {
        if (StringUtils.isEmpty(memberId)) {
            System.out.println("memberId为空");
            return null;
        }
       return DataSupport.where("member_id = ?",memberId).findAsync(Friend.class);
    }
}
