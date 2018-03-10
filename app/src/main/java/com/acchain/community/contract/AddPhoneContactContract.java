package com.acchain.community.contract;

import com.acchain.community.bean.PhoneContactResult;

import java.util.List;

/**
 * function---- AddPhoneContactContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 12:15:28 (+0000).
 */
public interface AddPhoneContactContract {
    interface Presenter{
        void getRegisterContact();
    }

    interface View{
        String phoneNumbers();

        void showContact(List<PhoneContactResult> phoneContactResultList);
    }
}