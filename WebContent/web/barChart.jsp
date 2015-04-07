<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./js/jquery-1.7.2.js"></script>
  <script type="text/javascript" src="./js/echarts.js"></script>
  <script type="text/javascript" src="./web/barChart.js"></script>
<title>创建柱图</title>
</head>
<body>
	<%-- <div id = 'showDiv'>
	<%=request.getAttribute("showHtml") %>
	</div> --%>
	<input type="button" value="图例" id="legand"/>
    <input type="button" value="标题" id= 'title'/>
    <input type="button" value="x轴" id= 'xAxis'/>
     <input type="button" value="y轴" id= 'yAxis'/>
     <input type="button" value="工具" id= 'toolbox'/>
     <input type="button" value="数据项" id= 'series'/>
     <input type="button" value="提示框" id= 'tooltip'/>
    <div id ='mainDiv'>
	  	<div id = 'legandDiv' style = "float:left;width:30%;height:500px;border:1px solid #FF82AB;overflow:scroll">
	  	<%=request.getAttribute("legendHtml") %>
	  	</div>
	     <div id = 'titleDiv' style = "display:none;float:left;width:30%;height:500px;border:1px solid #7CFC00;overflow:scroll">
	  		<%=request.getAttribute("titleHtml") %>
	  	</div>
	  	 <div id = 'xAxisDiv' style = "display:none;float:left;width:30%;height:500px;border:1px solid #7CFC00;overflow:scroll">
	  		<%=request.getAttribute("xAxisHtml") %>
	  	</div>
	  	<div id = 'yAxisDiv' style = "display:none;float:left;width:30%;height:500px;border:1px solid #7CFC00;overflow:scroll">
	  		<%=request.getAttribute("yAxisHtml") %>
	  	</div>
	  	<div id = 'toolboxDiv' style = "display:none;float:left;width:30%;height:500px;border:1px solid #7CFC00;overflow:scroll">
	  		<%=request.getAttribute("toolboxHtml") %>
	  	</div>
	  	<div id = 'seriesDiv' style = "display:none;float:left;width:30%;height:500px;border:1px solid #7CFC00;overflow:scroll">
	  		<%=request.getAttribute("seriesHtml") %>
	  	</div>
	  	 <div id = 'tooltipDiv' style = "display:none;float:left;width:30%;height:500px;border:1px solid #7CFC00;overflow:scroll">
	  		<%=request.getAttribute("tooltipHtml") %>
	  	</div>
	  	<div id = 'rightDiv' style = "float:left;width:68%;height:500px;border:1px solid #8DEEEE">
	  	<div id = 'show' style='width:100%;height:100%'></div>
	  	</div>
	  	<input id= 'fileChoose' type='file' accept='.xls,.xlsx'/><input type = 'button' value="数据修改" id= 'change'/><br/>
	  	
  </div>
</body>
</html>