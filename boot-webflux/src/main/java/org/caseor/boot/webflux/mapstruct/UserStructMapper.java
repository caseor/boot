package org.caseor.boot.webflux.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Fu Kai
 * @since 20211120
 */

@Mapper
public interface UserStructMapper {
  UserStructMapper INSTANCE = Mappers.getMapper(UserStructMapper.class);


}
