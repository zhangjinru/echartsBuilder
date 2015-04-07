package com.echartsBuilder.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.echartsBuilder.dao.SaleOrderDao;
import com.echartsBuilder.dao.TSaleOrderDao;
import com.echartsBuilder.entity.SaleOrder;
import com.echartsBuilder.entity.TSaleOrder;

/**
 * Servlet implementation class SaleOrderTestServlet
 */
public class SaleOrderTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleOrderTestServlet() {
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
		org.apache.ibatis.logging.LogFactory.useStdOutLogging();
		Map sqlMap = TSaleOrder.getSqlMap();//转义
		JSONObject jsonObj = new JSONObject();
		//整理出jsp 传来的参数
		Map mapAttr = this.getAttributes(request);
		TSaleOrderDao saleOrderDao = new TSaleOrderDao();
		Map paramsData = new HashMap();//从页面传过来的参数，变成sql中的参数
		paramsData = this.getTotalData(mapAttr,sqlMap);
		List<TSaleOrder> results = saleOrderDao.select_ex("TSaleOrder.select_dataStyle", paramsData);
		List dataXaxis =  this.getXaxis(sqlMap, mapAttr, saleOrderDao,paramsData);
		jsonObj = this.arrageData(results, mapAttr,dataXaxis);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonXaxis = new JSONObject();
		jsonXaxis.put("data", dataXaxis);
		jsonArray.add(jsonXaxis);
		jsonObj.put("xAxis", jsonArray);
		request.setAttribute("jsonData", jsonObj);
		request.getRequestDispatcher("saleOrderTest.jsp").forward(request, response);
		//response.sendRedirect("web/saleOrderTest.jsp");
	}
	/**
	 * 得到xaxis的data数据
	 * @param sqlMap
	 * @param attrsMap
	 * @param saleOrderDao
	 * @return
	 */
	private List  getXaxis(Map sqlMap,Map attrsMap,TSaleOrderDao saleOrderDao,Map paramsData){ 
		List list = new ArrayList();
		Map map = new HashMap();
		if(  !StringUtils.isBlank((String) paramsData.get("where")) ){
			map.put("where", paramsData.get("where"));//
		}
		String xaxis = this.changSignDate((String)sqlMap.get(attrsMap.get("xAxis")), (String)attrsMap.get("xAxis"));
		map.put("xAxis",  xaxis + " as '" + attrsMap.get("xAxis") +"'");
		List<TSaleOrder> results = saleOrderDao.select_ex("TSaleOrder.select_xAxis", map);
		for(int i = 0 ; i  < results.size();i++){
			Map mapData = results.get(i).toMap();
			list.add(mapData.get(attrsMap.get("xAxis")));
		}
		return list;
	}
	/**
	 * 整理sql语句
	 * @param request
	 * @return
	 */
	private Map getTotalData(Map mapAttr,Map sqlMap){
		Map map =  new HashMap();
		//sql语句的select 列表项
		String tabulationItems = "";
		String groupByStr = "group by ";
		String orderByStr = "order by ";
		if(mapAttr.get("xAxis") != null){
			//mapAttr.put("startSignDate", mapAttr.get("startSignDate"));
			String	xAxis = this.changSignDate((String) sqlMap.get(mapAttr.get("xAxis")),(String) mapAttr.get("xAxis"));
			tabulationItems += xAxis + " as '" + mapAttr.get("xAxis") +"'";
			groupByStr += xAxis;
			//orderByStr += sqlMap.get(mapAttr.get("xAxis"));
		} 
		if(mapAttr.get("yAxisLeft")!= null){
			//mapAttr.put("startSignDate", mapAttr.get("startSignDate"));
			if( mapAttr.get("yAxisLeft").equals("wtPrice") ){
				tabulationItems += "," + sqlMap.get(mapAttr.get("yAxisLeft")) + " as '" + mapAttr.get("yAxisLeft") + "'";
			}else{
				tabulationItems += ",sum(" + sqlMap.get(mapAttr.get("yAxisLeft")) + ") as '" + mapAttr.get("yAxisLeft") +"'";

			}
		} 
		String whereStr = "where ";
		int flagWhere = 0;
		if(mapAttr.get("startSignDate")!= null){
			//mapAttr.put("startSignDate", mapAttr.get("startSignDate"));
			whereStr += " " + "sign_Date >= '" +mapAttr.get("startSignDate") +  "' ";
			flagWhere = 1;
		} 
		if(mapAttr.get("endSignDate")!= null){
			//mapAttr.put("endSignDate", mapAttr.get("endSignDate"));
			if(flagWhere == 1){
				whereStr += " and "; 
			}
			whereStr += "sign_Date <= '" +mapAttr.get("endSignDate") +  "' ";
			flagWhere = 1;
		}
		if(mapAttr.get("companyCode")!= null){
			//mapAttr.put("companyCode", mapAttr.get("companyCode"));
			if(flagWhere == 1){
				whereStr += " and "; 
			}
			whereStr += "company_code like '%" +mapAttr.get("companyCode") +  "%' ";
			flagWhere = 1;
		}
		if(mapAttr.get("settleUserEname")!= null){
			//mapAttr.put("settleUserEname", mapAttr.get("settleUserEname"));
			if(flagWhere == 1){
				whereStr += " and "; 
			}
			whereStr += "settle_User_Ename like '%" +mapAttr.get("settleUserEname") +  "%' ";
			flagWhere = 1;
		}
		if(mapAttr.get("prodCode")!= null){
			//mapAttr.put("prodCode", mapAttr.get("prodCode"));
			if(flagWhere == 1){
				whereStr += " and "; 
			}
			whereStr +=  "prod_code like '%" +mapAttr.get("prodCode") +  "%' ";
			flagWhere = 1;
		}
		if(mapAttr.get("shopSign")!= null){
			//mapAttr.put("shopSign", mapAttr.get("shopSign"));
			if(flagWhere == 1){
				whereStr += " and "; 
			}
			whereStr +=  "shopSign like '%" +mapAttr.get("shopSign") +  "%' ";
			flagWhere = 1;
		}
		if(mapAttr.get("prodDept")!= null){
			//mapAttr.put("prodDept", mapAttr.get("prodDept"));
			if(flagWhere == 1){
				whereStr += " and "; 
			}
			whereStr += "prod_dept like '%" +mapAttr.get("prodDept") +  "%' ";
			flagWhere = 1;
		}
		
		if( !whereStr.trim().equals("where") ){
			map.put("where", whereStr);
		}
		
		//group by
		int i = 1;//因为group by
		if(mapAttr.get("groupBy1")!= null){
			String	xAxis = this.changSignDate((String) sqlMap.get(mapAttr.get("groupBy1")),(String) mapAttr.get("groupBy1"));
			groupByStr +=  "," + xAxis;
			orderByStr +=  xAxis;
			i = 1;
			tabulationItems +=  ","+ xAxis + " as '" +mapAttr.get("groupBy1") + "'";
		}
		if(mapAttr.get("groupBy2")!= null){
			if(i == 1){
				groupByStr +=  ","; 
				orderByStr +=  ","; 
			}
			String	xAxis = this.changSignDate((String) sqlMap.get(mapAttr.get("groupBy2")),(String) mapAttr.get("groupBy2"));
			groupByStr +=  xAxis;
			orderByStr +=  xAxis;
			i = 1;
			tabulationItems +=  ","+ xAxis  + "as '" +mapAttr.get("groupBy2") + "'";
		}
		if(mapAttr.get("groupBy3")!= null){
			if(i == 1){
				groupByStr +=  ","; 
				orderByStr += ",";
			}
			String	xAxis = this.changSignDate((String) sqlMap.get(mapAttr.get("groupBy3")),(String) mapAttr.get("groupBy3"));
			groupByStr +=  xAxis;
			orderByStr +=  xAxis;
			i = 1;
			tabulationItems +=  ","+ xAxis  + "as '" +mapAttr.get("groupBy3") + "'";
		}
		if( !groupByStr.trim().equals("group by") ){
			map.put("groupBy", groupByStr);
		}
		
		//orderBy
		if( !orderByStr.trim().equals("order by") ){
			map.put("orderBy", orderByStr);
		}
		
		map.put("tabulationItems", tabulationItems);
		return map;
	}
	/**
	 * 整理得到的数据
	 * @param results
	 * @return
	 */
	private JSONObject arrageData(List<TSaleOrder> results,Map attrsMap,List xAxisList){
		/*Map seriesData = new HashMap();//坐标值对应图例来说
*/		//开始保存数据
		//将第一个数据记录下来，进行对比，保存series的data
		
		if(results.size() <= 0)
			return new JSONObject();
		//以xAxis为基础
		//String types = (String) results.get(0).toMap().get(attrsMap.get("xAxis"));
		String keyStand = this.getTypeData(results.get(0).toMap(), attrsMap);
		    JSONArray jsonArray = new JSONArray();
			JSONArray lengthData = new JSONArray();
			List list = new ArrayList();
			for( int i = 0 ; i < results.size(); i++ ){
				TSaleOrder saleOrder = results.get(i);
				Map itemMap = saleOrder.toMap();
				String key = this.getTypeData(itemMap, attrsMap);
				if( !keyStand.equals(key)){
					JSONObject json = new JSONObject();
			//	json.put("data", ); 
					//json的处理
					
					json.put("data", this.getSeries(list, xAxisList, attrsMap));
					json.put("name", keyStand);
					json.put("type", "bar");
					jsonArray.add(json);
					lengthData.add(keyStand);
					//map.put( prodDept,  this.getSerieData(datas,year) );
					list.clear();
					keyStand = key;
				}
				//这里需要if
				list.add(itemMap);
			}
			if( !lengthData.contains(keyStand) ){
				JSONObject json = new JSONObject();
				json.put("data", this.getSeries(list, xAxisList, attrsMap));
				json.put("name", keyStand);
				json.put("type", "bar");
				jsonArray.add(json);
				lengthData.add(keyStand);
			}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("series", jsonArray);
			jsonObj.put("legend", "{data : " + lengthData + ",y:'top'}");
			return jsonObj;
	}
	/**
	 * 得到series的data 的key值
	 * @param map
	 * @param attrsMap
	 * @return
	 */
	private String getTypeData(Map map,Map attrsMap){
		String key = "";
		if(attrsMap.get("groupBy1") != null){
			key += "-" + map.get(attrsMap.get("groupBy1")) ;
		}
		if(attrsMap.get("groupBy2") != null ){
			key += "-" + map.get(attrsMap.get("groupBy2")) ;		
		}
		if(attrsMap.get("groupBy3") != null ){
			key += "-" + map.get(attrsMap.get("groupBy3")) ;
		}
		
		if(key.equals("")){
			key = (String) map.get(attrsMap.get("xAxis"));
		}else{
			key = key.substring(1);
		}
		return key;
	}
	/**
	 *获取页面表单设置的数据值
	 * @param request
	 * @return
	 */
	private Map getAttributes(HttpServletRequest request){
		Map map =  new HashMap();
		if(!StringUtils.isBlank(request.getParameter("xAxisData"))){
			map.put("xAxis", request.getParameter("xAxisData"));
		} 
		if(!StringUtils.isBlank(request.getParameter("yAxisLeft"))){
			map.put("yAxisLeft", request.getParameter("yAxisLeft"));
		} 
		if(!StringUtils.isBlank(request.getParameter("startSignDate"))){
			map.put("startSignDate", request.getParameter("startSignDate"));
		} 
		if(!StringUtils.isBlank(request.getParameter("endSignDate"))){
			map.put("endSignDate", request.getParameter("endSignDate"));
		}
		if(!StringUtils.isBlank(request.getParameter("companyCode"))){
			map.put("companyCode", request.getParameter("companyCode"));
		}
		if(!StringUtils.isBlank(request.getParameter("settleUserEname"))){
			map.put("settleUserEname", request.getParameter("settleUserEname"));
		}
		if(!StringUtils.isBlank(request.getParameter("prodCode"))){
			map.put("prodCode", request.getParameter("prodCode"));
		}
		if(!StringUtils.isBlank(request.getParameter("shopSign"))){
			map.put("shopSign", request.getParameter("shopSign"));
		}
		if(!StringUtils.isBlank(request.getParameter("prodDept"))){
			map.put("prodDept", request.getParameter("prodDept"));
		}
		if(!StringUtils.isBlank(request.getParameter("groupBy1"))){
			map.put("groupBy1", request.getParameter("groupBy1"));
		}
		if(!StringUtils.isBlank(request.getParameter("groupBy2"))){
			map.put("groupBy2", request.getParameter("groupBy2"));
		}
		if(!StringUtils.isBlank(request.getParameter("groupBy3"))){
			map.put("groupBy3", request.getParameter("groupBy3"));
		}
		return map;
	}
	/**
	 * 得到series的Data的最终值
	 * @param data
	 * @param xAxisList
	 * @param attrsMap
	 * @return
	 */
	private List getSeries(List data,List xAxisList,Map attrsMap){
		
		List list = new ArrayList();
		for(int j = 0 ; j< xAxisList.size() ; j++){
			String xAxis = (String) xAxisList.get(j);
			int flag = -1;//为了没有数据的值
			for(int i = 0 ; i < data.size(); i ++){
				Map map = (Map) data.get(i);
				if( map.get(attrsMap.get("xAxis")) != null && xAxis.equals(map.get(attrsMap.get("xAxis")))){
					list.add(map.get(attrsMap.get("yAxisLeft")));
					flag = 0 ;
					break;
				}
			}
			if(flag == -1){
				list.add(0);
			}
			flag = -1;
		}
		return list;
	}
	/**
	 * 处理日期
	 * @param signDate sql 的参数值
	 * @param worth 页面上的参数
	 * @return
	 */
	private String changSignDate(String signDate,String worth){
		if(!worth.equals("signDate")){
			return signDate;
		}
		return "substring(" + signDate+ ", 6 , 2)";
	}
}











