$(function(){
	alert("happy to learn intellij");
});

//路径配置
require.config({
    paths: {
        echarts: 'js'
    }
});
var myChart ;
// 使用
require(
    [
        'echarts',
        'echarts/chart/line',// 使用柱状图就加载bar模块，按需加载
        'echarts/chart/bar',
    ],
    function (ec) {
   	    myChart =  ec.init(document.getElementById('show')); 
   	   option = {
   			 title:{
   				text : "销售量",
   				subtext:"test",
   				y:'bottom'
   			 },
   		    tooltip : {
   		        trigger: 'axis'
   		    },
   		    toolbox: {
   		        show : true,
   		        y:'bottom',
   		        feature : {
   		            mark : {show: true},
   		            dataView : {show: true, readOnly: false},
   		            magicType: {show: true, type: ['line', 'bar']},
   		            restore : {show: true},
   		            saveAsImage : {show: true}
   		        }
   		    },
   		    yAxis : [
   		        {
   		            type : 'value',
   		        }
   		    ],
   		};
   	var jsonData = $("#jsonData").val();
	if(jsonData != ''){
		 addGroupJson(option,JSON.parse(jsonData));	
	}
    myChart.setOption(option); 
    }
);
function addGroupJson(targetJson, packJson){

    if(targetJson && packJson){

       for(var p in packJson){

           targetJson[p] = packJson[p];

       }
    }

};
//处理数据
var dealData = function(name){
   var jsonData = $('#jsonData').val();
   var jsonObj = eval('(' + jsonData + ')');
   for(var i in jsonObj){
	   if(name == i){
		   //处理value
		   dealObj(jsonObj[i],i);
		   return true;
	   }
   }
   return false;
};
//处理value，，并且设置
var dealObj = function(value,title){
	   var lengthData = [];
	   var seriesData = [];
	   var num = 0;
	   for(var i in value){
		   var seriesJson = {};
		   seriesJson['value'] = value[i] ;
		   seriesJson['name'] = i ;
		   lengthData[num] = i;
		   seriesData[num] = seriesJson;
		   num += 1;
	   }
	   var a = myChart.getOption();
	   a.title.text = title + '-'+  '销售额';
		a.series[0].data = seriesData;
		a.legend.data  = lengthData;
		//a.series.push(option);
		
		//myChart.setOption();
		myChart.clear();
		myChart.setOption(a);
};











