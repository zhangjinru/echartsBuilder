<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="./js/echarts.js"></script>
 <script type="text/javascript" src="./js/jquery-1.7.2.js"></script>
  <script type="text/javascript" src="./web/test.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id = 'show' style='width:100%;height:500px;'></div>
<input type="button" value="返回" id='abc' style='float:right'/>
<input type = "hidden" value=<%=request.getAttribute("jsonData") %> id = "jsonData"/>
<%-- <!-- <input type="range" id="range" min="10" max="50" step="1" value="" onchange="document.getElementById('result').value=document.getElementById('range').value + 'px'"/>
  <output id="result" value='0' >px  </output> -->
   <input type="button" value="图例" id="legand"/>
   <input type="button" value="标题" id= 'title'/>
    <div id ='mainDiv'>
	  	<div id = 'legandDiv' style = "float:left;width:30%;height:500px;border:1px solid #FF82AB">
	  	<%=request.getAttribute("showHtml") %>
	  	</div>
	     <div id = 'titleDiv' style = "display:none;float:left;width:30%;height:500px;border:1px solid #7CFC00">
	  	</div>
	  	<div id = 'rightDiv' style = "float:left;width:68%;height:500px;border:1px solid #8DEEEE">
	  	</div>
  </div> --%>
  <!-- <input id= 'fileChoose' type='file' accept='.xls,.xlsx'/>
  <input type='file' accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
   <input type='file' accept=" "/>
  
   <input type="button" value="修改数据"/> -->
   
</body>
</html>
