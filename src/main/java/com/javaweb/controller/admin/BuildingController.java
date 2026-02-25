package com.javaweb.controller.admin;
import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.BuildingSearchDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private BuildingService buildingService;
    @RequestMapping(value="/admin/building-list",method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchDTO buildingSearchDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchDTO.setStaffId(staffId);
            //mav.addObject("buildingSearchDTO", buildingService.findAll(buildingSearchDTO));
        }
        List<BuildingDTO> buildingList = buildingService.findAll(buildingSearchDTO);
        mav.addObject("buildingList", buildingList);
        mav.addObject("listStaffs",userService.getStaffs());
        mav.addObject("districts", districtCode.type());
        mav.addObject("buildingTypes",buildingType.type());
//        BuildingSearchResponse model = new BuildingSearchResponse();
//        DisplayTagUtils.of(request,model);
//        List<BuildingSearchResponse> buildingDTOS = buildingService.getAllBuilding(new PageRequest(model.getPage()-1, model.getMaxPageItems()));
//        model.setListResult(buildingDTOS);
//        model.setTotalItem(buildingService.findAll(buildingSearchDTO).size());
//        mav.addObject(SystemConstant.MODEL, model);
//        initMessageResponse(mav,request);
        return mav;
    }
    @RequestMapping(value="/admin/building-edit",method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", districtCode.type());
        mav.addObject("buildingTypes",buildingType.type());
        return mav;
        }
    @RequestMapping(value="/admin/building-edit-{id}",method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");//goi view
        BuildingDTO buildingDTO = buildingService.findById(id);
        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", districtCode.type());
        mav.addObject("buildingTypes",buildingType.type());
        return mav;
    }
}
