//路径配置
require.config({
    paths: {
        echarts: './js'
    }
});
var myChart ;
// 使用
require(
    [
        'echarts',
        'echarts/chart/line',// 使用柱状图就加载bar模块，按需加载
        'echarts/chart/bar',
        'echarts/chart/pie'
    ],
    function (ec) {
    myChart =  ec.init(document.getElementById('show')); 
   	option = {
   		    title : {
   		        text: '销售额',
   		        subtext: '-大冶钢铁的数据',
   		        x:'center'
   		    },
   		   xAxis : [
   		        {
   		        	name: '月份',
   		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
   		        }
   		    ],
   		  yAxis : [
   		        {
   		            type : 'value'
   		        }
   		    ],
   		  tooltip : {
   	        trigger: 'axis'
   	    },
   	    toolbox: {
         show : true,
         orient: 'vertical',
         x: 'right',
         y: 'center',
         feature : {
             mark : {show: true},
             dataView : {show: true, readOnly: false},
             magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
             restore : {show: true},
             saveAsImage : {show: true}
         }
     },
   		};
   	 addGroupJson(option,JSON.parse($("#jsonData").val()));
     myChart.setOption(option); 
    }
);

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
	function addGroupJson(targetJson, packJson){

	    if(targetJson && packJson){

	       for(var p in packJson){

	           targetJson[p] = packJson[p];

	       }

	    }

	};









