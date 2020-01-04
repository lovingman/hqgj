// pages/RegAgreement/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        nodes: `<p style="line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">甲方:</span>
</p>
<p style="line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">乙方：武陵区中小企业公共服务平台</span>
</p>
<p style="text-indent:48px;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">根据《中小企业促进法》有关规定，武陵区中小企业公共服务平台开展“创业服务一条龙”活动，为符合条件的申请人提供三大免费服务。本着平等自愿的原则，甲乙双方就代办服务相关事项达成如下协议：</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">一、甲方委托乙方免费代办的服务事项是（请打钩标记）：</span>
</p>
<p style="text-align:left;text-indent:8px;line-height:32px">
    <span style="font-size: 16px;font-family:宋体;color:black">1</span><span style="font-size:16px;font-family:宋体;color:black">、□ 工商注册免费代办服务（含公章一套）</span>
</p>
<p style="text-align:left;text-indent:8px;line-height:32px">
    <span style="font-size: 16px;font-family:宋体;color:black">2</span><span style="font-size:16px;font-family:宋体;color:black">、□ 银行基本户免费代办服务&nbsp; </span>
</p>
<p style="text-align:left;text-indent:8px;line-height:32px">
    <span style="font-size: 16px;font-family:宋体;color:black">3</span><span style="font-size:16px;font-family:宋体;color:black">、□ 银行开户费、小额账户管理费免费服务（暂限指定的合作银行方可享受）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
