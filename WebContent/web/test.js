$(function(){
	//$("#testDiv").html("<table><tr><td><input id = 'abd' style = 'border: 1px solid'/></td><tr></table>");
	$('input[type=button]').click( function (){
		 var a = myChart.getOption();
			a.legend.data = ['华北','华西','华南','华东'];
			a.series[0].data  = [ {value:409, name:'华北'},
			   		                {value:324, name:'华西'},
			   		                {value:885, name:'华南'},
			   		                {value:552, name:'华东'},];
			a.title.text =  '销售额';
			//a.series.push(option);
			
			//myChart.setOption();
			myChart.clear();
			myChart.setOption(a);
		
		
		
	});
});

/*function getPath(obj)    
{    
  if(obj)    
    {    
   
        if (window.navigator.userAgent.indexOf("MSIE")>=1)    
        {    
            obj.select();    
            return document.selection.createRange().text;    
        }   
        else if(window.navigator.userAgent.indexOf("Firefox")>=1)    
        {    
        	if(obj.files)    
        	{    
   
        		return obj.files.item(0).getAsDataURL();    
        	}    
        	return obj.value;    
        }  
        else if(window.navigator.userAgent.indexOf("Mozilla")>=1)    
        {    
        	if(obj.files)    
        	{    
        		
        		return obj.files.item(0).getAsDataURL();    
        	}    
        	return obj.value;    
        } 
        return obj.value;    
   	}    
}  */




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
   		        subtext: '测试',
   		        x:'center'
   		    },
   		    tooltip : {
   		        trigger: 'item',
   		        formatter: "{a} <br/>{b} : {c} ({d}%)"
   		    },
   		    legend: {
   		        orient : 'vertical',
   		        x : 'left',
   		        data:['华北','华西','华南','华东']
   		    },
   		    toolbox: {
   		        show : true,
   		        feature : {
   		            mark : {show: true},
   		            dataView : {show: true, readOnly: false},
   		            magicType : {
   		                show: true, 
   		                type: ['pie', 'funnel'],
   		                option: {
   		                    funnel: {
   		                        x: '25%',
   		                        width: '50%',
   		                        funnelAlign: 'left',
   		                        max: 1548
   		                    }
   		                }
   		            },
   		            restore : {show: true},
   		            saveAsImage : {show: true}
   		        }
   		    },

   		    series : [
   		        {
   		            name:'销售额',
   		            type:'pie',
   		            radius : '55%',
   		         itemStyle:{normal:{labelLine:{show:true}}},
   		          selectedMode :'multiple',
   		            center: ['50%', '60%'],
   		            data:[
   		                {value:409, name:'华北'},
   		                {value:324, name:'华西'},
   		                {value:885, name:'华南'},
   		                {value:552, name:'华东'},
   		            ]
   		        }
   		    ]
   		};
    	var ecConfig = require('echarts/config');
    	myChart.on(ecConfig.EVENT.CLICK, eConsole);                  
        // 为echarts对象加载数据 
        myChart.setOption(option); 
    }
);
var i = 0;
function abc(){
	  /* if(i == 0){
		   myChart.setOption({series:[{symbol:'heart'}]}); 
	   }
	   else{
		   myChart.setOption({series:[{symbol:' rectangle '}]}); 
	   }
		
		i = i == 0 ? 1:0;*/
	 myChart.setOption({series:[{itemStyle:{normal:{labelLine:{show:false}}}}]}); 
};

var eConsole = function(param){
	/*option = [{value:335, name:'公司a'},{value:310, name:'邮件营销'},{value:234, name:'联盟广告'}];
	var json = JSON.stringify(myChart.getOption());
	var series = myChart.getOption().series;
	var a = myChart.getOption();
	a.series[0].data = option;
	//a.series.push(option);
	
	//myChart.setOption();
	myChart.clear();
	myChart.setOption(a);*/
	dealData(param.name);	
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











