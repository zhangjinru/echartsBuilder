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
			  url:"squareChangeServlet",
			  type:"post",
			  dataType:"json",
			  //参数
			  data:{
				  fileName : $('#fileChoose')[0].files[0].name,
				  type:'line'
			  },
			  success:function(data){
				/* alert(data);
				 var data1 = {"series":[{"name":"最高气","data":[11,11,15,13,12,13,12]},{"name":"最低气","data":[1,-2,2,5,3,5,3]}]};
			*/	 myChart.setOption(data); 
				 $("#legand").click();
			   },
			   error:function(a,b){
				   alert(b);
			   }
			});
	});
});

var myChart;

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
        var option = {
        	title:{//名称
        		show:false,
        		text:"销量表",
        		subtext:"测试数据",
        	},
            legend: {//图例
                data:['销量','上月销量'],
                y:'bottom',
                x:'center'//位置
            },
            tooltip : {
            	show : true,
            },
            toolbox: {
                show : true,
                feature :{
                	  magicType: {
                          show : true,
                          title : {
                              line : '动态类型切换-折线图',
                              bar : '动态类型切换-柱形图',
                          },
                          type : ['line', 'bar']
                      },
                      saveAsImage : {
                          show : true,
                          title : '保存为图片',
                          type : 'jpeg',
                          lang : ['点击本地保存'] 
                      },
                }
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子",'衣服'],
                    name : "商品名",//坐标轴名
                    nameTextStyle : {"color":"gray","font-size":"20px"},//坐标轴名称的样式
                    boundaryGap:false,
                    splitLine:{
   		            	lineStyle:{
   		            	 color: 'd5d6cb',}},
                    splitArea : {
                        show: true,
                        areaStyle:{
                            color:['rgba(144,238,144,0.3)','rgba(135,200,250,0.3)']
                        }
                    },
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    name : "金额",
                    splitLine:{
   		            	lineStyle:{
   		            	 color: '#d5d6cb',}}
                  /*  axisLabel :{
                    	formatter :function (value){return "￥" + value;}
                    }*/
                    
                }
            ],
            series : [
                {
                	//可以设置自己独特的tooltip
                    "name":"销量",
                    "type":"line",
                    symbol: 'heart',//图标
                    symbolSize: 6,//图标大小
                    symbolRotate: -45,//图标倾斜角度
                    "data":[80, 10, 40,25, 30, 27,37],
                    showAllSymbol : true,
                    smooth : true,//曲线是否圆滑
                },
                {
                    "name":"上月销量",
                    "type":"line",
                    "data":[10, 20, 60,20, 10, 20,67]
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
	if((value == 'false' || value =='true' || !isNaN(value)) && value.trim() != ''){
		myChart.setOption(eval('(' + itemStart  + value + itemEnd + ')')); 
	}else{
		myChart.setOption(eval('(' + itemStart +  "'" + value + "'" + itemEnd + ')')); 
	}
	
};
