package com.bankdemo.io;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bankdemo.bean.UserInfo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadXls {

    public static List<UserInfo> readXls() throws Exception {

        InputStream in = new FileInputStream("src/com/bankdemo/data/bankUserInfo.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        int r = 0;
        while(rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells =  row.cellIterator();
            int i = 0;
            if (r == 0){
                r++;
                continue;
            }
            UserInfo userInfo = new UserInfo();
            while(cells.hasNext()) {
                Cell cell = cells.next();
                switch (i){
                    case 0:
                        userInfo.setUserId(cell.getStringCellValue());
                        break;
                    case 1:
                        userInfo.setPassword(cell.getStringCellValue());
                        break;
                    case 2:
                        userInfo.setPhone(cell.getStringCellValue());
                        break;
                    case 3:
                        userInfo.setUserName(cell.getStringCellValue());
                        break;
                    case 4:
                        userInfo.getBalance().setMoney(cell.getNumericCellValue());
                        break;
                    case 5:
                        userInfo.getBalance().setAddMoney(cell.getNumericCellValue());
                        break;
                    case 6:
                        userInfo.getBalance().setReduceMoney(cell.getNumericCellValue());
                        break;
                    case 7:
                        userInfo.getBalance().setModify((int)cell.getNumericCellValue());
                        break;
                    case 8:
                        userInfo.getCreditLevel().setLevel((int) cell.getNumericCellValue());
                        break;
                    case 9:
                        userInfo.getCreditLevel().setBlank(cell.getBooleanCellValue());
                        break;
                    case 10:
                        userInfo.getLoan().setLoanLimit(cell.getNumericCellValue());
                        break;
                    case 11:
                        userInfo.getLoan().setLoanValue(cell.getNumericCellValue());
                        break;
                    case 12:
                        userInfo.getLoan().setPartValue(cell.getNumericCellValue());
                        break;
                    case 13:
                        userInfo.getLoan().setRate(cell.getNumericCellValue());
                        break;
                    case 14:
                        userInfo.getLoan().setStartDay((long) cell.getNumericCellValue());
                        break;
                    case 15:
                        userInfo.getLoan().setBackTime((long) cell.getNumericCellValue());
                        break;
                    case 16:
                        userInfo.getLoan().setApplyDay((int) cell.getNumericCellValue());
                        break;
                    case 17:
                        userInfo.getLoan().setModel((int) cell.getNumericCellValue());
                        break;
                    case 18:
                        String[] strs = cell.getStringCellValue().split(",");
                        long[] partDays = new long[12];
                        for (int k = 0; k < 12; k++){
                            partDays[k] = Long.parseLong(strs[k]);
                        }
                        userInfo.getLoan().setPartdays(partDays);
                        break;
                    case 19:
                        String cellStr = cell.getStringCellValue();
                        if("null".equals(cellStr)){
                            List<String> records = new ArrayList<String>();
                            userInfo.setRecords(records);
                        }else{
                            List<String> records = new ArrayList<String>();
                            records.add(cellStr);
                            userInfo.setRecords(records);
                        }
                        break;
                }
                i++;
            }
            userInfos.add(userInfo);
        }
        return userInfos;
    }
        public static List<String> readFrozen() throws Exception {

        InputStream in = new FileInputStream("src/com/bankdemo/data/frozenInfo.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        List<String> frozenList = new ArrayList<String>();
        int r = 0;
        while(rows.hasNext()) {
            Row row = rows.next();
            if (r == 0){
                r++;
                continue;
            }
            Cell cell = row.getCell(0);
            frozenList.add(cell.getStringCellValue());
        }
        return frozenList;
    }
}

