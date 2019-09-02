package com.bankdemo.run;

import com.bankdemo.properties.CommonSentence;
import com.bankdemo.services.LoginService;

import java.util.Scanner;

public class Operation {
    public void run() throws Exception{
        Scanner sc = new Scanner(System.in);
        LoginService.login(sc);
        boolean flag = true;
        while(flag){
            CommonSentence.showMenu();
            int picked = sc.nextInt();
            flag = ChooseService.chooseServices(picked, sc);
        }
        sc.close();
    }
}
