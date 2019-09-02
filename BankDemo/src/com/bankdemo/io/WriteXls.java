package com.bankdemo.io;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.bankdemo.bean.AllInfo;
import com.bankdemo.bean.CustomerInfo;
import com.bankdemo.bean.UserInfo;
import com.bankdemo.bean.Frozen;
import lombok.Cleanup;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteXls {

    public static void writeXls() throws Exception  {

        @Cleanup OutputStream out = new FileOutputStream("src/com/bankdemo/data/bankUserInfo.xls");

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("sheet-1");

        HSSFRow row_title = sheet.createRow(0);

        HSSFCell row_title_cell0 = row_title.createCell(0);
        row_title_cell0.setCellValue("userId");     //String
        HSSFCell row_title_cell1 = row_title.createCell(1);
        row_title_cell1.setCellValue("password");   //String
        HSSFCell row_title_cell2 = row_title.createCell(2);
        row_title_cell2.setCellValue("phone");      //String
        HSSFCell row_title_cell3 = row_title.createCell(3);
        row_title_cell3.setCellValue("userName");   //String
        HSSFCell row_title_cell4 = row_title.createCell(4);
        row_title_cell4.setCellValue("money");      //double
        HSSFCell row_title_cell5 = row_title.createCell(5);
        row_title_cell5.setCellValue("addMoney");   //double
        HSSFCell row_title_cell6 = row_title.createCell(6);
        row_title_cell6.setCellValue("reduceMoney");    //double
        HSSFCell row_title_cell7 = row_title.createCell(7);
        row_title_cell7.setCellValue("modify");     //int
        HSSFCell row_title_cell8 = row_title.createCell(8);
        row_title_cell8.setCellValue("level");      //int
        HSSFCell row_title_cell9 = row_title.createCell(9);
        row_title_cell9.setCellValue("blank");      //boolean
        HSSFCell row_title_cell10 = row_title.createCell(10);
        row_title_cell10.setCellValue("loanLimit");  //double
        HSSFCell row_title_cell11 = row_title.createCell(11);
        row_title_cell11.setCellValue("loanValue");  //double
        HSSFCell row_title_cell12 = row_title.createCell(12);
        row_title_cell12.setCellValue("partValue");  //double
        HSSFCell row_title_cell13 = row_title.createCell(13);
        row_title_cell13.setCellValue("rate");       //double
        HSSFCell row_title_cell14 = row_title.createCell(14);
        row_title_cell14.setCellValue("startDay");   //long
        HSSFCell row_title_cell15 = row_title.createCell(15);
        row_title_cell15.setCellValue("backTime");   //long
        HSSFCell row_title_cell16 = row_title.createCell(16);
        row_title_cell16.setCellValue("applyDay");   //int
        HSSFCell row_title_cell17 = row_title.createCell(17);
        row_title_cell17.setCellValue("model");      //int
        HSSFCell row_title_cell18 = row_title.createCell(18);
        row_title_cell18.setCellValue("partdays");   //long[]
        HSSFCell row_title_cell19 = row_title.createCell(19);
        row_title_cell19.setCellValue("records");    //{[int,String],[int,String]...}
        CustomerInfo.cover();
        int len = AllInfo.getUserInfos().size();
        for (int i = 1 ; i <= len ; i++) {
            UserInfo userInfo = AllInfo.getUserInfos().get(i-1);
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 20; j++) {
                HSSFCell cell = row.createCell(j);
                switch (j){
                    case 0:
                        cell.setCellValue(userInfo.getUserId());
                        break;
                    case 1:
                        cell.setCellValue(userInfo.getPassword());
                        break;
                    case 2:
                        cell.setCellValue(userInfo.getPhone());
                        break;
                    case 3:
                        cell.setCellValue(userInfo.getUserName());
                        break;
                    case 4:
                        cell.setCellValue(userInfo.getBalance().getMoney());
                        break;
                    case 5:
                        cell.setCellValue(userInfo.getBalance().getAddMoney());
                        break;
                    case 6:
                        cell.setCellValue(userInfo.getBalance().getReduceMoney());
                        break;
                    case 7:
                        cell.setCellValue(userInfo.getBalance().getModify());
                        break;
                    case 8:
                        cell.setCellValue(userInfo.getCreditLevel().getLevel());
                        break;
                    case 9:
                        cell.setCellValue(userInfo.getCreditLevel().isBlank());
                        break;
                    case 10:
                        cell.setCellValue(userInfo.getLoan().getLoanLimit());
                        break;
                    case 11:
                        cell.setCellValue(userInfo.getLoan().getLoanValue());
                        break;
                    case 12:
                        cell.setCellValue(userInfo.getLoan().getPartValue());
                        break;
                    case 13:
                        cell.setCellValue(userInfo.getLoan().getRate());
                        break;
                    case 14:
                        cell.setCellValue(userInfo.getLoan().getStartDay());
                        break;
                    case 15:
                        cell.setCellValue(userInfo.getLoan().getBackTime());
                        break;
                    case 16:
                        cell.setCellValue(userInfo.getLoan().getApplyDay());
                        break;
                    case 17:
                        cell.setCellValue(userInfo.getLoan().getModel());
                        break;
                    case 18:
                        StringBuffer sb = new StringBuffer();
                        for (long partday : userInfo.getLoan().getPartdays()){
                            sb.append(partday).append(",");
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        cell.setCellValue(sb.toString());
                        break;
                    case 19:
                        if(!userInfo.getRecords().isEmpty()){
                            StringBuffer sb1 = new StringBuffer();
                            for (String item : userInfo.getRecords()){
                                sb1.append(item).append("\n");
                            }
                            sb1.deleteCharAt(sb1.length() - 1);
                            cell.setCellValue(sb1.toString());
                        }else{
                            cell.setCellValue("null");
                        }
                        break;
                }
            }
        }


        wb.write(out);


    }
    public static void writeFrozenList() throws Exception  {

        @Cleanup OutputStream out = new FileOutputStream("src/com/bankdemo/data/frozenInfo.xls");

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("sheet-1");

        HSSFRow row_title = sheet.createRow(0);

        HSSFCell row_title_cell0 = row_title.createCell(0);
        row_title_cell0.setCellValue("userId");     //String

        int len = Frozen.getFrozenList().size();
        for (int i = 1 ; i <= len ; i++) {
            String item = Frozen.getFrozenList().get(i-1);
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(item);
        }
        wb.write(out);
    }

}
