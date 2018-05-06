package com.sc.springmvc.dao;

import com.sc.springmvc.model.Function;
import com.sc.springmvc.model.FunctionCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionMapper {
    long countByExample(FunctionCriteria example);

    int deleteByExample(FunctionCriteria example);

    int deleteByPrimaryKey(String funcid);

    int insert(Function record);

    int insertSelective(Function record);

    List<Function> selectByExample(FunctionCriteria example);

    Function selectByPrimaryKey(String funcid);

    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionCriteria example);

    int updateByExample(@Param("record") Function record, @Param("example") FunctionCriteria example);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
}