var myChart = echarts.init(document.getElementById('chart_map'));
//存储切换的每一级地图信息
var mapStack = [];
var curMap = {};
//初始化地图
loadMap('430700', '常德市');
/**
   绑定用户切换地图区域事件
   cityMap对象存储着地图区域名称和区域的信息(name-code)
   当mapCode有值说明可以切换到下级地图
   此时保存上级地图的编号和名称
*/
myChart.on('mapselectchanged', function(params) {
	var name = params.batch[0].name;
	var mapCode = cityMap[name];
	if(!mapCode) {
		alert('无此区域地图显示');
		return;
	}
	loadMap(mapCode, name);
	//将上一级地图信息压入mapStack
	mapStack.push({
		mapCode: curMap.mapCode,
		mapName: curMap.mapName
	});
});
/**
   绑定右键事件
*/
myChart.on('contextmenu', function(params) {
	$('#contextMenu').css({
		left: params.event.offsetX,
		top: params.event.offsetY
	}).show();
});
/**
   响应图表的右键事件，返回上一级地图
*/
$('#contextMenu').on('click', function() {
	$(this).hide();
	//获取上一级地图信息
	var map = mapStack.pop();
	if(!mapStack.length && !map) {
		alert('已经到达最上一级地图了');
		return;
	}
	loadMap(map.mapCode, map.mapName);
});
/**
   加载地图：根据地图所在省市的行政编号，
   获取对应的json地图数据，然后向echarts注册该区域的地图
   最后加载地图信息
   @params {String} mapCode:地图行政编号,for example['中国':'100000', '湖南': '430000']
   @params {String} mapName: 地图名称
*/
var option={}
function loadMap(mapCode, mapName) {
	$.getJSON('./js/china-main-city/' + mapCode + '.geoJson', function(data) {
		if(data) {
			echarts.registerMap(mapName, data);
			option = {
				tooltip: {
					padding: 0,
					enterable: true,
					transitionDuration: 1,
					textStyle: {
						color: '#000',
						decoration: 'none',
					},
					// position: function (point, params, dom, rect, size) {
					//   return [point[0], point[1]];
					// },
					formatter: function(params) {
//						console.log(params)
						var tipHtml = '';
						tipHtml = `<div style="width:136px;height:70px;background:linear-gradient(0deg,rgba(5,137,255,0.5),rgba(70,249,225,0.5));padding:8px;border-radius:2px;">
			<p style="font-size:14px;font-family:MicrosoftYaHeiUI;font-weight:400;color:rgba(255,255,255,1);margin: 0px;">`+params.name+`</p>
			<div style="display: flex;">
				<div style="flex-grow: 1;">
					<p style="font-size:22px;font-family:Bahnschrift-Regular;font-weight:400;color:rgba(70,249,225,1);text-align: center;margin: 0px;">`+params.data.index+`</p>
					<p style=" font-size:12px;font-family:MicrosoftYaHeiUI;font-weight:400;color:rgba(255,255,255,1);text-align: center;margin: 0px;">排行</p>
				</div>
				<div style="flex-grow: 1;">
					<p style="font-size:22px;font-family:Bahnschrift-Regular;font-weight:400;color:rgba(255,155,37,1);;text-align: center;margin: 0px;">`+params.data.score+`</p>
					<p style=" font-size:12px;font-family:MicrosoftYaHeiUI;font-weight:400;color:rgba(255,255,255,1);text-align: center;margin: 0px;">得分</p>
				</div>
			</div>
		</div>`
						return tipHtml;
					}
				},
				series: [{
					zoom: 1.1,
					name: '',
					type: 'map',
					mapType: mapName,
					selectedMode: 'multiple',
					label: {
						normal: {
							show: true
						},
						emphasis: {
							show: true
						}
					},
					itemStyle: {
						normal: {
							areaColor: '#132F73',
							borderColor: '#7DA6E9',
							borderWidth: 2,
							shadowColor: '#7DA6E9',
							shadowBlur: 2,
							color: '#C1D1EC',
							label: {
								show: true,
								textStyle: {
									color: "rgb(230, 230, 230)"
								}
							}
						},
						emphasis: {
							areaColor: '#0D2665',
							label: {
								show: true,
								textStyle: {
									color: "rgb(249, 249, 249)"
								}
							}
						}
					},
					data: [{
							name: '石门县',
							index: 1,
							score: 160
						},
						{
							name: '澧县',
							index: 2,
							score: 160
						},
						{
							name: '临澧县',
							index: 3,
							score: 160
						},
						{
							name: '津市市',
							index: 4,
							score: 160
						},
						{
							name: '桃源县',
							index: 5,
							score: 160
						},
						{
							name: '鼎城区',
							index: 6,
							score: 160
						},
						{
							name: '武陵区',
							index: 7,
							score: 160
						},
						{
							name: '汉寿县',
							index: 8,
							score: 160
						},
						{
							name: '安乡县',
							index: 9,
							score: 160
						}
					]
				}]
			};
dataLength = option.series[0].data.length;
			myChart.setOption(option);
			//存储当前地图的信息
			curMap = {
				mapCode: mapCode,
				mapName: mapName
			};
		} else {
			alert('无法加载该地图');
		}
	});
}

var count = 0;
var timeTicket = null;
var dataLength =0;
timeTicket && clearInterval(timeTicket);
timeTicket = setInterval(function() {
    myChart.dispatchAction({
        type: 'downplay',
        seriesIndex: 0,
    });
    myChart.dispatchAction({
        type: 'highlight',
        seriesIndex: 0,
        dataIndex: (count) % dataLength
    });
    myChart.dispatchAction({
        type: 'showTip',
        seriesIndex: 0,
        dataIndex: (count) % dataLength
    });
    count++;
}, 1500);

myChart.on('mouseover', function(params) {
    console.log(params)
    clearInterval(timeTicket);
    myChart.dispatchAction({
        type: 'downplay',
        seriesIndex: 0
    });
    myChart.dispatchAction({
        type: 'highlight',
        seriesIndex: 0,
        dataIndex: params.dataIndex
    });
    myChart.dispatchAction({
        type: 'showTip',
        seriesIndex: 0,
        dataIndex: params.dataIndex,
    });
});
myChart.on('mouseout', function(params) {
    timeTicket && clearInterval(timeTicket);
    timeTicket = setInterval(function() {
        myChart.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
        });
        myChart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: (count) % dataLength
        });
        myChart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: (count) % dataLength
        });
        count++;
    }, 1500);
});