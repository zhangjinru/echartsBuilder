package com.echartsBuilder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.echartsBuilder.entity.SaleOrder;


public class SaleOrderDao extends BaseDao {
	public List<SaleOrder> select() {
		List<SaleOrder> lists = new ArrayList<SaleOrder>();
		this.openDB();
		String sql = "select prod_dept as 'prodDept'  ,sum(order_wt) as 'orderWt',avg(wt_price) as 'wtPrice' from t_sale_order_test group by prod_dept";
		this.querySql(sql);
		try {
			while (rs.next()){
				SaleOrder saleOrder = new SaleOrder();
				saleOrder.setProdDept(rs.getString("prodDept"));
				saleOrder.setOrderWt(rs.getDouble("orderWt"));
				saleOrder.setWtPrice(rs.getDouble("wtPrice"));
				lists.add(saleOrder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeDB();
		return lists;
	}
}
