package org.e3.common.jedis;

/**
 * @author xujin
 * @package-name org.e3.common.jedis
 * @createtime 2019-10-11 14:24
 */

public interface JedisClient {

    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String field, String value);
    String hget(String key, String field);
    Long hdel(String key, String... field);
}
