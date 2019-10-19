package org.poem.feign.user.impl;

import org.poem.config.ErrMsgConfiguration;
import org.poem.feign.OpenfeignRoute;
import org.poem.user.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author poem
 */
@FeignClient(value = OpenfeignRoute.SERVICE_PROVIDER,fallbackFactory = ErrMsgConfiguration.class)
public interface CUserService {

    /**
     *
     * @return
     */
    @GetMapping("/v1/user/getUser")
    public UserVO getUserVo();
}
