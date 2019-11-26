
export function toArray(areaCode){
    if(!areaCode){
        return [];
    }
    if(areaCode.length==4){
        return [areaCode];
    }
    if(areaCode.length==6){
        return [areaCode.substring(0,4),areaCode];
    }
    if(areaCode.length==8){
        return [areaCode.substring(0,4),areaCode.substring(0,6),areaCode];
    }
    return [areaCode];
}