package com.javaweb.api.admin;
import com.javaweb.model.dto.AssignmentBuildingDTO;
//import com.javaweb.model.dto.BuildingRequestDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping("/api/building")
@Transactional
public class BuildingAPI {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;
    @PersistenceContext
    private EntityManager entityManager;
    @PostMapping
    public void addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.save(buildingDTO);
    }
    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        buildingRepository.deleteByIdIn(ids);
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadstaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.liststaffs(id);
        return result;
    }
    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        assignmentBuildingService.addAssignmentBuildingEntity(assignmentBuildingDTO);
    }
}
