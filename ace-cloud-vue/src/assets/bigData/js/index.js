var chartOne = echarts.init(document.getElementById('echart_one'));

var chartTwo = echarts.init(document.getElementById('echart_two'));

var chartThree = echarts.init(document.getElementById('echart_three'));

var chartFour = echarts.init(document.getElementById('echart_four'));

var chartFive = echarts.init(document.getElementById('echart_five'));

// 指定图表的配置项和数据
var option_chartOne = {
	tooltip: {
		trigger: 'item',
		formatter: "{a} <br/>{b}: {c} ({d}%)"
	},
	legend: {
		x: 'right',
		y: 'center',
		data: ['矛盾纠纷', '安全隐患', '特殊人群', ],
		textStyle: {
			fontSize: 12,
			fontWeight: 400,
			color: 'rgba(255,255,255,1)',
		},
		itemHeight: 8,
		itemWidth: 8,
		borderRadius: 0,
		width: '200'

	},
	title: {
		text: '2019',
		left: '9%',
		top: '38%',
		// padding:[24,0],
		textStyle: {
			color: '#F7C86E',
			fontSize: 20,
			align: 'center',
			lineHeight: 10,
		},
		subtext: '服务实效数',
		subtextStyle: {
			color: '#7DBEFF',
			lineHeight: 10,
			fontSize: 10,
		}
	},
	grid: {
		left: 0,
	},
	series: [{
		name: '访问来源',
		type: 'pie',
		radius: ['55%', '90%'],
		avoidLabelOverlap: false,
		label: {
			normal: {
				show: false,
				position: 'center'
			},
			emphasis: {
				show: false,
				textStyle: {
					fontSize: '10',
					fontWeight: 'bold'
				}
			}
		},
		color: ['#44BAFF', '#FDCA64', '#FD6060'],
		center: ['20%', '50%'],
		itemStyle: {
			borderWidth: 3, //设置border的宽度有多大
			borderColor: '#163278',
			backgroundClip: 'contentBox'
		},
		labelLine: {
			normal: {
				show: false
			}
		},
		data: [{
				value: 335,
				name: '矛盾纠纷'
			},
			{
				value: 310,
				name: '安全隐患'
			},
			{
				value: 234,
				name: '特殊人群'
			}
		]
	}]
};

var option_chartTwo = {
	tooltip: {
		trigger: 'item',
		formatter: "{a} <br/>{b}: {c} ({d}%)"
	},
	legend: {
		show: false
	},
	title: {
		text: '2019',
		left: 'center',
		top: '38%',
		// padding:[24,0],
		textStyle: {
			color: '#F7C86E',
			fontSize: 20,
			align: 'center',
			lineHeight: 10,
		},
		subtext: '被服务人员数',
		subtextStyle: {
			color: '#7DBEFF',
			lineHeight: 10,
		}
	},
	grid: {
		left: 0,
	},
	series: [{
		type: 'pie',
		radius: ['50%', '80%'],
		avoidLabelOverlap: false,
		label: {
			normal: {
				show: false,
				position: 'center'
			},
			emphasis: {
				show: false,
				textStyle: {
					fontSize: '10',
					fontWeight: 'bold'
				}
			}
		},
		color: ['#F6975C', '#F4C76C', '#536BF5','#61E8A2','#F45F61','#A450F1','#44BAFF','#3C6576'],
		itemStyle: {
			borderWidth: 3, //设置border的宽度有多大
			borderColor: '#163278',
			backgroundClip: 'contentBox'
		},
		labelLine: {
			normal: {
				show: false
			}
		},
		data: [
			{
				value: 525,
				name: '社区矫正人员'
			},
			{
				value: 340,
				name: '刑满释放人员'
			},
			{
				value: 128,
				name: '肇事肇祸精神病人'
			},
			{
				value: 180,
				name: '重点青少年'
			},
			{
				value: 34,
				name: '重点上访人员'
			},
			{
				value: 46,
				name: '吸毒人员'
			},
			{
				value: 12,
				name: '个人极端重点人员'
			},
			{
				value: 40,
				name: '其他'
			}
		]
	}]
};



var option_chartThree = {
	tooltip: {
		trigger: 'item',
		formatter: "{a} <br/>{b}: {c} ({d}%)"
	},
	legend: {
		x: 'center',
		y: 'bottom',
		data: ['志愿者'],
		textStyle: {
			fontSize: 12,
			fontWeight: 400,
			color: 'rgba(255,255,255,1)',
		},
		itemHeight: 8,
		itemWidth: 8,
		borderRadius: 0,
		width: '200'

	},
	title: {
		text: '10000',
		left: 'center',
		top: '35%',
		// padding:[24,0],
		textStyle: {
			color: '#F7C86E',
			fontSize: 20,
			align: 'center',
			lineHeight: 10,
		},
		subtext: '总成员',
		subtextStyle: {
			color: '#7DBEFF',
			lineHeight: 10,
		}
	},
	grid: {
		left: 0,
	},
	series: [{
		name: '',
		type: 'pie',
		radius: ['40%', '60%'],
		avoidLabelOverlap: false,
		label: {
			normal: {
				show: false,
				position: 'center'
			},
			emphasis: {
				show: false,
				textStyle: {
					fontSize: '10',
					fontWeight: 'bold'
				}
			}
		},
		color: ['#61E8A2', '#264286', '#FD6060'],
		center: ['50%', '40%'],
		labelLine: {
			normal: {
				show: false
			}
		},
		data: [{
				value: 7000,
				name: '志愿者'
			},
			{
				value: 3000,
				name: '非志愿者'
			}
		]
	}]
};


