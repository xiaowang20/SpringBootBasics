package com.wg.basics.mapper;

import com.wg.basics.entity.Hr;
import com.wg.basics.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HrMapper {
    Hr getAdminByUsername(String username);
}
