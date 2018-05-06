package com.sc.springmvc.dao;

import com.sc.springmvc.model.FuncRole;
import com.sc.springmvc.model.FuncRoleCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FuncRoleMapper {
    long countByExample(FuncRoleCriteria example);

    int deleteByExample(FuncRoleCriteria example);

    int deleteByPrimaryKey(String funcroleid);

    int insert(FuncRole record);

    int insertSelective(FuncRole record);

    List<FuncRole> selectByExample(FuncRoleCriteria example);

    FuncRole selectByPrimaryKey(String funcroleid);

    int updateByExampleSelective(@Param("record") FuncRole record, @Param("example") FuncRoleCriteria example);

    int updateByExample(@Param("record") FuncRole record, @Param("example") FuncRoleCriteria example);

    int updateByPrimaryKeySelective(FuncRole record);

    int updateByPrimaryKey(FuncRole record);
}