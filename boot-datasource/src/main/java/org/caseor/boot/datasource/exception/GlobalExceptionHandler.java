package org.caseor.boot.datasource.exception;

import org.caseor.common.core.entity.R;
import org.caseor.common.core.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Fu Kai
 * @since 20211120
 */

@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 捕捉模块下的ServiceException
   */
  @ExceptionHandler(ServiceException.class)
  public R<?> exceptionHandler(ServiceException e) {
    return R.failure(e.getHttpCode(), e.getData(), e.getMsg());
  }

}
