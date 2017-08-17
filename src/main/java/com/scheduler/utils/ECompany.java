package com.scheduler.utils;

/**
 * Created by WYKIM on 2017-07-05.
 */
public enum ECompany {
    MEGA
    ,KINX;

    @Override
    public String toString() {
        switch(this) {
            case MEGA: return "MEGA";
            case KINX: return "KINX";
            default: throw new IllegalArgumentException();
        }
    }
}
