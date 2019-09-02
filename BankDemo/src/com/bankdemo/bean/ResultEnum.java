package com.bankdemo.bean;

//    业务状态码：
//    1 转账| 1成功 2账户余额不足
//    2 存款| 1成功
//    3 贷款| 1成功 2超出额度
//    4 还款| 1成功 2还款金额不足
//    5 修改信息| 1成功
public enum ResultEnum {
    LoanOutOfLimit(32,"贷款数目超出信用额度"),
    BalanceNoEnough(12, "账户余额不足"),
    Invalid(0,"无效操作"),
    MoneyNotEnough(42,"还款金额不足");
    private int resCode;
    private String msg;

    ResultEnum(int resCode, String msg) {
        this.resCode = resCode;
        this.msg = msg;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
