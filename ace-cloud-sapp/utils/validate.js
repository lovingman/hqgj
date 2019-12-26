var validate = {
    mobilePhone: /^1[3456789]\d{9}$/,
    thanlen6Str: /^[a-zA-Z0-9_-~!@#$%^&*]{6,32}$/,
    len4Num: /^\d{4,6}$/,
    IdCard: /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|[X,x])$/,
    chineseName:/^[\u4e00-\u9fa5]{2,5}$/,
};
module.exports = validate