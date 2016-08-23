package com.zyx.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;
import java.util.UUID;

import com.zyx.constants.SysConstants;
import com.zyx.model.SysRole;
import com.zyx.model.SysUser;
import com.zyx.parm.sys.CreateSystemRoleParam;
import com.zyx.parm.sys.CreateSystemUserParam;
import com.zyx.parm.sys.QuerySystemUserParam;
import com.zyx.utils.CipherUtil;
import com.zyx.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.zyx.jopo.ErrorResponseEntity;
import com.zyx.service.SysUserService;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
@RequestMapping("v1/su")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity addUser(@RequestBody SysUser user) {
        SysUser countUser = new SysUser();
        countUser.setUsername(user.getUsername());
        user.setUserId("10010");
        int result = sysUserService.addUser(user);
        if (result == 1) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ErrorResponseEntity.buildToResponseEntity(1000, "添加用户失败");

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView users(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam(required = false) String searchText, @RequestParam(required = false) String sortName, @RequestParam(required = false) String sortOrder) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QuerySystemUserParam param = new QuerySystemUserParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        param.setSearchText(searchText);
        param.setSortName(sortName);
        param.setSortOrder(sortOrder);
        Map<String, Object> map = sysUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView sysRoleInsert(@RequestParam String username, @RequestParam String pass, @RequestParam String name, @RequestParam String roleId, @RequestParam(required = false) String remark) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        SysUser sysUser = sysUserService.getUserByNamePass(username, CipherUtil.generatePassword(pass));

        if (sysUser != null) {
            map = MapUtils.buildErrorMap(SysConstants.ERROR_9004, SysConstants.ERROR_9004_MSG);
        } else {
            CreateSystemUserParam param = new CreateSystemUserParam();
            param.setRoleId(roleId);
            param.setName(name);
            param.setUserName(username);
            param.setPassword(CipherUtil.generatePassword(pass));
            param.setRemark(remark);
            map = sysUserService.insertSysUser(param);
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    public ModelAndView sysRoleEdit(@RequestParam Integer id, @RequestParam String roleId) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        CreateSystemUserParam param = new CreateSystemUserParam();
        param.setId(id);
        param.setRoleId(roleId);
        map = sysUserService.editSysRole(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable int id) {
        sysUserService.deleteById(id);
    }

}
