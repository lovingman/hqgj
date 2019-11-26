var changes = function(item,that, obj, dicObj,vue) { //显示必填未填
window.console.log(item)
	if (item.rules) {
		if(item.rules[0].type){
			that.checkeds(item.rules[0].type,item.model,function(res){
				if(res){
					item.error = true;
					item.placeholder = res.message;
				}else{
					item.error = false;
				}
			});
		}else{
			if (item.model != "") {
				item.error = false;
			} else {
				item.error = true;
			}
		}
		
	}
	if (item.array) {
		for (var i = 0; i < item.array.length; i++) {
			if (item.model == item.array[i].code) {
				item.NoType = item.array[i].name;
			}
		}
		if(item.isPartyValue){
			window.console.log(item.model)
			vue.typeChange(item.model)
		}
	}
	if (item.type == 3 || item.type == 9) {
		if (Array.isArray(item.model) == true) {
			if (item.model.length > 0) {
				item.wordType = item.model.join(",")
				if (item.model.length == 1) {
					for (var j = 0; j < item.array.length; j++) {
						if (item.array[j].id == item.model[0]) {
							item.NoType = item.array[j].text;
						}
					}
				} else if(item.model.length == 2) {
					for (var j = 0; j < item.array.length; j++) {
						if (item.array[j].id == item.model[0]) {
							item.NoType = item.array[j].text;
						}
						for (var i = 0; i < item.array[j].children.length; i++) {
							if (item.array[j].children[i].id == item.model[1]) {
								item.NoType = item.NoType + "," + item.array[j].children[i].text;
							}
						}
					}
				}else if(item.model.length == 3) {
					for (var j = 0; j < item.array.length; j++) {
						if (item.array[j].id == item.model[0]) {
							item.NoType = item.array[j].text;
						}
						let a = item.array[j].children;
						for (var i = 0; i < a.length; i++) {
							if (a[i].id == item.model[1]) {
								item.NoType = item.NoType + "," + a[i].text;
							}
							let c = a[i].children;
							if(c){
								for(var n = 0; n < c.length; n++){
									if (c[n].id == item.model[2]) {
										item.NoType = item.NoType + "," + c[n].text;
									}
								}
							}
							
						}
					}
				}else if(item.model.length == 4) {
					for (var j = 0; j < item.array.length; j++) {
						if (item.array[j].id == item.model[0]) {
							item.NoType = item.array[j].text;
						}
						for (var i = 0; i < item.array[j].children.length; i++) {
							if (item.array[j].children[i].id == item.model[1]) {
								item.NoType = item.NoType + "," + item.array[j].children[i].text;
							}
							let c = item.array[j].children[i].children;
							if(c){
							window.console.log(c)
								for(var n = 0; n < c.length; n++){
									if (c[n].id == item.model[2]) {
										window.console.log(c[n].id)
										window.console.log(c[n])
										item.NoType = item.NoType + "," + c[n].text;
									}
									let d = item.array[j].children[i].children[n].children;
									if(d){
										for(var m = 0; m < d.length; m++){
											if (d[m].id == item.model[3]) {
												item.NoType = item.NoType + "," + d[m].text;
											}
										}
									}
									
								}
							}
							
						}
					}
				}

			}
		}

		window.console.log(item)
	}
	if (item.select) {
		if (item.modelType == "vocationValue") {
			if (item.model == "3802" || item.model == "3803") {
				var RetireMessageItem = { //离退休模板
					id: {
						label: "id",
						error: false,
						model: "",
						modelType: "id",
						prop: "id",
						type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					retireCategory: {
						label: "离退类别",
						error: false,
						model: "",
						modelType: "retireCategory",
						prop: "retireCategory",
						placeholder: "请输入离退类别",
						rules: [{
							required: true,
							message: '请输入离退类别',
							trigger: 'blur'
						}],
						type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					retireTime: {
						label: "离退时间",
						error: false,
						model: "",
						modelType: "retireTime",
						prop: "retireTime",
						placeholder: "YYYY-MM-DD",
						type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					oldRetireValue: {
						label: "离退前级别",
						error: false,
						model: "",
						modelType: "oldRetireValue",
						prop: "oldRetireValue",
						placeholder: "请选择职业状态",
						array: obj.RetireMessageItem.oldRetireValue.array,
						wordName: "oldRetireType",
						type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					treatment: {
						label: "离退休前干部待遇 ",
						error: false,
						model: "",
						modelType: "treatment",
						prop: "treatment",
						placeholder: "请输入离退休前干部待遇",
						type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					treatmentLb: {
						label: "干部待遇类别 ",
						error: false,
						model: "",
						modelType: "treatmentLb",
						prop: "treatmentLb",
						placeholder: "请输入干部待遇类别",
						type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					companyName: {
						label: "管理单位名称",
						error: false,
						model: "",
						modelType: "companyName",
						prop: "companyName",
						placeholder: "请输入管理单位名称",
						type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					pzCompanyName: {
						label: "退(离)批准单位名称 ",
						error: false,
						model: "",
						modelType: "pzCompanyName",
						prop: "pzCompanyName",
						placeholder: "请输入退(离)批准单位名称 ",
						type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					ratifyNo: {
						label: "退(离)批准文号 ",
						error: false,
						model: "",
						modelType: "ratifyNo",
						prop: "ratifyNo",
						placeholder: "请输入退(离)批准文号 ",
						type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					}
				}
				obj.StudyAbroadOrginize.StudyAbroad = RetireMessageItem;
				obj.OverseasChineseOrginize.RetireMessage = RetireMessageItem;
			} else {
				obj.StudyAbroadOrginize.StudyAbroad = {};
				obj.OverseasChineseOrginize.RetireMessage = {};
			}
		}
		if (item.modelType == "identity") {
			if (item.model == "7402" || item.model == "7403") {
				var GuiqiaoQiaojuanItem = { //归侨，侨眷模板
					id: {
						label: "id",
						error: false,
						model: "",
						modelType: "id",
						prop: "id",
						type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					originalAddressValue: {
						label: "原侨居地",
						error: false,
						model: "",
						modelType: "originalAddressValue",
						prop: "originalAddressValue",
						placeholder: "请输入原侨居地",
						rules: [{
							required: true,
							message: '请输入原侨居地',
							trigger: 'blur'
						}],
						type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					returnHomeTime: {
						label: "回国时间",
						error: false,
						model: "",
						modelType: "returnHomeTime",
						prop: "returnHomeTime",
						placeholder: "YYYY-MM-DD",
						type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
					vocationValue: {
						label: "职业状态",
						error: false,
						model: "",
						modelType: "vocationValue",
						prop: "vocationValue",
						placeholder: "请选择职业状态",
						array: obj.GuiqiaoQiaojuanItem.vocationValue.array,
						select: "true",
						wordName: "vocationType",
						type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
						changes: changes
					},
				}
				obj.OverseasChineseOrginize.GuiqiaoQiaojuan = GuiqiaoQiaojuanItem;
			} else {
				obj.OverseasChineseOrginize.GuiqiaoQiaojuan = {};
				obj.OverseasChineseOrginize.RetireMessage = {};
			}
		}
	}

	if (item.isLian) {
		if (item.model == "4303") {
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.isShow = false;
			obj.ReligionOrginize.ReligionOrginize.appellationValue.isShow = false;
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue["rules"] = [{
				required: true,
				message: '请选择宗教派别',
				trigger: 'change'
			}];
			obj.ReligionOrginize.ReligionOrginize.appellationValue["rules"] = [{
				required: true,
				message: '请输入宗教称谓',
				trigger: 'change'
			}];
			let arr = [];
			for (var i = 0; i < dicObj.religionFactionValue.length; i++) {
				if (dicObj.religionFactionValue[i].pcode == "4303") {
					arr.push(dicObj.religionFactionValue[i]);
				}
			}

			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.array = [];
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.NoType = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.array = arr;
			obj.ReligionOrginize.ReligionOrginize.appellationValue.placeholder = "请填写法号"
		} else if (item.model == "4304") {
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.NoType = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.isShow = false;
			obj.ReligionOrginize.ReligionOrginize.appellationValue.isShow = false;
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue["rules"] = [{
				required: true,
				message: '请选择宗教派别',
				trigger: 'change'
			}];
			obj.ReligionOrginize.ReligionOrginize.appellationValue["rules"] = [{
				required: true,
				message: '请输入宗教称谓',
				trigger: 'change'
			}];
			let arr = [];
			for (var i = 0; i < dicObj.religionFactionValue.length; i++) {
				if (dicObj.religionFactionValue[i].pcode == "4304") {
					arr.push(dicObj.religionFactionValue[i]);
				}
			}
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.NoType = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.array = arr;
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.array = [];
			obj.ReligionOrginize.ReligionOrginize.appellationValue.placeholder = "请填写道号"
		} else if (item.model == "4305") {
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.NoType = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.isShow = true;
			delete obj.ReligionOrginize.ReligionOrginize.religionFactionValue["rules"];
			obj.ReligionOrginize.ReligionOrginize.appellationValue.isShow = false;
			obj.ReligionOrginize.ReligionOrginize.appellationValue["rules"] = [{
				required: true,
				message: '请输入宗教称谓',
				trigger: 'change'
			}];
			obj.ReligionOrginize.ReligionOrginize.appellationValue.placeholder = "请填写经名"
			var arr = [];
			for (var i = 0; i < dicObj.jobIdentityValue.length; i++) {
				if (dicObj.jobIdentityValue[i].pcode == item.model) {
					arr.push(dicObj.jobIdentityValue[i]);
				}
			}
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.NoType = "";
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.array = arr;
		} else if (item.model == "4301") {
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.NoType = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.isShow = true;
			delete obj.ReligionOrginize.ReligionOrginize.religionFactionValue["rules"];
			obj.ReligionOrginize.ReligionOrginize.appellationValue.isShow = false;
			obj.ReligionOrginize.ReligionOrginize.appellationValue["rules"] = [{
				required: true,
				message: '请输入宗教称谓',
				trigger: 'change'
			}];
			obj.ReligionOrginize.ReligionOrginize.appellationValue.placeholder = "请填写圣名";
			var arr = [];
			for (var i = 0; i < dicObj.jobIdentityValue.length; i++) {
				if (dicObj.jobIdentityValue[i].pcode == item.model) {
					arr.push(dicObj.jobIdentityValue[i]);
				}
			}
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.array = arr;
		} else if (item.model == "4302") {
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.NoType = "";
			obj.ReligionOrginize.ReligionOrginize.religionFactionValue.isShow = true;
			delete obj.ReligionOrginize.ReligionOrginize.religionFactionValue["rules"]
			obj.ReligionOrginize.ReligionOrginize.appellationValue.isShow = true;
			obj.ReligionOrginize.ReligionOrginize.appellationValue.model = "";
			delete obj.ReligionOrginize.ReligionOrginize.appellationValue["rules"]
			obj.ReligionOrginize.ReligionOrginize.appellationValue.placeholder = ""
			var arr = [];
			for (var i = 0; i < dicObj.jobIdentityValue.length; i++) {
				if (dicObj.jobIdentityValue[i].pcode == item.model) {
					arr.push(dicObj.jobIdentityValue[i]);
				}
			}
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.model = "";
			obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.array = arr;
		}
	}
	if (item.iserLian) {
		var arr = [];
		for (var i = 0; i < dicObj.jobIdentityValue.length; i++) {
			if (dicObj.jobIdentityValue[i].pcode == item.model) {
				arr.push(dicObj.jobIdentityValue[i]);
			}
		}
		obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.model = "";
		obj.ReligionOrginize.ReligionOrginize.jobIdentityValue.array = arr;
	}
}

var changeTree = function(obj, title) {
	if (Array.isArray(obj) == true) {
		let arr1 = [];
		for (var i = 0; i < obj.length; i++) {
			let obj1 = {};
			for (let index in obj[i]) {
				obj1[index] = obj[i][index].model;
				if (obj[i][index].NoType) {
					obj1[obj[i][index].wordName] = obj[i][index].NoType;
				}
				if (obj[i][index].type == 3 ) {
					obj1[index] = obj[i][index].wordType;
					obj1[obj[i][index].wordName] = obj[i][index][obj[i][index].wordName];
				}
				if(obj[i][index].type == 9){
					obj1[index] = obj[i][index].wordType;
					obj1[obj[i][index].wordName] = obj[i][index].wordType;
				}
			}
			arr1.push(obj1);
		}
		return arr1;
	} else {
		let obj1 = {};
		for (let i in obj) {
			obj1[i] = obj[i].model;
			if (obj[i].NoType) {
				obj1[obj[i].wordName] = obj[i].NoType;
			}
			if (obj[i].type == 3) {
				obj1[i] = obj[i].wordType;
				obj1[obj[i].wordName] = obj[i].NoType;
			}
			if (obj[i].type == 9) {
				obj1[i] = obj[i].wordType;
				obj1[obj[i].wordName] = obj[i].NoType;
			}
			if (obj[i].prop == "zjPhoto") {

			}
		}
		return obj1;
	}
}

const clernObj = function(obj) {
	if (Array.isArray(obj) == true) {
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].category == 1) {

			} else {
				let obj1 = row.educationcMessage[i];
				for (let j in obj1) {
					for (let n in obj) {
						if (n == j) {
							obj[n].model = obj1[j];
						}
					}
				}
			}
		}
	}

}

const rulesObj = function(obj) {
	let errorArray = [];
	for (let item in obj) {
		if (Array.isArray(obj[item]) == true) {
			for (var i = 0; i < obj[item].length; i++) {
				for (let it in obj[item][i]) {
					if (obj[item][i][it].rules) {
						if (obj[item][i][it].model == "" || obj[item][i][it].model == null) {
							obj[item][i][it].error = true;
							errorArray.push("true")
						} else {
							obj[item][i][it].error = false;
							errorArray.push("false")
						}
					}
				}
			}
		} else {
			for (let it in obj[item]) {
				if (obj[item][it].rules) {
					if (obj[item][it].model == "" || obj[item][it].model == null) {
						obj[item][it].error = true;
						errorArray.push("true")
					} else {
						obj[item][it].error = false;
						errorArray.push("false")
					}
				}
			}
		}
	}
	return errorArray;
}

var MZDPPersonFamilyItem = { //家庭信息模板
	name: {
		label: "姓名",
		error: false,
		model: "",
		modelType: "name",
		prop: "name",
		placeholder: "请输入姓名",
		rules: [{
			required: true,
			message: '请输入姓名',
			trigger: 'blur'
		}],
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	appellation: {
		label: "称谓",
		error: false,
		model: "",
		modelType: "appellation",
		prop: "appellation",
		placeholder: "请输入称谓",
		rules: [{
			required: true,
			message: '请输入称谓',
			trigger: 'blur'
		}],
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	identity: {
		label: "公民身份证号",
		error: false,
		model: "",
		modelType: "identity",
		prop: "identity",
		placeholder: "请输入身份证号",
		rules: [{
			required: false,
			message: '请输入身份证号',
			trigger: 'blur',
			type:5
		}],
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	birthTime: {
		label: "出生日期",
		error: false,
		model: "",
		modelType: "birthTime",
		prop: "birthTime",
		placeholder: "YYYY-MM-DD",
		// rules: [{
		// 	required: true,
		// 	message: '请选择出生日期',
		// 	trigger: 'change'
		// }],
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	genderValue: {
		label: "性别",
		error: false,
		model: "",
		modelType: "genderValue ",
		prop: "genderValue ",
		placeholder: "请选择性别",
		array: [],
		// rules: [{
		// 	required: true,
		// 	message: '性别',
		// 	trigger: 'change'
		// }],
		wordName: "genderType",
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	workUnitName: {
		label: "工作单位名称",
		error: false,
		model: "",
		modelType: "workUnitName",
		prop: "workUnitName",
		placeholder: "请输入工作单位名称",
		// rules: [{
		// 	required: true,
		// 	message: '请输入工作单位名称',
		// 	trigger: 'blur'
		// }],
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	unitAdressValue: {
		label: "单位所在政区",
		error: false,
		model: [],
		modelType: "unitAdressValue",
		prop: "unitAdressValue",
		placeholder: "请选择省/请选择市/请选择区",
		type: 9, //1:input 2:select 3:cascader 4:textarea 5:picker
		array: [],
		wordName: "unitAdress",
		wordType: "",
		changes: changes
	},
	presentValue: {
		label: "现状",
		error: false,
		model: "",
		modelType: "presentValue",
		prop: "presentValue",
		placeholder: "请选择状态",
		wordName: "presentType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	politicalFaceValue: {
		label: "政治面貌",
		error: false,
		model: "",
		modelType: "politicalFaceValue",
		prop: "politicalFaceValue",
		placeholder: "请选择面貌",
		wordName: "politicalFaceType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	nationalityValue: {
		label: "国籍",
		error: false,
		model: "",
		modelType: "nationalityValue",
		prop: "nationalityValue",
		placeholder: "请选择国籍",
		wordName: "nationalityType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
}
var MZDPPersonRewardItem = { //奖励模板
	rewardNo: {
		label: "奖励序号",
		error: false,
		model: "",
		modelType: "rewardNo",
		prop: "rewardNo",
		placeholder: "请输入序号",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	rewardUnitValue: {
		label: "做出奖励单位",
		error: false,
		model: "",
		modelType: "rewardUnitValue",
		prop: "rewardUnitValue",
		placeholder: "请输入奖励单位",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	rewardLevelValue: {
		label: "奖励级别",
		error: false,
		model: "",
		modelType: "rewardLevelValue",
		prop: "rewardLevelValue",
		placeholder: "请选择奖励级别",
		wordName: "rewardLevelType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	rewardName: {
		label: "奖励名称",
		error: false,
		model: "",
		modelType: "rewardName",
		prop: "rewardName",
		placeholder: "请输入奖励名称",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	rewardTime: {
		label: "奖励批准时间",
		error: false,
		model: "",
		modelType: "rewardTime",
		prop: "rewardTime",
		placeholder: "YYYY-MM-DD",
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	rewardReason: {
		label: "奖励原因",
		error: false,
		model: "",
		modelType: "rewardReason",
		prop: "rewardReason",
		placeholder: "请输入内容，最多100个字",
		rules: [{
			required: false,
			message: '输入内容最多100个字',
			trigger: 'blur',
			type:9
		}],
		type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	zwccValue: {
		label: "奖励时职务层次",
		error: false,
		model: "",
		modelType: "zwccValue",
		prop: "zwccValue",
		placeholder: "请选择层次",
		wordName: "zwccType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	rewardFile: {
		label: "奖励决定文件",
		error: false,
		model: "",
		modelType: "rewardFile",
		prop: "rewardFile",
		placeholder: "请输入文件名称",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	rewardExplain: {
		label: "奖励说明",
		error: false,
		model: "",
		modelType: "rewardExplain",
		prop: "rewardExplain",
		placeholder: "请输入内容，最多100个字",
		rules: [{
			required: false,
			message: '输入内容最多100个字',
			trigger: 'blur',
			type:9
		}],
		type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	}
}
var MZDPPersonPunishItem = { //惩罚模板
	punishNo: {
		label: "惩处序号",
		error: false,
		model: "",
		modelType: "punishNo",
		prop: "punishNo",
		placeholder: "请输入序号",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishJgName: {
		label: "惩处单位",
		error: false,
		model: "",
		modelType: "punishJgName",
		prop: "punishJgName",
		placeholder: "请输入惩处单位",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishLevelValue: {
		label: "惩处机关级别",
		error: false,
		model: "",
		modelType: "punishLevelValue",
		prop: "punishLevelValue",
		placeholder: "请选择级别",
		wordName: "punishLevelType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishValue: {
		label: "惩处类型",
		error: false,
		model: "",
		modelType: "punishValue",
		prop: "punishValue",
		placeholder: "请选择类型",
		wordName: "punishType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishTime: {
		label: "惩处批准时间",
		error: false,
		model: "",
		modelType: "punishTime",
		prop: "punishTime",
		placeholder: "YYYY-MM-DD",
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	zwccValue: {
		label: "惩处时职务层次",
		error: false,
		model: "",
		modelType: "zwccValue",
		prop: "zwccValue",
		placeholder: "请选择层次",
		wordName: "zwccType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishReason: {
		label: "惩处原因",
		error: false,
		model: "",
		modelType: "punishReason",
		prop: "punishReason",
		placeholder: "请输入内容，最多100个字",
		rules: [{
			required: false,
			message: '输入内容最多100个字',
			trigger: 'blur',
			type:9
		}],
		type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishTerm: {
		label: "惩处批准时间",
		error: false,
		model: "",
		modelType: "punishTerm",
		prop: "punishTerm",
		placeholder: "YYYY-MM-DD",
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishExplain: {
		label: "惩处影响结果",
		error: false,
		model: "",
		modelType: "punishExplain",
		prop: "punishExplain",
		placeholder: "请输入内容，最多100个字",
		rules: [{
			required: false,
			message: '输入内容最多100个字',
			trigger: 'blur',
			type:9
		}],
		type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	punishFile: {
		label: "惩处决定文件",
		error: false,
		model: "",
		modelType: "punishFile",
		prop: "punishFile",
		placeholder: "请输入文件名称",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	cancelTime: {
		label: "撤销时间",
		error: false,
		model: "",
		modelType: "cancelTime",
		prop: "cancelTime",
		placeholder: "YYYY-MM-DD",
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	}
}
var MZDPPersonAcademicTitleItem = { //职称模板
	hpZyJsLevelValue: {
		label: "当前获聘专业技术职务级别",
		error: false,
		model: "",
		modelType: "hpZyJsLevelValue",
		prop: "hpZyJsLevelValue",
		placeholder: "请选择级别",
		// rules: [{
		// 	required: true,
		// 	message: '请选择级别',
		// 	trigger: 'change'
		// }],
		array: [],
		wordName: "hpZyJsLevelType",
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	zzRzZgValue: {
		label: "专技任职资格（职称）",
		error: false,
		model: "",
		modelType: "zzRzZgValue",
		prop: "zzRzZgValue",
		placeholder: "请选择职称",
		// rules: [{
		// 	required: true,
		// 	message: '请选择职称',
		// 	trigger: 'change'
		// }],
		wordName: "zzRzZgType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	zyJsLevelValue: {
		label: "取得专业技术职务级别",
		error: false,
		model: "",
		modelType: "zyJsLevelValue",
		prop: "zyJsLevelValue",
		placeholder: "请选择级别",
		// rules: [{
		// 	required: true,
		// 	message: '请选择级别',
		// 	trigger: 'change'
		// }],
		wordName: "zyJsLevelType",
		array: [],
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	zyJsLeveTime: {
		label: "取得专业技术职务日期",
		error: false,
		model: "",
		modelType: "zyJsLeveTime",
		prop: "zyJsLeveTime",
		placeholder: "YYYY-MM-DD",
		// rules: [{
		// 	required: true,
		// 	message: '请选择日期',
		// 	trigger: 'change'
		// }],
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	getChannelValue: {
		label: "取得资格途径",
		error: false,
		model: "",
		modelType: "getChannelValue",
		prop: "getChannelValue",
		placeholder: "请输入途径",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	examName: {
		label: "评委会或考试名称",
		error: false,
		model: "",
		modelType: "examName",
		prop: "examName",
		placeholder: "请输入评委会或考试名称",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	}
}
var RetireMessageItem = { //离退休模板
	id: {
		label: "id",
		error: false,
		model: "",
		modelType: "id",
		prop: "id",
		type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	retireCategory: {
		label: "离退类别",
		error: false,
		model: "",
		modelType: "retireCategory",
		prop: "retireCategory",
		placeholder: "请输入离退类别",
		rules: [{
			required: true,
			message: '请输入离退类别',
			trigger: 'blur'
		}],
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	retireTime: {
		label: "离退时间",
		error: false,
		model: "",
		modelType: "retireTime",
		prop: "retireTime",
		placeholder: "YYYY-MM-DD",
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	oldRetireValue: {
		label: "离退前级别",
		error: false,
		model: "",
		modelType: "oldRetireValue",
		prop: "oldRetireValue",
		placeholder: "请选择职业状态",
		array: [],
		wordName: "oldRetireType",
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	treatment: {
		label: "离退休前干部待遇 ",
		error: false,
		model: "",
		modelType: "treatment",
		prop: "treatment",
		placeholder: "请输入离退休前干部待遇",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	treatmentLb: {
		label: "干部待遇类别 ",
		error: false,
		model: "",
		modelType: "treatmentLb",
		prop: "treatmentLb",
		placeholder: "请输入干部待遇类别",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	companyName: {
		label: "管理单位名称",
		error: false,
		model: "",
		modelType: "companyName",
		prop: "companyName",
		placeholder: "请输入管理单位名称",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	pzCompanyName: {
		label: "退(离)批准单位名称 ",
		error: false,
		model: "",
		modelType: "pzCompanyName",
		prop: "pzCompanyName",
		placeholder: "请输入退(离)批准单位名称 ",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	ratifyNo: {
		label: "退(离)批准文号 ",
		error: false,
		model: "",
		modelType: "ratifyNo",
		prop: "ratifyNo",
		placeholder: "请输入退(离)批准文号 ",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	}
}
var GuiqiaoQiaojuanItem = { //归侨，侨眷模板
	id: {
		label: "id",
		error: false,
		model: "",
		modelType: "id",
		prop: "id",
		type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	originalAddressValue: {
		label: "原侨居地",
		error: false,
		model: "",
		modelType: "originalAddressValue",
		prop: "originalAddressValue",
		placeholder: "请输入原侨居地",
		rules: [{
			required: true,
			message: '请输入原侨居地',
			trigger: 'blur'
		}],
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	returnHomeTime: {
		label: "回国时间",
		error: false,
		model: "",
		modelType: "returnHome",
		prop: "returnHome",
		placeholder: "YYYY-MM-DD",
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	vocationValue: {
		label: "职业状态",
		error: false,
		model: "",
		modelType: "vocationValue",
		prop: "vocationValue",
		placeholder: "请选择职业状态",
		array: [],
		select: "true",
		wordName: "vocationType",
		type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
}
// var other = {
// 	
// }
var latelyFiveYearItem = {
	itemTime: {
		label: "项目时间",
		error: false,
		model: "",
		modelType: "itemTime",
		prop: "itemTime",
		placeholder: "YYYY-MM-DD",
		rules: [{
			required: true,
			message: '请选择项目时间',
			trigger: 'change'
		}],
		type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	itemAddress: {
		label: "项目地点",
		error: false,
		model: "",
		modelType: "itemAddress",
		prop: "itemAddress",
		placeholder: "请输入项目地点",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	cyUnit: {
		label: "参与单位",
		error: false,
		model: "",
		modelType: "cyUnit",
		prop: "cyUnit",
		placeholder: "请输入参与单位",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	contributeMoney: {
		label: "实际捐款捐物(万元)",
		error: false,
		model: "",
		modelType: "contributeMoney",
		prop: "contributeMoney",
		placeholder: "请输入实际捐款捐物(万元)",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	witnessUnit: {
		label: "证明人单位",
		error: false,
		model: "",
		modelType: "witnessUnit",
		prop: "witnessUnit",
		placeholder: "请输入证明人单位",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
	witnessName: {
		label: "证明人姓名",
		error: false,
		model: "",
		modelType: "witnessName",
		prop: "witnessName",
		placeholder: "请输入证明人姓名",
		type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
		changes: changes
	},
}

var MZDPPersonBasic = {
	MZDPPersonBasic: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		zjPhoto: {
			label: "zjPhoto",
			error: false,
			model: "",
			modelType: "zjPhoto",
			prop: "zjPhoto",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		name: {
			label: "姓名",
			error: false,
			model: "",
			modelType: "name",
			prop: "name",
			placeholder: "请输入姓名",
			rules: [{
				required: true,
				message: '请输入姓名',
				trigger: 'blur'
			}],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		beforeName: {
			label: "曾用名",
			error: false,
			model: "",
			modelType: "beforeName",
			prop: "",
			placeholder: "请输入曾用名",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		genderValue: {
			label: "性别",
			error: false,
			model: "",
			modelType: "genderValue ",
			prop: "genderValue ",
			placeholder: "请选择性别",
			array: [],
			rules: [{
				required: true,
				message: '性别',
				trigger: 'change'
			}],
			wordName: "genderType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		birth: {
			label: "出生日期",
			error: false,
			model: "",
			modelType: "birth",
			prop: "birth",
			placeholder: "YYYY-MM-DD",
			rules: [{
				required: true,
				message: '出生日期',
				trigger: 'change'
			}],
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nationalityValue: {
			label: "国籍",
			error: false,
			model: "",
			modelType: "nationalityValue",
			prop: "nationalityValue",
			placeholder: "请选择国籍",
			array: [],
			rules: [{
				required: true,
				message: '国籍',
				trigger: 'change'
			}],
			wordName: "nationalityType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nativerValue: {
			label: "籍贯",
			error: false,
			model: [],
			modelType: "nativer",
			prop: "nativer",
			placeholder: "请选择省/请选择市",
			rules: [{
				required: true,
				message: '籍贯',
				trigger: 'change'
			}],
			type: 3, //1:input 2:select 3:cascader 4:textarea 5:picker
			array: [],
			wordName: "nativer",
			wordType: "",
			changes: changes
		},
		huKouLocalDistrict: {
			label: "户口所在地",
			error: false,
			model: [],
			modelType: "huKouLocalDistrict",
			prop: "huKouLocalDistrict",
			placeholder: "请选择省/市/区/街道",
			type: 9, //1:input 2:select 3:cascader 4:textarea 5:picker
			array: [],
			wordName: "huKouLocalDistrictType",
			wordType: "",
			changes: changes
		},
		huKouLocal: {
			label: "详细地址",
			error: false,
			model: "",
			modelType: "huKouLocal",
			prop: "",
			placeholder: "请输入详细地址,精确到门牌号",
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		healthySituationValue: {
			label: "健康状况",
			error: false,
			model: "",
			modelType: "healthySituationValue",
			prop: "healthySituationValue",
			placeholder: "请选择健康状况",
			array: [],
			wordName: "healthySituationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		marriageSituationValue: {
			label: "婚姻状况",
			error: false,
			model: "",
			modelType: "marriageSituationValue",
			prop: "marriageSituationValue",
			placeholder: "请选择婚姻状况",
			array: [],
			wordName: "marriageSituationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isTaiwan: {
			label: "是否台湾省籍",
			error: false,
			model: "",
			modelType: "isTaiwan",
			prop: "isTaiwan",
			placeholder: "请选择",
			array: [{
				code: "1",
				name: "否"
			}, {
				code: "2",
				name: "是"
			}],
			rules: [{
				required: true,
				message: '台湾省籍',
				trigger: 'change'
			}],
			wordName: "isTaiwanType",
			type: 10, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nationValue: {
			label: "民族",
			error: false,
			model: "",
			modelType: "nationValue",
			prop: "nationValue",
			placeholder: "请选择民族",
			array: [],
			rules: [{
				required: true,
				message: '民族',
				trigger: 'change'
			}],
			wordName: "nationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		identity: {
			label: "公民身份证号",
			error: false,
			model: "",
			modelType: "identity",
			prop: "identity",
			placeholder: "请输入身份证号码",
			rules: [{
				required: true,
				message: '身份证号码',
				trigger: 'blur',
				type:5
			}],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		practiceArrange: {
			label: "实职安排",
			error: false,
			model: "",
			modelType: "practiceArrange",
			prop: "practiceArrange",
			placeholder: "请输入",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		societyArrange: {
			label: "社会安排",
			error: false,
			model: "",
			modelType: "societyArrange",
			prop: "societyArrange",
			placeholder: "请输入",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	MZDPPersonFullEducation: {
		educationValue: {
			label: "全日制学历",
			error: false,
			model: "",
			modelType: "educationValue",
			prop: "educationValue",
			placeholder: "请选择全日制学历",
			array: [],
			// rules: [{
			// 	required: true,
			// 	message: '全日制学历',
			// 	trigger: 'change'
			// }],
			wordName: "educationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		xueWeiValue: {
			label: "全日制学位",
			error: false,
			model: "",
			modelType: "xueWeiValue",
			prop: "xueWeiValue",
			placeholder: "请选择全日制学位",
			array: [],
			// rules: [{
			// 	required: true,
			// 	message: '全日制学位',
			// 	trigger: 'change'
			// }],
			wordName: "xueWeiType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		xueZhiValue: {
			label: "学制",
			error: false,
			model: "",
			modelType: "xueZhiValue",
			prop: "xueZhiValue",
			placeholder: "请选择学制",
			array: [],
			wordName: "xueZhiType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		enterSchoolTime: {
			label: "入学日期",
			error: false,
			model: "",
			modelType: "enterSchoolTime",
			prop: "enterSchoolTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		graduateTime: {
			label: " 毕（肄）业日期",
			error: false,
			model: "",
			modelType: "graduateTime",
			prop: "graduateTime",
			placeholder: "YYYY-MM-DD",
			// rules: [{
			// 	required: true,
			// 	message: ' 毕（肄）业日期',
			// 	trigger: 'change'
			// }],
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		xueWeiTime: {
			label: "学位授予日期",
			error: false,
			model: "",
			modelType: "xueWeiTime",
			prop: "xueWeiTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		schoolName: {
			label: " 学校名称",
			error: false,
			model: "",
			modelType: "schoolName",
			prop: "schoolName",
			placeholder: "请输入学校名称",
			// rules: [{
			// 	required: true,
			// 	message: '学校名称',
			// 	trigger: 'change'
			// }],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		yxName: {
			label: " 院系名称",
			error: false,
			model: "",
			modelType: "yxName",
			prop: "yxName",
			placeholder: "请输入院系名称",
			// rules: [{
			// 	required: true,
			// 	message: '院系名称',
			// 	trigger: 'change'
			// }],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		majorName: {
			label: " 所学专业",
			error: false,
			model: "",
			modelType: "majorName",
			prop: "majorName",
			placeholder: "请输入所学专业",
			array: [],
			// rules: [{
			// 	required: true,
			// 	message: '所学专业',
			// 	trigger: 'change'
			// }],
			wordName: "majorNameType",
			type: 10, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		majorCategoryValue: {
			label: " 专业分类",
			error: false,
			model: "",
			modelType: "majorCategoryValue",
			prop: "majorCategoryValue",
			placeholder: "请输入专业分类",
			wordName: "majorCategoryType",
			array: [],
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		studySituationValue: {
			label: " 学习完成情况",
			error: false,
			model: "",
			modelType: "studySituationValue",
			prop: "studySituationValue",
			placeholder: "请输入学习完成情况",
			array: [{
				code: "1",
				name: "完成"
			}, {
				code: "2",
				name: "未完成"
			}],
			wordName: "studySituationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		educationStatusValue: {
			label: "学历审核状态",
			error: false,
			model: "",
			modelType: "educationStatusValue",
			prop: "educationStatusValue",
			placeholder: "请输入学历审核状态",
			array: [],
			wordName: "educationStatusType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		educationShNo: {
			label: "学历审核文号",
			error: false,
			model: "",
			modelType: "educationShNo",
			prop: "educationShNo",
			placeholder: "请输入学历审核文号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isEducationMarkValue: {
			label: "最高学历标识",
			error: false,
			model: "",
			modelType: "isEducationMarkValue",
			prop: "isEducationMarkValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isXwMarkValue: {
			label: "最高学位标识",
			error: false,
			model: "",
			modelType: "isXwMarkValue",
			prop: "isXwMarkValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	MZDPPersonOnJobEducation: {
		educationValue: {
			label: "在职学历",
			error: false,
			model: "",
			modelType: "educationValue",
			prop: "educationValue",
			placeholder: "请选择在职学历",
			array: [],
			// rules: [{
			// 	required: true,
			// 	message: '在职学历',
			// 	trigger: 'change'
			// }],
			wordName: "educationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		xueWeiValue: {
			label: "在职学位",
			error: false,
			model: "",
			modelType: "xueWeiValue",
			prop: "xueWeiValue",
			placeholder: "请选择在职学位",
			array: [],
			// rules: [{
			// 	required: true,
			// 	message: '在职学位',
			// 	trigger: 'change'
			// }],
			wordName: "xueWeiType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		xueZhiValue: {
			label: "学制",
			error: false,
			model: "",
			modelType: "xueZhiValue",
			prop: "xueZhiValue",
			placeholder: "请选择学制",
			array: [],
			wordName: "xueZhiType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		enterSchoolTime: {
			label: "入学日期",
			error: false,
			model: "",
			modelType: "enterSchoolTime",
			prop: "enterSchoolTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		graduateTime: {
			label: " 毕（肄）业日期",
			error: false,
			model: "",
			modelType: "graduateTime",
			prop: "graduateTime",
			placeholder: "YYYY-MM-DD",
			// rules: [{
			// 	required: true,
			// 	message: ' 毕（肄）业日期',
			// 	trigger: 'change'
			// }],
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		xueWeiTime: {
			label: "学位授予日期",
			error: false,
			model: "",
			modelType: "xueWeiTime",
			prop: "xueWeiTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		schoolName: {
			label: " 学校名称",
			error: false,
			model: "",
			modelType: "schoolName",
			prop: "schoolName",
			placeholder: "请输入学校名称",
			// rules: [{
			// 	required: true,
			// 	message: '学校名称',
			// 	trigger: 'change'
			// }],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		yxName: {
			label: " 院系名称",
			error: false,
			model: "",
			modelType: "yxName",
			prop: "yxName",
			placeholder: "请输入院系名称",
			// rules: [{
			// 	required: true,
			// 	message: '院系名称',
			// 	trigger: 'change'
			// }],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		majorName: {
			label: " 所学专业",
			error: false,
			model: "",
			modelType: "majorName",
			prop: "majorName",
			placeholder: "请输入所学专业",
			array: [],
			// rules: [{
			// 	required: true,
			// 	message: '所学专业',
			// 	trigger: 'change'
			// }],
			wordName: "majorNameType",
			type: 10, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		majorCategoryValue: {
			label: " 专业分类",
			error: false,
			model: "",
			modelType: "majorCategoryValue",
			prop: "majorCategoryValue",
			placeholder: "请输入专业分类",
			array: [],
			wordName: "majorCategoryType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		studySituationValue: {
			label: " 学习完成情况",
			error: false,
			model: "",
			modelType: "studySituationValue",
			prop: "studySituationValue",
			placeholder: "请输入学习完成情况",
			array: [{
				code: "1",
				name: "完成"
			}, {
				code: "2",
				name: "未完成"
			}],
			wordName: "studySituationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		educationStatusValue: {
			label: "学历审核状态",
			error: false,
			model: "",
			modelType: "educationStatusValue",
			prop: "educationStatusValue",
			placeholder: "请输入学历审核状态",
			array: [],
			wordName: "educationStatusType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		educationShNo: {
			label: "学历审核文号",
			error: false,
			model: "",
			modelType: "educationShNo",
			prop: "educationShNo",
			placeholder: "请输入学历审核文号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isEducationMarkValue: {
			label: "最高学历标识",
			error: false,
			model: "",
			modelType: "isEducationMarkValue",
			prop: "isEducationMarkValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isXwMarkValue: {
			label: "最高学位标识",
			error: false,
			model: "",
			modelType: "isXwMarkValue",
			prop: "isXwMarkValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	MZDPPersonWay: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		telphone: {
			label: "移动电话",
			error: false,
			model: "",
			modelType: "telphone",
			prop: "telphone",
			placeholder: "请输入移动电话",
			rules: [{
				required: true,
				message: '移动电话',
				trigger: 'blur',
				type:3
			}],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		fax: {
			label: "传真",
			error: false,
			model: "",
			modelType: "fax",
			prop: "fax",
			placeholder: "请输入传真",
			rules:[
			  { required: false, message: '请输入传真', trigger: 'blur',type:7 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		weixin: {
			label: "微信号",
			error: false,
			model: "",
			modelType: "weixin",
			prop: "weixin",
			placeholder: "请输入微信号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		qq: {
			label: "QQ号",
			error: false,
			model: "",
			modelType: "qq",
			prop: "qq",
			placeholder: "请输入QQ号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		email: {
			label: "电子邮箱",
			error: false,
			model: "",
			modelType: "email",
			prop: "email",
			placeholder: "请输入电子邮箱",
			rules:[
			  { required: false, message: '请输入邮箱地址', trigger: 'blur',type:"1" },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		otherContactInformation: {
			label: "其他联系方式",
			error: false,
			model: "",
			modelType: "otherContactInformation",
			prop: "otherContactInformation",
			placeholder: "请输入其他联系方式",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		homeDistrictValue: {
			label: "省 / 市 / 区 / 街道",
			error: false,
			model: [],
			modelType: "homeDistrictValue",
			prop: "homeDistrictValue",
			placeholder: "请选择省 / 市 / 区 / 街道",
			rules: [{
				required: true,
				message: '省 / 市 / 区 / 街道',
				trigger: 'blur'
			}],
			type: 3, //1:input 2:select 3:cascader 4:textarea 5:picker
			array: [],
			wordName: "homeDistrict",
			wordType: "",
			changes: changes
		},
		homeAddress: {
			label: "详细地址",
			error: false,
			model: "",
			modelType: "homeAddress",
			prop: "homeAddress",
			placeholder: "请输入详细地址",
			rules: [{
				required: true,
				message: '请输入详细地址',
				trigger: 'blur',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	},
};
var MZDPPersonOrginize = {
	MZDPPersonOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		partyValue: {
			label: "党派类别",
			error: false,
			model: "",
			modelType: "partyValue",
			prop: "partyValue",
			placeholder: "请选择党派类别",
			rules: [{
				required: true,
				message: '请选择党派类别',
				trigger: 'change'
			}],
			array: [],
			isPartyValue:true,
			wordName: "partyType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		// branchNameValue: {
		// 	label: "所在支部",
		// 	error: false,
		// 	model: "",
		// 	modelType: "branchNameValue",
		// 	prop: "branchNameValue",
		// 	placeholder: "请选择所在支部",
		// 	rules: [{
		// 		required: true,
		// 		message: '请选择所在支部',
		// 		trigger: 'change'
		// 	}],
		// 	array: [],
		// 	wordName: "branchNameType",
		// 	type: 13, //1:input 2:select 3:cascader 4:textarea 5:picker
		// 	changes: changes
		// },
		activistTime: {
			label: "成为积极分子时间",
			error: false,
			model: "",
			modelType: "activistTime",
			prop: "activistTime",
			placeholder: "YYYY-MM-DD",
			rules: [{
				required: true,
				message: '请选择成为积极分子时间',
				trigger: 'change'
			}],
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		joinPartyTime: {
			label: "加入党派时间",
			error: false,
			model: "",
			modelType: "joinPartyTime",
			prop: "joinPartyTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		servingPartyTime: {
			label: "任党派职务开始时间",
			error: false,
			model: "",
			modelType: "servingPartyTime",
			prop: "servingPartyTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		servingPartyEndTime: {
			label: "任党派职务结束时间",
			error: false,
			model: "",
			modelType: "servingPartyEndTime",
			prop: "servingPartyEndTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	},
};
var MZDPPersonWork = {
	MZDPPersonWork: {
		workUnit: {
			label: "工作单位及职务",
			error: false,
			model: "",
			modelType: "workUnit",
			prop: "workUnit",
			placeholder: "请输入工作单位及职务",
			rules: [{
				required: true,
				message: '请输入工作单位及职务',
				trigger: 'change'
			}],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		workGwei: {
			label: "工作（学习）岗位",
			error: false,
			model: "",
			modelType: "workGwei",
			prop: "workGwei",
			placeholder: "请选择岗位",
			// rules: [{
			// 	required: true,
			// 	message: '请选择岗位',
			// 	trigger: 'change'
			// }],
			array: [],
			wordName: "workGweiType",
			type: 10, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		joinWorkTime: {
			label: "参加工作日期",
			error: false,
			model: "",
			modelType: "joinWorkTime",
			prop: "joinWorkTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		speciality: {
			label: "专长",
			error: false,
			model: "",
			modelType: "speciality",
			prop: "speciality",
			placeholder: "请输入专长，不同专长用“，”逗号隔开，最多60个字",
			rules: [{
				required: false,
				message: '输入内容最多100个字',
				trigger: 'blur',
				type:10
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	},
	MZDPPersonAcademicTitle: [],
};
var MZDPPersonResume = {
	MZDPPersonResume: {
		resume: {
			label: "个人简介",
			error: false,
			model: "",
			modelType: "resume",
			prop: "resume",
			placeholder: "请输入200字以内的个人经历",
			rules: [{
				required: false,
				message: '请输入200字以内的个人经历',
				trigger: 'change',
				type:2
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		societyInfluence: {
			label: "主要工作成就及社会影响",
			error: false,
			model: "",
			modelType: "societyInfluence",
			prop: "societyInfluence",
			placeholder: "请输入内容，最多300个字",
			rules: [{
				required: false,
				message: '输入内容最多300个字符',
				trigger: 'blur',
				type:11
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		cultivateSituation: {
			label: "统一战线组织培训情况",
			error: false,
			model: "",
			modelType: "cultivateSituation",
			prop: "cultivateSituation",
			placeholder: "请输入内容，最多100个字符",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		societyDuties: {
			label: "主要社会职务",
			error: false,
			model: "",
			modelType: "societyDuties",
			prop: "societyDuties",
			placeholder: "请输入内容，最多100个字",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		tzIdea: {
			label: "统战部意见",
			error: false,
			model: "",
			modelType: "tzIdea",
			prop: "tzIdea",
			list: [{
				label: "同意"
			}, {
				label: "不同意"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		remarks: {
			label: "备注",
			error: false,
			model: "",
			modelType: "remarks",
			prop: "remarks",
			placeholder: "请输入内容，最多100个字",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	},
};
var MZDPPersonReward = {
	MZDPPersonReward: [],
	MZDPPersonPunish: [],
};
var MZDPPersonFamily = {
	MZDPPersonFamily: [],
};
var MZDPPersonCPPCC = {
	MZDPPersonCPPCC: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		categoryValue: {
			label: "类别",
			error: false,
			model: "",
			modelType: "categoryValue",
			prop: "categoryValue",
			placeholder: "请选择类别",
			array: [],
			wordName: "categoryType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		jieNo: {
			label: "届次",
			error: false,
			model: "",
			modelType: "jieNo",
			prop: "jieNo",
			placeholder: "请输入届次",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		recommendUnit: {
			label: "推荐单位",
			error: false,
			model: "",
			modelType: "recommendUnit",
			prop: "recommendUnit",
			placeholder: "请输入推荐单位",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		tmValue: {
			label: "提名类型",
			error: false,
			model: "",
			modelType: "tmValue",
			prop: "tmValue",
			placeholder: "请选择提名类型",
			array: [],
			wordName: "tmType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		recommendWayValue: {
			label: "推荐方式",
			error: false,
			model: "",
			modelType: "recommendWayValue",
			prop: "recommendWayValue",
			placeholder: "请选择推荐方式",
			array: [],
			wordName: "recommendWayType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		identityValue: {
			label: "身份",
			error: false,
			model: "",
			modelType: "identityValue",
			prop: "identityValue",
			placeholder: "请选择身份",
			array: [],
			wordName: "identityType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		creditCode: {
			label: "统一社会信用代码",
			error: false,
			model: "",
			modelType: "creditCode",
			prop: "creditCode",
			placeholder: "请输入统一社会信用代码",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			isShow:true,
			changes: changes
		},
		isLdlValue: {
			label: "是否领导干部",
			error: false,
			model: "",
			modelType: "isLdlValue",
			prop: "isLdlValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		ascriptionValue: {
			label: "所属界别",
			error: false,
			model: "",
			modelType: "ascriptionValue",
			prop: "ascriptionValue",
			placeholder: "请选择所属界别",
			array: [],
			wordName: "ascriptionType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isZxcwValue: {
			label: "是否政协常委",
			error: false,
			model: "",
			modelType: "isZxcwValue",
			prop: "isZxcwValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		delegationWs: {
			label: "所属委室（活动组）",
			error: false,
			model: "",
			modelType: "delegationWs",
			prop: "delegationWs",
			placeholder: "请输入内容",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		appraisetOpinionValue: {
			label: "政协年度履职评价意见",
			error: false,
			model: "",
			modelType: "appraisetOpinionValue",
			prop: "appraisetOpinionValue",
			placeholder: "请选择",
			array: [],
			wordName: "appraiseOpinionType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		appraiseGradeValue: {
			label: "非公经济人士综合评价意见",
			error: false,
			model: "",
			modelType: "appraiseGradeValue",
			prop: "appraiseGradeValue",
			placeholder: "请选择",
			array: [],
			wordName: "appraiseGradeType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		checkOpinionValue: {
			label: "部门联合审查意见",
			error: false,
			model: "",
			modelType: "checkOpinionValue",
			prop: "checkOpinionValue",
			placeholder: "请选择",
			array: [],
			wordName: "checkOpinionType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		material: {
			label: "考察材料",
			error: false,
			model: "",
			modelType: "material",
			prop: "material",
			placeholder: "请输入链接",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		meetingName: {
			label: "当选会议名称",
			error: false,
			model: "",
			modelType: "meetingName",
			prop: "meetingName",
			placeholder: "请输入单位",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		electionWayValue: {
			label: "产生方式",
			error: false,
			model: "",
			modelType: "electionWayValue",
			prop: "electionWayValue",
			placeholder: "请选择",
			array: [],
			wordName: "electionWayType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		jobStatusValue: {
			label: "职务状态",
			error: false,
			model: "",
			modelType: "jobStatusValue",
			prop: "jobStatusValue",
			placeholder: "请选择",
			array: [],
			wordName: "jobStatusType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	MZDPPersonNPC: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		categoryValue: {
			label: "类别",
			error: false,
			model: "",
			modelType: "categoryValue",
			prop: "categoryValue",
			placeholder: "请选择类别",
			array: [],
			wordName: "categoryType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		jieNo: {
			label: "届次",
			error: false,
			model: "",
			modelType: "jieNo",
			prop: "jieNo",
			placeholder: "请输入届次",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		recommendUnit: {
			label: "推荐单位",
			error: false,
			model: "",
			modelType: "recommendUnit",
			prop: "recommendUnit",
			placeholder: "请输入推荐单位",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		tmValue: {
			label: "提名类型",
			error: false,
			model: "",
			modelType: "tmValue",
			prop: "tmValue",
			placeholder: "请选择提名类型",
			array: [],
			wordName: "tmType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		tmWayValue: {
			label: "提名方式",
			error: false,
			model: "",
			modelType: "tmWayValue",
			prop: "tmWayValue",
			placeholder: "请输入提名方式",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		vocationGc: {
			label: "职业构成",
			error: false,
			model: "",
			modelType: "vocationGc",
			prop: "vocationGc",
			placeholder: "请输入职业构成",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isLdlValue: {
			label: "是否领导干部",
			error: false,
			model: "",
			modelType: "isLdlValue",
			prop: "isLdlValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isGyzValue: {
			label: "是否非公有制经济人士",
			error: false,
			model: "",
			modelType: "isGyzValue",
			prop: "isGyzValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isGqValue: {
			label: "是否归侨",
			error: false,
			model: "",
			modelType: "isGqValue",
			prop: "isGqValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		delegation: {
			label: "所属代表团",
			error: false,
			model: "",
			modelType: "delegation",
			prop: "delegation",
			placeholder: "请输入所属代表团",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isRdcwyyValue: {
			label: "是否人大常委会委员",
			error: false,
			model: "",
			modelType: "isRdcwyyValue",
			prop: "isRdcwyyValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		committeeMember: {
			label: "常委级别",
			error: false,
			model: "",
			modelType: "committeeMember",
			prop: "committeeMember",
			placeholder: "请输入常委级别",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		assessmentOpinionValue: {
			label: "人大届度履职评定意见",
			error: false,
			model: "",
			modelType: "assessmentOpinionValue",
			prop: "assessmentOpinionValue",
			placeholder: "请选择意见",
			array: [],
			wordName: "assessmentOpinionType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		appraiseGradeValue: {
			label: "公有制经济人士综合评价等次",
			error: false,
			model: "",
			modelType: "appraiseGradeValue",
			prop: "appraiseGradeValue",
			placeholder: "请选择",
			array: [],
			wordName: "appraiseGradeType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		material: {
			label: "考察材料",
			error: false,
			model: "",
			modelType: "material",
			prop: "material",
			placeholder: "请输入链接",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		xjUnit: {
			label: "选举单位",
			error: false,
			model: "",
			modelType: "xjUnit",
			prop: "xjUnit",
			placeholder: "请输入单位",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		electionWayValue: {
			label: "产生方式",
			error: false,
			model: "",
			modelType: "electionWayValue",
			prop: "electionWayValue",
			placeholder: "请选择",
			array: [],
			wordName: "electionWayType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		jobStatusValue: {
			label: "职务状态",
			error: false,
			model: "",
			modelType: "jobStatusValue",
			prop: "jobStatusValue",
			placeholder: "请选择",
			array: [],
			wordName: "jobStatusType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
};
var MZDPPersonAppointment = {
	MZDPPersonAppointment: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		mechanismName: {
			label: "任职机构名称",
			error: false,
			model: "",
			modelType: "mechanismName",
			prop: "mechanismName",
			placeholder: "请输入名称",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nsMechanismName: {
			label: "职务内设机构名称",
			error: false,
			model: "",
			modelType: "nsMechanismName",
			prop: "nsMechanismName",
			placeholder: "请输入名称",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		jobNameValue: {
			label: "职务名称",
			error: false,
			model: "",
			modelType: "jobNameValue",
			prop: "jobNameValue",
			placeholder: "请选择职务",
			array: [],
			wordName: "jobNameType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		jobExplain: {
			label: "职务说明",
			error: false,
			model: "",
			modelType: "jobExplain",
			prop: "jobExplain",
			placeholder: "请输入内容，最多100个字",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		rzJlTypeValue: {
			label: "相关任职经历标识",
			error: false,
			model: "",
			modelType: "rzJlTypeValue",
			prop: "rzJlTypeValue",
			placeholder: "请选择相关任职经历",
			array: [],
			wordName: "rzJlType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		responsibility: {
			label: "岗位职责",
			error: false,
			model: "",
			modelType: "responsibility",
			prop: "responsibility",
			placeholder: "请选择岗位职责",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nominateJgName: {
			label: "决定或提名任职机关的名称",
			error: false,
			model: "",
			modelType: "nominateJgName",
			prop: "nominateJgName",
			placeholder: "请输入名称",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nominateJgTime: {
			label: "决定或提名任职机关的日期",
			error: false,
			model: "",
			modelType: "nominateJgTime",
			prop: "nominateJgTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nominateJgNo: {
			label: "决定或提名任职机关的文号",
			error: false,
			model: "",
			modelType: "nominateJgNo",
			prop: "nominateJgNo",
			placeholder: "请输入文号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		appointmentName: {
			label: "批准任职的机关名称",
			error: false,
			model: "",
			modelType: "appointmentName",
			prop: "appointmentName",
			placeholder: "请输入名称",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		appointmentTime: {
			label: "批准任职的日期",
			error: false,
			model: "",
			modelType: "appointmentTime",
			prop: "appointmentTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		appointmentNo: {
			label: "批准任职的文号",
			error: false,
			model: "",
			modelType: "appointmentNo",
			prop: "appointmentNo",
			placeholder: "请输入文号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		servingStatusValue: {
			label: "任职状态",
			error: false,
			model: "",
			modelType: "servingStatusValue",
			prop: "servingStatusValue",
			placeholder: "请选择任职状态",
			array: [],
			wordName: "servingStatusType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nowJobTime: {
			label: "任现职务的日期",
			error: false,
			model: "",
			modelType: "nowJobTime",
			prop: "nowJobTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nowZjTime: {
			label: "任现职级的日期",
			error: false,
			model: "",
			modelType: "nowZjTime",
			prop: "nowZjTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nominateMzName: {
			label: "决定或提名免职机关的名称",
			error: false,
			model: "",
			modelType: "nominateMzName",
			prop: "nominateMzName",
			placeholder: "请输入名称",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nominateMzTime: {
			label: "决定或提名免职日期",
			error: false,
			model: "",
			modelType: "nominateMzTime",
			prop: "nominateMzTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		nominateMzNo: {
			label: "决定或提名免职的文号",
			error: false,
			model: "",
			modelType: "nominateMzNo",
			prop: "nominateMzNo",
			placeholder: "请输入文号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		dismissalName: {
			label: "批准免职的机关名称",
			error: false,
			model: "",
			modelType: "dismissalName",
			prop: "dismissalName",
			placeholder: "请输入名称",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		dismissalTime: {
			label: "批准免职的日期",
			error: false,
			model: "",
			modelType: "dismissalTime",
			prop: "dismissalTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		dismissalNo: {
			label: "批准免职的文号",
			error: false,
			model: "",
			modelType: "dismissalNo",
			prop: "dismissalNo",
			placeholder: "请输入文号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		ldZwValue: {
			label: "是否领导职务",
			error: false,
			model: "",
			modelType: "ldZwValue",
			prop: "ldZwValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		bzCyValue: {
			label: "是否是班子成员",
			error: false,
			model: "",
			modelType: "bzCyValue",
			prop: "bzCyValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		bzCyCategoryValue: {
			label: "班子成员类别",
			error: false,
			model: "",
			modelType: "bzCyCategoryValue",
			prop: "bzCyCategoryValue",
			placeholder: "请选择类别",
			array: [],
			wordName: "bzCyCategoryType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isBgJbValue: {
			label: "是否破格提拔",
			error: false,
			model: "",
			modelType: "isBgJbValue",
			prop: "isBgJbValue",
			list: [{
				code: "1",
				label: "是"
			}, {
				code: "2",
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
};
var nonpartyOrginize = {
	nonpartyOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	}
}
var minorityOrginize = {
	minorityOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	}
}
var ReligionOrginize = {
	ReligionOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		religionValue: {
			label: "宗教类型",
			error: false,
			model: "",
			modelType: "religionValue ",
			prop: "religionValue ",
			placeholder: "请选择宗教类型",
			array: [],
			rules: [{
				required: true,
				message: '请选择宗教类型',
				trigger: 'change'
			}],
			isLian: true,
			wordName: "religionType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		religionFactionValue: {
			label: "宗教派别",
			error: false,
			model: "",
			modelType: "religionFactionValue ",
			prop: "religionFactionValue ",
			placeholder: "请选择宗教派别",
			array: [],
			rules: [{
				required: true,
				message: '请选择宗教派别',
				trigger: 'change'
			}],
			isShow: true,
			iserLian: true,
			wordName: "religionFactionType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		jobIdentityValue: {
			label: "教职身份",
			error: false,
			model: "",
			modelType: "jobIdentityValue ",
			prop: "jobIdentityValue ",
			placeholder: "请选择教职身份",
			array: [],
			rules: [{
				required: true,
				message: '请选择教职身份',
				trigger: 'change'
			}],
			wordName: "jobIdentityType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		appellationValue: {
			label: " 宗教称谓",
			error: false,
			model: "",
			modelType: "appellationValue ",
			prop: "appellationValue ",
			placeholder: "请输入宗教称谓",
			rules: [{
				required: true,
				message: '请输入宗教称谓',
				trigger: 'change'
			}],
			isShow: false,
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		educationLevelValue: {
			label: "国民教育程度",
			error: false,
			model: "",
			modelType: "educationLevelValue ",
			prop: "educationLevelValue ",
			placeholder: "请选择国民教育程度",
			array: [],
			wordName: "educationLevelType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		religionLevelValue: {
			label: "宗教教育程度",
			error: false,
			model: "",
			modelType: "religionLevelValue ",
			prop: "religionLevelValue ",
			placeholder: "请选择宗教教育程度",
			array: [],
			wordName: "religionLevelType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		tjServingSituation: {
			label: "在宗教团体任职的情况",
			error: false,
			model: "",
			modelType: "tjServingSituation",
			prop: "",
			placeholder: "请输入内容，最多100个字",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		csServingSituation: {
			label: "在宗教活动场所任职的情况",
			error: false,
			model: "",
			modelType: "csServingSituation",
			prop: "",
			placeholder: "请输入内容，最多100个字",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		yxServingSituation: {
			label: "在宗教院校任职的情况",
			error: false,
			model: "",
			modelType: "yxServingSituation",
			prop: "",
			placeholder: "请输入内容，最多100个字",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		awardUnitName: {
			label: "宗教教职人员证书颁发单位",
			error: false,
			model: "",
			modelType: "awardUnitName",
			prop: "awardUnitNam",
			placeholder: "请输入颁发单位",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		certificateNo: {
			label: "证书编号",
			error: false,
			model: "",
			modelType: "certificateNo",
			prop: "certificateNo",
			placeholder: "请输入证书编号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		certificateTime: {
			label: "证书颁发时间",
			error: false,
			model: "",
			modelType: "certificateTime",
			prop: "certificateTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		bajgName: {
			label: "备案机关",
			error: false,
			model: "",
			modelType: "bajgName",
			prop: "bajgName",
			placeholder: "请输入备案机关",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		baTime: {
			label: "备案时间",
			error: false,
			model: "",
			modelType: "baTime",
			prop: "baTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		remarks: {
			label: "备注",
			error: false,
			model: "",
			modelType: "remarks",
			prop: "",
			placeholder: "请输入内容，最多100个字",
			rules: [{
				required: false,
				message: '请输入100字符',
				trigger: 'change',
				type:9
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	}
}
var NewSocialOrginize = {
	NewSocialOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		categoryValue: {
			label: "所属类别",
			error: false,
			model: "",
			modelType: "categoryValue ",
			prop: "categoryValue ",
			placeholder: "请选择类别",
			array: [],
			rules: [{
				required: true,
				message: '请选择类别',
				trigger: 'change'
			}],
			wordName: "categoryType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	}
}
var HongkongMacaoOrginize = {
	HongkongMacaoOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		personValue:{
			label: "人员类别",
			error: false,
			model: "",
			modelType: "personValue ",
			prop: "personValue ",
			placeholder: "请人员类别",
			array: [],
			rules: [{
				required: true,
				message: '人员类别',
				trigger: 'change'
			}],
			wordName: "personType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	}
}
var TaiwanOrginize = {
	TaiwanOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		personValue:{
			label: "人员类别",
			error: false,
			model: "",
			modelType: "personValue ",
			prop: "personValue ",
			placeholder: "请人员类别",
			array: [],
			rules: [{
				required: true,
				message: '人员类别',
				trigger: 'change'
			}],
			wordName: "personType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	}
}
var dwIntellectualOrginize = {
	dwIntellectualOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		industryValue: {
			label: "行业类型",
			error: false,
			model: "",
			modelType: "industryValue ",
			prop: "industryValue ",
			placeholder: "请选择类别",
			array: [],
			rules: [{
				required: true,
				message: '请选择类别',
				trigger: 'change'
			}],
			wordName: "industryType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	}
}
var OtherContactOrginize = {
	OtherContactOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		categoryValue: {
			label: "身份类型",
			error: false,
			model: "",
			modelType: "categoryValue ",
			prop: "categoryValue ",
			placeholder: "请身份类型",
			array: [],
			rules: [{
				required: true,
				message: '身份类型',
				trigger: 'change'
			}],
			wordName: "categoryType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	}
}
var StudyAbroadOrginize = {
	StudyAbroadOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		originaLocal: {
			label: "原侨居地",
			error: false,
			model: "",
			modelType: "originaLocal",
			prop: "originaLocal",
			placeholder: "请输入原侨居地",
			rules: [{
				required: true,
				message: '请输入原侨居地',
				trigger: 'blur'
			}],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		returnHome: {
			label: "回国时间",
			error: false,
			model: "",
			modelType: "returnHome",
			prop: "returnHome",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		vocationValue: {
			label: "职业状态",
			error: false,
			model: "",
			modelType: "vocationValue",
			prop: "vocationValue",
			placeholder: "请选择职业状态",
			array: [],
			select: "true",
			wordName: "vocationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	StudyAbroad: {}
}
var OverseasChineseOrginize = {
	OverseasChineseOrginize: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		identity: {
			label: "涉侨身份",
			error: false,
			model: "",
			modelType: "identity",
			prop: "identity",
			placeholder: "请选择涉侨身份",
			array: [],
			select: "true",
			wordName: "identityType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		goAbroad: {
			label: "出国时间",
			error: false,
			model: "",
			modelType: "goAbroad",
			prop: "goAbroad",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		country: {
			label: "现居住国",
			error: false,
			model: "",
			modelType: "country",
			prop: "country",
			placeholder: "请选择现居住国",
			array: [],
			wordName: "countryType",
			type: 10, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	other:{},
	GuiqiaoQiaojuan: {},
	RetireMessage: {},
}
var fgyzPersonOrginize = {
	enterpriseBasicMessage: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		enterpriseName: {
			label: "企业名称",
			error: false,
			model: "",
			modelType: "enterpriseName",
			prop: "enterpriseName",
			placeholder: "请输入企业名称",
			// rules: [{
			// 	required: true,
			// 	message: '请输入企业名称',
			// 	trigger: 'blur'
			// }],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		creditCode: {
			label: "工商登记注册号或统一社会信用代码",
			error: false,
			model: "",
			modelType: "creditCode",
			prop: "creditCode",
			placeholder: "请输入",
			// rules: [{
			// 	required: true,
			// 	message: '请输入工商登记注册号或统一社会信用代码',
			// 	trigger: 'blur'
			// }],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		enterpriseInsureNo: {
			label: "企业社会保险登记号",
			error: false,
			model: "",
			modelType: "enterpriseInsureNo",
			prop: "enterpriseInsureNo",
			placeholder: "请输入企业社会保险登记号",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		establishTime: {
			label: "企业成立时间",
			error: false,
			model: "",
			modelType: "establishTime",
			prop: "establishTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		enterpriseDistrictValue: {
			label: "企业通讯地址区",
			error: false,
			model: [],
			modelType: "enterpriseDistrictValue",
			prop: "enterpriseDistrictValue",
			placeholder: "请选择省/市/区/街道",
			type: 3, //1:input 2:select 3:cascader 4:textarea 5:picker
			array: [],
			wordName: "enterpriseDistrictType",
			wordType: "",
			changes: changes
		},
		enterpriseAddress: {
			label: "详细地址",
			error: false,
			model: "",
			modelType: "enterpriseAddress",
			prop: "",
			placeholder: "请输入详细地址,精确到门牌号",
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		postalCode: {
			label: "邮编",
			error: false,
			model: "",
			modelType: "postalCode",
			prop: "postalCode",
			placeholder: "请输入邮编",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		fax: {
			label: "传真",
			error: false,
			model: "",
			modelType: "fax",
			prop: "fax",
			placeholder: "请输入传真",
			rules:[
			  { required: false, message: '请输入传真', trigger: 'blur',type:7 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		email: {
			label: "企业邮箱",
			error: false,
			model: "",
			modelType: "email",
			prop: "email",
			placeholder: "请输入企业邮箱",
			rules:[
			  { required: false, message: '请输入企业邮箱', trigger: 'blur',type:1 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		secretaryName: {
			label: "秘书姓名",
			error: false,
			model: "",
			modelType: "secretaryName",
			prop: "secretaryName",
			placeholder: "请输入秘书姓名",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		djzcValue: {
			label: "登记注册类型",
			error: false,
			model: "",
			modelType: "djzcValue",
			prop: "djzcValue",
			placeholder: "请选择登记注册类型",
			array: [],
			wordName: "djzcType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		mainBusiness: {
			label: "主营业务",
			error: false,
			model: "",
			modelType: "mainBusiness",
			prop: "",
			placeholder: "请输入内容，最多200个字",
			rules: [{
				required: false,
				message: '请输入200字符',
				trigger: 'change',
				type:2
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		mainproductService: {
			label: "主要产品与服务",
			error: false,
			model: "",
			modelType: "mainproductService",
			prop: "",
			placeholder: "请输入内容，最多200个字",
			rules: [{
				required: false,
				message: '请输入200字符',
				trigger: 'change',
				type:2
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	fgyzPerson: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		personalDetails: {
			label: "履历情况",
			error: false,
			model: "",
			modelType: "personalDetails",
			prop: "",
			placeholder: "请输入内容，最多200个字",
			rules: [{
				required: false,
				message: '请输入200字符',
				trigger: 'change',
				type:2
			}],
			type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		}
	},
	workersBasicMessage: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		zgTotal: {
			label: "职工总人数",
			error: false,
			model: "",
			inputType: Number,
			modelType: "zgTotal",
			prop: "zgTotal",
			placeholder: "请输入职工总人数",
			rules:[
			  { required: false, message: '请输入职工总人数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		yjsTotal: {
			label: "研究生人数",
			error: false,
			model: "",
			modelType: "yjsTotal",
			prop: "yjsTotal",
			placeholder: "请输入研究生人数",
			rules:[
			  { required: false, message: '请输入研究生人数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		dzTotal: {
			label: "大专以上人数",
			error: false,
			model: "",
			modelType: "dzTotal",
			prop: "dzTotal",
			placeholder: "请输入大专以上人数",
			rules:[
			  { required: false, message: '请输入大专以上人数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		bsTotal: {
			label: "博士人数",
			error: false,
			model: "",
			modelType: "bsTotal",
			prop: "bsTotal",
			placeholder: "请输入博士人数",
			rules:[
			  { required: false, message: '请输入博士人数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		zgjZcTotal: {
			label: "中高级职称人数",
			error: false,
			model: "",
			modelType: "zgjZcTotal",
			prop: "zgjZcTotal",
			placeholder: "请输入中高级职称人数",
			rules:[
			  { required: false, message: '请输入中高级职称人数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		signingRate: {
			label: "劳动合同签订率(%)",
			error: false,
			model: "",
			modelType: "signingRate",
			prop: "signingRate",
			placeholder: "请输入劳动合同签订率(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		contractSituationValue: {
			label: "企业签订集体合同情况",
			error: false,
			model: "",
			modelType: "contractSituationValue",
			prop: "contractSituationValue",
			placeholder: "请选择企业签订集体合同情况",
			array: [],
			wordName: "contractSituationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		pensionInsure: {
			label: "养老保险(%)",
			error: false,
			model: "",
			modelType: "pensionInsure",
			prop: "pensionInsure",
			placeholder: "请输入养老保险(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		medicalInsure: {
			label: "医疗保险(%)",
			error: false,
			model: "",
			modelType: "medicalInsure",
			prop: "medicalInsure",
			placeholder: "请输入医疗保险(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		unemploymentInsure: {
			label: "失业保险(%)",
			error: false,
			model: "",
			modelType: "unemploymentInsure",
			prop: "unemploymentInsure",
			placeholder: "请输入失业保险(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		injuryInsure: {
			label: "工伤保险(%)",
			error: false,
			model: "",
			modelType: "injuryInsure",
			prop: "injuryInsure",
			placeholder: "请输入工伤保险(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		birthInsure: {
			label: "生育保险(%)",
			error: false,
			model: "",
			modelType: "birthInsure",
			prop: "birthInsure",
			placeholder: "请输入生育保险(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		fiveInsure: {
			label: "五险合一(%)",
			error: false,
			model: "",
			modelType: "fiveInsure",
			prop: "fiveInsure",
			placeholder: "请输入五险合一(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	workersManageSituation: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		registeredCapital: {
			label: "注册资金(万元)",
			error: false,
			model: "",
			modelType: "registeredCapital",
			prop: "registeredCapital",
			placeholder: "请输入注册资金(万元)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		totalAssets: {
			label: "总资产(万元)",
			error: false,
			model: "",
			modelType: "totalAssets",
			prop: "totalAssets",
			placeholder: "请输入总资产(万元)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		netAssets: {
			label: "净资产(万元)",
			error: false,
			model: "",
			modelType: "netAssets",
			prop: "netAssets",
			placeholder: "请输入净资产(万元)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		assetsLiabilities: {
			label: "资产负债率(%)",
			error: false,
			model: "",
			modelType: "assetsLiabilities",
			prop: "assetsLiabilities",
			placeholder: "请输入资产负债率(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	workersManageSituationz: {
		dnOperatingIncome: {
			label: "当年",
			error: false,
			model: "",
			modelType: "dnOperatingIncome",
			prop: "dnOperatingIncome",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		synOperatingIncome: {
			label: "上一年",
			error: false,
			model: "",
			modelType: "synOperatingIncome",
			prop: "synOperatingIncome",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		synOperatingIncome: {
			label: "上两年",
			error: false,
			model: "",
			modelType: "synOperatingIncome",
			prop: "synOperatingIncome",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	workersManageSituationl: {
		dnTotalProfit: {
			label: "当年",
			error: false,
			model: "",
			modelType: "dnTotalProfit",
			prop: "dnTotalProfit",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		synTotalProfit: {
			label: "上一年",
			error: false,
			model: "",
			modelType: "synTotalProfit",
			prop: "synTotalProfit",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		slnTotalProfit: {
			label: "上两年",
			error: false,
			model: "",
			modelType: "slnTotalProfit",
			prop: "slnTotalProfit",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	workersManageSituationn: {
		dnTaxAmount: {
			label: "当年",
			error: false,
			model: "",
			modelType: "dnTaxAmount",
			prop: "dnTaxAmount",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		synTaxAmount: {
			label: "上一年",
			error: false,
			model: "",
			modelType: "synTaxAmount",
			prop: "synTaxAmount",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		slnTaxAmount: {
			label: "上两年",
			error: false,
			model: "",
			modelType: "slnTaxAmount",
			prop: "slnTaxAmount",
			placeholder: "请输入数字,支持两位小数",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	technologicalInnovation: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		isGxjsEnterpriseValue: {
			label: "是否被认定为高新技术企业值",
			error: false,
			model: "",
			modelType: "isGxjsEnterpriseValue",
			prop: "isGxjsEnterpriseValue",
			list: [{
				label: "是"
			}, {
				label: "否"
			}],
			type: 6, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		patentNumber: {
			label: "发明专利数",
			error: false,
			model: "",
			modelType: "patentNumber",
			prop: "patentNumber",
			placeholder: "请输入发明专利数",
			rules:[
			  { required: false, message: '请输入发明专利数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		incomeProportion: {
			label: "研发费用占销售收入比例(%)",
			error: false,
			model: "",
			modelType: "incomeProportion",
			prop: "incomeProportion",
			placeholder: "请输入研发费用占销售收入比例(%)",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	technologicalInnovationy: {
		gjjYfNumber: {
			label: "国家级",
			error: false,
			model: "",
			modelType: "gjjYfNumber",
			prop: "gjjYfNumber",
			placeholder: "请输入国家级",
			rules:[
			  { required: false, message: '请输入国家级', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		sjYfNumber: {
			label: "省级",
			error: false,
			model: "",
			modelType: "sjYfNumber",
			prop: "sjYfNumber",
			placeholder: "请输入省级",
			rules:[
			  { required: false, message: '请输入省级', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		sxjYfNumber: {
			label: "市县级",
			error: false,
			model: "",
			modelType: "sxjYfNumber",
			prop: "sxjYfNumber",
			placeholder: "请输入市县级",
			rules:[
			  { required: false, message: '请输入市县级', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	technologicalInnovationh: {
		gjjJbjNumber: {
			label: "国家级",
			error: false,
			model: "",
			modelType: "gjjJbjNumber",
			prop: "gjjJbjNumber",
			placeholder: "请输入国家级",
			rules:[
			  { required: false, message: '请输入国家级', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		sjJbjNumber: {
			label: "省级",
			error: false,
			model: "",
			modelType: "sjJbjNumber",
			prop: "sjJbjNumber",
			placeholder: "请输入省级",
			rules:[
			  { required: false, message: '请输入省级', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		sxjJbjNumber: {
			label: "市县级",
			error: false,
			model: "",
			modelType: "sxjJbjNumber",
			prop: "sxjJbjNumber",
			placeholder: "请输入市县级",
			rules:[
			  { required: false, message: '请输入市县级', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	latelyFiveYear: [],
	nearTwoYear: {
		nearTwoYearValue: {
			label: "",
			error: false,
			model: "",
			modelType: "nearTwoYearValue",
			prop: "nearTwoYearValue",
			list: [],
			type: 8, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	enterprisePartySituation: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		foundTime: {
			label: "团组织成立时间",
			error: false,
			model: "",
			modelType: "foundTime",
			prop: "foundTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		partyValue: {
			label: "团组织结构",
			error: false,
			model: "",
			modelType: "partyValue",
			prop: "partyValue",
			placeholder: "请选择团组织结构",
			array: [],
			wordName: "partyType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		memberNum: {
			label: "团员人数",
			error: false,
			model: "",
			modelType: "memberNum",
			prop: "memberNum",
			placeholder: "请输入团员人数",
			rules:[
			  { required: false, message: '请输入团员人数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		tradeUnionTime: {
			label: "企业工会组织成立时间",
			error: false,
			model: "",
			modelType: "tradeUnionTime",
			prop: "tradeUnionTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	},
	partyOrganizationSituation: {
		id: {
			label: "id",
			error: false,
			model: "",
			modelType: "id",
			prop: "id",
			type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		foundTime: {
			label: "党组织成立时间",
			error: false,
			model: "",
			modelType: "foundTime",
			prop: "foundTime",
			placeholder: "YYYY-MM-DD",
			type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		organizationValue: {
			label: "党组织结构",
			error: false,
			model: "",
			modelType: "organizationValue",
			prop: "organizationValue",
			placeholder: "请选择党组织结构",
			array: [],
			wordName: "organizationType",
			type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		sjYfNumber: {
			label: "党员人数",
			error: false,
			model: "",
			modelType: "sjYfNumber",
			prop: "sjYfNumber",
			placeholder: "请输入党员人数",
			rules:[
			  { required: false, message: '请输入党员人数', trigger: 'blur',type:6 },
			],
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
		shuJiName: {
			label: "党(总支或支部)书记",
			error: false,
			model: "",
			modelType: "shuJiName",
			prop: "shuJiName",
			placeholder: "请输入党(总支或支部)书记",
			type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
			changes: changes
		},
	}
}
const clernAll = function(obj) {
	if(obj.fgyzPersonOrginize){
		for (let i in obj.fgyzPersonOrginize) {
			let a = obj.fgyzPersonOrginize[i];
			if (Array.isArray(a) == true) {
				for (var j = 0; j < a.length; j++) {
					a[j].model = "";
					a[j].error = false;
					if(a[j].type==3||a[j].type==9){
						a[j].model = [];
						a[j].error = false;
					}
				}
			} else {
				for (let j in a) {
					a[j].model = "";
					a[j].error = false;
					if(a[j].type==3||a[j].type==9){
						a[j].model = [];
						a[j].error = false;
					}
				}
			}
		}
	}
	if(obj.MZDPPersonBasic){
		for (let i in obj.MZDPPersonBasic) {
			for (let n in obj.MZDPPersonBasic[i]) {
				obj.MZDPPersonBasic[i][n].model = "";
				obj.MZDPPersonBasic[i][n].error = false;
				if(obj.MZDPPersonBasic[i][n].type==3||obj.MZDPPersonBasic[i][n].type==9){
					obj.MZDPPersonBasic[i][n].model = [];
					obj.MZDPPersonBasic[i][n].error = false;
				}
			}
		}
	}
	if(obj.OverseasChineseOrginize){
		for (let i in obj.OverseasChineseOrginize) {
			for (let n in obj.OverseasChineseOrginize[i]) {
				obj.OverseasChineseOrginize[i][n].model = "";
				obj.OverseasChineseOrginize[i][n].error = false;
				if(obj.OverseasChineseOrginize[i][n].type==3||obj.OverseasChineseOrginize[i][n].type==9){
					obj.OverseasChineseOrginize[i][n].model = [];
					obj.OverseasChineseOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.StudyAbroadOrginize){
		for (let i in obj.StudyAbroadOrginize) {
			for (let n in obj.StudyAbroadOrginize[i]) {
				obj.StudyAbroadOrginize[i][n].model = "";
				obj.StudyAbroadOrginize[i][n].error = false;
				if(obj.StudyAbroadOrginize[i][n].type==3||obj.StudyAbroadOrginize[i][n].type==9){
					obj.StudyAbroadOrginize[i][n].model = [];
					obj.StudyAbroadOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.OtherContactOrginize){
		for (let i in obj.OtherContactOrginize) {
			for (let n in obj.OtherContactOrginize[i]) {
				obj.OtherContactOrginize[i][n].model = "";
				obj.OtherContactOrginize[i][n].error = false;
				if(obj.OtherContactOrginize[i][n].type==3||obj.OtherContactOrginize[i][n].type==9){
					obj.OtherContactOrginize[i][n].model = [];
					obj.OtherContactOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.dwIntellectualOrginize){
		for (let i in obj.dwIntellectualOrginize) {
			for (let n in obj.dwIntellectualOrginize[i]) {
				obj.dwIntellectualOrginize[i][n].model = "";
				obj.dwIntellectualOrginize[i][n].error = false;
				if(obj.dwIntellectualOrginize[i][n].type==3||obj.dwIntellectualOrginize[i][n].type==9){
					obj.dwIntellectualOrginize[i][n].model = [];
					obj.dwIntellectualOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.MZDPPersonOrginize){
		for (let i in obj.MZDPPersonOrginize) {
			for (let n in obj.MZDPPersonOrginize[i]) {
				obj.MZDPPersonOrginize[i][n].model = "";
				obj.MZDPPersonOrginize[i][n].error = false;
				if(obj.MZDPPersonOrginize[i][n].type==3||obj.MZDPPersonOrginize[i][n].type==9){
					obj.MZDPPersonOrginize[i][n].model = [];
					obj.MZDPPersonOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.nonpartyOrginize){
		for (let i in obj.nonpartyOrginize) {
			for (let n in obj.nonpartyOrginize[i]) {
				obj.nonpartyOrginize[i][n].model = "";
				obj.nonpartyOrginize[i][n].error = false;
				if(obj.nonpartyOrginize[i][n].type==3||obj.nonpartyOrginize[i][n].type==9){
					obj.nonpartyOrginize[i][n].model = [];
					obj.nonpartyOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.minorityOrginize){
		for (let i in obj.minorityOrginize) {
			for (let n in obj.minorityOrginize[i]) {
				obj.minorityOrginize[i][n].model = "";
				obj.minorityOrginize[i][n].error = false;
				if(obj.minorityOrginize[i][n].type==3||obj.minorityOrginize[i][n].type==9){
					obj.minorityOrginize[i][n].model = [];
					obj.minorityOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.ReligionOrginize){
		for (let i in obj.ReligionOrginize) {
			for (let n in obj.ReligionOrginize[i]) {
				obj.ReligionOrginize[i][n].model = "";
				obj.ReligionOrginize[i][n].error = false;
				if(obj.ReligionOrginize[i][n].type==3||obj.ReligionOrginize[i][n].type==9){
					obj.ReligionOrginize[i][n].model = [];
					obj.ReligionOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.NewSocialOrginize){
		for (let i in obj.NewSocialOrginize) {
			for (let n in obj.NewSocialOrginize[i]) {
				obj.NewSocialOrginize[i][n].model = "";
				obj.NewSocialOrginize[i][n].error = false;
				if(obj.NewSocialOrginize[i][n].type==3||obj.NewSocialOrginize[i][n].type==9){
					obj.NewSocialOrginize[i][n].model = [];
					obj.NewSocialOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.HongkongMacaoOrginize){
		for (let i in obj.HongkongMacaoOrginize) {
			for (let n in obj.HongkongMacaoOrginize[i]) {
				obj.HongkongMacaoOrginize[i][n].model = "";
				obj.HongkongMacaoOrginize[i][n].error = false;
				if(obj.HongkongMacaoOrginize[i][n].type==3||obj.HongkongMacaoOrginize[i][n].type==9){
					obj.HongkongMacaoOrginize[i][n].model = [];
					obj.HongkongMacaoOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.TaiwanOrginize){
		for (let i in obj.TaiwanOrginize) {
			for (let n in obj.TaiwanOrginize[i]) {
				obj.TaiwanOrginize[i][n].model = "";
				obj.TaiwanOrginize[i][n].error = false;
				if(obj.TaiwanOrginize[i][n].type==3||obj.TaiwanOrginize[i][n].type==9){
					obj.TaiwanOrginize[i][n].model = [];
					obj.TaiwanOrginize[i][n].error = false;
				}
			}
		}
	}
	if(obj.MZDPPersonResume){
		for (let i in obj.MZDPPersonResume) {
			for (let n in obj.MZDPPersonResume[i]) {
				obj.MZDPPersonResume[i][n].model = "";
				obj.MZDPPersonResume[i][n].error = false;
				if(obj.MZDPPersonResume[i][n].type==3||obj.MZDPPersonResume[i][n].type==9){
					obj.MZDPPersonResume[i][n].model = [];
					obj.MZDPPersonResume[i][n].error = false;
				}
			}
		}
	}
	if(obj.MZDPPersonCPPCC){
		for (let i in obj.MZDPPersonCPPCC) {
			for (let n in obj.MZDPPersonCPPCC[i]) {
				obj.MZDPPersonCPPCC[i][n].model = "";
				obj.MZDPPersonCPPCC[i][n].error = false;
				if(obj.MZDPPersonCPPCC[i][n].type==3||obj.MZDPPersonCPPCC[i][n].type==9){
					obj.MZDPPersonCPPCC[i][n].model = [];
					obj.MZDPPersonCPPCC[i][n].error = false;
				}
			}
		}
	}
	if(obj.MZDPPersonAppointment){
		for (let i in obj.MZDPPersonAppointment) {
			for (let n in obj.MZDPPersonAppointment[i]) {
				obj.MZDPPersonAppointment[i][n].model = "";
				obj.MZDPPersonAppointment[i][n].error = false;
				if(obj.MZDPPersonAppointment[i][n].type==3||obj.MZDPPersonAppointment[i][n].type==9){
					obj.MZDPPersonAppointment[i][n].model = [];
					obj.MZDPPersonAppointment[i][n].error = false;
				}
			}
		}
	}
	if(obj.MZDPPersonReward){
		for (let i in obj.MZDPPersonReward) {
			if (Array.isArray(obj.MZDPPersonReward[i]) == true) {
				obj.MZDPPersonReward[i] = [];
			}
		}
	}
	if(obj.MZDPPersonFamily){
		for (let i in obj.MZDPPersonFamily) {
			if (Array.isArray(obj.MZDPPersonFamily[i]) == true) {
				obj.MZDPPersonFamily[i] = [];
			}
		}
	}
	if(obj.MZDPPersonWork){
		for (let i in obj.MZDPPersonWork) {
			if (Array.isArray(obj.MZDPPersonWork[i]) == true) {
				obj.MZDPPersonWork[i] = [];
			} else {
				for (let n in obj.MZDPPersonWork[i]) {
					obj.MZDPPersonWork[i][n].model = "";
					obj.MZDPPersonWork[i][n].error = false;
					if(obj.MZDPPersonWork[i][n].type==3||obj.MZDPPersonWork[i][n].type==9){
						obj.MZDPPersonWork[i][n].model = [];
						obj.MZDPPersonWork[i][n].error = false;
					}
				}
			}
		}
	}
	
}
const editNum = function(row, publicArray, dicObj) {
	let MZDPPersonFullEducation = publicArray.MZDPPersonBasic.MZDPPersonFullEducation;
	let MZDPPersonOnJobEducation = publicArray.MZDPPersonBasic.MZDPPersonOnJobEducation;
	let MZDPPersonBasic = publicArray.MZDPPersonBasic.MZDPPersonBasic;
	let MZDPPersonWay = publicArray.MZDPPersonBasic.MZDPPersonWay;
	let MZDPPersonOrginize = publicArray.MZDPPersonOrginize.MZDPPersonOrginize;
	let MZDPPersonWork = publicArray.MZDPPersonWork.MZDPPersonWork;
	let MZDPPersonResume = publicArray.MZDPPersonResume.MZDPPersonResume;
	let MZDPPersonCPPCC = publicArray.MZDPPersonCPPCC.MZDPPersonCPPCC;
	let MZDPPersonNPC = publicArray.MZDPPersonCPPCC.MZDPPersonNPC;
	let MZDPPersonAppointment = publicArray.MZDPPersonAppointment.MZDPPersonAppointment;
	let MZDPPersonAcademicTitle = publicArray.MZDPPersonWork.MZDPPersonAcademicTitle;
	let MZDPPersonReward = publicArray.MZDPPersonReward.MZDPPersonReward;
	let MZDPPersonPunish = publicArray.MZDPPersonReward.MZDPPersonPunish;
	let MZDPPersonFamily = publicArray.MZDPPersonFamily.MZDPPersonFamily;
	let nonpartyOrginize = publicArray.nonpartyOrginize.nonpartyOrginize;
	let ReligionOrginize = publicArray.ReligionOrginize.ReligionOrginize;
	let NewSocialOrginize = publicArray.NewSocialOrginize.NewSocialOrginize;
	let HongkongMacaoOrginize = publicArray.HongkongMacaoOrginize.HongkongMacaoOrginize;
	let TaiwanOrginize = publicArray.TaiwanOrginize.TaiwanOrginize;
	let dwIntellectualOrginize = publicArray.dwIntellectualOrginize.dwIntellectualOrginize;
	let OtherContactOrginize = publicArray.OtherContactOrginize.OtherContactOrginize;
	let minorityOrginize = publicArray.minorityOrginize.minorityOrginize;
	let StudyAbroadOrginize = publicArray.StudyAbroadOrginize.StudyAbroadOrginize;
	let StudyAbroad = publicArray.StudyAbroadOrginize.StudyAbroad;
	let OverseasChineseOrginize = publicArray.OverseasChineseOrginize.OverseasChineseOrginize;
	let other = publicArray.OverseasChineseOrginize.other;
	let GuiqiaoQiaojuan = publicArray.OverseasChineseOrginize.GuiqiaoQiaojuan;
	let RetireMessage = publicArray.OverseasChineseOrginize.RetireMessage;
	let enterpriseBasicMessage = publicArray.fgyzPersonOrginize.enterpriseBasicMessage;
	let fgyzPerson = publicArray.fgyzPersonOrginize.fgyzPerson;
	let workersBasicMessage = publicArray.fgyzPersonOrginize.workersBasicMessage;
	let workersManageSituation = publicArray.fgyzPersonOrginize.workersManageSituation;
	let workersManageSituationz = publicArray.fgyzPersonOrginize.workersManageSituationz;
	let workersManageSituationl = publicArray.fgyzPersonOrginize.workersManageSituationl;
	let workersManageSituationn = publicArray.fgyzPersonOrginize.workersManageSituationn;
	let technologicalInnovation = publicArray.fgyzPersonOrginize.technologicalInnovation;
	let technologicalInnovationy = publicArray.fgyzPersonOrginize.technologicalInnovationy;
	let technologicalInnovationh = publicArray.fgyzPersonOrginize.technologicalInnovationh;
	let latelyFiveYear = publicArray.fgyzPersonOrginize.latelyFiveYear;
	let nearTwoYear = publicArray.fgyzPersonOrginize.nearTwoYear;
	let enterprisePartySituation = publicArray.fgyzPersonOrginize.enterprisePartySituation;
	let partyOrganizationSituation = publicArray.fgyzPersonOrginize.partyOrganizationSituation;
	if (row.familyMemberMessage) {
		for (var i = 0; i < row.familyMemberMessage.length; i++) {
			var item = { //家庭信息模板
				name: {
					label: "姓名",
					error: false,
					model: "",
					modelType: "name",
					prop: "name",
					placeholder: "请输入姓名",
					rules: [{
						required: true,
						message: '请输入姓名',
						trigger: 'blur'
					}],
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				appellation: {
					label: "称谓",
					error: false,
					model: "",
					modelType: "appellation",
					prop: "appellation",
					placeholder: "请输入称谓",
					rules: [{
						required: true,
						message: '请输入称谓',
						trigger: 'blur'
					}],
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				identity: {
					label: "公民身份证号",
					error: false,
					model: "",
					modelType: "identity",
					prop: "identity",
					placeholder: "请输入身份证号",
					rules: [{
						required: false,
						message: '请输入身份证号',
						trigger: 'blur',
						type: 5
					}],
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				birthTime: {
					label: "出生日期",
					error: false,
					model: "",
					modelType: "birthTime",
					prop: "birthTime",
					placeholder: "YYYY-MM-DD",
					// rules: [{
					// 	required: true,
					// 	message: '请选择出生日期',
					// 	trigger: 'change'
					// }],
					type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				genderValue: {
					label: "性别",
					error: false,
					model: "",
					modelType: "genderValue ",
					prop: "genderValue ",
					placeholder: "请选择性别",
					array: publicArray.MZDPPersonFamilyItem.genderValue.array,
					// rules: [{
					// 	required: true,
					// 	message: '性别',
					// 	trigger: 'change'
					// }],
					wordName: "genderType",
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				workUnitName: {
					label: "工作单位名称",
					error: false,
					model: "",
					modelType: "workUnitName",
					prop: "workUnitName",
					placeholder: "请输入工作单位名称",
					// rules: [{
					// 	required: true,
					// 	message: '请输入工作单位名称',
					// 	trigger: 'blur'
					// }],
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				unitAdress: {
					label: "单位所在政区",
					error: false,
					model: [],
					modelType: "unitAdress",
					prop: "unitAdress",
					placeholder: "请选择省/请选择市/请选择区",
					type: 9, //1:input 2:select 3:cascader 4:textarea 5:picker
					array: publicArray.MZDPPersonFamilyItem.unitAdressValue.array,
					wordName: "unitAdressType",
					wordType: "",
					changes: changes
				},
				presentValue: {
					label: "现状",
					error: false,
					model: "",
					modelType: "presentValue",
					prop: "presentValue",
					placeholder: "请选择状态",
					wordName: "presentType",
					array: publicArray.MZDPPersonFamilyItem.presentValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				politicalFaceValue: {
					label: "政治面貌",
					error: false,
					model: "",
					modelType: "politicalFaceValue",
					prop: "politicalFaceValue",
					placeholder: "请选择面貌",
					wordName: "politicalFaceType",
					array: publicArray.MZDPPersonFamilyItem.politicalFaceValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				nationalityValue: {
					label: "国籍",
					error: false,
					model: "",
					modelType: "nationalityValue",
					prop: "nationalityValue",
					placeholder: "请选择国籍",
					wordName: "nationalityType",
					array: publicArray.MZDPPersonFamilyItem.nationalityValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
			};
			for (let m in row.familyMemberMessage[i]) {
				for (let n in item) {
					if (n == m) {
						item[n].model = row.familyMemberMessage[i][m];
						if (item[n].array) {
							item[n].NoType = row.familyMemberMessage[i][item[n].wordName];
						}
						if (item[n].type == 3||item[n].type==9) {
							if (row.familyMemberMessage[i][m]) {
								item[n].model = row.familyMemberMessage[i][m].split(",");
							}
							item[n][item[n].wordName] = row.familyMemberMessage[i][item[n].wordName];
							item[n].wordType = row.familyMemberMessage[i][m];
						}
					}
				}
			}
			MZDPPersonFamily.push(item)
		}
	}
	if (row.punishMessage) {
		for (var i = 0; i < row.punishMessage.length; i++) {
			var item = { //惩罚模板
				punishNo: {
					label: "惩处序号",
					error: false,
					model: "",
					modelType: "punishNo",
					prop: "punishNo",
					placeholder: "请输入序号",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishJgName: {
					label: "惩处单位",
					error: false,
					model: "",
					modelType: "punishJgName",
					prop: "punishJgName",
					placeholder: "请输入惩处单位",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishLevelValue: {
					label: "惩处机关级别",
					error: false,
					model: "",
					modelType: "punishLevelValue",
					prop: "punishLevelValue",
					placeholder: "请选择级别",
					wordName: "punishLevelType",
					array: publicArray.MZDPPersonPunishItem.punishLevelValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishValue: {
					label: "惩处类型",
					error: false,
					model: "",
					modelType: "punishValue",
					prop: "punishValue",
					placeholder: "请选择类型",
					wordName: "punishType",
					array: publicArray.MZDPPersonPunishItem.punishValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishTime: {
					label: "惩处批准时间",
					error: false,
					model: "",
					modelType: "punishTime",
					prop: "punishTime",
					placeholder: "YYYY-MM-DD",
					type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				zwccValue: {
					label: "惩处时职务层次",
					error: false,
					model: "",
					modelType: "zwccValue",
					prop: "zwccValue",
					placeholder: "请选择层次",
					wordName: "zwccType",
					array: publicArray.MZDPPersonPunishItem.zwccValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishReason: {
					label: "惩处原因",
					error: false,
					model: "",
					modelType: "punishReason",
					prop: "punishReason",
					placeholder: "请输入内容，最多100个字",
					rules: [{
						required: false,
						message: '请输入100字符',
						trigger: 'change',
						type:9
					}],
					type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishTerm: {
					label: "惩处影响期限",
					error: false,
					model: "",
					modelType: "punishTerm",
					prop: "punishTerm",
					placeholder: "YYYY-MM-DD",
					type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishExplain: {
					label: "惩处影响结果",
					error: false,
					model: "",
					modelType: "punishExplain",
					prop: "punishExplain",
					placeholder: "请输入内容，最多100个字",
					rules: [{
						required: false,
						message: '请输入100字符',
						trigger: 'change',
						type:9
					}],
					type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				punishFile: {
					label: "惩处决定文件",
					error: false,
					model: "",
					modelType: "punishFile",
					prop: "punishFile",
					placeholder: "请输入文件名称",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				cancelTime: {
					label: "撤销时间",
					error: false,
					model: "",
					modelType: "cancelTime",
					prop: "cancelTime",
					placeholder: "YYYY-MM-DD",
					type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				}
			};
			for (let m in row.punishMessage[i]) {
				for (let n in item) {
					if (n == m) {
						item[n].model = row.punishMessage[i][m];
						if(item[n].array){
							item[n].NoType = row.punishMessage[i][item[n].wordName];
						}
					}
				}
			}
			MZDPPersonPunish.push(item)

		}

	}
	if (row.rewardMessage) {
		for (var i = 0; i < row.rewardMessage.length; i++) {
			var item = { //奖励模板
				rewardNo: {
					label: "奖励序号",
					error: false,
					model: "",
					modelType: "rewardNo",
					prop: "rewardNo",
					placeholder: "请输入序号",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				rewardUnitValue: {
					label: "做出奖励单位",
					error: false,
					model: "",
					modelType: "rewardUnitValue",
					prop: "rewardUnitValue",
					placeholder: "请输入奖励单位",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				rewardLevelValue: {
					label: "奖励级别",
					error: false,
					model: "",
					modelType: "rewardLevelValue",
					prop: "rewardLevelValue",
					placeholder: "请选择奖励级别",
					wordName: "rewardLevelType",
					array: publicArray.MZDPPersonRewardItem.rewardLevelValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				rewardName: {
					label: "奖励名称",
					error: false,
					model: "",
					modelType: "rewardName",
					prop: "rewardName",
					placeholder: "请输入奖励名称",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				rewardTime: {
					label: "奖励批准时间",
					error: false,
					model: "",
					modelType: "rewardTime",
					prop: "rewardTime",
					placeholder: "YYYY-MM-DD",
					type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				rewardReason: {
					label: "奖励原因",
					error: false,
					model: "",
					modelType: "rewardReason",
					prop: "rewardReason",
					placeholder: "请输入内容，最多100个字",
					rules: [{
						required: false,
						message: '请输入100字符',
						trigger: 'change',
						type:9
					}],
					type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				zwccValue: {
					label: "奖励时职务层次",
					error: false,
					model: "",
					modelType: "zwccValue",
					prop: "zwccValue",
					placeholder: "请选择层次",
					wordName: "zwccType",
					array: publicArray.MZDPPersonRewardItem.zwccValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				rewardFile: {
					label: "奖励决定文件",
					error: false,
					model: "",
					modelType: "rewardFile",
					prop: "rewardFile",
					placeholder: "请输入文件名称",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				rewardExplain: {
					label: "奖励说明",
					error: false,
					model: "",
					modelType: "rewardExplain",
					prop: "rewardExplain",
					placeholder: "请输入内容，最多100个字",
					rules: [{
						required: false,
						message: '请输入100字符',
						trigger: 'change',
						type:9
					}],
					type: 4, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				}
			};
			for (let m in row.rewardMessage[i]) {
				for (let n in item) {
					if (n == m) {
						item[n].model = row.rewardMessage[i][m];
						if(item[n].array){
							item[n].NoType = row.rewardMessage[i][item[n].wordName];
						}
					}
				}
			}
			MZDPPersonReward.push(item)

		}

	}
	if (row.takeOfficeSeniority) {
		window.console.log(publicArray.MZDPPersonAcademicTitleItem.zyJsLevelValue.array)
		for (var i = 0; i < row.takeOfficeSeniority.length; i++) {
			var item = { //职称模板
				zzRzZgValue: {
					label: "专技任职资格（职称）",
					error: false,
					model: "",
					modelType: "zzRzZgValue",
					prop: "zzRzZgValue",
					placeholder: "请选择职称",
					// rules: [{
					// 	required: true,
					// 	message: '请选择职称',
					// 	trigger: 'change'
					// }],
					wordName: "zzRzZgType",
					array: publicArray.MZDPPersonAcademicTitleItem.zzRzZgValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				zyJsLevelValue: {
					label: "取得专业技术职务级别",
					error: false,
					model: "",
					modelType: "zyJsLevelValue",
					prop: "zyJsLevelValue",
					placeholder: "请选择级别",
					// rules: [{
					// 	required: true,
					// 	message: '请选择级别',
					// 	trigger: 'change'
					// }],
					wordName: "zyJsLevelType",
					array: publicArray.MZDPPersonAcademicTitleItem.zyJsLevelValue.array,
					type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				zyJsLeveTime: {
					label: "取得专业技术职务日期",
					error: false,
					model: "",
					modelType: "zyJsLeveTime",
					prop: "zyJsLeveTime",
					placeholder: "YYYY-MM-DD",
					// rules: [{
					// 	required: true,
					// 	message: '请选择日期',
					// 	trigger: 'change'
					// }],
					type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				getChannelValue: {
					label: "取得资格途径",
					error: false,
					model: "",
					modelType: "getChannelValue",
					prop: "getChannelValue",
					placeholder: "请输入途径",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				},
				examName: {
					label: "评委会或考试名称",
					error: false,
					model: "",
					modelType: "examName",
					prop: "examName",
					placeholder: "请输入评委会或考试名称",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: publicArray.changes
				}
			};
			for (let m in row.takeOfficeSeniority[i]) {
				for (let n in item) {
					if (n == m) {
						item[n].model = row.takeOfficeSeniority[i][m];
						if (item[n].array) {
							item[n].NoType = row.takeOfficeSeniority[i][item[n].wordName];
						}
					}
				}
			}
			MZDPPersonAcademicTitle.push(item)
			window.console.log(MZDPPersonAcademicTitle)
		}
	}
	if (row.servingSituation) {
		for (let j in row.servingSituation) {
			for (let n in MZDPPersonAppointment) {
				if (n == j) {
					MZDPPersonAppointment[n].model = row.servingSituation[j];
					if(MZDPPersonAppointment[n].array){
						MZDPPersonAppointment[n].NoType = row.servingSituation[MZDPPersonAppointment[n].wordName];
					}
				}
			}
		}
	}
	if (row.npcDeputies) {
		for (let j in row.npcDeputies) {
			for (let n in MZDPPersonNPC) {
				if (n == j) {
					MZDPPersonNPC[n].model = row.npcDeputies[j];
					if(MZDPPersonNPC[n].array){
						MZDPPersonNPC[n].NoType = row.npcDeputies[MZDPPersonNPC[n].wordName];
					}
				}
			}
		}
	}
	if (row.cppccMember) {
		for (let j in row.cppccMember) {
			for (let n in MZDPPersonCPPCC) {
				if (n == j) {
					MZDPPersonCPPCC[n].model = row.cppccMember[j];
					if(MZDPPersonCPPCC[n].array){
						MZDPPersonCPPCC[n].NoType = row.cppccMember[MZDPPersonCPPCC[n].wordName];
					}
				}
			}
		}

	}
	if (row.personBasicMessage) {
		for (let j in row.personBasicMessage) {
			for (let n in MZDPPersonResume) {
				if (n == j) {
					MZDPPersonResume[n].model = row.personBasicMessage[j];
				}
			}
		}
	}
	if (row.personBasicMessage) {
		for (let j in row.personBasicMessage) {
			for (let n in MZDPPersonWork) {
				if (n == j) {
					MZDPPersonWork[n].model = row.personBasicMessage[j];
					if(MZDPPersonWork[n].array){
						MZDPPersonWork[n].NoType = row.personBasicMessage[MZDPPersonWork[n].wordName];
					}
				}
			}
		}
	}
	if (row.retireMessage) {
		var item = { //离退休模板
			id: {
				label: "id",
				error: false,
				model: "",
				modelType: "id",
				prop: "id",
				type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			retireCategory: {
				label: "离退类别",
				error: false,
				model: "",
				modelType: "retireCategory",
				prop: "retireCategory",
				placeholder: "请输入离退类别",
				rules: [{
					required: true,
					message: '请输入离退类别',
					trigger: 'blur'
				}],
				type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			retireTime: {
				label: "离退时间",
				error: false,
				model: "",
				modelType: "retireTime",
				prop: "retireTime",
				placeholder: "YYYY-MM-DD",
				type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			oldRetireValue: {
				label: "离退前级别",
				error: false,
				model: "",
				modelType: "oldRetireValue",
				prop: "oldRetireValue",
				placeholder: "请选择职业状态",
				array: publicArray.RetireMessageItem.oldRetireValue.array,
				wordName: "oldRetireType",
				type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			treatment: {
				label: "离退休前干部待遇 ",
				error: false,
				model: "",
				modelType: "treatment",
				prop: "treatment",
				placeholder: "请输入离退休前干部待遇",
				type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			treatmentLb: {
				label: "干部待遇类别 ",
				error: false,
				model: "",
				modelType: "treatmentLb",
				prop: "treatmentLb",
				placeholder: "请输入干部待遇类别",
				type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			companyName: {
				label: "管理单位名称",
				error: false,
				model: "",
				modelType: "companyName",
				prop: "companyName",
				placeholder: "请输入管理单位名称",
				type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			pzCompanyName: {
				label: "退(离)批准单位名称 ",
				error: false,
				model: "",
				modelType: "pzCompanyName",
				prop: "pzCompanyName",
				placeholder: "请输入退(离)批准单位名称 ",
				type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			ratifyNo: {
				label: "退(离)批准文号 ",
				error: false,
				model: "",
				modelType: "ratifyNo",
				prop: "ratifyNo",
				placeholder: "请输入退(离)批准文号 ",
				type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			}
		}
		for (let j in row.retireMessage) {
			for (let n in item) {
				if (j == n) {
					item[n].model = row.retireMessage[j];
				}
				if (item[n].array) {
					item[n].NoType = row.retireMessage[item[n].wordName];
				}
			}
		}
		publicArray.StudyAbroadOrginize.StudyAbroad = item;
		publicArray.OverseasChineseOrginize.RetireMessage = item;
	}
	if (row.guiqiaoQiaojuan) {
		var item1 = { //归侨，侨眷模板
			id: {
				label: "id",
				error: false,
				model: "",
				modelType: "id",
				prop: "id",
				type: 0, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			originalAddressValue: {
				label: "原侨居地",
				error: false,
				model: "",
				modelType: "originalAddressValue",
				prop: "originalAddressValue",
				placeholder: "请输入原侨居地",
				rules: [{
					required: true,
					message: '请输入原侨居地',
					trigger: 'blur'
				}],
				type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			returnHomeTime: {
				label: "回国时间",
				error: false,
				model: "",
				modelType: "returnHomeTime",
				prop: "returnHomeTime",
				placeholder: "YYYY-MM-DD",
				type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
			vocationValue: {
				label: "职业状态",
				error: false,
				model: "",
				modelType: "vocationValue",
				prop: "vocationValue",
				placeholder: "请选择职业状态",
				array: publicArray.GuiqiaoQiaojuanItem.vocationValue.array,
				select: "true",
				wordName: "vocationType",
				type: 2, //1:input 2:select 3:cascader 4:textarea 5:picker
				changes: changes
			},
		}
		for (let j in row.guiqiaoQiaojuan) {
			for (let n in item1) {
				if (j == n) {
					item1[n].model = row.guiqiaoQiaojuan[j];
				}
				if (item1[n].array) {
					item1[n].NoType = row.guiqiaoQiaojuan[item1[n].wordName];
				}
			}
		}
		publicArray.OverseasChineseOrginize.GuiqiaoQiaojuan = item1;
	}
	for (let j in row) {
		for (let n in OverseasChineseOrginize) {
			if (n == j) {
				OverseasChineseOrginize[n].model = row[j];
				if (OverseasChineseOrginize[n].array) {
					OverseasChineseOrginize[n].NoType = row[OverseasChineseOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in StudyAbroadOrginize) {
			if (n == j) {
				StudyAbroadOrginize[n].model = row[j];
				if (StudyAbroadOrginize[n].array) {
					StudyAbroadOrginize[n].NoType = row[StudyAbroadOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in OtherContactOrginize) {
			if (n == j) {
				OtherContactOrginize[n].model = row[j];
				if (OtherContactOrginize[n].array) {
					OtherContactOrginize[n].NoType = row[OtherContactOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in HongkongMacaoOrginize) {
			if (n == j) {
				HongkongMacaoOrginize[n].model = row[j];
				if (HongkongMacaoOrginize[n].array) {
					HongkongMacaoOrginize[n].NoType = row[HongkongMacaoOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in dwIntellectualOrginize) {
			if (n == j) {
				dwIntellectualOrginize[n].model = row[j];
				if (dwIntellectualOrginize[n].array) {
					dwIntellectualOrginize[n].NoType = row[dwIntellectualOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in NewSocialOrginize) {
			if (n == j) {
				NewSocialOrginize[n].model = row[j];
				if (NewSocialOrginize[n].array) {
					NewSocialOrginize[n].NoType = row[NewSocialOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in MZDPPersonOrginize) {
			if (n == j) {
				MZDPPersonOrginize[n].model = row[j];
				if (MZDPPersonOrginize[n].array) {
					MZDPPersonOrginize[n].NoType = row[MZDPPersonOrginize[n].wordName];
				}
			}
		}
	}
	window.console.log(row.religionValue)
	if (typeof(row.religionValue)=="object"||row.religionValue) {
		window.console.log(dicObj)
		for (let j in row) {
			for (let n in ReligionOrginize) {
				if (n == j) {
					ReligionOrginize[n].model = row[j];
					if (ReligionOrginize[n].array) {
						ReligionOrginize[n].NoType = row[ReligionOrginize[n].wordName];
					}
				}
				if (ReligionOrginize[n].isLian) {
					var arr = [];
					for (var i = 0; i < dicObj.religionFactionValue.length; i++) {
						if (dicObj.religionFactionValue[i].pcode == ReligionOrginize[n].model) {
							arr.push(dicObj.religionFactionValue[i]);
						}
					}
					if (ReligionOrginize[n].model != "") {
						ReligionOrginize.religionFactionValue.array = arr;
					}
					if (ReligionOrginize[n].model == "4302" || ReligionOrginize[n].model == "4301" || ReligionOrginize[n].model ==
						"4305") {
						ReligionOrginize.religionFactionValue.model = "";
						ReligionOrginize.religionFactionValue.NoType = "";
						ReligionOrginize.religionFactionValue.isShow = true;
						delete ReligionOrginize.religionFactionValue["rules"]
						ReligionOrginize.appellationValue.isShow = true;
						ReligionOrginize.appellationValue.model = "";
						delete ReligionOrginize.appellationValue["rules"]
						ReligionOrginize.appellationValue.placeholder = ""
						var arr = [];
						for (var i = 0; i < dicObj.jobIdentityValue.length; i++) {
							if (dicObj.jobIdentityValue[i].pcode == ReligionOrginize[n].model) {
								arr.push(dicObj.jobIdentityValue[i]);
							}
						}
						ReligionOrginize.jobIdentityValue.array = arr;
					}
				}
				if (ReligionOrginize[n].iserLian) {
					var arr = [];
					for (var i = 0; i < dicObj.jobIdentityValue.length; i++) {
						if (dicObj.jobIdentityValue[i].pcode == ReligionOrginize[n].model) {
							arr.push(dicObj.jobIdentityValue[i]);
						}
					}
					if (ReligionOrginize[n].model == "") {
						ReligionOrginize[n].isShow = true;
					} else {
						ReligionOrginize[n].isShow = false;
						ReligionOrginize.jobIdentityValue.array = arr;
					}
				}
			}
		}
	}
	for (let j in row) {
		for (let n in minorityOrginize) {
			if (n == j) {
				minorityOrginize[n].model = row[j];
				if (minorityOrginize[n].array) {
					minorityOrginize[n].NoType = row[minorityOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in nonpartyOrginize) {
			if (n == j) {
				nonpartyOrginize[n].model = row[j];
				if (nonpartyOrginize[n].array) {
					nonpartyOrginize[n].NoType = row[nonpartyOrginize[n].wordName];
				}
			}
		}
	}
	for (let j in row) {
		for (let n in TaiwanOrginize) {
			if (n == j) {
				TaiwanOrginize[n].model = row[j];
				if (TaiwanOrginize[n].array) {
					TaiwanOrginize[n].NoType = row[TaiwanOrginize[n].wordName];
				}
			}
		}
	}
	if (row.personBasicMessage) {
		window.console.log(row.personBasicMessage)
		for (let j in row.personBasicMessage) {
			for (let n in MZDPPersonBasic) {
				if (n == j) {
					if (MZDPPersonBasic[n].type == 3||MZDPPersonBasic[n].type == 9) {
						if (row.personBasicMessage[j]) {
							MZDPPersonBasic[n].model = row.personBasicMessage[j].split(",");
						}
						MZDPPersonBasic[n][MZDPPersonBasic[n].wordName] = row.personBasicMessage[MZDPPersonBasic[n].wordName];
						MZDPPersonBasic[n].wordType = row.personBasicMessage[j];
					}else{
						MZDPPersonBasic[n].model = row.personBasicMessage[j];
					}
					if (MZDPPersonBasic[n].array) {
						MZDPPersonBasic[n].NoType = row.personBasicMessage[MZDPPersonBasic[n].wordName];
					}
				}
			}
		}
		window.console.log(MZDPPersonBasic)
	}
	if (row.contactInformation) {
		for (let j in row.contactInformation) {
			for (let n in MZDPPersonWay) {
				if (n == j) {
					MZDPPersonWay[n].model = row.contactInformation[j];
					if (MZDPPersonWay[n].type == 3||MZDPPersonWay[n].type == 9) {
						if (row.contactInformation[j]) {
							MZDPPersonWay[n].model = row.contactInformation[j].split(",");
						}
						MZDPPersonWay[n].wordType = row.contactInformation[j];
						MZDPPersonWay[n].NoType = row.contactInformation[MZDPPersonWay[n].wordName];
					}
				}
			}
		}
		window.console.log(MZDPPersonWay)
	}
	if (row.educationcMessage) {
		for (var i = 0; i < row.educationcMessage.length; i++) {
			if (row.educationcMessage[i].category == 1) {
				let obj1 = row.educationcMessage[i];
				for (let j in obj1) {
					for (let n in MZDPPersonFullEducation) {
						if (n == j) {
							MZDPPersonFullEducation[n].model = obj1[j];
							if (MZDPPersonFullEducation[n].array) {
								MZDPPersonFullEducation[n].NoType = row.educationcMessage[i][MZDPPersonFullEducation[n].wordName];
							}
						}
					}
				}
			} else {
				let obj1 = row.educationcMessage[i];
				for (let j in obj1) {
					for (let n in MZDPPersonOnJobEducation) {
						if (n == j) {
							MZDPPersonOnJobEducation[n].model = obj1[j];
							if (MZDPPersonOnJobEducation[n].array) {
								MZDPPersonOnJobEducation[n].NoType = row.educationcMessage[i][MZDPPersonOnJobEducation[n].wordName];
							}
						}
					}
				}
			}
		}
	}
	if (row.fgyzPerson) {
		for (let j in row.fgyzPerson) {
			for (let n in fgyzPerson) {
				if (j == n) {
					fgyzPerson[n].model = row.fgyzPerson[j];
					if (fgyzPerson[n].array) {
						fgyzPerson[n].NoType = row[fgyzPerson[n].wordName];
					}
				}
			}
		}
	}
	if (row.enterpriseBasicMessage && row.enterpriseBasicMessage != null) {
		for (let j in row.enterpriseBasicMessage) {
			for (let n in enterpriseBasicMessage) {
				if (j == n) {
					enterpriseBasicMessage[n].model = row.enterpriseBasicMessage[j];
					if (enterpriseBasicMessage[n].array) {
						enterpriseBasicMessage[n].NoType = row[enterpriseBasicMessage[n].wordName];
					}
				}
			}
		}
	}
	if (row.workersBasicMessage && row.workersBasicMessage != null) {
		for (let j in row.workersBasicMessage) {
			for (let n in workersBasicMessage) {
				if (j == n) {
					workersBasicMessage[n].model = row.workersBasicMessage[j];
					if (workersBasicMessage[n].array) {
						workersBasicMessage[n].NoType = row[workersBasicMessage[n].wordName];
					}
				}
			}
		}
	}
	if (row.workersManageSituation && row.workersManageSituation != null) {
		for (let j in row.workersManageSituation) {
			for (let n in workersManageSituation) {
				if (j == n) {
					workersManageSituation[n].model = row.workersManageSituation[j];
					if (workersManageSituation[n].array) {
						workersManageSituation[n].NoType = row[workersManageSituation[n].wordName];
					}
				}
			}
			for (let n in workersManageSituationz) {
				if (j == n) {
					workersManageSituationz[n].model = row.workersManageSituation[j];
					if (workersManageSituationz[n].array) {
						workersManageSituationz[n].NoType = row[workersManageSituationz[n].wordName];
					}
				}
			}
			for (let n in workersManageSituationl) {
				if (j == n) {
					workersManageSituationl[n].model = row.workersManageSituation[j];
					if (workersManageSituationl[n].array) {
						workersManageSituationl[n].NoType = row[workersManageSituationl[n].wordName];
					}
				}
			}
			for (let n in workersManageSituationn) {
				if (j == n) {
					workersManageSituationn[n].model = row.workersManageSituation[j];
					if (workersManageSituationn[n].array) {
						workersManageSituationn[n].NoType = row[workersManageSituationn[n].wordName];
					}
				}
			}
		}
	}
	if (row.technologicalInnovation && row.technologicalInnovation != null) {
		for (let j in row.technologicalInnovation) {
			for (let n in technologicalInnovation) {
				if (j == n) {
					technologicalInnovation[n].model = row.technologicalInnovation[j];
					if (technologicalInnovation[n].array) {
						technologicalInnovation[n].NoType = row[technologicalInnovation[n].wordName];
					}
				}
			}
			for (let n in technologicalInnovationy) {
				if (j == n) {
					technologicalInnovationy[n].model = row.technologicalInnovation[j];
					if (technologicalInnovationy[n].array) {
						technologicalInnovationy[n].NoType = row[technologicalInnovationy[n].wordName];
					}
				}
			}
			for (let n in technologicalInnovationh) {
				if (j == n) {
					technologicalInnovationh[n].model = row.technologicalInnovation[j];
					if (technologicalInnovationh[n].array) {
						technologicalInnovationh[n].NoType = row[technologicalInnovationh[n].wordName];
					}
				}
			}
		}
	}
	if (row.partyOrganizationSituation && row.partyOrganizationSituation != null) {
		for (let j in row.partyOrganizationSituation) {
			for (let n in partyOrganizationSituation) {
				if (j == n) {
					partyOrganizationSituation[n].model = row.partyOrganizationSituation[j];
					if (partyOrganizationSituation[n].array) {
						partyOrganizationSituation[n].NoType = row[partyOrganizationSituation[n].wordName];
					}
				}
			}
		}
	}
	if (row.enterprisePartySituation && row.enterprisePartySituation != null) {
		for (let j in row.enterprisePartySituation) {
			for (let n in enterprisePartySituation) {
				if (j == n) {
					enterprisePartySituation[n].model = row.enterprisePartySituation[j];
					if (enterprisePartySituation[n].array) {
						enterprisePartySituation[n].NoType = row[enterprisePartySituation[n].wordName];
					}
				}
			}
		}
	}
	if (row.latelyFiveYear) {
		for (var i = 0; i < row.latelyFiveYear.length; i++) {
			var item = {
				itemTime: {
					label: "项目时间",
					error: false,
					model: "",
					modelType: "itemTime",
					prop: "itemTime",
					placeholder: "YYYY-MM-DD",
					// rules: [{
					// 	required: true,
					// 	message: '请选择项目时间',
					// 	trigger: 'change'
					// }],
					type: 5, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: changes
				},
				itemAddress: {
					label: "项目地点",
					error: false,
					model: "",
					modelType: "itemAddress",
					prop: "itemAddress",
					placeholder: "请输入项目地点",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: changes
				},
				cyUnit: {
					label: "参与单位",
					error: false,
					model: "",
					modelType: "cyUnit",
					prop: "cyUnit",
					placeholder: "请输入参与单位",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: changes
				},
				contributeMoney: {
					label: "实际捐款捐物(万元)",
					error: false,
					model: "",
					modelType: "contributeMoney",
					prop: "contributeMoney",
					placeholder: "请输入实际捐款捐物(万元)",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: changes
				},
				witnessUnit: {
					label: "证明人单位",
					error: false,
					model: "",
					modelType: "witnessUnit",
					prop: "witnessUnit",
					placeholder: "请输入证明人单位",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: changes
				},
				witnessName: {
					label: "证明人姓名",
					error: false,
					model: "",
					modelType: "witnessName",
					prop: "witnessName",
					placeholder: "请输入证明人姓名",
					type: 1, //1:input 2:select 3:cascader 4:textarea 5:picker
					changes: changes
				},
			}
			for (let m in row.latelyFiveYear[i]) {
				for (let n in item) {
					if (n == m) {
						item[n].model = row.latelyFiveYear[i][m];
					}
				}
			}
			latelyFiveYear.push(item);
		}

	}
}
const reset = function(obj) {
	for (let i in obj) {
		if (Array.isArray(obj[i]) == true) {
			if (obj[i].length > 0) {
				for (var m = 0; m < obj[i].length; m++) {
					for (let n in obj[i][m]) {
						obj[i][m][n].model = "";
					}
				}
				obj[i] = [];
			}

		} else {
			for (let n in obj[i]) {
				if (n != "id") {
					obj[i][n].model = "";
				}
			}
		}

	}
}
const checkOut = function(obj,that) {
	window.console.log(that)
	let errorArray = [];
	let flag = false;
	for (let item in obj) {
		if (Array.isArray(obj[item]) == true) {
			for (var i = 0; i < obj[item].length; i++) {
				for (let it in obj[item][i]) {
					if (obj[item][i][it].rules) {
						if(obj[item][i][it].rules[0].required==true){
							if (obj[item][i][it].model == "") {
								obj[item][i][it].error = true;
								errorArray.push("true");
							} else {
								let items = obj[item][i][it];
								that.checkeds(items.rules[0].type,items.model,function(res){
									if(res){
										items.error = true;
										items.placeholder = res.message;
										errorArray.push("true");
									}else{
										items.error = false;
										errorArray.push("false");
									}
								});
							}
						}else{
							if(obj[item][i][it].model == ""){
								obj[item][i][it].error = false;
								errorArray.push("false");
							}else{
								let items = obj[item][i][it];
								that.checkeds(items.rules[0].type,items.model,function(res){
									if(res){
										items.error = true;
										items.placeholder = res.message;
										errorArray.push("true");
									}else{
										items.error = false;
										errorArray.push("false");
									}
								});
							}
						}
						
					}
				}
			}
		} else {
			window.console.log(obj[item])
			for (let it in obj[item]) {
				if (obj[item][it].rules) {
					if(obj[item][it].rules[0].required==true){
						if (obj[item][it].model == ""||obj[item][it].model == null) {
							obj[item][it].error = true;
							errorArray.push("true");
						} else {
							let items = obj[item][it];
							that.checkeds(items.rules[0].type,items.model,function(res){
								if(res){
									items.error = true;
									items.placeholder = res.message;
									errorArray.push("true");
								}else{
									items.error = false;
									errorArray.push("false");
								}
							});
						}
					}else{
						if (obj[item][it].model == ""||obj[item][it].model == null) {
							obj[item][it].error = false;
							errorArray.push("false");
						} else {
							let items = obj[item][it];
							that.checkeds(items.rules[0].type,items.model,function(res){
								if(res){
									items.error = true;
									items.placeholder = res.message;
									errorArray.push("true");
								}else{
									items.error = false;
									errorArray.push("false");
								}
							});
						}
						
					}
					
				}
			}
		}
	}
	let errorStr = errorArray.join("");
	if (errorStr.indexOf("true") != -1) {
		return true;
	} else {
		return false;
	}
}
export default {
	changes: changes,
	editNum: editNum,
	checkOut: checkOut,
	reset: reset,
	clernAll: clernAll,
	rulesObj: rulesObj,
	MZDPPersonFamilyItem: MZDPPersonFamilyItem,
	MZDPPersonRewardItem: MZDPPersonRewardItem,
	MZDPPersonPunishItem: MZDPPersonPunishItem,
	MZDPPersonAcademicTitleItem: MZDPPersonAcademicTitleItem,
	changeTree: changeTree,
	MZDPPersonBasic: MZDPPersonBasic,
	MZDPPersonOrginize: MZDPPersonOrginize,
	nonpartyOrginize: nonpartyOrginize,
	minorityOrginize: minorityOrginize,
	ReligionOrginize: ReligionOrginize,
	NewSocialOrginize: NewSocialOrginize,
	HongkongMacaoOrginize: HongkongMacaoOrginize,
	TaiwanOrginize: TaiwanOrginize,
	dwIntellectualOrginize: dwIntellectualOrginize,
	OtherContactOrginize: OtherContactOrginize,
	StudyAbroadOrginize: StudyAbroadOrginize,
	OverseasChineseOrginize: OverseasChineseOrginize,
	RetireMessageItem: RetireMessageItem,
	GuiqiaoQiaojuanItem: GuiqiaoQiaojuanItem,
	fgyzPersonOrginize: fgyzPersonOrginize,
	MZDPPersonWork: MZDPPersonWork,
	MZDPPersonResume: MZDPPersonResume,
	MZDPPersonReward: MZDPPersonReward,
	MZDPPersonFamily: MZDPPersonFamily,
	MZDPPersonCPPCC: MZDPPersonCPPCC,
	MZDPPersonAppointment: MZDPPersonAppointment,
}
