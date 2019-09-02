package com.bankdemo.run;

import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.io.WriteXls;

import java.util.Scanner;

public class ChooseService {
    public static boolean chooseServices(int picked, Scanner sc) throws Exception{
        if(picked == -1){
            RunPart.voucher();
            WriteXls.writeXls();
            System.out.println("已将所有内容写入本地！");;
            return false;
        }else{
            switch (picked){
                case 1:
                    System.out.println("存款业务");
                    System.out.println("---------------------------------------------------------------------");
                    RunPart.depositPart(sc);
                    break;
                case 2:
                    System.out.println("转账业务");
                    System.out.println("---------------------------------------------------------------------");
                    RunPart.transferPart(sc);
                    break;
                case 3:
                    System.out.println("修改个人信息业务");
                    System.out.println("---------------------------------------------------------------------");
                    RunPart.settingPart(sc);
                    break;
                case 4:
                    System.out.println("查看账户信息业务");
                    System.out.println("---------------------------------------------------------------------");
                    RunPart.showUserInfo();
                    break;
                case 5:
                    System.out.println("申请贷款业务");
                    System.out.println("---------------------------------------------------------------------");
                    RunPart.applyLoanPart(sc);
                    break;
                case 6:
                    System.out.println("还贷业务");
                    System.out.println("---------------------------------------------------------------------");
                    RunPart.repaymentPart(sc);
                    break;
                case 7:
                    System.out.println("查询贷款详情业务");
                    System.out.println("---------------------------------------------------------------------");
                    RunPart.queryRepaymentPart(sc);
                    break;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
            return true;
        }
    }
}
