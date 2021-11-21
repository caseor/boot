package org.caseor.boot.rabtmq.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Fu Kai
 * @since 20201021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageStruct implements Serializable {
  private static final long serialVersionUID = -7067428257238522776L;
  private String message;
}
