package com.supply.common;

public class SupplyConstants {
    	/*
    	 * 环境相关
    	 */
	public static String U8TEST = "U8TEST";//U8测试数据源编码
	public static String SHJH = "SHJH";//送货计划单号前缀；
	public static String THJH = "THJH";//提货计划单号前缀；
	public static String U8SQLSERVER = "U8SQLSERVER";//U8数据源
	public static String U8PONAME = "view_po_order";//U8采购订单的视图名称
	public static String U8SONAME = "view_so_order";//U8销售订单的视图名称
	public static String APPRO_SWB = "appro_swb";//商务部审批角色
	public static String APPRO_CWB = "appro_cwb";//财务部审批角色
	public static String APPRO_SCWB = "appro_scwb";//商务财务部审批角色
	
	/*
	 * 单据相关
	 */
	public static String BILLSTATUS_SAVE = "0";//保存
	public static String BILLSTATUS_APPROVE = "1";//审核
	public static String BILLSTATUS_UNAPPROVE = "2";//弃审
	public static String BILLSTATUS_CLOSE = "3";//关闭
	public static String BILLSTATUS_CANCEL = "-1";//作废
	public static String BILLSTATUS_INFO = "请输入审批意见!(不可超过150字)";//默认审批显示语
	public static String CARSTATUS_SAVE = "0";//计划已下达
	public static String CARSTATUS_GETCARD = "1";//已取卡
	public static String CARSTATUS_GETGOODS = "2";//发货/卸货
	public static String CARSTATUS_OUT = "3";//已出厂
	
	
	
}
