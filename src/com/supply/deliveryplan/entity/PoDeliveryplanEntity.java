package com.supply.deliveryplan.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 送货计划主表
 * @author onlineGenerator
 * @date 2016-07-05 11:16:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "po_deliveryplan", schema = "")
@SuppressWarnings("serial")
public class PoDeliveryplanEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**创建日期时间*/
	private java.util.Date createDatetime;
	/**修改日期时间*/
	private java.lang.String updateDatetime;
	/**计划单号*/
    @Excel(name="计划单号")
	private java.lang.String deliveryplancode;
	/**合同编号*/
    @Excel(name="合同编号")
	private java.lang.String contractcode;
	/**到期日期*/
    @Excel(name="到期日期",format = "yyyy-MM-dd")
	private java.util.Date expiredate;
    /**供应商名称*/
    @Excel(name="供应商编码")
	private java.lang.String supplercode;
	/**供应商名称*/
    @Excel(name="供应商名称")
	private java.lang.String supplername;
	/**物料编码*/
    @Excel(name="物料编码")
	private java.lang.String materialcode;
	/**物料名称*/
    @Excel(name="物料名称")
	private java.lang.String materialname;
	/**规格*/
    @Excel(name="规格")
	private java.lang.String spec;
	/**卸货地点*/
    @Excel(name="卸货地点")
	private java.lang.String unloadplace;
	/**仓库*/
    @Excel(name="仓库")
	private java.lang.String store;
	/**承运单位*/
    @Excel(name="承运单位")
	private java.lang.String carrier;
	/**剩余数量*/
    @Excel(name="剩余数量")
	private java.math.BigDecimal surplusnum;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String vnote;
	/**删除标志*/
	private java.lang.String dr;
	/**采购订单号*/
    @Excel(name="采购订单号")
	private java.lang.String poordercode;
	/**单据状态*/
	private java.lang.String billstatus;
	/**时间戳*/
	private java.util.Date ts;
	/**审核人*/
	private java.lang.String approve;
	/**审核日期*/
	private java.util.Date approvedate;
	/**作废人*/
	private java.lang.String cancel;
	/**作废日期*/
	private java.util.Date canceldate;
	/**采购订单数量*/
	private java.math.BigDecimal poordernum;
	/**运输商编码*/
	private java.lang.String trancode;
	/**运输商名称*/
	private java.lang.String tranname;
	private java.lang.String approveone;
	private java.util.Date approveonedate;
	private java.lang.String approveoneinfo;
	private java.lang.String approveonestatus;
	private java.lang.String approvetwo;
	private java.util.Date approvetwodate;
	private java.lang.String approvetwoinfo;
	private java.lang.String approvetwostatus;
	private java.lang.String approveonename;
	private java.lang.String approvetwoname;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期时间
	 */
	
	@Column(name ="CREATE_DATETIME",nullable=true,length=32)
	public java.util.Date getCreateDatetime(){
		return this.createDatetime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期时间
	 */
	public void setCreateDatetime(java.util.Date createDatetime){
		this.createDatetime = createDatetime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改日期时间
	 */
	
	@Column(name ="UPDATE_DATETIME",nullable=true,length=32)
	public java.lang.String getUpdateDatetime(){
		return this.updateDatetime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改日期时间
	 */
	public void setUpdateDatetime(java.lang.String updateDatetime){
		this.updateDatetime = updateDatetime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划单号
	 */
	
	@Column(name ="DELIVERYPLANCODE",nullable=true,length=50)
	public java.lang.String getDeliveryplancode(){
		return this.deliveryplancode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划单号
	 */
	public void setDeliveryplancode(java.lang.String deliveryplancode){
		this.deliveryplancode = deliveryplancode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同编号
	 */
	
	@Column(name ="CONTRACTCODE",nullable=true,length=32)
	public java.lang.String getContractcode(){
		return this.contractcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编号
	 */
	public void setContractcode(java.lang.String contractcode){
		this.contractcode = contractcode;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日期
	 */
	
	@Column(name ="EXPIREDATE",nullable=true,length=32)
	public java.util.Date getExpiredate(){
		return this.expiredate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日期
	 */
	public void setExpiredate(java.util.Date expiredate){
		this.expiredate = expiredate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商名称
	 */
	
	@Column(name ="SUPPLERNAME",nullable=true,length=200)
	public java.lang.String getSupplername(){
		return this.supplername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商名称
	 */
	public void setSupplername(java.lang.String supplername){
		this.supplername = supplername;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商编码
	 */
	
	@Column(name ="SUPPLERCODE",nullable=true,length=200)
	public java.lang.String getSupplercode(){
		return this.supplercode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商编码
	 */
	public void setSupplercode(java.lang.String supplercode){
		this.supplercode = supplercode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料编码
	 */
	
	@Column(name ="MATERIALCODE",nullable=true,length=200)
	public java.lang.String getMaterialcode(){
		return this.materialcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料编码
	 */
	public void setMaterialcode(java.lang.String materialcode){
		this.materialcode = materialcode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物料名称
	 */
	
	@Column(name ="MATERIALNAME",nullable=true,length=200)
	public java.lang.String getMaterialname(){
		return this.materialname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物料名称
	 */
	public void setMaterialname(java.lang.String materialname){
		this.materialname = materialname;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格
	 */
	
	@Column(name ="SPEC",nullable=true,length=32)
	public java.lang.String getSpec(){
		return this.spec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格
	 */
	public void setSpec(java.lang.String spec){
		this.spec = spec;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卸货地点
	 */
	
	@Column(name ="UNLOADPLACE",nullable=true,length=200)
	public java.lang.String getUnloadplace(){
		return this.unloadplace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卸货地点
	 */
	public void setUnloadplace(java.lang.String unloadplace){
		this.unloadplace = unloadplace;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库
	 */
	
	@Column(name ="STORE",nullable=true,length=200)
	public java.lang.String getStore(){
		return this.store;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setStore(java.lang.String store){
		this.store = store;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承运单位
	 */
	
	@Column(name ="CARRIER",nullable=true,length=200)
	public java.lang.String getCarrier(){
		return this.carrier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承运单位
	 */
	public void setCarrier(java.lang.String carrier){
		this.carrier = carrier;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  剩余数量
	 */
	
	@Column(name ="SURPLUSNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getSurplusnum(){
		return this.surplusnum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  剩余数量
	 */
	public void setSurplusnum(java.math.BigDecimal surplusnum){
		this.surplusnum = surplusnum;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="VNOTE",nullable=true,length=200)
	public java.lang.String getVnote(){
		return this.vnote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setVnote(java.lang.String vnote){
		this.vnote = vnote;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  删除标志
	 */
	
	@Column(name ="DR",nullable=true,length=32)
	public java.lang.String getDr(){
		return this.dr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  删除标志
	 */
	public void setDr(java.lang.String dr){
		this.dr = dr;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购订单号
	 */
	
	@Column(name ="POORDERCODE",nullable=true,length=32)
	public java.lang.String getPoordercode(){
		return this.poordercode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购订单号
	 */
	public void setPoordercode(java.lang.String poordercode){
		this.poordercode = poordercode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据状态
	 */
	
	@Column(name ="BILLSTATUS",nullable=true,length=32)
	public java.lang.String getBillstatus(){
		return this.billstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据状态
	 */
	public void setBillstatus(java.lang.String billstatus){
		this.billstatus = billstatus;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  时间戳
	 */
	
	@Column(name ="TS",nullable=true,length=32)
	public java.util.Date getTs(){
		return this.ts;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  时间戳
	 */
	public void setTs(java.util.Date ts){
		this.ts = ts;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */
	
	@Column(name ="APPROVE",nullable=true,length=32)
	public java.lang.String getApprove(){
		return this.approve;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setApprove(java.lang.String approve){
		this.approve = approve;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审核日期
	 */
	
	@Column(name ="APPROVEDATE",nullable=true,length=32)
	public java.util.Date getApprovedate(){
		return this.approvedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核日期
	 */
	public void setApprovedate(java.util.Date approvedate){
		this.approvedate = approvedate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  作废人
	 */
	
	@Column(name ="CANCEL",nullable=true,length=32)
	public java.lang.String getCancel(){
		return this.cancel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  作废人
	 */
	public void setCancel(java.lang.String cancel){
		this.cancel = cancel;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  作废日期
	 */
	
	@Column(name ="CANCELDATE",nullable=true,length=32)
	public java.util.Date getCanceldate(){
		return this.canceldate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  作废日期
	 */
	public void setCanceldate(java.util.Date canceldate){
		this.canceldate = canceldate;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  采购订单数量
	 */
	
	@Column(name ="POORDERNUM",nullable=true,scale=2,length=9)
	public java.math.BigDecimal getPoordernum(){
		return this.poordernum;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  采购订单数量
	 */
	public void setPoordernum(java.math.BigDecimal poordernum){
		this.poordernum = poordernum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输商编码
	 */
	
	@Column(name ="TRANCODE",nullable=true,length=200)
	public java.lang.String getTrancode(){
		return this.trancode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输商编码
	 */
	public void setTrancode(java.lang.String trancode){
		this.trancode = trancode;
	}/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  运输商名称
	 */
	
	@Column(name ="TRANNAME",nullable=true,length=200)
	public java.lang.String getTranname(){
		return this.tranname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  运输商名称
	 */
	public void setTranname(java.lang.String tranname){
		this.tranname = tranname;
	}
	@Column(name ="APPROVEONE",nullable=true,length=200)
	public java.lang.String getApproveone() {
	    return approveone;
	}

	public void setApproveone(java.lang.String approveone) {
	    this.approveone = approveone;
	}

	@Column(name ="APPROVEONEDATE",nullable=true,length=200)
	public java.util.Date getApproveonedate() {
	    return approveonedate;
	}

	public void setApproveonedate(java.util.Date approveonedate) {
	    this.approveonedate = approveonedate;
	}

	@Column(name ="APPROVEONEINFO",nullable=true,length=200)
	public java.lang.String getApproveoneinfo() {
	    return approveoneinfo;
	}

	public void setApproveoneinfo(java.lang.String approveoneinfo) {
	    this.approveoneinfo = approveoneinfo;
	}

	@Column(name ="APPROVEONESTATUS",nullable=true,length=200)
	public java.lang.String getApproveonestatus() {
	    return approveonestatus;
	}

	public void setApproveonestatus(java.lang.String approveonestatus) {
	    this.approveonestatus = approveonestatus;
	}

	@Column(name ="APPROVETWO",nullable=true,length=200)
	public java.lang.String getApprovetwo() {
	    return approvetwo;
	}

	public void setApprovetwo(java.lang.String approvetwo) {
	    this.approvetwo = approvetwo;
	}

	@Column(name ="APPROVETWODATE",nullable=true,length=200)
	public java.util.Date getApprovetwodate() {
	    return approvetwodate;
	}

	public void setApprovetwodate(java.util.Date approvetwodate) {
	    this.approvetwodate = approvetwodate;
	}

	@Column(name ="APPROVETWOINFO",nullable=true,length=200)
	public java.lang.String getApprovetwoinfo() {
	    return approvetwoinfo;
	}

	public void setApprovetwoinfo(java.lang.String approvetwoinfo) {
	    this.approvetwoinfo = approvetwoinfo;
	}

	@Column(name ="APPROVETWOSTATUS",nullable=true,length=200)
	public java.lang.String getApprovetwostatus() {
	    return approvetwostatus;
	}

	public void setApprovetwostatus(java.lang.String approvetwostatus) {
	    this.approvetwostatus = approvetwostatus;
	}

	@Column(name ="APPROVEONENAME",nullable=true,length=200)
	public java.lang.String getApproveonename() {
	    return approveonename;
	}

	public void setApproveonename(java.lang.String approveonename) {
	    this.approveonename = approveonename;
	}
	@Column(name ="APPROVETWONAME",nullable=true,length=200)
	public java.lang.String getApprovetwoname() {
	    return approvetwoname;
	}

	public void setApprovetwoname(java.lang.String approvetwoname) {
	    this.approvetwoname = approvetwoname;
	}
	
}

