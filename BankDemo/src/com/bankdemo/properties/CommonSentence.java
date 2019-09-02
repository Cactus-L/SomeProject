package com.bankdemo.properties;
import lombok.*;

import java.util.Properties;

@Data
@ToString
public class CommonSentence {
    public static Properties properties = new Properties();

    static {
        try{
            properties.load(CommonSentence.class.getResourceAsStream("common-sentence.properties"));
        }catch (Exception e) {
            System.err.println("common-sentence load error");
            e.printStackTrace();
        }
    }

    public static final String MENU = properties.getProperty("menu");


    public static void showMenu() {
        System.out.println(MENU);
    }
}
