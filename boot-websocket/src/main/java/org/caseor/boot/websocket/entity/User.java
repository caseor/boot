package org.caseor.boot.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Fu Kai
 * @since 20210121
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String id;
  private String name;
  private Integer age;
  private LocalDateTime birthday;
}
