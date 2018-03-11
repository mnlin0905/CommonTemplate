package com.common.template.window;

import android.content.res.AssetManager;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.common.template.R;
import com.common.template.base.BaseActivity;
import com.common.template.base.BaseApplication;
import com.common.template.bean.CityJsonBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2018/1/17
 * function : 选择地址
 *
 * @author ACChain
 */

public class LocationPickerViewDialog {
    private static LocationPickerViewDialog singleInstance;
    private static WeakReference<BaseActivity> reference;
    onLocationPickerViewListener listener;
    private OptionsPickerView pvLocation;
    private ArrayList<CityJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private String province;
    private String city;
    private String area;
    private volatile boolean finished;

    private LocationPickerViewDialog() {
        //在线程中初始化城市列表信息
        initDataInThread();
    }

    /**
     * 以activity为key,保存dialog数据,保证每个activity弹出同一个dialog
     */
    public synchronized static LocationPickerViewDialog getInstance(BaseActivity activity) {
        if (activity != null) {
            reference = new WeakReference<BaseActivity>(activity);
        }
        if (singleInstance == null) {
            singleInstance = new LocationPickerViewDialog();
        }
        return singleInstance;
    }

    /**
     * 线程中初始化数据
     */
    private void initDataInThread() {
        Observable.create((ObservableOnSubscribe<String>) subscriber -> {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                AssetManager assetManager = BaseApplication.app.getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        assetManager.open("province.json")));
                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException err) {
                err.printStackTrace();
                subscriber.onError(err);
            }
            subscriber.onNext(stringBuilder.toString());
            subscriber.onComplete();
        }).map(s -> {
            try {
                return new JSONArray(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }).map(jsonArray -> {
            ArrayList<CityJsonBean> detail = new ArrayList<>();
            try {
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    CityJsonBean entity = gson.fromJson(jsonArray.optJSONObject(i).toString(), CityJsonBean.class);
                    detail.add(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return detail;
        }).subscribeOn(Schedulers.single())
                .observeOn(Schedulers.single())
                .subscribe(this::initCityInfoFinish);
    }

    /**
     * @param data 初始化列表组
     */
    private void initCityInfoFinish(ArrayList<CityJsonBean> data) {
        options1Items = data;
        for (int i = 0; i < data.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_areaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < data.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = data.get(i).getCityList().get(c).getName();
                cityList.add(CityName);//添加城市

                ArrayList<String> city_areaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (data.get(i).getCityList().get(c).getArea() == null
                        || data.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_areaList.add("");
                } else {
                    //该城市对应地区所有数据
                    //添加该城市所有地区数据
                    city_areaList.addAll(data.get(i).getCityList().get(c).getArea());
                }
                province_areaList.add(city_areaList);//添加该省所有地区数据
            }

            options2Items.add(cityList);

            options3Items.add(province_areaList);
        }

        finished = true;
    }

    /**
     * @return 生成pickerview
     */
    private void createPickerView(BaseActivity activity) {
        createPickerView(activity, new boolean[]{true, true, true});
    }

    /**
     * @param activity  活动对象
     * @param showItems item:0,1,2分别对应省市区,出入false表示不显示
     */
    private void createPickerView(BaseActivity activity, boolean[] showItems) {
        if (showItems == null) {
            createPickerView(activity);
            return;
        }
        if (showItems.length != 3) {
            showItems = new boolean[]{showItems.length <= 0 || showItems[0], showItems.length > 1 && showItems[1], showItems.length > 2 && showItems[2]};
        }

        pvLocation = new OptionsPickerView
                .Builder(activity, (options1, options2, options3, v1) -> {
                    province = options1Items.get(options1).getPickerViewText();
                    city = options2Items.get(options1).get(options2);
                    area = options3Items.get(options1).get(options2).get(options3);
                    if (listener != null) {
                        listener.onLocationPickerFinish(province, city, area);
                    }
                })
                .setLayoutRes(R.layout.layout_pickerview_options_bunkers, view -> {
                    view.findViewById(R.id.tv_confirm).setOnClickListener(v1 -> {
                        pvLocation.returnData();
                        pvLocation.dismiss();
                    });
                    view.findViewById(R.id.tv_cancel).setOnClickListener(v1 -> {
                        pvLocation.dismiss();
                    });
                })
                .setContentTextSize((int) (activity.getResources().getDimensionPixelSize(R.dimen.text_size_17sp) / activity.getResources().getDisplayMetrics().density))
                .setTextColorCenter(activity.dispatchGetColor(R.color.black_text_333_1))
                .setTextColorOut(activity.dispatchGetColor(R.color.black_text_999_1))
                .setLineSpacingMultiplier(3F)
                .isDialog(false)
                .isCenterLabel(false)
                .setOutSideCancelable(false)
                .setDividerType(WheelView.DividerType.FILL)
                .setDividerColor(activity.dispatchGetColor(R.color.black_text_999_1))
                .build();

        pvLocation.setPicker(showItems[0] ? options1Items : null, showItems[1] ? options2Items : null, showItems[2] ? options3Items : null);//三级选择器

        pvLocation.setOnDismissListener(o -> {
            if (listener != null) {
                listener.onLocationPickerDismiss();
                listener = null;
            }
        });
    }

    /**
     * 直接显示dialog
     *
     * @return true表示显示成功, false表示尚未初始化完成
     */
    public boolean show() {
        if (finished && reference.get() != null) {
            createPickerView(reference.get());
            pvLocation.show();
            return true;
        }
        return false;
    }

    /**
     * 直接显示dialog
     *
     * @return true表示显示成功, false表示尚未初始化完成
     */
    public boolean show(boolean... showItems) {
        if (finished && reference.get() != null) {
            createPickerView(reference.get(), showItems);
            pvLocation.show();
            return true;
        }
        return false;
    }


    /**
     * 设置监听器
     */
    public LocationPickerViewDialog setOnPickerViewListener(onLocationPickerViewListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 时间选择器监听事件
     */
    public interface onLocationPickerViewListener {
        /**
         * 当picker消失时进行回调
         */
        void onLocationPickerDismiss();

        /**
         * @param province 省
         * @param city     市
         * @param country  区
         */
        void onLocationPickerFinish(String province, String city, String country);
    }
}
