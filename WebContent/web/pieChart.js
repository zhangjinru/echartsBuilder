$(function(){
	//$("#showDiv").html($('#showHtml').val());
	$('input[type=button]').click( function (){
		var clickId = this.id;
		$.each( $('input[type=button]'), function(i, n){
			//  alert( "Name: " + i + ", Value: " + n );
			if(clickId == n.id){
				$("#" + clickId + "Div").show();
				this.disabled = true;
			}else{
				this.disabled = false;
				$("#" + n.id + "Div").hide();
			}
		});
		
	});
	$('input').change(function(){
		if(this.id.indexOf('[') >= 0){
			changeArrayData(this.id,this.value);
		}else{
			changMyChart(this.id,this.value);
		}
		
	});
	$('select').change(function(){
		if(this.id.indexOf('[') >= 0){
			changeArrayData(this.id,this.value);
		}else{
			changMyChart(this.id,this.value);
		}
		
	});
	$("#change").click( function (){
		//alert($('#fileChoose').val());
		$.ajax({
			  url:"pieChangeServlet",
			  type:"post",
			  dataType:"json",
			  //参数
			  data:{
				  fileName : $('#fileChoose')[0].files[0].name
			  },
			  success:function(data){
				 //alert(data);
				 myChart.setOption(data); 
				 $("#legand").click();
			   },
			   error:function(a,b){
				   alert(b);
			   }
			});
	});
});


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
   		        text: '某站点用户访问来源',
   		        subtext: '纯属虚构',
   		        x:'center'
   		    },
   		    tooltip : {
   		        trigger: 'item',
   		        formatter: "{a} <br/>{b} : {c} ({d}%)"
   		    },
   		    legend: {
   		        orient : 'vertical',
   		        x : 'left',
   		        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
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
   		            name:'访问来源',
   		            type:'pie',
   		            radius : '55%',
   		            center: ['40%', '60%'],
   		        /* itemStyle: { normal: {
   		          label: {
   		              show: false
   		          },
   		          labelLine: {
   		              show: false
   		          },
   		      } }
   		         , */  data:[
   		                {value:335, name:'直接访问'},
   		                {value:310, name:'邮件营销'},
   		                {value:234, name:'联盟广告'},
   		                {value:135, name:'视频广告'},
   		                {value:1548, name:'搜索引擎'}
   		            ]
   		        }
   		    ]
   		};
   		                    
        
        // 为echarts对象加载数据 
        myChart.setOption(option); 
    }
);
var changMyChart = function(id,value){
	var items = id.split('-');
   // var myChart2 = require('echarts').init(document.getElementById('main'));
	var itemStart = '';
	var itemEnd = '';
	var type = items[0];
	var startNum = 0;
	for (var i = startNum ; i < items.length; i ++){
		itemStart += "{" + items[i] + ":";
		itemEnd = "}" +itemEnd;
	}
	if((value == 'false' || value =='true' || !isNaN(value) || value.indexOf('[') >= 0) && value.trim() != '' ){
		myChart.setOption(eval('(' + itemStart  + value + itemEnd + ')')); 
	}else{
		myChart.setOption(eval('(' + itemStart +  "'" + value + "'" + itemEnd + ')')); 
	}
	
};
var changeArrayData = function(id,value){
	var items = id.split('[');
   // var myChart2 = require('echarts').init(document.getElementById('main'));
	var itemStart = '';
	var itemEnd = '';
	var type = items[0];
	if(type != null && type.trim() != ''){
		itemStart += "{" + type + ":[";
		itemEnd = ']}';
	}
	var itemValue = items[1].split("-");
	for (var i = 0; i < itemValue.length; i ++){
		itemStart += "{" + itemValue[i] + ":";
		itemEnd = "}" +itemEnd;
	}
	if((value == 'false' || value =='true' || !isNaN(value) || value.indexOf('[') >= 0) && value.trim() != ''){
		myChart.setOption(eval('(' + itemStart  + value + itemEnd + ')')); 
	}else{
		myChart.setOption(eval('(' + itemStart +  "'" + value + "'" + itemEnd + ')')); 
	}
	
};
