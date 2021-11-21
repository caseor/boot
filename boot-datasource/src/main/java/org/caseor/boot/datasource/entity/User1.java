package org.caseor.boot.datasource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User1 implements Serializable {
  private static final long serialVersionUID = -76517425790622382L;

  private Long id;

  private String name;

  private Long age;

  private Long sex;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date birthday;

}
