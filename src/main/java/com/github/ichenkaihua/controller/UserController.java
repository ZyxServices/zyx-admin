package com.github.ichenkaihua.controller;


import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ichenkaihua.utils.URIUtils;
import com.zyx.jopo.ErrorResponseEntity;
import com.zyx.model.SysUser;
import com.zyx.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "users", produces = {APPLICATION_JSON_UTF8_VALUE})
@RestController
@Api(value = "/users", tags = "UserApi", description = "用户信息接口")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(
            value = "根据id获取用户信息,不包含密码",
            response = SysUser.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "指定id的用户不存在",
                    response = ErrorResponseEntity.class)
    })
    public ResponseEntity getUserBYId(@ApiParam(value = "用户id",required = true) @PathVariable int id) {
        System.out.println("userServ:"+userService);
        SysUser user = userService.getUserById(id);
        if (user == null) {
            return ErrorResponseEntity.buildToResponseEntity(1000, "用户不存在");
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {APPLICATION_JSON_VALUE})
    @ApiOperation(value = "添加用户,会忽略id，用于自动创建", code = 201, response = SysUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "用户已经存在",response = ErrorResponseEntity.class)
    })
    public ResponseEntity addUser(@ApiParam(value = "用户信息") @RequestBody SysUser user) {
    	SysUser countUser = new SysUser();
        countUser.setUsername(user.getUsername());
        //如果存在，返回错误码
        if (userService.isExist(countUser)) {
            return ErrorResponseEntity.buildToResponseEntity(1000, "手机号已经注册");
        }
        userService.addUser(user);
        return ResponseEntity.created(URIUtils.createURI("/users/{id}", user.getId())).body(user);

    }


    @ApiOperation(value = "获取所有用户", response = SysUser.class, responseContainer = "List")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity users() {
        List<SysUser> users = userService.getUsers(null);
        return new ResponseEntity(users, HttpStatus.OK);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除指定用户的id")
    public void deleteById(@PathVariable int id) {
        userService.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户的基本信息", notes = "不会处理id字段和password字段")
    public void update(@PathVariable("id") @ApiParam("用户id") int id ,@RequestBody @ApiParam("新的用户信息") SysUser user) {
        user.setId(id);
        userService.update(user);
    }


}
