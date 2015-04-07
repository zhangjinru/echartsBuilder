<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="./js/echarts.js"></script>
 <script type="text/javascript" src="./js/jquery-1.7.2.js"></script>
  <script type="text/javascript" src="saleOrderTest.js"></script>
<title>Insert title here</title>
</head>
<body>
<center><p style="font-size: 32px;font-style: normal;font-weight: bold">数据修改</p></center>
<div id="queryDiv" style= "width:1000px;height:200px;margin-left:auto;margin-right:auto;">
    <form action="./saleOrderTestServlet" method="post">
  
   <fieldset>
    <legend>定义轴数据</legend>
  <table cellpadding="10">
  	<tr >
  		<td width="150px">x轴：<select id="xAxisData" style="margin-left: 5px" name="xAxisData">
  		<option value="signDate" label="日期-月"></option>
  		<option value="companyCode" label="公司"></option>
  		<option value="settleUserEname" label="用户名称"></option>
  		<option value="prodCode" label="品种"></option>
  		<option value="shopSign" label="牌号"></option>
  		<option value="prodDept" label="销售部门"></option>
  		</select>
  		</td>
  		<td width="150px"> y轴-左：<select id="yAxisLeft" style="margin-left: 5px" name="yAxisLeft">
  		<option value="orderWt" label="重量"></option>
  		<option value="wtPrice" label="单价"></option>
  		<option value="totalPrice" label="总价"></option>
  		</select>
  		</td>
  		<td width="150px">y轴-右：<select id="yAxisRight" style="margin-left: 5px" name="yAxisRight">
  		<option value="orderWt" label="重量"></option>
  		<option value="wtPrice" label="单价"></option>
  		<option value="totalPrice" label="总价"></option>
  		</select>
  		</td>
  	</tr>
  </table>
</fieldset>
  <fieldset>
    <legend>数据值</legend>
  <table  cellpadding="10">
  	<tr >
  		<td width="400px">日期：<input id ="startSignDate"  name ="startSignDate" type = 'date'>-&nbsp;<input id ="endSignDate" name ="endSignDate" type = 'date'>
  		</td>
  		<td width="300px">公司： <input id ="companyCode" name ="companyCode">
  		</td>
  		<td width="300px">用户名称：<input id ="settleUserEname" name ="settleUserEname">
  		</td>
  	</tr>
  	<tr >
  		<td >品种：<input id ="prodCode" name="prodCode">
  		</td>
  		<td >牌号： <input id ="shopSign" name ="shopSign">
  		</td>
  		<td >销售部门：<input id ="prodDept" name ="prodDept">
  		</td>
  	</tr>
  </table>
</fieldset>
<fieldset>
    <legend>分组类别</legend>
    <table  cellpadding="10">
  	<tr >
  		<td width="400px">
  		  类别1：
  		  <select id = "groupBy1" name = "groupBy1" >
  	   <option value="" label="--请选择--"></option>
  		<option value="signDate" label="日期"></option>
  		<option value="companyCode" label="公司"></option>
  		<option value="settleUserEname" label="用户名称"></option>
  		<option value="prodCode" label="品种"></option>
  		<option value="shopSign" label="牌号"></option>
  		<option value="prodDept" label="销售部门"></option>
  		</select>
  		</td>
  		<td width="300px">
  		  类别2：
  		 <select id = "groupBy2" name = "groupBy2">
  		  <option value="" label="--请选择--"></option>
  		<option value="signDate" label="日期"></option>
  		<option value="companyCode" label="公司"></option>
  		<option value="settleUserEname" label="用户名称"></option>
  		<option value="prodCode" label="品种"></option>
  		<option value="shopSign" label="牌号"></option>
  		<option value="prodDept" label="销售部门"></option>
  		</select>
  		</td>
  		<td width="300px">
  		  类别3：
  		    <select id = "groupBy3" name = "groupBy3">
  		     <option value="" label="--请选择--"></option>
  		<option value="signDate" label="日期"></option>
  		<option value="companyCode" label="公司"></option>
  		<option value="settleUserEname" label="用户名称"></option>
  		<option value="prodCode" label="品种"></option>
  		<option value="shopSign" label="牌号"></option>
  		<option value="prodDept" label="销售部门"></option>
  		</select>
  		</td>
  	</tr>
   </table>
</fieldset>
<input type="submit" value="确定" id='change' style='float:right'/>
    </form>
</div>
<div id = 'show' style='width:1000px;height:500px;margin-left: auto;margin-right:auto'></div>
<input type = "hidden" value=<%=request.getAttribute("jsonData") %> id = "jsonData"/>
</body>
</html>
