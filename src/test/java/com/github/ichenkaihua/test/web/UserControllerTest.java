package com.github.ichenkaihua.test.web;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.github.ichenkaihua.utils.RandomUtils;
import com.jayway.restassured.module.mockmvc.response.MockMvcResponse;
import com.zyx.model.SysUser;
import com.zyx.service.UserService;

/**
 * Created by chenkh on 16-5-15.
 */
public class UserControllerTest extends RestAssuredBaseTest {


    @Autowired
    UserService userService;

    @Test
    public void testGetAllUsers(){
        given()
                .log().all()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .get("/users")
                .then().log().all()
                .apply(document("获取所有用户",preprocessResponse(prettyPrint())))
                .assertThat().statusCode(200);
    }


    private SysUser randomUser(){
    	SysUser user = new SysUser();
        user.setUsername(RandomUtils.randomString(7));
        user.setPassword(RandomUtils.randomString(10));
        return user;

    }


    private SysUser inserRandomUser(){
    	SysUser user = randomUser();
        userService.addUser(user);
        return user;
    }



    @Test
    public void testAddUser(){
    	SysUser user = randomUser();
        MockMvcResponse mockMvcResponse = given()
                .body(user)
                .log().all()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .post("/users");
        mockMvcResponse
                .then()
                .log().all()
        .apply(document("添加用户,会忽略id，用于自动创建", preprocessResponse(prettyPrint())))
                .assertThat().statusCode(201);
        SysUser responUser = mockMvcResponse.as(SysUser.class);

        assertNotNull(responUser.getId());
        SysUser queryUser = userService.getUserById(responUser.getId());
        assertNotNull(queryUser);
        assertEqualsUser(responUser,queryUser);
    }


    public void assertEqualsUser(SysUser expectedUser,SysUser actualUser){
        assertNotNull(expectedUser);
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(),actualUser.getId());
        assertEquals(expectedUser.getUsername(),actualUser.getUsername());
        assertEquals(expectedUser.getPassword(),actualUser.getPassword());
    }



    @Test
    public void testUpdateUserInfo(){
    	SysUser user = inserRandomUser();
        String randomName = RandomUtils.randomString(10);
        SysUser updateUser = new SysUser();
        updateUser.setId(user.getId());
        updateUser.setUsername(randomName);
        MockMvcResponse put = given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(updateUser)
                .when()
                .put("users/{id}", user.getId());
        put
                .then().assertThat().statusCode(200)
        .apply(document("更新用户的基本信息"));
        SysUser queryUser = userService.getUserById(user.getId());
        assertNotNull(queryUser);
        assertEquals(updateUser.getUsername(),queryUser.getUsername());
    }



    @Test
    public void testGetUserById(){
    	SysUser user = inserRandomUser();
        MockMvcResponse mockMvcResponse = given().log().all()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .get("users/{id}", user.getId());

        mockMvcResponse
                .then().log().all()
               .apply(document("根据id获取用户信息,不包含密码", preprocessResponse(prettyPrint())))
                .assertThat()
                .statusCode(200);
        SysUser queryUser = mockMvcResponse.as(SysUser.class);
        assertEqualsUser(user,queryUser);
    }


    @Test
    public void testDeleteById(){
    	SysUser user = inserRandomUser();
        given()
                .log().all()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .when()
                .delete("/users/{id}",user.getId())
                .then()
                .log().all()
                .apply(document("删除指定用户的id",preprocessResponse(prettyPrint())))
                .assertThat().statusCode(200);
        assertNull(userService.getUserById(user.getId()));
    }


}
