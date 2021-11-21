package org.caseor.boot.datasource.service;

import org.caseor.boot.datasource.entity.User;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

public interface UserService {

    int insert(User user);

    int deleteById(Long id);

    int updateById(User user);

}
