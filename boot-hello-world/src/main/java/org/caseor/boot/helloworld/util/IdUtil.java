package org.caseor.boot.helloworld.util;

import com.github.yitter.contract.IIdGenerator;
import com.github.yitter.contract.IdGeneratorException;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.DefaultIdGenerator;

/**
 * @author Fu Kai
 * @since 20210411
 */

public class IdUtil {

  /**
   * 节点id 务必保证每个节点id不同
   * WorkerIdBitLength默认为6 即2^6-1 = 63
   * WORKER_ID取值范围在[0, 63] 最大节点只允许64个
   */
  private static final Short WORKER_ID = 0;

  /**
   * 基于系统最新时间 所有节点必须统一 初始设置一次 系统运行后禁止修改
   */
  private static final Long BASE_TIME = 1618115412754L;


  private IdUtil() {
  }

  enum IdEnum {
    /**
     * 实例
     */
    INSTANCE;
    private final IIdGenerator idGenerator;

    IdEnum() {
      IdGeneratorOptions options = new IdGeneratorOptions(WORKER_ID);
      options.BaseTime = BASE_TIME;
      idGenerator = new DefaultIdGenerator(options);
    }

    public IIdGenerator get() {
      return idGenerator;
    }
  }

  /**
   * 生成id
   */
  public static long id() throws IdGeneratorException {
    return IdEnum.INSTANCE.get().newLong();
  }

}
