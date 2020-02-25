var validate = {
    mobilePhone: /^1[3456789]\d{9}$/,//电话
    thanlen6Str: /^[a-zA-Z0-9_-~!@#$%^&*]{6,32}$/, //超过6位字符
    len4Num: /^\d{4,6}$/,//长度在4和6之间
    idCard: /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|[X,x])$/,//身份证
    chineseName: /^[\u4e00-\u9fa5]{2,5}$/,//中国名字
    companyName: /^[\u4e00-\u9fa5]{2,500}$/,//中国公司名称
    floatNum: /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/,//无限制小数
    float2Num2: /^[1-9]\d{0,1}(\.\d{1,3})?$|^0(\.\d{1,2})?$/, //正数2位小数点后2位
    ageNum: /^[1-9]\d{0,2}$/, //正数2位小数点后2位
    floatNum4: /^\d+(\.\d{1,4})?$/, //小数点后位
    notEmpty: /\S/,  //不能为空
    integer:/^[1-9]\d*$/   //整数
};
module.exports = validate