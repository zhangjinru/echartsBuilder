package com.echartsBuilder.entity;

import java.util.HashMap;
import java.util.Map;

public class TSaleOrder {
	private String signDate;//日期
	private String companyCode;//公司
	private String settleUserEname;//用户名称
	private String prodCode;//品种
	private String shopSign;//牌号
	private String prodDept;//销售部门
	private double orderWt;//重量
	private double wtPrice;//单价
	private double totalPrice;//总价
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getSettleUserEname() {
		return settleUserEname;
	}
	public void setSettleUserEname(String settleUserEname) {
		this.settleUserEname = settleUserEname;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getShopSign() {
		return shopSign;
	}
	public void setShopSign(String shopSign) {
		this.shopSign = shopSign;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProdDept() {
		return prodDept;
	}
	public void setProdDept(String prodDept) {
		this.prodDept = prodDept;
	}
	public double getOrderWt() {
		return orderWt;
	}
	public void setOrderWt(double orderWt) {
		this.orderWt = orderWt;
	}
	public double getWtPrice() {
		return wtPrice;
	}
	public void setWtPrice(double wtPrice) {
		this.wtPrice = wtPrice;
	}
	public Map toMap(){
		Map map = new HashMap();
		map.put("signDate", getSignDate());
		map.put("companyCode", getCompanyCode());
		map.put("settleUserEname", getSettleUserEname());
		map.put("prodCode", getProdCode());
		map.put("shopSign", getShopSign());
		map.put("prodDept", getProdDept());
		map.put("orderWt", getOrderWt());
		map.put("wtPrice", getWtPrice());
		map.put("totalPrice", getTotalPrice());
		return map;
	}
	public static Map getSqlMap(){
		Map map = new HashMap();
		map.put("signDate", "sign_date");
		map.put("companyCode", "company_Code");
		map.put("settleUserEname", "settle_User_Ename");
		map.put("prodCode", "prod_Code");
		map.put("shopSign", "shopSign");
		map.put("prodDept", "prod_Dept");
		map.put("orderWt", "order_Wt");
		map.put("wtPrice", "wt_Price");
		map.put("totalPrice", "order_Wt * wt_Price");
		return map;
	}
}
