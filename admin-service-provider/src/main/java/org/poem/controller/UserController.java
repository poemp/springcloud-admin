package org.poem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.poem.user.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author poem
 */
@Api(tags = {"01-用户"})
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * getUser
     * @return 用户消息
     */
    @ApiOperation(value = "0101-获取用户", notes = "获取用户")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没有填好"),
            @ApiResponse(code = 404, message = "请求路径没有找到")
    })
    @GetMapping("/getUser")
    public UserVO getUser(){
        logger.info("UserController -  getUser. ");
        UserVO userVO = new UserVO();
        userVO.setName("Hello Spring Cloud");
        return userVO;
    }
}
