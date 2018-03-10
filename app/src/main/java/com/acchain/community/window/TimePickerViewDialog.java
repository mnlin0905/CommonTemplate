package com.acchain.community.window;

import android.view.Gravity;
import android.view.View;

import com.acchain.community.R;
import com.acchain.community.base.BaseActivity;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.view.BasePickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.WeakHashMap;

/**
 * Created on 2018/1/13
 * function : 日期选择框,可以获取日期
 *
 * @author ACChain
 */

public class TimePickerViewDialog {
    /**
     * 使用软引用,避免内存泄漏
     */
    static WeakHashMap<BaseActivity, TimePickerViewDialog> map = new WeakHashMap<>();

    /**
     * 弹出日期选择框(框架)
     */
    TimePickerView pvTime;

    onPickerViewListener listener;
    private BaseActivity baseActivity;

    /**
     * 弹出日期选择框(自定义)
     */
    private OptionsPickerView pvOptions;

    private TimePickerViewDialog(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;

        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2017, 0, 1);

        //时间选择器
        pvTime = new TimePickerView
                .Builder(baseActivity, (date, v) -> {
            //选中事件回调
            // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            if (listener != null) {
                listener.onPickerFinish(date, v);
            }
        }).setLayoutRes(R.layout.layout_pickerview_time_bunkers, v -> {
            v.findViewById(R.id.tv_confirm).setOnClickListener(v1 -> {
                pvTime.returnData();
                pvTime.dismiss();
            });
            v.findViewById(R.id.tv_cancel).setOnClickListener(v1 -> {
                pvTime.dismiss();
            });
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, false, false, false, false})
                .setLabel("年", "月", "", "", "", "")
                .setContentSize((int) (baseActivity.getResources().getDimensionPixelSize(R.dimen.text_size_17sp) / baseActivity.getResources().getDisplayMetrics().density))
                .setTextColorCenter(baseActivity.dispatchGetColor(R.color.black_text_333_1))
                .setTextColorOut(baseActivity.dispatchGetColor(R.color.black_text_999_1))
                .setLineSpacingMultiplier(3F)
                .isDialog(false)
                .gravity(Gravity.CENTER)
                .isCenterLabel(false)
                .setOutSideCancelable(false)
                .setDividerType(WheelView.DividerType.FILL)
                .setDividerColor(baseActivity.dispatchGetColor(R.color.black_text_999_1))
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setDecorView(null)
                .build();

        pvTime.setOnDismissListener(o -> {
            if (listener != null) {
                listener.onPickerDismiss();
            }
        });
    }

    /**
     * 以activity为key,保存dialog数据,保证每个activity弹出同一个dialog
     */
    public synchronized static TimePickerViewDialog getInstance(BaseActivity baseActivity) {
        if (map.get(baseActivity) == null) {
            TimePickerViewDialog timePickerViewDialog = new TimePickerViewDialog(baseActivity);
            map.put(baseActivity, timePickerViewDialog);
            return timePickerViewDialog;
        }
        return map.get(baseActivity);
    }

    /**
     * @return 获取实例对象
     */
    public BasePickerView getPickerView() {
        return pvTime;
    }

    /**
     * 直接显示dialog
     */
    private BasePickerView show() {
        if (pvTime != null) {
            pvTime.show();
        }
        return pvTime;
    }

    /**
     * 设置监听器
     */
    public TimePickerViewDialog setOnPickerViewListener(onPickerViewListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 显示自定义的弹出框
     */
    private void showSelectDialog(BaseActivity baseActivity) {
        //条件选择器
        LinkedList<String> array = new LinkedList<>();
        pvOptions = new OptionsPickerView.Builder(baseActivity, (options1, options2, options3, v) -> {
        }).setLayoutRes(R.layout.layout_pickerview_options_bunkers, v -> {
            v.findViewById(R.id.tv_confirm).setOnClickListener(v1 -> {
                pvOptions.returnData();
                pvOptions.dismiss();
            });
            v.findViewById(R.id.tv_cancel).setOnClickListener(v1 -> {
                pvOptions.dismiss();
            });
        })
                .setLabels("年  ", "月", "")
                .setContentTextSize((int) (baseActivity.getResources().getDimensionPixelSize(R.dimen.text_size_17sp) / baseActivity.getResources().getDisplayMetrics().density))
                .setTextColorCenter(baseActivity.dispatchGetColor(R.color.black_text_333_1))
                .setTextColorOut(baseActivity.dispatchGetColor(R.color.black_text_999_1))
                .setLineSpacingMultiplier(3F)
                .setCyclic(false, false, false)
                .isDialog(false)
                .isCenterLabel(false)
                .setSelectOptions(0)
                .setOutSideCancelable(true)
                .setDividerType(WheelView.DividerType.FILL)
                .setDividerColor(baseActivity.dispatchGetColor(R.color.black_text_999_1))
                .build();
        pvOptions.setPicker(array);
        pvOptions.show();
    }

    /**
     * 时间选择器监听事件
     */
    public interface onPickerViewListener {
        /**
         * 当picker消失时进行回调
         */
        void onPickerDismiss();

        /**
         * 当选择了一个时间后进行回调
         *
         * @param date 选择的时间节点
         * @param view show方法调用时,绑定的view
         */
        void onPickerFinish(Date date, View view);
    }
}
