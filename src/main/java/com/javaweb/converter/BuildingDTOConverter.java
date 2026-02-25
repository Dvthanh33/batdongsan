package com.javaweb.converter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingDTO toBuildingDTO(BuildingEntity entity){
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
        dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + entity.getDistrict());
        if (entity.getTypeCode() != null) {
            List<String> types = Arrays.asList(entity.getTypeCode().split(","));
            dto.setTypeCode(types);
        }
        if (entity.getRentAreaEntities() != null && !entity.getRentAreaEntities().isEmpty()) {
            String rentArea = entity.getRentAreaEntities().stream()
                    .map(RentAreaEntity::getValue)
                    .map(String::valueOf)
                    .reduce((a, b) -> a + "," + b)
                    .orElse("");
            dto.setRentArea(rentArea);
        }
        return dto;
    }
    public BuildingEntity toBuildingEntity(BuildingDTO dto) {
        BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
        if (dto.getTypeCode() != null) {
            entity.setTypeCode(String.join(",", dto.getTypeCode()));
        }
        if (dto.getRentArea() != null && !dto.getRentArea().isEmpty()) {
            List<RentAreaEntity> rentAreaEntities = Arrays.stream(dto.getRentArea().split(","))
                    .map(String::trim)
                    .map(Long::valueOf)
                    .map(value -> {
                        RentAreaEntity rentArea = new RentAreaEntity();
                        rentArea.setValue(value);
                        rentArea.setBuilding(entity); // quan trọng
                        return rentArea;
                    })
                    .collect(Collectors.toList());
            entity.setRentAreaEntities(rentAreaEntities);
        }
        return entity;
    }
    public void updateBuildingEntity(BuildingDTO dto, BuildingEntity entity) {
        modelMapper.map(dto, entity);
        if (dto.getTypeCode() != null) {
            entity.setTypeCode(String.join(",", dto.getTypeCode()));
        }
        // Xóa rentArea cũ
        entity.getRentAreaEntities().clear();
        if (dto.getRentArea() != null && !dto.getRentArea().isEmpty()) {
            List<RentAreaEntity> rentAreaEntities = Arrays.stream(dto.getRentArea().split(","))
                    .map(String::trim)
                    .map(Long::valueOf)
                    .map(value -> {
                        RentAreaEntity rentArea = new RentAreaEntity();
                        rentArea.setValue(value);
                        rentArea.setBuilding(entity);
                        return rentArea;
                    })
                    .collect(Collectors.toList());
            entity.getRentAreaEntities().addAll(rentAreaEntities);
        }
    }
}