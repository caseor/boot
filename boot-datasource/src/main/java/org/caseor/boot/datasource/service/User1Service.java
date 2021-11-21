package org.caseor.boot.datasource.service;

import org.caseor.boot.datasource.entity.User1;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

public interface User1Service {

    int insert(User1 user1);

    int deleteById(Long id);

    int updateById(User1 user1);

}
