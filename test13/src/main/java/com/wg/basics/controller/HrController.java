package com.wg.basics.controller;

import com.wg.basics.common.CommonResult;
import com.wg.basics.entity.Hr;
import com.wg.basics.entity.Menu;
import com.wg.basics.entity.vo.AdminLoginParam;
import com.wg.basics.service.HrService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class HrController {
    @Autowired
    HrService hrService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<Hr> register(@RequestBody Hr adminParam, BindingResult result) {
        Hr admin = hrService.register(adminParam);
        if (admin == null) {
            CommonResult.failed("注册失败");
        }
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody AdminLoginParam adminLoginParam, BindingResult result) {
        String token = hrService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    public CommonResult<List<Menu>> getPermissionList(@PathVariable long adminId) {
        List<Menu> permissionList = hrService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
