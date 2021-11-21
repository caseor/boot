package org.caseor.boot.datasource.mapper;

import org.caseor.boot.datasource.entity.User;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

@Repository
public interface UserMapper {

    int insert(User user);

    int deleteById(@Param("id") Long id);

    int updateById(User user);

    List<User> select(@Param("params") Map<String, Object> params);
}