</p>
<p style="line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">二、甲方应明确了解的事项、拥有的权利以及应承担的责任：</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">1</span><span style="font-size:16px;font-family:宋体;color:black">、本协议第一条提供的服务，均不收取任何服务费用，甲方可根据自身需求选择相应服务。</span>
</p>
<p style="text-align:left;line-height:32px">
    <strong><span style="font-size:16px;font-family:宋体;color:black">2</span></strong><strong><span style="font-size:16px;font-family:宋体;color:black">、甲方应填写《武陵区中小企业公共服务平台“创业服务一条龙”免费服务申请表》</span></strong><strong><span style="font-size:16px;font-family:黑体;color:black">，并按照要求完成平台注册，方可享受上述免费服务，该申请表作为本协议的附件，具有同等法律效力。甲方应知晓相关法律条款，并承担由此产生的法律责任</span></strong><strong><span style="font-size:16px;font-family:宋体;color:black">。</span></strong>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">3</span><span style="font-size:16px;font-family:宋体;color:black">、甲方应配合乙方按相关部门的要求提供相关资料的原件或复印件，并</span><span style="font-size:16px;font-family:黑体;color:black">对所提交的资料、证件及签名的真实性、有效性和合法性承担全部法律责任。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:仿宋_GB2312;color:black">4</span><span style="font-size:16px;font-family:仿宋_GB2312;color:black">、</span><span style="font-size:16px;font-family:新宋体;color:black">乙方根据甲方填写的“</span><span style="font-size:16px;font-family:宋体;color:black">创业服务一条龙”免费服务申请表上的注册要求开展服务，</span><span style="font-size:16px;font-family:宋体;color:black">但最终名称、经营范围均以甲方在工商登记部门现场确定并通过工商核定的为准，乙方须及时告知甲方相关信息，但不承担由此产生的任何责任。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">5</span><span style="font-size:16px;font-family:宋体;color:black">、以下（但不局限于）情形：</span><span style="font-size:16px;font-family:宋体;color:black">A</span><span style="font-size:16px;font-family: 宋体;color:black">、甲方需提供前置许可而不能提供的；B、提交的资质、材料等不符合国家政策的；C、甲方指定的法人代表或者股东有工商、银行、税务的不良记录（黑名单），导致不能注册公司或不能办理相关证件的，责任由甲方自行承担。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:仿宋_GB2312;color:black">6</span><span style="font-size:16px;font-family:仿宋_GB2312;color:black">、</span><span style="font-size:16px;font-family:宋体;color:black">本协议中乙方的服务不包含为甲方办理前置许可或后置许可。因此，若甲方的经营范围中涉及前置审批、后置许可的，均由甲方自行办理，并向乙方提供有关资料。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">7</span><span style="font-size:16px;font-family:宋体;color:black">、服务中途终止协议的约定：签订本协议一个工作日后，甲方要求停办业务的，甲方应与乙方签订《服务终止及免责协议》，乙方将现有业务办理的全部资料移交给甲方，甲方自行承担由此产生的一切责任及后果。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">8</span><span style="font-size:16px;font-family:宋体;color:black">、根据政府职能部门及银行部门的要求，以下事务需法人代表或股东本人持身份证到场验证身份后方可办理的，甲方必须配合与理解：A、工商注册登记 B、银行基本户开户。</span><span style="font-size:16px;font-family:黑体;color:black">乙方提前2天通知甲方需要配合办理的事项与时间段，若甲方不配合的，乙方有权终止与解除本免费服务协议。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:仿宋_GB2312;color:black">9</span><span style="font-size:16px;font-family:仿宋_GB2312;color:black">、</span><span style="font-size:16px;font-family:宋体;color:black">办结后甲乙双方必须办理资料移交手续，甲方或其委托人签字确认接收，乙方方予发放所有资料。若发生证件移交纠纷均以甲方已经签字的资料移交清单为接收资料的依据。</span><span style="font-size:16px;font-family:黑体;color:black">甲方应在本协议签订的办结日期之后5个工作日内与乙方办理移交手续，因甲方原因没有及时移交导致的一切后果（包括没有到税务局报到）均由甲方个人承担。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">10</span><span style="font-size:16px;font-family:宋体;color:black">、</span><span style="font-size:16px;font-family:黑体;color:black">甲方应依据《公司法》及国家相关法律条款，合法经营，并按时参加工商年报、税务申报。甲、乙双方办理移交后，本协议自动终止，此后甲方在经营过程中产生的任何行为与损失均与乙方无关。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">三、乙方的权利与应承担的责任：</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">1</span><span style="font-size:16px;font-family:宋体;color:black">、乙方承诺<span style="text-decoration:underline;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp; &nbsp;&nbsp;月&nbsp; &nbsp;&nbsp;&nbsp;日 </span>之前办结上述事项，如遇特殊意外或不可抗拒情况（比如政务部门不能办理相关事务），乙方应及时告知甲方，办结时间顺延。</span><span style="font-size:16px;font-family:黑体;color:black">乙方应提前1天通知甲方需要亲自到场配合的项目（尤其是银行事务），若因甲方原因没有按时到场，则双方约定的业务办结期自动延期10个工作日。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">2</span><span style="font-size:16px;font-family:宋体;color:black">、乙方对收取的甲方各类证件的原件负保管责任，因乙方原因导致的遗失，乙方委托的相关服务提供商负赔偿责任。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">四、其他补充协议：</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">1</span><span style="font-size:16px;font-family:宋体;color:black">、工商注册所需的财务负责人的信息资料由&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;方提供。&nbsp; </span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">2</span><span style="font-size:16px;font-family:宋体;color:black">、</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">五、本协议一式两份，甲乙双方签字（或盖章）后生效。若因本协议产生的纠纷，双方协商解决，协商不成的，可向常德市仲裁委员会申请仲裁。</span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
</p>
<p style="text-align:left;line-height:32px">
    <span style="font-size:16px;font-family:宋体;color:black">甲方签章：&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 乙方签章：武陵区中小企业公共服务平台</span>
</p>
<p style="text-align:left;text-indent:24px;line-height:32px">
    <span style="font-family: 宋体; text-indent: 88px;">年 &nbsp;月 &nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp; 月&nbsp; 日</span>
</p>
<p>
    <br/>
</p>`
    },
    //同意跳转
    agreeClick: function() {
        wx.navigateTo({
            url: '/pages/RegProcess/index'
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    }
})