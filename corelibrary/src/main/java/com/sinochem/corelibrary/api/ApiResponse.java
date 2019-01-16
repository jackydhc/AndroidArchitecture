package com.sinochem.corelibrary.api;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public class ApiResponse<T> {
    public int code;
    public T data;
    public String msg;

    /*
        { "code":0 }
     */
    public static <T> ApiResponse<T> empty() {
        return new ApiResponse<>();
    }
}
