export default {
	install(Vue, options) {

		//将全局属性注入Vue原型
		Vue.prototype.globalData = {
			api: '',
			imgApi: '',
			imgUploadApi: '',
			pageNum: 1, //当前页码
			pageSize: 10, //每页显示多少条
			pageNumFixed: 1, //固定每页显示多少条
			pageSizeFixed: 10, //固定每页显示多少条
			pageSizes: [10, 20, 50, 100, 200, 500],
			isLogin: false,  //是否登录
			buttonNumber: 0, //表格操作权限按钮数量
			defaultDataStatus:'2',  //默认数据状态,1未提交审核 2，生效中 3,驳回 4,一审中
			defaultDataStatusText:'未提交审核',  //默认数据状态,1未提交审核 2，生效中 3,驳回 4,一审中
			tabelHeaderColor: {   //表头颜色设置
				background: '#F7F7F7'
			},

		}
		//全局方法
		Vue.prototype.globalMethods = {
			initpage(that){
				window.console.log(that.globalData)
				that.globalData.pageNum = 1;
				that.globalData.pageSize = 10;
				this.defaultDataStatus = "1";
			},
			//当前时期
			getCurrentDate() {
				var d = new Date(),
					year = d.getFullYear(),
					month = d.getMonth() + 1,
					day = d.getDate(),
					h = d.getHours(),
					minutes = d.getMinutes(),
					secounds = d.getSeconds();
				if (month < 10) {
					month = '0' + month;
				}
				if (day < 10) {
					day = '0' + day;
				}
				if (minutes < 10) {
					minutes = '0' + minutes;
				}
				if (secounds < 10) {
					secounds = '0' + secounds;
				}
				return year + '-' + month + '-' + day + ' ' + h + ':' + minutes + ':' + secounds;
			},
			//得到地区数据
			getAreas(areaCode, level) {
				// level 5,省， 4.市
				if (areaCode == null || level == null) {
					return null;
				}
				if (level == 4) {
					if (areaCode.length == 4) {
						return [areaCode];
					}
					if (areaCode.length == 6) {
						return [areaCode.substring(0, 4), areaCode];
					}
					if (areaCode.length == 9) {
						return [areaCode.substring(0, 4), areaCode.substring(0, 6), areaCode];
					}
					if (areaCode.length == 12) {
						return [areaCode.substring(0, 4), areaCode.substring(0, 6), areaCode.substring(0, 9), areaCode];
					}
				}
			},
			//查找字符串最长的值
			findLongestWord(strArray) {
				strArray.sort(function (a, b) {
					return b.length - a.length;
				});
				return strArray[0];
			},

			//传入选定的级联数组，和下拉树数组，返回相应的对象数组
			getCascaderObj(val, opt) {
				if(val!=""){
					return val.map(function (value, index, array) {
						for (var itm of opt) {
							// window.console.log(opt)
							if (itm.id == value) { opt = itm.children; return itm; }
						}
						return null;
					});
				}else{
					return [];
				}
				
			},
			//插入指定字符串
			insertStr(soure, zdstr, splitStr) {
				var newStr = '';
				var index = soure.indexOf(zdstr);  //找到指定字符第一个索引
				if (index != -1) {
					index += 1;
					//插入分割字符
					newStr = soure.slice(0, index) + splitStr + soure.slice(index);
				} else {
					newStr = soure;
				}
				return newStr;
			},
			//格式化开始时间，结束时间
			formatTimeArr(date) {
				if (date) {
					var d = new Date(date),
						year = d.getFullYear(),
						month = d.getMonth() + 1,
						day = d.getDate(),
						time1 = '';
					if (month < 10) {
						month = '0' + month;
					}
					if (day < 10) {
						day = '0' + day;
					}
					time1 = year + '-' + month + '-' + day;
					return time1;
				}
			},
			//格式化年月
			formatTimeYearMonth(date) {
				if (date) {
					var d = new Date(date),
						year = d.getFullYear(),
						month = d.getMonth() + 1,
						time1 = '';
					if (month < 10) {
						month = '0' + month;
					}

					time1 = year + '-' + month;
					return time1;
				}
			},
			//设置cookie
			setCookie(name, value, days) {
				var d = new Date;
				d.setTime(d.getTime() + 24 * 60 * 60 * 1000 * days);
				window.document.cookie = name + "=" + value + ";path=/;expires=" + d.toGMTString();
			},
			//获取cookie
			getCookie(name) {
				var v = window.document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
				return v ? v[2] : null;

			},
			//清除cookiie
			deleteCookie(name, value, days) {
				var d = new Date;
				d.setTime(d.getTime() + 24 * 60 * 60 * 1000 * days);
				window.document.cookie = name + "=" + value + ";path=/;expires=" + d.toGMTString();
			},
			checkeds(type,value,callback){
				//1 邮箱checkEmail 2 textarea200字符输入框checkTextarea 3 手机号验证checkPhone 4 固话checkTel
				//5 身份证号码校验checkIdCard 6 正整数校验checkIntegerP 7 传真验证checkFax 8 网址验证checkWebsite
				// 9 textarea100字符输入框checkTextareaone 10 textarea60字符输入框checkTextareasix 11 textarea300字符输入框checkTextareathree
				let rule = [];
				if(type==1){
					this.checkEmail(rule,value,callback);
				}
				if(type==2){
					this.checkTextarea(rule,value,callback);
				}
				if(type==3){
					this.checkPhone(rule,value,callback);
				}
				if(type==4){
					this.checkTel(rule,value,callback);
				}
				if(type==5){
					this.checkIdCard(rule,value,callback);
				}
				if(type==6){
					this.checkIntegerP(rule,value,callback);
				}
				if(type==7){
					this.checkFax(rule,value,callback);
				}
				if(type==8){
					this.checkWebsite(rule,value,callback);
				}
				if(type==9){
					this.checkTextareaone(rule,value,callback);
				}
				if(type==10){
					this.checkTextareasix(rule,value,callback);
				}
				if(type==11){
					this.checkTextareathree(rule,value,callback);
				}
			},
			//检查200textarea输入框
			checkTextarea(rule,value, callback) {
				if (value && value.length > 200) {
					callback(new Error('字符长度在200范围之内'));
				} else {
					callback();
				}
			},
			//检查100textarea输入框
			checkTextareaone(rule,value, callback) {
				if (value && value.length > 100) {
					callback(new Error('字符长度在100范围之内'));
				} else {
					callback();
				}
			},
			//检查60textarea输入框
			checkTextareasix(rule,value, callback) {
				if (value && value.length > 60) {
					callback(new Error('字符长度在60范围之内'));
				} else {
					callback();
				}
			},
			//检查300textarea输入框
			checkTextareathree(rule,value, callback) {
				if (value && value.length > 300) {
					callback(new Error('字符长度在300范围之内'));
				} else {
					callback();
				}
			},
			// 手机号验证
			checkPhone(rule,value, callback) {
				if (value && !(/^1[34578]\d{9}$/.test(value)||/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,4}))?$/.test(value))) {
					callback(new Error('请输入正确格式,例如：13500228899或者0736-1234567'));
				} else {
					callback();
				}
			},
			//固话
			checkTel(rule,value, callback) {
				if (value && !(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,4}))?$/.test(value))) {
					callback(new Error('请输入正确的电话号码,例如：0736-1234567'));
				} else {
					callback();
				}
			},
			/**
	     * 邮箱校验
	     */
			checkEmail(rule,value, callback) {
				var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
				if (value && !reg.test(value)) {
					callback(new Error('请输入有效的邮箱,例如：aw@sina.com'));
				} else {
					callback();
				}
			},
			checkIdCard(rule,value,callback){
				var regIdCard = /^[A-Za-z0-9]{1,18}$/;
				var tip = "";
				var pass = true;
				if(value && !regIdCard.test(value)){
					tip = "身份证号格式错误";
					pass = false;
				}
				if (!pass) {
					callback(new Error(tip));
				} else {
					callback();
				}
				return pass;
			},
			/**
				* 身份证号码校验
				*/
			checkIdCards(rule,value, callback) {
				var city = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江 ", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北 ", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏 ", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外 " };
				//15位和18位身份证号码的正则表达式
				var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
				var tip = "";
				var pass = true;
				if (value && !regIdCard.test(value)) {
					tip = "身份证号格式错误";
					pass = false;
				} else if (!city[value.substr(0, 2)]) {
					tip = "身份证号格式错误";
					pass = false;
				} else {
					//18位身份证需要验证最后一位校验位
					if (value.length == 18) {
						value = value.split('');
						//∑(ai×Wi)(mod 11)
						//加权因子
						var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
						//校验位
						var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
						var sum = 0;
						var ai = 0;
						var wi = 0;
						for (var i = 0; i < 17; i++) {
							ai = value[i];
							wi = factor[i];
							sum += ai * wi;
						}
						var last = parity[sum % 11];
						if (parity[sum % 11] != value[17]) {
							tip = "校验位错误";
							pass = false;
						}
					}
				}
				if (!pass) {
					callback(new Error(tip));
				} else {
					callback();
				}
				return pass;
			},
			/**
	     * 正整数校验
	     */
			checkIntegerP(rule,value, callback) {
				if (value && !(/^[1-9]\d*$/).test(value)) {
					callback(new Error('只能填写正整数'));
				} else {
					callback();
				}
			},
		// 	/**
	    //  * 统一信用代码
	    //  */
		// 	checkCode(rule,value, callback) {
		// 		if (value && !(/^[A-Za-z0-9-]{0,20}$/).test(value)) {
		// 			callback(new Error('请输入正确的信用代码，比如：q158689-15200'));
		// 		} else {
		// 			callback();
		// 		}
		// 	},
			/**
	         * 传真验证
	        */
			checkFax(rule,value, callback) {
				if (value && !(/^(\d{3,4}-)?\d{7,8}$/).test(value)) {
					callback(new Error('请输入正确传真号,例如：0736-1234567'));
				} else {
					callback();
				}
			},
			//网址验证
			checkWebsite(rule,value, callback) {
				var reg = /((https|http|ftp|rtsp|mms):\/\/)?(([0-9a-z_!~*'().&=+$%-]+:)?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)/g;
				if (value && !reg.test(value)) {
					callback(new Error('请输入有效的网址,例如：www.a123.com'));
				} else {
					callback();
				}
			},
			//整数加小数点2位
			checkIntegerDecimal(rule,value, callback) {
				var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,2})?$/;
				if (value && !reg.test(value)) {
					callback(new Error('请输入正整数和最多2位小数'));
				} else {
					callback();
				}
			},
			//整数加小数点4位
			checkIntegerFour(rule,value, callback) {
				var reg = /^0{1}([.]\d{1,2})?$|^[1-9]\d*([.]{1}[0-9]{1,4})?$/;
				if (value && !reg.test(value)) {
					callback(new Error('请输入正整数和最多4位小数'));
				} else {
					callback();
				}
			},
			//初始化按钮权限
			initButtonAuthority(sysButtonsArr, that) {
				if (sysButtonsArr && sysButtonsArr.length > 0) {
					var buttonNumber = 0;
					sysButtonsArr.forEach(item => {
						switch (item.url) {
							//增加
							case that.localAddButtonUrl:
								that.serverAddButtonUrl = item.url;
								break;
							//删除
							case that.localDeleteButtonUrl:
								that.serverDeleteButtonUrl = item.url;
								buttonNumber++;
								break;
							//修改
							case that.localUpdateButtonUrl:
								that.serverUpdateButtonUrl = item.url;
								buttonNumber++;
								break;
							//查看
							case that.localCheckButtonUrl:
								that.serverCheckButtonUrl = item.url;
								buttonNumber++;
								break;
							//查询
							case that.localQueryButtonUrl:
								that.serverQueryButtonUrl = item.url;
								break;
							//初审
							case that.localStartShenHeButtonUrl:
								that.serverStartShenHeButtonUrl = item.url;
								buttonNumber++;
								break;
							//二审
							case that.localTwoShenHeButtonUrl:
								that.serverTwoShenHeButtonUrl = item.url;
								buttonNumber++;
								break;
							//三审
							case that.localThreeShenHeButtonUrl:
								that.serverThreeShenHeButtonUrl = item.url;
								buttonNumber++;
								break;
							//终审
							case that.localEndShenHeButtonUrl:
								that.serverEndShenHeButtonUrl = item.url;
								buttonNumber++;
								break;
						}
					});

					that.globalData.buttonNumber = buttonNumber;
				}
			},
			// 初始化操作列宽度
			initOperationWidth(buttonNumber) {
				//4个按钮宽度一共172
				// window.console.log(buttonNumber)
				var width = '';
				switch (buttonNumber) {
					case 1:
						width = '50';
						break;
					case 2:
						width = '100';
						break;
					case 3:
						width = '140';
						break;
					case 4:
						width = '200';
						break;
					case 5:
						width = '200';
						break;
					case 6:
						width = '200';
						break;
					case 7:
						width = '200';
						break;
					default:
						width = '80';

				}
				return width;
			},

		}
	}
}
