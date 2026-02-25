package com.javaweb.converter;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import com.javaweb.security.utils.SecurityUtils;
@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
        Long staffId = null;
        boolean isManager = SecurityUtils.getAuthorities().stream()
                .anyMatch(role -> role.equals("ROLE_MANAGER"));

        if (!isManager) {
            staffId = SecurityUtils.getPrincipal().getId();
        }
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(params, "name", String.class))
                .setFloorArea(MapUtils.getObject(params, "floorArea", Integer.class))
                .setNumberOfBasement(MapUtils.getObject(params, "numberOfBasement", Integer.class))
                .setDistrict(MapUtils.getObject(params, "district",String.class))
                .setWard(MapUtils.getObject(params, "ward", String.class))
                .setStreet(MapUtils.getObject(params, "street", String.class))
                .setManagerName(MapUtils.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtils.getObject(params, "managerPhone", String.class))
                .setRentPriceFrom(MapUtils.getObject(params, "rentPriceFrom", Long.class))
                .setRentPriceTo(MapUtils.getObject(params, "rentPriceTo", Long.class))
                .setAreaFrom(MapUtils.getObject(params, "areaFrom", Long.class))
                .setAreaTo(MapUtils.getObject(params, "areaTo", Long.class))
                .setTypeCode(typeCode)
                .setStaffId(staffId)
                .build();
        return buildingSearchBuilder;
    }
}
