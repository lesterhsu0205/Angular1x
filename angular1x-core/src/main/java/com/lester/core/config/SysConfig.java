package com.lester.core.config;

public class SysConfig {
	

	public static class CodeCate {
		/** 縣市 */
		public static String CITY = "CITY";
		/** 鄉鎮 */
		public static String TOWN = "TOWN";
		/** 案件類型 */
		public static String CASE_TYPE = "CASE_TYPE";
		/** 案件分類 */
		public static String CASE_GROUP = "CASE_GROUP";
		/** 工單狀態 */
		public static String STATUS = "STATUS";
		/** 工單子狀態 */
		public static String SUB_STATUS = "SUB_STATUS";
		/** 檔案類型 */
		public static String FILE_TYPE = "FILE_TYPE";
		/** 預設資料 */
		public static String DEFAULT_CONFIG = "DEFAULT_CONFIG";
	}


	public static class Code {
		/** 預設年度區間上限 */
		public static String DEFAULT_CONFIG_YEAR_RANGE_UPPER = "DEFAULT_CONFIG_YEAR_RANGE_UPPER";
		/** 預設年度區間下限 */
		public static String DEFAULT_CONFIG_YEAR_RANGE_LOWER = "DEFAULT_CONFIG_YEAR_RANGE_LOWER";
		/** 預設期別數量 */
		public static String DEFAULT_CONFIG_SEASON_COUNT = "DEFAULT_CONFIG_SEASON_COUNT";
	}
	
	public static class Security {
		public static final String ROLE_USER = "ROLE_USER";
	}

	public static class SessionKey {
		public static final String LOGINUSER = "loginUser";
	}
	
}
