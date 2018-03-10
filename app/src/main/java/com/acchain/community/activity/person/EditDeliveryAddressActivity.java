package com.acchain.community.activity.person;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.DeliveryAddressBean;
import com.acchain.community.contract.EditDeliveryAddressContract;
import com.acchain.community.presenter.EditDeliveryAddressPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.RegexConst;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.LocationPickerViewDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.KeyboardUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.MODEL_DELIVERY_ADDRESS;
import static com.acchain.community.util.Const.REQUEST_CODE_ONE;

/**
 * function---- EditDeliveryAddressActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 11:23:45 (+0000).
 */
@Route(path = ARouterConst.Activity_EditDeliveryAddressActivity)
public class EditDeliveryAddressActivity extends BaseActivity<EditDeliveryAddressPresenter> implements EditDeliveryAddressContract.View, LineMenuView.LineMenuListener, LocationPickerViewDialog.onLocationPickerViewListener {
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.lmv_location)
    LineMenuView mLmvLocation;
    @BindView(R.id.lmv_default_status)
    LineMenuView mLmvDefaultStatus;

    /**
     * 地址id,默认为负值,如果有传值,则表示为修改功能,如果未传值(默认为-1),则表示未新增收货地址
     * 对象
     */
    @Autowired(name = MODEL_DELIVERY_ADDRESS, required = false)
    DeliveryAddressBean addressModel;


    /**
     * 收货地址参数
     */
    private String name;
    private String phone;
    private String address;
    private String province;
    private String city;
    private String area;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_delivery_address;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化数据
        if (addressModel != null) {
            mEtName.setText(addressModel.getName());
            mEtPhone.setText(addressModel.getMobile());
            mEtAddress.setText(addressModel.getAddress());
            this.province = addressModel.getProvince();
            this.city = addressModel.getCity();
            this.area = addressModel.getArea();
            mLmvLocation.setBrief(province + " " + city + " " + area);
            mLmvDefaultStatus.setTransition(addressModel.getDefaultStatus() == 0);
        }
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete_text, menu);
        return addressModel != null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPresenter.deleteAddress(DefaultPreferenceUtil.getInstance().getToken(), addressModel.getId());
        return true;
    }

    @OnClick({R.id.fl_select_contact, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_select_contact://选择联系人
                Intent intent = new Intent();
                intent.setAction("android.intent.action.PICK");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setType("vnd.android.cursor.dir/phone_v2");
                startActivityForResult(intent, REQUEST_CODE_ONE);
                break;
            case R.id.bt_save:// 保存:调用修改或者是新增接口
                if (!verity_info()) {
                    return;
                }

                if (addressModel == null) {
                    mPresenter.addAddress(DefaultPreferenceUtil.getInstance().getToken(),
                            name, phone, province, city, area, address, mLmvDefaultStatus.getTransition() ? 0 : 1);
                } else {
                    mPresenter.updateAddress(DefaultPreferenceUtil.getInstance().getToken(),
                            name, phone, province, city, area, address, addressModel.getId(), mLmvDefaultStatus.getTransition() ? 0 : 1);
                }
                break;
        }
    }

    /**
     * 验证填写内容
     *
     * @return true表示数据格式无误
     */
    private boolean verity_info() {
        name = mEtName.getText().toString();
        phone = mEtPhone.getText().toString().replaceAll("\\s", "");
        address = mEtAddress.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入收货人姓名");
            return false;
        }
        if (!name.matches(RegexConst.REGEX_VERITY_NAME)) {
            showToast("姓名格式不符");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return false;
        }
        if (!phone.matches(RegexConst.REGEX_PHONE)) {
            showToast("手机号格式不符");
            return false;
        }
        if (TextUtils.isEmpty(province) || TextUtils.isEmpty("市") || TextUtils.isEmpty("区")) {
            showToast("请选择地区");
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            showToast("地址不能为空");
            return false;
        }
        return true;
    }


    /**
     * 选择联系人
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK && requestCode == REQUEST_CODE_ONE) {
            Uri uri = data.getData();
            //电话号码和联系人名称
            String phone = null, name = null;
            ContentResolver contentResolver = getContentResolver();
            if (uri == null) {
                showToast("操作已取消");
                return;
            }
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    showToast("联系人已选择");
                }
                cursor.close();
            }

            if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(name)) {
                //联系人中可能存在-号
                phone = phone.replace("-", "");

                mEtName.setText(name);
                mEtPhone.setText(phone);
            }
        }
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        if (v == mLmvLocation) {
            //关闭输入法弹出框
            KeyboardUtils.hideSoftInput(this);

            //如果还未加载完成,则提示需要等待
            if (!LocationPickerViewDialog.getInstance(this).setOnPickerViewListener(this).show()) {
                showToast("正在加载数据,请稍后进行操作");
            }
        }

        //设置/取消 默认状态
        if (v == mLmvDefaultStatus) {
            mLmvDefaultStatus.setTransition(!mLmvDefaultStatus.getTransition());
        }
    }

    @Override
    public void addAddressFinish() {
        showToast("地址已保存");
        finish();
    }

    @Override
    public void updateAddressFinish() {
        super.updateAddressFinish();
        addAddressFinish();
    }

    @Override
    public void deleteAddressFinish() {
        super.deleteAddressFinish();
        showToast("地址已删除");
        finish();
    }

    @Override
    public void onLocationPickerDismiss() {

    }

    @Override
    public void onLocationPickerFinish(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = area;

        mLmvLocation.setBrief(province + " " + city + " " + area);
    }
}