package com.zyx.controller;

import com.zyx.constants.SysConstants;
import com.zyx.dto.SystemRoleListDto;
import com.zyx.model.SysRole;
import com.zyx.parm.sys.CreateSystemRoleParam;
import com.zyx.parm.sys.QuerySystemRoleParam;
import com.zyx.service.SysRoleService;
import com.zyx.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/v1/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView sysRoleLists(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam(required = false) String searchText, @RequestParam(required = false) String sortName, @RequestParam(required = false) String sortOrder) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QuerySystemRoleParam param = new QuerySystemRoleParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        param.setSearchText(searchText);
        param.setSortName(sortName);
        param.setSortOrder(sortOrder);
        Map<String, Object> map = sysRoleService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView sysRoleAllLists() {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        List<SystemRoleListDto> list = sysRoleService.queryAllList();
        map.put("data", list);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView sysRoleInsert(@RequestParam String roleName, @RequestParam String roleDesc, @RequestParam(required = false) String menuPerm) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        SysRole sysRole = sysRoleService.selectByRoleName(roleName);

        if (sysRole != null) {
            map = MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9001, SysConstants.ERROR_APP_USER_9001_MSG);
        } else {
            CreateSystemRoleParam param = new CreateSystemRoleParam();
            param.setRoleName(roleName);
            param.setRoleDesc(roleDesc);
            param.setMenuPerm(menuPerm);
            param.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
            map = sysRoleService.insertSysRole(param);
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView sysRoleEdit(@RequestParam Integer editRoleId, @RequestParam String roleDesc, @RequestParam(required = false) String menuPerm) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        CreateSystemRoleParam param = new CreateSystemRoleParam();
        param.setId(editRoleId);
        param.setRoleDesc(roleDesc);
        param.setMenuPerm(menuPerm);
        map = sysRoleService.editSysRole(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}