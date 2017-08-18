package com.scheduler.utils;

/**
 * Created by WYKIM on 2017-07-05.
 */
public enum ERegion {
    KIC
    ,AIC
    ,EIC
    ,RUC;

    @Override
    public String toString() {
        switch(this) {
            case KIC: return "KIC";
            case AIC: return "AIC";
            case EIC: return "EIC";
            case RUC: return "RUC";
            default: throw new IllegalArgumentException();
        }
    }
}
