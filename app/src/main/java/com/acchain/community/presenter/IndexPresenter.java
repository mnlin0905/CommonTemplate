package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.IndexDataBean;
import com.acchain.community.contract.IndexContract;
import com.acchain.community.fragment.IndexFragment;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- IndexPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:48:47 (+0000).
 */
public class IndexPresenter extends BasePresenter<IndexFragment> implements IndexContract.Presenter {
    @Inject
    public IndexPresenter() {
    }

    @Override
    public void getIndexData() {
        requestHttp(httpInterface.getIndexData("mall_app_index"), new HttpListener<BaseHttpBean<IndexDataBean>>() {
            @Override
            public void onSuccess(BaseHttpBean<IndexDataBean> response) {
                    mView.onIndexDataFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onIndexDataFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onIndexDataFinish(null);
            }
        });
    }

}