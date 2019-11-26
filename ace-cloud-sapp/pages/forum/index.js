var app = getApp()
Page({
	data: {
		limit: 10,
		category: 0,
		navbarArray: [
			{
				text: '热门',
				active: '',
				type: '1'
			}, {
				text: '护安校园',
				active: '',
				type: '2'
			}, {
				text: '法安沅澧',
				active: '',
				type: '3'
			}, {
				text: '和事佬',
				active: 'navbar-item-active',
				type: '4'
			}, {
				text: '心防工程',
				active: 'navbar-item-active',
				type: '5'
			}, {
				text: '巡逻守护',
				active: 'navbar-item-active',
				type: '6'
			},
		],
		lists: [{
			list: [1,2,3,4,5,6],
			start: 0,
			isfrist: true,
			category: '1',
			LoadOver: false,
			Loadingstatus: false,
		},
		{
			list: [2],
			start: 0,
			isfrist: true,
			category: '2',
			LoadOver: false,
			Loadingstatus: false,
		},
		{
			list: [],
			start: 0,
			isfrist: true,
			category: '3',
			LoadOver: false,
			Loadingstatus: false,
		},
		{
			list: [],
			start: 0,
			isfrist: true,
			category: '4',
			LoadOver: false,
			Loadingstatus: false,
		},
		{
			list: [],
			start: 0,
			isfrist: true,
			category: '5',
			LoadOver: false,
			Loadingstatus: false,
		},
		{
			list: [],
			start: 0,
			isfrist: true,
			category: '6',
			LoadOver: false,
			Loadingstatus: false,
		},
		]

	},

	onLoad: function () {
		let that = this;
		that.initdata();
	},
	loadMore: function () {
		var self = this;
		// 当前页是最后一页
		if (self.data.currentPage == self.data.allPages) {
			self.onReachBottom();
			return;
		}
	},

	changeActivity: function (e) {
		// let that = this;
		// let index = e.detail.current;
		// that.switchChannel(index);
		// let temp = that.getTarget();
		// if (temp.isfrist) {
		// 	that.initdata();
		// 	return;
		// }
	},

	getTarget: function () {
		let that = this;
		return that.data.lists[that.data.category - 1];
	},

	showLoading: function () {
		let that = this;
		let temp = that.getTarget();
		temp.Loadingstatus = true;
		that.setDataTarget();
	},
	setDataTarget: function () {
		let that = this;
		var tempDate = 'lists[' + (that.data.category - 1) + ']';
		console.log(tempDate);
		console.log(that.data);
		that.setData({
			category: that.data.category,
			[tempDate]: that.getTarget(),
		});
	},


	viewData: function (data) {
		let that = this;
		let temp = that.getTarget();
		temp.isfrist = false;
		temp.Loadingstatus = false;
		if (data) {
			temp.list = temp.list.concat(data);
			if (data.length < that.data.limit) {
				temp.LoadOver = true;
			}
		}
		that.setDataTarget();
	},

	// 下拉刷新
	onPullDownRefresh: function () {
		let that = this;
		console.log('-------------下拉加载-------------');
		let temp = that.getTarget();
		temp.list = [];
		temp.start = 0;
		temp.LoadOver = false;
		that.initdata();
	},

	// // 上拉加载
	onReachBottom: function () {
		var that = this;
		console.log('-------------上拉加载-------------');
		let temp = that.getTarget();
		temp.start += that.data.limit;
		that.initdata();
	},

	onTapNavbar: function (e) {
		this.switchChannel(parseInt(e.currentTarget.id));
	},
	switchChannel: function (targetChannelIndex) {
		let that = this;
		console.log(targetChannelIndex);
		that.getArticles(targetChannelIndex);
		let navbarArray = that.data.navbarArray;
		navbarArray.forEach((item, index, array) => {
			item.active = '';
			if (index === targetChannelIndex) {
				item.active = 'navbar-item-active';
			}
		});
		that.setData({
			category: targetChannelIndex + 1,
			navbarArray: navbarArray,
		});
	},
	getArticles: function (index) {
		this.setData({
			loadingModalHide: false,
			articleContent: ''
		});
		setTimeout(() => {
			this.setData({
				loadingModalHide: true,
				articleContent: this.data.navbarArray[index].text
			});
		}, 500);
	},
	viewInfo: function (e) {
		let that = this;
		let data = e.currentTarget.dataset
		let p = data.id;
		let title = data.title;
		wx.navigateTo({
			url: '../reportInfo/index?id=' + p
		})
	}
});