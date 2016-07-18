package com.zyx.controller.menu;

import java.util.List;

import com.zyx.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zyx.jopo.ErrorResponseEntity;
import com.zyx.service.SysMenuService;

import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/sysmenu")
public class SysMenuController {
	 @Autowired
	 SysMenuService sysMenuService;
	
	 //http://localhost:8080/zyx/sysmenu/1
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity getPermissionsBYId(@ApiParam(value = "权限id",required = true) @PathVariable int id) {
        System.out.println("SysMenuService:"+sysMenuService);
        SysMenu per = sysMenuService.getSysMenuById(id);
        if (per == null) {
            return ErrorResponseEntity.buildToResponseEntity(1000, "权限不存在");
        }
        return ResponseEntity.ok(per);
    }
	//http://localhost:8080/zyx/sysmenu/list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView  users() {
        ModelAndView view = new ModelAndView("/sys/listmenu");
        List<SysMenu> pers = sysMenuService.getSysMenu(null);
        view.addObject("pers", pers);
        return view;
    }

}
