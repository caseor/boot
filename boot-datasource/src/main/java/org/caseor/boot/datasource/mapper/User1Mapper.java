package org.caseor.boot.datasource.mapper;

import org.caseor.boot.datasource.entity.User1;
import org.caseor.common.datasource.annotation.DsSecond;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

@Repository
public interface User1Mapper {

  int insert(User1 user1);

  int deleteById(@Param("id") Long id);

  int updateById(User1 user1);

  @DsSecond
  List<User1> select(@Param("params") Map<String, Object> params);
}

