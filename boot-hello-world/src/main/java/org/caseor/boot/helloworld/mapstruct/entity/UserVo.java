package org.caseor.boot.helloworld.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Fu Kai
 * @since 20211120
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserVo {
  private String id;
  private String name;
  private String age;
  private String sex;
  private String birthday;
}
