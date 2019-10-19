package org.poem.feign.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.poem.feign.user.impl.CUserService;
import org.poem.user.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author poem
 */
@Api(tags = {"01-用户"})
@RequestMapping("/v1/user")
public class CUserController {

    private static final Logger logger = LoggerFactory.getLogger(CUserController.class);

    @Autowired
    private CUserService cUserService;


    @ApiOperation(value = "0101-获取用户", notes = "获取用户")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没有填好"),
            @ApiResponse(code = 404, message = "请求路径没有找到")
    })
    @RequestMapping("/getUser")
    public UserVO getUser(){
        return this.cUserService.getUserVo();
    }
}
