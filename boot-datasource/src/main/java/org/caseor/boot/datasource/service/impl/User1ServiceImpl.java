package org.caseor.boot.datasource.service.impl;

import org.caseor.boot.datasource.entity.User1;
import org.caseor.boot.datasource.mapper.User1Mapper;
import org.caseor.boot.datasource.service.User1Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

@Service
public class User1ServiceImpl implements User1Service {

  private final User1Mapper user1Mapper;

  @Autowired
  public User1ServiceImpl(User1Mapper user1Mapper) {
    this.user1Mapper = user1Mapper;
  }

  @Override
  public int insert(User1 user1) {
    return user1Mapper.insert(user1);
  }

  @Override
  public int deleteById(Long id) {
    return user1Mapper.deleteById(id);
  }

  @Override
  public int updateById(User1 user1) {
    return user1Mapper.updateById(user1);
  }

}
