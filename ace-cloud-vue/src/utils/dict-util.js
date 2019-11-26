export function formatDict(data) {
    var dict={};
    for (var i in data) {
        dict[i]={};
        dict[i].data = data[i];
        dict[i].map = valueToMap(data[i]);
    }
    return dict;
}

function valueToMap (data) {
    var obj=data;
    var map={};
    for (var i in obj) {
        map[obj[i].code]=data[i].name;
    }
    return map;
}