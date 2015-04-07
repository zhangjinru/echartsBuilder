package com.echartsBuilder.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.echartsBuilder.common.OptionBean;
import com.echartsBuilder.common.XmlToJson;

/**
 * Servlet implementation class AddPieChartServlet
 */
public class AddPieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPieChartServlet() {
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
		/*OptionBean option = new OptionBean();
		XmlToJson xmlToJson = new XmlToJson();
		JSONObject json = xmlToJson.convertXmlToJson("legend","src/test.xml");
		option.setContentJson(json);
		option.showContent("legend");
		request.setAttribute("legendHtml", option.getShowHtml());
		
		JSONObject titleJson = xmlToJson.convertXmlToJson("title","src/test.xml");
		option.setContentJson(titleJson);
		option.showContent("title");
		request.setAttribute("titleHtml", option.getShowHtml());
		
		JSONObject seriesJson = xmlToJson.convertXmlToJson("series","src/pieChart.xml");
		option.setContentJson(seriesJson);
		option.showContent("series");
		request.setAttribute("seriesHtml", option.getShowHtml());
		
		JSONObject toolboxJson = xmlToJson.convertXmlToJson("toolbox","src/test.xml");
		option.setContentJson(toolboxJson);
		option.showContent("toolbox");
		request.setAttribute("toolboxHtml", option.getShowHtml());
		
		JSONObject tooltipJson = xmlToJson.convertXmlToJson("tooltip","src/test.xml");
		option.setContentJson(tooltipJson);
		option.showContent("tooltip");
		request.setAttribute("tooltipHtml", option.getShowHtml());*/
		request.setAttribute("legendHtml",this.getHtml("legend","src/test.xml"));
		request.setAttribute("titleHtml", this.getHtml("title","src/test.xml"));
		request.setAttribute("seriesHtml", this.getHtml("series","src/pieChart.xml"));
		request.setAttribute("toolboxHtml", this.getHtml("toolbox","src/test.xml"));
		request.setAttribute("tooltipHtml", this.getHtml("tooltip","src/test.xml"));
		request.getRequestDispatcher("web/pieChart.jsp").forward(request, response);
	}
	private StringBuilder getHtml(String name ,String src){
		OptionBean option = new OptionBean();
		XmlToJson xmlToJson = new XmlToJson();
		JSONObject tooltipJson = xmlToJson.convertXmlToJson(name,src);
		option.setContentJson(tooltipJson);
		option.showContent(name);
		return option.getShowHtml();
	}
}
