package org.caseor.boot.datasource.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 **/

@Component
public class RedisService {

  private final RedisTemplate<String, Object> redisTemplate;

  @Autowired
  public RedisService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  /**
   * 缓存基本的对象，Integer、String、实体类等
   */
  public <T> void setObject(final String key, final T val) {
    redisTemplate.opsForValue().set(key, val);
  }

  /**
   * 缓存基本的对象，Integer、String、实体类等
   * @param key 缓存的键值
   * @param val 缓存的值
   * @param timeout 时间
   * @param timeUnit 时间颗粒度
   */
  public <T> void setObject(final String key, final T val, final Long timeout, final TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, val, timeout, timeUnit);
  }

  /**
   * 获得缓存的基本对象。
   * @param key 缓存键值
   * @return 缓存键值对应的数据
   */
  public Object getObject(final String key) {
    return redisTemplate.opsForValue().get(key);
  }

  /**
   * 设置有效时间
   * @param key Redis键
   * @param timeout 超时时间
   * @return true=设置成功；false=设置失败
   */
  public Boolean expire(final String key, final long timeout) {
    return expire(key, timeout, TimeUnit.SECONDS);
  }

  /**
   * 设置有效时间
   * @param key Redis键
   * @param timeout 超时时间
   * @param unit 时间单位
   * @return true=设置成功；false=设置失败
   */
  public Boolean expire(final String key, final long timeout, final TimeUnit unit) {
    return redisTemplate.expire(key, timeout, unit);
  }

  /**
   * 判断 key是否存在
   * @param key 键
   * @return true 存在 false不存在
   */
  public Boolean isKeyExisted(String key) {
    return redisTemplate.hasKey(key);
  }

  /**
   * 删除单个对象
   */
  public Boolean deleteObject(final String key) {
    return redisTemplate.delete(key);
  }

  /**
   * 删除集合对象
   * @param collection 多个对象
   */
  public Long deleteObject(final Collection collection) {
    return redisTemplate.delete(collection);
  }

  /**
   * 缓存List数据
   * @param key 缓存的键值
   * @param dataList 待缓存的List数据
   * @return 缓存的对象
   */
  public <T> Long setList(final String key, final List<T> dataList) {
    Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
    return count == null ? 0 : count;
  }

  /**
   * 获得缓存的list对象
   * @param key 缓存的键值
   * @return 缓存键值对应的数据
   */
  public List<Object> getList(final String key) {
    return redisTemplate.opsForList().range(key, 0, -1);
  }

  /**
   * 获得缓存的基本对象列表
   * @param pattern 字符串前缀
   * @return 对象列表
   */
  public Collection<String> keys(final String pattern) {
    return redisTemplate.keys(pattern);
  }
}
