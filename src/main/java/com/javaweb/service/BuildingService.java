package com.javaweb.service;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    ResponseDTO liststaffs(Long buildingId);
    void save(BuildingDTO buildingDTO);
    List<BuildingDTO> findAll(BuildingSearchDTO searchDTO);
    BuildingDTO findById(Long id);
//    List<BuildingSearchResponse> getAllBuilding(PageRequest);
}
