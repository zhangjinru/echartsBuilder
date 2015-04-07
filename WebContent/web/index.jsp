<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建图表</title>
</head>
<body>
    
	<div style='margin-left: auto;margin-right: auto;width:800px;'>
	<center><h1><font face="verdana">选择创建表类型</font></h1></center>
	折线图：</p>
	<a href="../addSquareEchartServlet?type=line">
	<img src="../image/a.png" alt="标准折线图" height="260" width='400' style="border:2px solid gray;"/>
	</a>
	</p>
	饼图：</p>
	<a href="../addPieChartServlet">
	<img src="../image/b.png" alt="标准折线图" height="260" width='400' style="border:2px solid gray;"/>
	</a></p>
	柱状图：</p>
	<a href="../addSquareEchartServlet?type=bar">
	<img src="../image/c.png" alt="标准折线图" height="260" width='400' style="border:2px solid gray;"/>
	</a>
	</div>
</body>
</html>