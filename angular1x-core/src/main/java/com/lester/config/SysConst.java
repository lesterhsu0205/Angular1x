package com.lester.config;

import com.lester.support.util.DateUtil;

public class SysConst {

    public final static String BATCH_HPOS_USER = "HPOS_SYS";

    public final static String XWS_TOKEN = "XWS_TOKEN";

    public final static String DS = "001";

    public final static String FS = "002";
    public static final String jsonType = "application/json";
    public static final String formType = "x-www-form-urlencoded";

    public final static class HTTP_ATTR {
        public final static String USER_TOKEN = "USER_TOKEN";// USER_TOKEN
    }

    public final static class TRANSACTION_TYPE {
        public final static String ORDER_ISSUE = "ORDER_ISSUE";// 銷貨出庫
        public final static String ORDER_RECEIPT = "ORDER_RECEIPT";// 銷退入庫
        public final static String CHANGE_ISSUE = "CHANGE_ISSUE";// 銷換出庫
        public final static String CHANGE_RECEIPT = "CHANGE_RECEIPT";// 銷換入庫
        public final static String MAINTAIN_ISSUE = "MAINTAIN_ISSUE";// 不良品出庫
        public final static String MAINTAIN_RECEIPT = "MAINTAIN_RECEIPT";// 完修入庫
        public final static String MOVE_ORDER_RECEIPT = "MOVE_ORDER_RECEIPT";// 調撥收料
    }

    public final static class STOCKTAKING_TYPE {
        public final static Integer DAY = 1; //日盤
        public final static Integer WEEK = 2; //周盤
        public final static Integer MONTH = 3; //月盤
    }

    public final static class IDX {
        public static int dtlIdx = 18;
        public static int DETAIL_ID = 0; // 明細ID
        public static int ITEM_ID = 1; // 商品ID
        public static int ITEM_CODE = 2; // 商品代碼
        public static int ITEM_NAME = 3; // 商品名稱
        public static int ITEM_UNIT = 4; // 商品單位
        public static int BALANCE_QTY = 5; // 前一天庫存量 ONHAND_QTY-TRANSACTION_QTY
        public static int RESERVE_QTY = 6;// 保留數量
        public static int STAGE_QTY = 7;// 已調出量
        public static int MAINTAIN_QTY = 8;// 維修數量
        public static int SALE_QTY = 9; // 銷售數量
        public static int RECEIPT_QTY = 10; // 收料數量
        public static int DELIVERIED_QTY = 11; // 出貨數量
        public static int TRANSACTION_QTY = 12; // 今日異動 SALE_QTY+RECEIPT_QTY-DELIVERIED_QTY
        public static int ONHAND_QTY = 13; // 現有庫存數
        public static int STOCKTAKING_QTY = 14; // 盤點庫存量
        public static int DIFF_QTY = 15; // 差點數量
        public static int DIFFREMARK = 16; // 異差原因
        public static int WAIT_ACCEPTANCE_QTY = 17; // 待收料量
    }

    /**
     * Created by lesterhsu on 2017/3/29.
     */
    public final static class INT_POS_ERP_STORE {
        public final static String ORG_NAME = "ORG_NAME";
        public final static String STORENO = "STORENO";
        public final static String STORENAME = "STORENAME";
        public final static String BRANCHNO = "BRANCHNO";
        public final static String BRANCHNAME = "BRANCHNAME";
        public final static String COSTCENTER = "COSTCENTER";
        public final static String COSTCENTERNAME = "COSTCENTERNAME";
        public final static String REGION = "REGION";
        public final static String SALES_CHANNEL = "SALES_CHANNEL";
        public final static String PARENT_STORENO = "PARENT_STORENO";
        public final static String VIRTUAL_WAREHOUSE_FLAG = "VIRTUAL_WAREHOUSE_FLAG";
        public final static String CONTACT_PERSON = "CONTACT_PERSON";
        public final static String CONTACT_PHONE = "CONTACT_PHONE";
        public final static String COMPANY_ID = "COMPANY_ID";
        public final static String DIVISION = "DIVISION";
        public final static String DEPARTMENT = "DEPARTMENT";
        public final static String SHIP_SUB = "SHIP_SUB";
        public final static String SHIP_SUB_NAME = "SHIP_SUB_NAME";
        public final static String ZIPCODE = "ZIPCODE";
        public final static String DEALERSTATUS = "DEALERSTATUS";
        public final static String REPORT_EMAIL1 = "REPORT_EMAIL1";
        public final static String SHIPPINGADDRESS = "SHIPPINGADDRESS";
        public final static String FAXNUMBER = "FAXNUMBER";
        public final static String TELPHONE_NUMBER = "TELPHONE_NUMBER";
        public final static String CONTRACT_STARTDATE = "CONTRACT_STARTDATE";
        public final static String CONTRACT_ENDDATE = "CONTRACT_ENDDATE";
        public final static String OPEN_DATE = "OPEN_DATE";
        public final static String CLOSE_PERIOD_STAR = "CLOSE_PERIOD_STAR";
        public final static String CLOSE_PERIOD_END = "CLOSE_PERIOD_END";
    }

