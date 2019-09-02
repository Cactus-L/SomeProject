package com.shopcar.bean;

import java.util.HashMap;
import java.util.Map;

public class ParamList{
    //用以生成产品参数列表中的参数值列表
    private Map<String, Double> paramList = new HashMap<String, Double>();
    public ParamList(Param...paramValues){
        for(Param paramValue : paramValues){
            this.paramList.put(paramValue.getParamName(), paramValue.getPrice());
        }
    }

    public Map<String, Double> getParamList() {
        return paramList;
    }

    public void setParamList(Map<String, Double> paramList) {
        this.paramList = paramList;
    }
}
