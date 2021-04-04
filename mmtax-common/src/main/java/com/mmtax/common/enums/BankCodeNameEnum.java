package com.mmtax.common.enums;

public enum  BankCodeNameEnum {

    // 未知银行 图片http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075044536221607504453622635.png
    //
    CMB("招商银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075041512591607504151259317.png"),
    ICBC("中国工商银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075043445011607504344501783.png"),
    CCB("中国建设银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075040552861607504055286362.png"),
    SPDB("上海浦东发展银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075044056021607504405602609.png"),
    ABC("中国农业银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075029542051607502954205586.png"),
    SDB("深圳发展银行","none"),
    CIB("兴业银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075041057351607504105735520.png"),
    CEB("中国光大银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075040823771607504082377808.png"),
    CMBC("中国民生银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075041731741607504173174537.png"),
    CITIC("中信银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075041304601607504130460590.png"),
    GDB("广东发展银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075042750751607504275075625.png"),
    SZPAB("平安银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075044343181607504434317472.png"),
    BOC("中国银行","none"),
    COMM("中国交通银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075041977331607504197733229.png"),
    PSBC("中国邮政储蓄银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075043643941607504364394365.png"),
    BOS("上海银行","none"),
    CRB("华润银行","none"),
    SDEB("顺德农商","none"),
    HXB("华夏银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075043257741607504325774521.png"),
    BCCB("北京银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075039862481607503986248935.png"),
    CBHB("渤海银行","none"),
    EGBANK("恒丰银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075042477411607504247741531.png"),
    CZB("浙商银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075042267671607504226767689.png"),
    HCCB("杭州银行","none"),
    BSB("包商银行","none"),
    CQCB("重庆银行","none"),
    BOCD("成都银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075040211981607504021198263.png"),
    HBCB("哈尔滨银行","none"),
    GDNYB("南粤银行","none"),
    JSBC("江苏银行","none"),
    NBCB("宁波银行","none"),
    NJCB("南京银行","none"),
    ZZCB("郑州银行","none"),
    TCCB("天津银行","none"),
    BJRCB("北京农商行","none"),
    CCQTGB("重庆三峡银行","none"),
    CITYBANK("城市商业银行","none"),
    COUNTYBANK("村镇银行","none"),
    CSCB("长沙银行","none"),
    CYB("集友银行","none"),
    CZCB("浙江稠州商业银行","none"),
    GNXS("广州市农信社","none"),
    GZCB("广州市商业银行","none"),
    HKBCHINA("汉口银行","none"),
    HKBEA("东亚银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075042975121607504297512759.png"),
    HNNXS("湖南农信社","none"),
    HSBANK("徽商银行","none"),
    RCB("农村商业银行","http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075043829811607504382981233.png"),
    RCC("农村信用合作社","none"),
    SCB("渣打银行","none"),
    SDE("顺德农信社","none"),
    SHRCB("上海农村商业银行","none"),
    SNXS("深圳农村商业银行","none"),
    SXJS("晋城市商业银行","none"),
    UCC("城市信用合作社","none"),
    URCB("农村合作银行","none"),
    WZCB("温州市商业银行","none"),
    YDXH("尧都信用合作联社","none"),
    ZHNX("珠海市农村信用合作社","none"),
    MYBANK("网商银行","none"),
    UPOP("银联支付","none");


    private String bankName;
    private String bankImage;

    BankCodeNameEnum(String bankName, String bankImage) {
        this.bankName = bankName;
        this.bankImage = bankImage;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankImage() {
        return bankImage;
    }
}
