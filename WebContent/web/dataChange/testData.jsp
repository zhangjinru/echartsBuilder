<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="./js/echarts.js"></script>
 <script type="text/javascript" src="./js/jquery-1.7.2.js"></script>
  <script type="text/javascript" src="./web/dataChange/testData.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id = 'show' style='width:100%;height:500px;'></div>
<input type = "hidden" value=<%=request.getAttribute("jsonData") %> id = "jsonData"/>
</body>
</html>
