package com.eziozhao.leafblog.mbg.mapper;

import com.eziozhao.leafblog.mbg.entity.RolesUser;
import com.eziozhao.leafblog.mbg.entity.RolesUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesUserMapper {
    long countByExample(RolesUserExample example);

    int deleteByExample(RolesUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolesUser record);

    int insertSelective(RolesUser record);

    List<RolesUser> selectByExample(RolesUserExample example);

    RolesUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolesUser record, @Param("example") RolesUserExample example);

    int updateByExample(@Param("record") RolesUser record, @Param("example") RolesUserExample example);

    int updateByPrimaryKeySelective(RolesUser record);

    int updateByPrimaryKey(RolesUser record);
}