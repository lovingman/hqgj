import { login, logout, getInfo, getBtn, getDict} from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
// import { getPAreaCode } from '@/api/sys'

const state = {
	token: getToken(),
	name: '',
	avatar: '',
	introduction: '',
	roles: [],
	syCfg: {},
	userProp: {},
	userBtn: {},
	dictArrayObj: {},
	// provinceCityOptions: [],
}

const mutations = {
	SET_TOKEN: (state, token) => {
		state.token = token
	},
	SET_INTRODUCTION: (state, introduction) => {
		state.introduction = introduction
	},
	SET_NAME: (state, name) => {
		state.name = name
	},
	SET_AVATAR: (state, avatar) => {
		state.avatar = avatar
	},
	SET_ROLES: (state, roles) => {
		state.roles = roles
	},
	SET_SYCFG: (state, syCfg) => {
		state.syCfg = syCfg
	},
	SET_USERPROP: (state, userProp) => {
		state.userProp = userProp
	},
	SET_USERBTN: (state, userBtn) => {
		state.userBtn = userBtn
	},
	SET_DICOBJECT: (state, dictArrayObj) => {
		state.dictArrayObj = dictArrayObj
	},
	SET_CITYOPTIONS: (state, provinceCityOptions) => {
		state.provinceCityOptions = provinceCityOptions
	}
}

const actions = {
	// user login
	login({ commit }, userInfo) {
		const { username, password } = userInfo
		return new Promise((resolve, reject) => {
			login({ username: username.trim(), password: password }).then(response => {
				const data = response.data
				const tokenStr = data.tokenHead + data.token
				setToken(tokenStr);
				commit('SET_TOKEN', tokenStr)
				resolve()
			}).catch(error => {
				reject(error)
			})
		})
	},
	setSyCfg({ commit }, syCfg) {
		commit('SET_SYCFG', syCfg)
	},
	setUserProp({ commit }, userProp) {
		commit('SET_USERPROP', userProp)
	},
	// get user info
	getInfo({ commit }) {
		return new Promise((resolve, reject) => {
			getInfo().then(response => {
				const data = response.data;
				data.role = ['admin'];

				if (!data) {
					reject('Verification failed, please Login again.')
				}

				const { role, name, areaCode } = data

				// roles must be a non-empty array
				if (!role || role.length <= 0) {
					reject('getInfo: roles must be a non-null array!')
				}

				commit('SET_ROLES', role)
				commit('SET_NAME', name)
				commit('SET_SYCFG', data.cfg)
				commit('SET_USERPROP', data)
				//commit('SET_AVATAR', avatar)
				// commit('SET_INTRODUCTION', introduction)
				// getPAreaCode(areaCode).then(response => {
				// 	data.pAreaCode = response.data;
				// 	commit('SET_USERPROP', data);
				// 	window.console.log(data);
				// });
				resolve(data)
			}).catch(error => {
				reject(error)
			})
		})
	},
	getUserBtn({ commit }) {
		return new Promise((resolve, reject) => {
			getBtn().then(response => {
				let btn = {};
				for (let o of response.data) {
					btn[o.url] = o.title;
				}
				window.console.log(btn);
				commit('SET_USERBTN', btn)
			});
			resolve()
		})
	},
	// getProvinceCityOptions({ commit }) {
	// 	return new Promise((resolve, reject) => {
	// 		getProvinceCityOptions().then(response => {
	// 			let data = response.data;
	// 			commit('SET_CITYOPTIONS', data)
	// 		});
	// 		resolve()
	// 	})
	// },
	getDicObject({ commit }) {
		return new Promise((resolve, reject) => {
			getDict().then(response => {
				let dictArrayObj = {
					genderValue:[], //4
					projectValue:[], //47
					ProjectLevelValue:[], //48
					peopleValue:[], //49
					statusValue:[], //50
					serviceObjectValue:[], //51
				};
				//4 性别 47 项目类型 48 项目等级 49 人物身份 50 数据状态 51被服务对象类型
				dictArrayObj.genderValue = response.data["4"]; //47
				dictArrayObj.projectValue = response.data["47"]; //47
				dictArrayObj.ProjectLevelValue = response.data["48"]; //47
				dictArrayObj.peopleValue = response.data["49"]; //49
				dictArrayObj.statusValue = response.data["50"]; //50
				dictArrayObj.serviceObjectValue = response.data["51"]; //51
				commit('SET_DICOBJECT', dictArrayObj)
			});
			resolve()
		})
	},
	// user logout
	logout({ commit, state }) {
		return new Promise((resolve, reject) => {
			logout(state.token).then(() => {
				commit('SET_TOKEN', '')
				commit('SET_ROLES', [])
				removeToken()
				resetRouter()
				resolve()
			}).catch(error => {
				reject(error)
			})
		})
	},

	// remove token
	resetToken({ commit }) {
		return new Promise(resolve => {
			commit('SET_TOKEN', '')
			commit('SET_ROLES', [])
			removeToken()
			resolve()
		})
	},

	// dynamically modify permissions
	changeRoles() {
		return new Promise(async resolve => {
			resolve()
		})
	}
}

export default {
	namespaced: true,
	state,
	mutations,
	actions
}
