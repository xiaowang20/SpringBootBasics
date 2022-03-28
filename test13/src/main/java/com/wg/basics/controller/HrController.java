package com.wg.basics.controller;

import com.wg.basics.common.CommonResult;
import com.wg.basics.entity.Menu;
import com.wg.basics.service.HrService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class HrController {
    @Autowired
    HrService hrService;
    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    public CommonResult<List<Menu>> getPermissionList(@PathVariable long adminId) {
        List<Menu> permissionList = hrService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
