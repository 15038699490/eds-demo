package com.yzw.eds.dao;

import com.yzw.eds.model.Citys;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface CitysDAO extends Mapper<Citys> {

    List<Map<String,String>> derivedTable(@Param(value = "tableName")String tableName);

    List<Map<String,String>> getTables();

}
