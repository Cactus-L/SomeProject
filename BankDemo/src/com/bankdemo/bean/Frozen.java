package com.bankdemo.bean;

import com.bankdemo.io.WriteXls;

import java.util.ArrayList;
import java.util.List;

public class Frozen{
    private static List<String > frozenList = new ArrayList<String>();
    public static boolean isFrozen(String id){
        if(frozenList.contains(id)){
            return true;
        }else{
            return false;
        }
    }
    public static void add2FrozenList(String id) throws Exception{
        frozenList.add(id);
        WriteXls.writeFrozenList();
    }

    public static List<String> getFrozenList() {
        return frozenList;
    }

    public static void setFrozenList(List<String> frozenList) {
        Frozen.frozenList = frozenList;
    }
}
