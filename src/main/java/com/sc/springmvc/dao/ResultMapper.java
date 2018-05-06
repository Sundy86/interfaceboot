package com.sc.springmvc.dao;

import com.sc.springmvc.model.Result;
import com.sc.springmvc.model.ResultCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ResultMapper {
    long countByExample(ResultCriteria example);

    int deleteByExample(ResultCriteria example);

    int deleteByPrimaryKey(Date runtime);

    int insert(Result record);

    int insertSelective(Result record);

    List<Result> selectByExample(ResultCriteria example);

    Result selectByPrimaryKey(Date runtime);

    int updateByExampleSelective(@Param("record") Result record, @Param("example") ResultCriteria example);

    int updateByExample(@Param("record") Result record, @Param("example") ResultCriteria example);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}