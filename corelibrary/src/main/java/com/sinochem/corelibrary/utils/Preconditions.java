package com.sinochem.corelibrary.utils;

import android.support.annotation.NonNull;

/**
 * @author jackydu
 * @date 2019/1/23
 */
public final class Preconditions {
    public static <T> T checkNotNull(T refrence,@NonNull Object expValue){
        if (refrence == null) throw new NullPointerException(String.valueOf(expValue));
        return refrence;
    }
}
