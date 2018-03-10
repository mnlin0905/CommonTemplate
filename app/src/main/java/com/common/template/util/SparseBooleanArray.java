package com.common.template.util;

/**
 * 自定义sparse对象,可实现统计功能
 *
 * Created by admin on 2017/3/31.
 */

public class SparseBooleanArray extends android.util.SparseBooleanArray {
    public SparseBooleanArray(){
        this(10);
    }
    public SparseBooleanArray(int amount){
        super(amount);
        for(int i=0;i<amount;i++){
            put(i,false);
        }
    }
    public int countNumber(boolean value){
        int number=0;
        for(int i=0;i<size();i++){
            if(valueAt(i)==value) {
                number++;
            }
        }
        return number;
    }
    public void initValue(boolean value){
        for(int i=0;i<size();i++){
            put(i,value);
        }
    }
    public void resize(int size){
        if(size()==size) {
            return;
        }
        if(size<size()){
            for(int i=size;i<size();i++){
                delete(i);
            }
        }else{
            for(int i=size();i<size;i++){
                put(i,false);
            }
        }
    }
    public void reverse(int position){
        put(position,!get(position));
    }
    public boolean allIsValue(boolean value){
        return countNumber(value)==size();
    }
}
