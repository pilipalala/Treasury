package com.umeng.soexample.object;

/**
 * Created by Administrator on 2017/1/12.
 */
public class Entity {
    private String time;
    private boolean isSelect;

    public Entity(String time, boolean isSelect) {
        this.time = time;
        this.isSelect = isSelect;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
