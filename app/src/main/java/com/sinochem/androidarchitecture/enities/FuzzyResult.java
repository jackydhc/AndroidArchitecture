package com.sinochem.androidarchitecture.enities;

import java.util.List;

/**
 * @author jackydu
 * @date 2019/3/7
 */
public class FuzzyResult {
    public String resCode;
    public String resmsg;
    public Data data;

    public class Data {
       public List<FuzzyData> items;
    }
}
