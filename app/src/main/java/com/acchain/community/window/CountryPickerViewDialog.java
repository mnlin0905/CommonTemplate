package com.acchain.community.window;

import android.content.res.AssetManager;

import com.acchain.community.R;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseApplication;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.lib.WheelView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2018/1/17
 * function : 身份证件选择
 *
 * @author ACChain
 */

public class CountryPickerViewDialog {
    private static CountryPickerViewDialog singleInstance;
    private static WeakReference<BaseActivity> reference;
    onCountryPickerViewListener listener;
    private OptionsPickerView pvLocation;
    private List<Country> countries;

    /**
     * 是否出现了异常
     */
    private boolean error;
    private boolean finished;

    private CountryPickerViewDialog() {
        initDataInThread();
    }

    /**
     * 以activity为key,保存dialog数据,保证每个activity弹出同一个dialog
     */
    public synchronized static CountryPickerViewDialog getInstance(BaseActivity activity) {
        if (activity != null) {
            reference = new WeakReference<BaseActivity>(activity);
        }
        if (singleInstance == null) {
            singleInstance = new CountryPickerViewDialog();
        }
        return singleInstance;
    }

    private void initDataInThread() {
        //线程中加载国家和地区列表
        Observable
                .create((ObservableOnSubscribe<JSONArray>) e -> {
                    StringBuilder builder = new StringBuilder();
                    AssetManager assetManager = BaseApplication.app.getAssets();
                    BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open("country.json")));
                    String line;
                    while ((line = bf.readLine()) != null) {
                        builder.append(line);
                    }
                    JSONArray array = new JSONArray(builder.toString());

                    e.onNext(array);
                    e.onComplete();
                })
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.single())
                .subscribe(jsonArray -> {
                    int length = jsonArray.length();
                    String item;
                    countries = new LinkedList<>();
                    for (int i = 0; i < length; i++) {
                        item = jsonArray.getString(i);
                        String[] split = item.split("-");
                        countries.add(new Country(split[0], split[1], split[2]));
                    }
                }, throwable -> error = true, () -> finished = true);
    }

    /**
     * 生成pickerview
     */
    private void createPickerView(BaseActivity activity) {
        pvLocation = new OptionsPickerView.Builder(activity, (options1, options2, options3, v1) -> {
            if (listener != null) {
                listener.onCountryPickerFinish(countries.get(options1));
            }
        }).setLayoutRes(R.layout.layout_pickerview_options_bunkers, view -> {
            view.findViewById(R.id.tv_confirm).setOnClickListener(v1 -> {
                pvLocation.returnData();
                pvLocation.dismiss();
            });
            view.findViewById(R.id.tv_cancel).setOnClickListener(v1 -> {
                pvLocation.dismiss();
            });
        }).setContentTextSize((int) (activity.getResources().getDimensionPixelSize(R.dimen.text_size_17sp) / activity.getResources().getDisplayMetrics().density))
                .setTextColorCenter(activity.dispatchGetColor(R.color.black_text_333_1))
                .setTextColorOut(activity.dispatchGetColor(R.color.black_text_999_1))
                .setLineSpacingMultiplier(3F)
                .isDialog(false)
                .isCenterLabel(false)
                .setOutSideCancelable(false)
                .setDividerType(WheelView.DividerType.FILL)
                .setDividerColor(activity.dispatchGetColor(R.color.black_text_999_1))
                .build();

        pvLocation.setPicker(countries);

        pvLocation.setOnDismissListener(o -> {
            if (listener != null) {
                listener.onCountryPickerDismiss();
            }
        });
    }

    /**
     * 直接显示dialog
     *
     * @return true表示显示成功, false表示尚未初始化完成
     */
    public boolean show() {
        if (reference.get() != null) {
            if (error) {
                reference.get().showToast("无法初始化国家列表,即将刷新数据");
                initDataInThread();
                return false;
            }
            if (!finished) {
                reference.get().showToast("数据正在加载,请稍后操作");
                return false;
            }
            createPickerView(reference.get());
            pvLocation.show();
            return true;
        }
        return false;
    }

    /**
     * 设置监听器
     */
    public CountryPickerViewDialog setOnPickerViewListener(onCountryPickerViewListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 时间选择器监听事件
     */
    public interface onCountryPickerViewListener {
        /**
         * 当picker消失时进行回调
         */
        void onCountryPickerDismiss();

        /**
         * 0 表示身份证
         * 1 表示军官证
         * 2 表示护照
         */
        void onCountryPickerFinish(Country country);
    }

    /**
     * 国家信息
     * <p>
     * 英文名
     * 中文名
     * 国家/地区代码
     */
    public static class Country {
        private String englishName;
        private String chineseName;
        private String countryCode;

        public Country(String englishName, String chineseName, String countryCode) {
            this.chineseName = chineseName;
            this.englishName = englishName;
            this.countryCode = countryCode;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getChineseName() {
            return chineseName;
        }

        public void setChineseName(String chineseName) {
            this.chineseName = chineseName;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        @Override
        public String toString() {
            return chineseName;
        }
    }
}
