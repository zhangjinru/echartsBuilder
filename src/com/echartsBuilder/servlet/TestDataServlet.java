package com.echartsBuilder.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echartsBuilder.dao.TSaleOrderDao;
import com.echartsBuilder.entity.TSaleOrder;

/**
 * Servlet implementation class TestDataServlet
 */
public class TestDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TSaleOrderDao saleOrderDao = new TSaleOrderDao();
		String year = "2014";
		Map map = new HashMap();
		map.put("companyCode", "大冶特钢");
		map.put("signDate", year);
		List<TSaleOrder> results = saleOrderDao.select_ex("TSaleOrder.select_ex", map);
		Map seriesData = new HashMap();//坐标值对应图例来说
		String prodDept = results.size() != 0 ? results.get(0).getProdDept() : "";
		Map datas = new HashMap();//总价 key 日期 ，value 总价
		JSONArray jsonArray = new JSONArray();
	//	List lengthData = new ArrayList();
		JSONArray lengthData = new JSONArray();
	//	JSONObject json = new JSONObject();
		for( int i = 0 ; i < results.size(); i++ ){
			TSaleOrder saleOrder = results.get(i);
			datas.put(saleOrder.getSignDate(),saleOrder.getTotalPrice());
			if( !prodDept.equals(saleOrder.getProdDept()) ){
				JSONObject json = new JSONObject();
				json.put("data", this.getSerieData(datas,year)); 
				json.put("name", prodDept);
				json.put("type", "bar");
				json.put("stack", "总量");
				jsonArray.add(json);
				lengthData.add(prodDept);
				//map.put( prodDept,  this.getSerieData(datas,year) );
				datas.clear();
				prodDept = saleOrder.getProdDept();
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("series", jsonArray);
		jsonObj.put("legend", "{data : " + lengthData + ",y:'bottom'}");
		request.setAttribute("jsonData", jsonObj);
		request.getRequestDispatcher("web/dataChange/testData.jsp").forward(request, response);
	}
	private List getSerieData(Map map,String year){
		List list = new ArrayList();
		for(int i = 0 ; i < 12 ; i ++){
			String key = "";
			if( i < 9){
			    key = year + "-0" + (i + 1);
			}else{
				 key =  year + "-" +(i + 1);
			}
			list.add(map.get(key)== null ? 0.0 : map.get(key));
		}
		return list;
	}

}
