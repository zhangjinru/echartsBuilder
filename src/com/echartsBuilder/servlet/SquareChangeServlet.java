package com.echartsBuilder.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.echartsBuilder.common.ChartChangeData;
import com.echartsBuilder.common.IChartChangeData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SquareChartServlet
 */
public class SquareChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SquareChangeServlet() {
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
		// TODO Auto-generated method stub
				JSONObject jobj = new JSONObject();
				JSONArray jArray = new JSONArray();
				String file = request.getParameter("fileName");
				String ChartType = request.getParameter("type");
				InputStream  ins=new FileInputStream("D:\\" + file); 
				int columnNum = 0;
				int rowNum = 0;
				IChartChangeData changeData = new ChartChangeData();
				try {
					List<Map> list = changeData.getData(ins);
					for(int i = 0 ; i < list.size(); i ++){
						Map  map = list.get(i);
						columnNum = (Integer) map.get("columnNum");
						rowNum = (Integer) map.get("rowNum");
						
						//处理节点数据
						//处理展示的数据series
						for(int j = 1 ; j < columnNum ; j ++){
							Map jsonMap = new HashMap();
							jsonMap.put("name", map.get(0 + "" + j));
							List data = new ArrayList();
							for(int z = 1 ; z < rowNum ; z ++){
								data.add(map.get(z + "" + j));
							}
							jsonMap.put("data", data);
							jsonMap.put("type",ChartType);
							JSONObject obj = new JSONObject();
							obj.putAll(jsonMap);
							jArray.add(obj);
						}
					}
					List<String> xAxisData = new ArrayList<String>();
					//处理xAxis
					Map  map = list.get(0);
					for(int i = 1 ;i < rowNum ; i ++){
						xAxisData.add((String) map.get(i + "" + 0));
					}
					List legengData = new ArrayList();
					for(int j = 1 ; j < columnNum;j++){
						legengData.add(map.get(0 + "" + j));
					}
					response.setContentType("text/html;charset=utf-8");
					jobj.put("series", jArray);
					jobj.put("xAxis", "[{data:" + JSONArray.fromObject(xAxisData)+"}]");
				 	jobj.put("legend", "{data:" + JSONArray.fromObject(legengData) + "}");
					PrintWriter out=response.getWriter();
				    out.write(jobj.toString());
				    out.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	
	}

}
