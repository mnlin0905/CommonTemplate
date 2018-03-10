package com.acchain.community.activity.friend;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.acchain.community.R;
import com.acchain.community.adapter.PhoneContactAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.PhoneContact;
import com.acchain.community.bean.PhoneContactResult;
import com.acchain.community.contract.AddPhoneContactContract;
import com.acchain.community.presenter.AddPhoneContactPresenter;
import com.acchain.community.util.CharacterParser;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 添加手机联系人
 * function---- AddPhoneContactActivity     item: item_phone_contact
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 12:15:28 (+0000).
 */
@RuntimePermissions
@Route(path = ARouterConst.Activity_AddPhoneContactActivity)
public class AddPhoneContactActivity extends BaseActivity<AddPhoneContactPresenter> implements AddPhoneContactContract.View {

    List<PhoneContact> phoneContactList;
    @BindView(R.id.listview)
    ListView listview;
    CharacterParser mCharacterParser;
    private PhoneContactAdapter adapter;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_phone_contact;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //mTvSearch.post(() -> mTvSearch.setBackground(new RoundRectShapeDrawable(mTvSearch.getWidth(), mTvSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
        mCharacterParser = CharacterParser.getInstance();
        AddPhoneContactActivityPermissionsDispatcher.readContactWithPermissionCheck(this);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    public void readContact() {
        Cursor cursor = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        int contactIdIndex = 0;
        int nameIndex = 0;

        if (cursor.getCount() > 0) {
            contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        }
        PhoneContact phoneContact = null;
        List<String> phoneNumberList = null;
        phoneContactList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(contactIdIndex);
            String name = cursor.getString(nameIndex);
            Logger.d("联系人id:" + contactId);
            Logger.d("联系人名称:" + name);
            phoneContact = new PhoneContact();
            phoneContact.setName(name);
            String spelling = mCharacterParser.getSpelling(name);
            String sortString;
            if (!StringUtils.isEmpty(spelling)) {
                sortString = spelling.substring(0, 1).toUpperCase();
            } else {
                sortString = "#";
            }
            phoneContact.setLetters(sortString);
            /*
             * 查找该联系人的phone信息
             */
            Cursor phones = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                    null, null);
            int phoneIndex = 0;
            if (phones.getCount() > 0) {
                phoneIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            }
            phoneNumberList = new ArrayList<>();
            while (phones.moveToNext()) {
                String phoneNumber = phones.getString(phoneIndex);
                Logger.d("联系人电话号码:" + phoneNumber);
                phoneNumberList.add(phoneNumber.replace(" ", "").replace("-", ""));
            }
            phoneContact.setPhoneNumber(phoneNumberList);
            phoneContactList.add(phoneContact);
        }
        for (PhoneContact contact : phoneContactList) {
            System.out.println(contact);
        }
        if (!phoneContactList.isEmpty()) {
            mPresenter.getRegisterContact();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AddPhoneContactActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.READ_CONTACTS)
    public void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("温馨提示")
                .setMessage("您需要提供读取联系人的权限才能匹配手机联系人")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                        finish();
                    }
                })
                .create()
                .show();
    }

    @OnPermissionDenied(Manifest.permission.READ_CONTACTS)
    public void onPermissionDenied() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("温馨提示")
                .setMessage("没有权限将无法获取联系人")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent appDetailSettingIntent = getAppDetailSettingIntent();
                        startActivity(appDetailSettingIntent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    @OnNeverAskAgain(Manifest.permission.READ_CONTACTS)
    public void onNeverAskAgain() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("温馨提示")
                .setMessage("没有权限将无法获取联系人")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent appDetailSettingIntent = getAppDetailSettingIntent();
                        startActivity(appDetailSettingIntent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();
    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        return localIntent;

        /*Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            startActivity(intent);
        }*/
    }

    @Override
    public String phoneNumbers() {
        StringBuilder sb = new StringBuilder();

        for (PhoneContact phoneContact : phoneContactList) {
            List<String> phoneNumber = phoneContact.getPhoneNumber();
            for (String s : phoneNumber) {
                sb.append(s + ",");
            }
        }
        String phoneNumber = sb.substring(0, sb.length() - 1);
        return phoneNumber;
    }

    @Override
    public void showContact(List<PhoneContactResult> phoneContactResultList) {
        Iterator<PhoneContactResult> it = phoneContactResultList.iterator();
        while (it.hasNext()) {
            PhoneContactResult next = it.next();
            if (next.getMobile().equals(BasePresenter.singleAccountBean.getMobile())) {
                it.remove();//把自己删除
                break;
            }
        }
        for (PhoneContactResult phoneContactResult : phoneContactResultList) {
            out:
            for (PhoneContact phoneContact : phoneContactList) {
                List<String> phoneNumber = phoneContact.getPhoneNumber();
                for (String phone : phoneNumber) {
                    if (phoneContactResult.getMobile().equals(phone)) {
                        phoneContactResult.setPhoneName(phoneContact.getName());
                        break out;
                    }
                }
            }
        }
        adapter = new PhoneContactAdapter(this,0,phoneContactResultList);
        listview.setAdapter(adapter);
    }
}