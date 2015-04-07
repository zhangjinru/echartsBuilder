package com.echartsBuilder.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.echartsBuilder.common.ChartChangeData;
import com.echartsBuilder.common.IChartChangeData;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
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
     /* String fileName = (String) request.getAttribute("abcc");
      request.getRequestDispatcher("web/test.jsp").forward(request, response);*/
		//String file = request.getParameter("fileName");
  		InputStream ins = new FileInputStream("D:\\special.xlsx");
		IChartChangeData changeData = new ChartChangeData();
		List<Map> list = new ArrayList<Map>();
	 	try {
	    	list =  changeData.getDataMerge(ins);
	    	JSONObject json = new JSONObject();
	    	Map mapData = list.get(0);
	    	int start = 1;//标记合并了多少个单元格
	    	int end = 0;
	    	String value = "";
	    	int columnNum = (Integer)mapData.get("columnNum") -1;
	    	Map map =  new HashMap();
	    	for(int i = 1 ; i <= columnNum ; i ++){
	    		if(mapData.get("0" + i) != null ){
	    			/*json.put(mapData.get("0" + i) , this.getData(mapData,  i, flagWorth));
	    			flagWorth = i;*/
	    		    if(i != 1){
	    		    	json.put(mapData.get("0" + start) , this.getData(mapData,  start,i -1));
	    		    	start = i;
	    		    }
	    		   /* if(i == columnNum){
	    				if(mapData.get("0" + i) != null){
	    					json.put(mapData.get("0" + i) , this.getData(mapData,  i , i));
	    				}
	    			}*/
	    		}
	    	}
	    	if(start ==  columnNum){
	    		//说明最后一个不是合并单元格数据
	    		json.put(mapData.get("0" + start) , this.getData(mapData,  start,start));
	    	}else{
	    		json.put(mapData.get("0" + start) , this.getData(mapData,  start,columnNum));
	    	}
	    	request.setAttribute("jsonData", json);
	    	request.getRequestDispatcher("web/test.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Map getData(Map mapData,int start,int end){
		Map map = new HashMap();
		for(int i = start ; i <= end; i ++){
			map.put(mapData.get("1" + i), mapData.get("2" + i));
		}
		return map;
	}
	private void getData(JSONObject json , Map map ,Map mapData){
		//Set dealData = map.
	}
}
