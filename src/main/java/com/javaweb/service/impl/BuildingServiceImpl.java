package com.javaweb.service.impl;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Transactional
    public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params,typeCode);
        return buildingRepository.findAll(buildingSearchBuilder)
                .stream()
                .map(buildingDTOConverter::toBuildingDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ResponseDTO liststaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for(UserEntity it : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }
    @Override
    public void save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity;
        if (buildingDTO.getId() != null) {
            BuildingEntity existing = buildingRepository.findById(buildingDTO.getId()).get();
            buildingDTOConverter.updateBuildingEntity(buildingDTO, existing);
            buildingEntity = existing;
        } else {
            buildingEntity = new BuildingEntity();
            buildingDTOConverter.updateBuildingEntity(buildingDTO, buildingEntity);
        }
        buildingRepository.save(buildingEntity);
    }
    @Override
    public List<BuildingDTO> findAll(BuildingSearchDTO searchDTO) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", searchDTO.getName());
        params.put("floorArea", searchDTO.getFloorArea());
        params.put("numberOfBasement", searchDTO.getNumberOfBasement());
        params.put("district", searchDTO.getDistrict());
        params.put("ward", searchDTO.getWard());
        params.put("street", searchDTO.getStreet());
        params.put("managerName", searchDTO.getManagerName());
        params.put("managerPhone", searchDTO.getManagerPhone());
        params.put("rentPriceFrom", searchDTO.getRentPriceFrom());
        params.put("rentPriceTo", searchDTO.getRentPriceTo());
        params.put("areaFrom", searchDTO.getAreaFrom());
        params.put("areaTo", searchDTO.getAreaTo());
        params.put("staffId", searchDTO.getStaffId());
        return findAll(params, searchDTO.getTypeCode());
    }
    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity entity = buildingRepository.findById(id).get();;
        return buildingDTOConverter.toBuildingDTO(entity);
    }
}