var option_chartFour = {
	tooltip: {
		trigger: 'item',
		formatter: "{a} <br/>{b}: {c} ({d}%)"
	},
	legend: {
		x: 'center',
		y: 'bottom',
		data: ['公职人员', '群众', '党员'],
		textStyle: {
			fontSize: 12,
			fontWeight: 400,
			color: 'rgba(255,255,255,1)',
		},
		itemHeight: 8,
		itemWidth: 8,
		borderRadius: 0,
		width: '200'

	},
	title: {
		text: '2019',
		left: 'center',
		top: '35%',
		// padding:[24,0],
		textStyle: {
			color: '#F7C86E',
			fontSize: 20,
			align: 'center',
			lineHeight: 10,
		},
		subtext: '总成员',
		subtextStyle: {
			color: '#7DBEFF',
			lineHeight: 10,
		}
	},
	grid: {
		left: 0,
	},
	series: [{
		name: '访问来源',
		type: 'pie',
		radius: ['40%', '60%'],
		avoidLabelOverlap: false,
		label: {
			normal: {
				show: false,
				position: 'center'
			},
			emphasis: {
				show: false,
				textStyle: {
					fontSize: '10',
					fontWeight: 'bold'
				}
			}
		},
		color: ['#44BAFF', '#FD6060', '#FDCA64'],
		center: ['50%', '40%'],
		itemStyle: {
			borderWidth: 3, //设置border的宽度有多大
			borderColor: '#163278',
			backgroundClip: 'contentBox'
		},
		labelLine: {
			normal: {
				show: false
			}
		},
		data: [{
				value: 335,
				name: '公职人员'
			},
			{
				value: 310,
				name: '群众'
			},
			{
				value: 234,
				name: '党员'
			}
		]
	}]
};


var option_chartFive = {
	tooltip: {
		trigger: 'item',
		formatter: "{a} <br/>{b}: {c} ({d}%)"
	},
	legend: {
		x: 'center',
		y: 'bottom',
		data: ['心理', '法律', '教师', '医生','社工','其他'],
		textStyle: {
			fontSize: 12,
			fontWeight: 400,
			color: 'rgba(255,255,255,1)',
		},
		itemHeight: 8,
		itemWidth: 8,
		borderRadius: 0,
		width: '200'

	},
	title: {
		text: '2019',
		left: 'center',
		top: '35%',
		// padding:[24,0],
		textStyle: {
			color: '#F7C86E',
			fontSize: 20,
			align: 'center',
			lineHeight: 10,
		},
		subtext: '总成员',
		subtextStyle: {
			color: '#7DBEFF',
			lineHeight: 10,
		}
	},
	grid: {
		left: 0,
	},
	series: [{
		type: 'pie',
		radius: ['40%', '60%'],
		avoidLabelOverlap: false,
		label: {
			normal: {
				show: false,
				position: 'center'
			},
			emphasis: {
				show: false,
				textStyle: {
					fontSize: '10',
					fontWeight: 'bold'
				}
			}
		},
		color: ['#44BAFF', '#FD6060', '#3C6576','#FDCA64','#536BF5','#61E8A2'],
		center: ['50%', '40%'],
		itemStyle: {
			borderWidth: 3, //设置border的宽度有多大
			borderColor: '#163278',
			backgroundClip: 'contentBox'
		},
		labelLine: {
			normal: {
				show: false
			}
		},
		data: [{
				value: 335,
				name: '医生'
			},
			{
				value: 310,
				name: '社工'
			},
			{
				value: 234,
				name: '心理'
			},
			{
				value: 234,
				name: '法律'
			}
			,
			{
				value: 234,
				name: '教师'
			},
			{
				value: 234,
				name: '其他'
			}
		]
	}]
};






// 使用刚指定的配置项和数据显示图表。
chartOne.setOption(option_chartOne);

chartTwo.setOption(option_chartTwo);

chartThree.setOption(option_chartThree);

chartFour.setOption(option_chartFour);

chartFive.setOption(option_chartFive);


productNumber=function (){
	var arr = [];
	$('#productNumber i').each(function(i, dom) {
		var iDom = $(dom),
			decimals = 0,
			sum = iDom.data('sum');
		//初始化传参，参数为上面注释中的参数    
		arr.push(new CountUp(iDom.attr('id'), 0, sum, decimals, 4, {
			useEasing: true,//效果
			separator: ''//数字分隔符
		}));
		arr[i].start();
	});
}();