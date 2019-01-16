package com.sinochem.corelibrary.utils.rx;

/**
 * @author jackydu
 * @date 2019/1/16
 */
public class NullResponceExe extends RuntimeException {

    public NullResponceExe(){
        super("response is null");
    }
    public NullResponceExe(String msg){
        super(msg);
    }


}
