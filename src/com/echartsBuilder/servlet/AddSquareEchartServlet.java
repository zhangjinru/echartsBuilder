package com.echartsBuilder.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.echartsBuilder.common.Legend;
import com.echartsBuilder.common.OptionBean;
import com.echartsBuilder.common.XmlToJson;

/**
 * Servlet implementation class AddEchartsServlet
 */
public class AddSquareEchartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSquareEchartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*response.setContentType("text/html;charset=utf-8");
		OptionBean option = new OptionBean();
		String context = "{legend:{show:'legend-show,是否显示,radio=true-false',orient:'legend-orient,布局方式,select=horizontal-vertical',x:'legend-x,水平位置,range=0-2000-1',y:'legend-y,垂直位置,range=0-2000-1',backgroundColor:'legend-backgroundColor,背景颜色,color=-',borderColor:'legend-borderColor,边框颜色,color=-',borderWidth:'legend-borderWidth,边框宽度,range=0-10-1',itemGap:'legend-itemGap,每个图例的距离,range=5-20-1',itemWidth:'legend-itemWidth,图形宽度,range=10-50-1',itemHeight:'legend-itemHeight,图形高度,range=10-50-1',selectedMode:'legend-selectedMode,开关模式,select=single-multiple'}}";
		String titleContext = "{  title: {    show: 'title-show,是否显示,radio=true-false',    text: 'title-text,标题名称,text=-',    link: 'title-link,超链接,text=-',    target: 'title-target,链接打开位置,select=self-blank',    subtext: 'title-subtext,副标题名称,text=-',sublink: 'title-sublink,副超链接,text=-',    subtarget: 'title-subtarget,副链接打开位置,select=self-blank',    x: 'title-x,水平位置,range=0-2000-1',    y: 'title-y,垂直位置,range=0-2000-1',    textAlign: 'title-textAlign,水平对齐方式,select=left-right-center',    backgroundColor: 'title-backgroundColor,背景颜色,color=-',    borderColor: 'title-borderColor,边框颜色,color=-',    borderWidth: 'title-borderWidth,边框宽度,range=0-10-1',    itemGap: 'title-itemGap,每个图例的距离,range=5-20-1' ,"
		                  + "textStyle-color: 'title-textStyle-color,标题字体颜色,color=-',  textStyle-fontFamily: 'title-textStyle-fontFamily,标题字体,select=Arial-Verdana',textStyle-fontSize: 'title-textStyle-fontSize,标题字体字号,range=8-50-1', textStyle-fontStyle: 'title-textStyle-fontStyle,标题字体样式,select=normal-italic-oblique',    textStyle-fontWeight: 'title-textStyle-fontWeight,标题字体粗细,select=normal-bold-bolder-lighter',    subtextStyle-color: 'title-subtextStyle-color,副标题字体颜色,color=-',    subtextStyle-fontFamily: 'title-subtextStyle-fontFamily,副标题字体,select=Arial-Verdana',    subtextStyle-fontSize: 'title-subtextStyle-fontSize,副标题字体字号,range=8-50-1',    subtextStyle-fontStyle: 'title-subtextStyle-fontStyle,副标题字体样式,select=normal-italic-oblique',    subtextStyle-fontWeight: 'title-subtextStyle-fontWeight,副标题字体粗细,select=normal-bold-bolder-lighter'  }}";
		String xAsixContext = "{ xAxis: {show: 'xAxis[show,是否显示,radio=true-false',position:'xAxis[position,位置,select=bottom-top', "
		+ "name: 'xAxis[name,坐标轴名称,text=-', nameTextStyle: 'xAxis[nameTextStyle,坐标轴位置,select=start-end',  nameTextStyle-fontFamily: 'xAxis[nameTextStyle-fontFamily,名称字体,select=Arial-Verdana', nameTextStyle-fontSize: 'xAxis[nameTextStyle-fontSize,名称字体字号,range=8-50-1',    nameTextStyle-fontStyle: 'xAxis[nameTextStyle-fontStyle,名称字体样式,select=normal-italic-oblique',    nameTextStyle-fontWeight: 'xAxis[nameTextStyle-fontWeight,名称字体粗细,select=normal-bold-bolder-lighter',    boundaryGap: 'xAxis[boundaryGap,是否留空白,radio=true-false' }}";
		option.setContentJson(JSONObject.fromObject(context));
		option.showContent("legend");
		request.setAttribute("legendHtml", option.getShowHtml());
		option.setContentJson(JSONObject.fromObject(titleContext));
		option.showContent("title");
		request.setAttribute("titleHtml", option.getShowHtml());
		option.setContentJson(JSONObject.fromObject(xAsixContext));
		option.showContent("xAxis");
		request.setAttribute("xAxisHtml", option.getShowHtml());
		request.getRequestDispatcher("web/show.jsp").forward(request, response);*/
		response.setContentType("text/html;charset=utf-8");
		OptionBean option = new OptionBean();
		XmlToJson xmlToJson = new XmlToJson();
		JSONObject json = xmlToJson.convertXmlToJson("legend","src/test.xml");
		option.setContentJson(json);
		option.showContent("legend");
		request.setAttribute("legendHtml", option.getShowHtml());
		
		JSONObject titleJson = xmlToJson.convertXmlToJson("title","src/test.xml");
		option.setContentJson(titleJson);
		option.showContent("title");
		request.setAttribute("titleHtml", option.getShowHtml());
		
		JSONObject xAxisJson = xmlToJson.convertXmlToJson("xAxis","src/test.xml");
		option.setContentJson(xAxisJson);
		option.showContent("xAxis");
		request.setAttribute("xAxisHtml", option.getShowHtml());
		
		JSONObject yAxisJson = xmlToJson.convertXmlToJson("yAxis","src/test.xml");
		option.setContentJson(yAxisJson);
		option.showContent("yAxis");
		request.setAttribute("yAxisHtml", option.getShowHtml());
		
		JSONObject toolboxJson = xmlToJson.convertXmlToJson("toolbox","src/test.xml");
		option.setContentJson(toolboxJson);
		option.showContent("toolbox");
		request.setAttribute("toolboxHtml", option.getShowHtml());
		
		JSONObject tooltipJson = xmlToJson.convertXmlToJson("tooltip","src/test.xml");
		option.setContentJson(tooltipJson);
		option.showContent("tooltip");
		request.setAttribute("tooltipHtml", option.getShowHtml());
		
		String type = request.getParameter("type");
		String src = "lineChart";
		if(type.equals("bar")){
			src = "barChart";
		}
		JSONObject seriesJson = xmlToJson.convertXmlToJson("series","src/" + src + ".xml");
		option.setContentJson(seriesJson);
		option.showContent("series");
		request.setAttribute("seriesHtml", option.getShowHtml());
		request.getRequestDispatcher("web/" + src + ".jsp").forward(request, response);
	}

}
