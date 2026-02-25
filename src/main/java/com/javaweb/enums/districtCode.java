package com.javaweb.enums;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
public enum  districtCode {
    Quan_HM ("Quận Hoàng Mai"),
    QUAN_BD ("Quận Ba Đình"),
    QUAN_CG ("Quận Cầu Giấy"),
    QUAN_TX ("Quận Thanh Xuân"),
    QUAN_1 ("Quận 1"),
    QUAN_MD ("Quận Mỹ Đình"),
    QUAN_3 ("Quận 3"),
    QUAN_4 ("Quận 4"),
    QUAN_5 ("Quận 5"),
    QUAN_6 ("Quận 6"),
    QUAN_7 ("Quận 7"),
    QUAN_8 ("Quận 8"),
    ;
    private final String districtName;
    districtCode(String districtName) {
        this.districtName = districtName;
    }
    public String getDistrictName() {
        return districtName;
    }
    public static Map<String,String> type(){
        Map<String,String> listType = new TreeMap<>();
        for(districtCode item : districtCode.values()){
            listType.put(item.toString() , item.districtName);
        }
        return listType;
    }
}
