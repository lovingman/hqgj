import Vue from 'vue'
import VueResource from 'vue-resource'
//初始化字典数据
function initDictData() {
	console.log(VueResource.http)
	// this.$http
	// 	.get(
	// 		this.globalData.api +
	// 		"/portal/dict/getByCategoryIds?categoryIds=1,2,3,4,5,6,7,8,9,12,13,14,15,17,19,20,23,24,25,26,28,29,30,31,32,33,34,35,36,37,39,40,46,55,56,57,58",
	// 		JSON.stringify({}), {
	// 			emulateJSON: true
	// 		}
	// 	)
	// 	.then(res => {
	// 		// 1,政治面貌 2,职级  3,文化程度 4,性别 5,民族 ,8,在职学历,9,在职学位
	// 		// 7, 婚姻状况 12,职级 ,13,取得专业技术职务级别, 14,当前获聘专业技术职务级别, 15,党派类别 17,任职状态 19,班子成员标识
	// 		// 20,班子成员类别 23,奖励级别 24,奖励时职务层次 25,惩罚时职务层次 26,惩罚类型 28,家庭现状 29 单位职务 30政协委员类别 31人大代表类别 32 提名类型 33政协委员推荐方式 34政协所属界别
	// 		//35政协年度履职评价意见 36非公经济人士综合评价意见 37委员产生方式 39人员类别 40,工作岗位，46，国籍 55,所学专业 56,专业分类 57,学历审核状态 58,学制
	// 		if (res.body.status == 1) {
	// 			// this.sexArr = res.body.data["4"];
	// 			// this.maritalStatusArr = res.body.data["7"];
	// 			// this.rankArr = res.body.data["2"];
	// 			// this.partyTypeArr = res.body.data["15"];
	// 			// this.degreeEducationArr = res.body.data["3"];
	// 			// //基础信息
	// 			// this.MZDPPersonBasicObj.MZDPPersonBasic.genderValue.array = res.body.data["4"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonBasic.nationValue.array = res.body.data["5"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonBasic.healthySituationValue.array = res.body.data["6"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonBasic.marriageSituationValue.array = res.body.data["7"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonBasic.nationalityValue.array = res.body.data["46"];
	// 			// //全日制学历
	// 			// this.MZDPPersonBasicObj.MZDPPersonFullEducation.educationValue.array = res.body.data["8"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonFullEducation.xueWeiValue.array = res.body.data["9"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonFullEducation.majorName.array = res.body.data["55"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonFullEducation.majorCategoryValue.array = res.body.data["56"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonFullEducation.educationStatusValue.array = res.body.data["57"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonFullEducation.xueZhiValue.array = res.body.data["58"];
	// 			// //在职学历
	// 			// this.MZDPPersonBasicObj.MZDPPersonOnJobEducation.educationValue.array = res.body.data["8"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonOnJobEducation.xueWeiValue.array = res.body.data["9"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonOnJobEducation.majorName.array = res.body.data["55"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonOnJobEducation.majorCategoryValue.array = res.body.data["56"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonOnJobEducation.educationStatusValue.array = res.body.data["57"];
	// 			// this.MZDPPersonBasicObj.MZDPPersonOnJobEducation.xueZhiValue.array = res.body.data["58"];
	// 			// //奖励
	// 			// this.MZDPPersonReward.MZDPPersonReward.rewardLevelValue.array = res.body.data["23"];
	// 			// this.MZDPPersonReward.MZDPPersonReward.zwccValue.array = res.body.data["24"];
	// 			// //惩罚
	// 			// this.MZDPPersonReward.MZDPPersonPunish.punishLevelValue.array = res.body.data["23"];
	// 			// this.MZDPPersonReward.MZDPPersonPunish.punishValue.array = res.body.data["26"];
	// 			// this.MZDPPersonReward.MZDPPersonPunish.zwccValue.array= res.body.data['25'];
	// 			// //政协
	// 			// this.MZDPPersonCPPCC.MZDPPersonCPPCC.categoryValue.array = res.body.data['30'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonCPPCC.tmValue.array = res.body.data['32'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonCPPCC.recommendWayValue.array = res.body.data['33'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonCPPCC.ascriptionValue.array = res.body.data['34'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonCPPCC.CPPCCOpinion.array = res.body.data['35'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonCPPCC.appraiseGradeValue.array = res.body.data['36'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonCPPCC.electionWayValue.array = res.body.data['37'];
	// 			// //人大
	// 			// this.MZDPPersonCPPCC.MZDPPersonNPC.categoryValue.array = res.body.data['31'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonNPC.tmValue.array = res.body.data['32'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonNPC.assessmentOpinionValue.array = res.body.data['35'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonNPC.appraiseGradeValue.array = res.body.data['36'];
	// 			// this.MZDPPersonCPPCC.MZDPPersonNPC.electionWayValue.array = res.body.data['37'];
	// 			// //家庭
	// 			// this.MZDPPersonFamily.MZDPPersonFamily.politicalFaceValue.array = res.body.data["1"];
	// 			// this.MZDPPersonFamily.MZDPPersonFamily.status.array = res.body.data['28'];
	// 			// this.MZDPPersonFamily.MZDPPersonFamily.nationalityValue.array = res.body.data['46'];
	// 			// // this.MZDPPersonAppointment.MZDPPersonAppointment.bzCyCategoryValue.array = res.body.data['19'];
	// 			// //任职情况
	// 			// this.MZDPPersonAppointment.MZDPPersonAppointment.servingStatusValue.array = res.body.data['17'];
	// 			// this.MZDPPersonAppointment.MZDPPersonAppointment.bzCyCategoryValue.array = res.body.data['20'];
	// 			// //组织
	// 			// this.MZDPPersonOrginize.MZDPPersonOrginize.partyValue.array = res.body.data["15"];
	// 			// //工作信息
	// 			// this.MZDPPersonWork.MZDPPersonWork.hpZyJsLevelValue.array = res.body.data["14"];
	// 			// this.MZDPPersonWork.MZDPPersonWork.levelValue.array = res.body.data["29"];
	// 			// this.MZDPPersonWork.MZDPPersonWork.workGwei.array = res.body.data["40"];
	// 			// //职称
	// 			// this.MZDPPersonWork.MZDPPersonAcademicTitle.academicTitle.array = res.body.data["12"];
	// 			// this.MZDPPersonWork.MZDPPersonAcademicTitle.zyJsLevelValue.array = res.body.data["13"];
	// 			// console.log(res.body.data["14"])
	// 		} else {
	// 			this.$message({
	// 				message: res.body.message,
	// 				type: "warning"
	// 			});
	// 		}
	// 	})
	// 	.catch(res => {
	// 		//捕捉异常
	// 		console.log(res);
	// 	});
}
initDictData();