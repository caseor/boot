package org.caseor.boot.helloworld.mapstruct;

import org.caseor.boot.helloworld.mapstruct.entity.UserVo;
import org.caseor.common.core.entity.User;
import org.caseor.common.core.util.DateUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author Fu Kai
 * @since 20211120
 */

@Mapper
public interface UserStructMapper {
  UserStructMapper INSTANCE = Mappers.getMapper(UserStructMapper.class);

  @Mappings({
    @Mapping(source = "birthday", target = "birthday", dateFormat = DateUtil.CH_S)
  })
  UserVo user2UserVo(User user);
}
