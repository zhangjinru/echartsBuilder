package com.echartsBuilder.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.echartsBuilder.common.ChartChangeData;
import com.echartsBuilder.common.IChartChangeData;

/**
 * Servlet implementation class PieChangeServlet
 */
public class PieChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieChangeServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		try {
			String file = request.getParameter("fileName");
			InputStream ins = new FileInputStream("D:\\" + file);
			IChartChangeData changeData = new ChartChangeData();
			List<Map> list =  changeData.getData(ins);
			if(list.size() > 0){
				JSONObject jobj = new JSONObject();
				Map map = list.get(0);
				int rowNum = (Integer) map.get("rowNum");
				int columnNum = (Integer) map.get("columnNum");
				//获得数据的名称
				String columnname = (String) map.get("01");
				jobj.put("name", columnname);
				String str = "";
				for(int i = 1 ; i < rowNum; i ++){
					String name =  (String) map.get(i + "" + 0);
					double value = (Double) map.get(i + "" + 1);
					str += "{\"name\" : \"" + name + "\",\"value\" :" + value + " },";
				}
				jobj.put("data", "[" + str +"]");
				List legengData = new ArrayList();
				for(int j =  1; j < rowNum ; j++){
					legengData.add(map.get(j + "" + 0));
				}
				JSONArray jsonArray = new JSONArray();
				jsonArray.add(jobj);
				PrintWriter pw = response.getWriter();
				JSONObject jo = new JSONObject();
				jo.put("series", jsonArray);
				jo.put("legend","{data:" + JSONArray.fromObject(legengData) + "}");
				pw.write(jo.toString());
				pw.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