    public final static class CodeCate {
        /**
         * 縣市
         */
        public final static String CITY = "CITY";
        /**
         * 鄉鎮
         */
        public final static String TOWN = "TOWN";
        /**
         * 案件類型
         */
        public final static String CASE_TYPE = "CASE_TYPE";
        /**
         * 案件分類
         */
        public final static String CASE_GROUP = "CASE_GROUP";
        /**
         * 工單狀態
         */
        public final static String STATUS = "STATUS";
        /**
         * 工單子狀態
         */
        public final static String SUB_STATUS = "SUB_STATUS";
        /**
         * 檔案類型
         */
        public final static String FILE_TYPE = "FILE_TYPE";
        /**
         * 預設資料
         */
        public final static String DEFAULT_CONFIG = "DEFAULT_CONFIG";
    }


    public final static class Code {
        /**
         * 預設年度區間上限
         */
        public final static String DEFAULT_CONFIG_YEAR_RANGE_UPPER = "DEFAULT_CONFIG_YEAR_RANGE_UPPER";
        /**
         * 預設年度區間下限
         */
        public final static String DEFAULT_CONFIG_YEAR_RANGE_LOWER = "DEFAULT_CONFIG_YEAR_RANGE_LOWER";
        /**
         * 預設期別數量
         */
        public final static String DEFAULT_CONFIG_SEASON_COUNT = "DEFAULT_CONFIG_SEASON_COUNT";
    }

    public final static class Security {
        public final static String ROLE_USER = "ROLE_USER";
    }

    public final static class SessionKey {
        public final static String LOGINUSER = "loginUser";
    }

    public enum RestValid {

        // 訊息
        Success("000", "成功"),
        UnusualSave("099", "儲存資料異常"),
        UnusualStockCost("001", "料號可能不存在物料主檔 or 屬於不扣庫存的料號"),
        UnusualScanRepeat("098", "重複掃描"),
        UnusualScanNoImei("002", "此掃描值非IEMI非料號"),
        UnusualNoImeiInPoint("003", "此IMEI目前不在該店點內"),
        UnusualNoImeiAuthority("004", "此IMEI對應的料號不屬於管控IMEI者，請掃描料號"),
        UnusualNoImei("006", "此IMEI對應的料號有問題，請向相關人員反應"),
        UnusualNoImeiButItem("000", "此掃描值為料號，非為IMEI"),
        UnusualNoImeiButItem2("007", "此掃描值為料號，非為IMEI"),
        NoCreateAccount("012", "未建立該系統帳號，請向窗口反應"),
        NoAuthority("011", "請申請該店點的使用者權限"),
        AuthorityError("013", "授權資料異常"),
        Error("002", ""),
        NoQueryCondition("001", ""),
        HasEpNo("002", "此單號已有EP單號"),
        NotInHpos("001", "單號不存在HPOS"),
        ErrDealerCode("002", "查詢條件-店點代碼 有誤"),

        // 訊息+驗證規則
        CompanyId("companyId", "099", "無公司別", new String[]{"1"}),
        CompanyId_1("companyId", "099", "無公司別", new String[]{"1"}),
        ChannelCode("channelCode", "099", "無通路別"),
        ChannelCode_001("channelCode", "099", "通路別非001(直營)", new String[]{DS}),
        ChannelCode_002("channelCode", "099", "通路別非002(加盟)", new String[]{FS}),
        ChannelCode_001_002("channelCode", "010", "通路別只能屬於001(直營)或002(加盟)", new String[]{DS, FS}),
        UserId("userId", "099", "無工號"),
        DealerCode("dealerCode", "099", "無店點代碼"),
        SalesDate("salesDate", "099", "無營業日期"),
        SalesDate_yyyyMMddS("salesDate", "099", "營業日期格式必須為yyyy/MM/dd", new String[]{DateUtil.FORMAT_Date}),
        StNo("stNo", "099", "無欲查詢的盤點單號"),
        ItemNumber("itemNumber", "099", "無料號"),
        DetailId("detailId", "099", "未指定明細值"),
        Cnt("cnt", "099", "無數量"),
        TransType("transType", "099", "無交易別"),
        TransType_Sale_Return_toStore("transType", "002", "交易別值有誤", new String[]{"SALE", "RETURN", "ToSTORE"}),
        ScanValue("scanValue", "099", "無掃描內容"),
        Mazepd("mazepd", "099", "無九宮格內容"),
        IsScan("isScan", "099", "無註記是否為掃描器掃入"),
        PoNo("poNo", "099", "無收料單號"),
        ProcessSerialNo("processSerialno", "099", "無EP流程編號"),
        EpNo("epNo", "099", "無EP單號"),
        TicketNo("ticketNo", "099", "無收料/盤點單號"),
        EventNo("eventNo", "099", "無異常單號"),
        Subject("subject", "099", "無異常主題(標題)"),
        Contents("contents", "099", "無異常內容說明"),
        Type("type", "099", "無單據種類");

        private String code;

        private String param;

        private String text;

        private String[] rules;

        RestValid(String code, String text) {
            this(null, code, text, null);
        }

        RestValid(String param, String code, String text) {
            this(param, code, text, null);
        }

        RestValid(String param, String code, String text, String[] rules) {
            this.param = param;
            this.code = code;
            this.text = text;
            this.rules = rules;
        }

        public String getText() {
            return text;
        }

        public String getCode() {
            return code;
        }

        public String[] getRules() {
            return rules;
        }

        public String getParam() {
            return param;
        }

    }
}