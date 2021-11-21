package org.caseor.boot.datasource.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Fu Kai
 * @since 20210711
 */

@Configuration
@MapperScan(basePackages = {"org.caseor.boot.datasource.mapper"})
@EnableTransactionManagement
public class MybatisConfig {

  /**
   * 分页
   */
  @Bean
  public MybatisPlusInterceptor paginationInterceptor() {
    MybatisPlusInterceptor paginationInterceptor = new MybatisPlusInterceptor();
    paginationInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    paginationInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
    paginationInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
    // paginationInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
    // paginationInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.SQL_SERVER));
    return paginationInterceptor;
  }
}
