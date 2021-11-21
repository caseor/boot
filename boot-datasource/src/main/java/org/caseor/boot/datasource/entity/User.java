package org.caseor.boot.datasource.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
  private static final long serialVersionUID = -78337047327181521L;
  /**
   * 自增id
   */
  private Long id;
  /**
   * 用户名
   */
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 姓名
   */
  private String name;
  /**
   * 性别
   */
  private Integer sex;
  /**
   * 年龄
   */
  private Integer age;
  /**
   * 生日
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime birthday;
  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;
  /**
   * 创建人
   */
  private String createBy;
  /**
   * 更新时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateTime;
  /**
   * 更新人
   */
  private String updateBy;

}
