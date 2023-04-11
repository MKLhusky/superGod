package org.fd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //========================事务========================

    /**
     * 开启事务
     */
    public void tranStart(){
        redisTemplate.multi();
    }

    /**
     * 放弃事务
     */
    public void tranDiscard(){
        redisTemplate.discard();
    }

    /**
     * 对一个key开启监控，如果在开启监控的时间内该key的值发生变化了，那么就取消整个事务
     */
    public void watch(String key){
        redisTemplate.watch(key);
    }


    //========================String=============================

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Boolean setValueNx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public Boolean setValueXx(String key, String value) {
        return redisTemplate.opsForValue().setIfPresent(key, value);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Long incrBy(String key, long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    public Double incrBy(String key, double value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    public Long decrBy(String key, long value) {
        return redisTemplate.opsForValue().decrement(key, value);
    }

    public String delStr(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }

    public void multiSet(Map<String, String> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public Boolean expire(String key, long time, TimeUnit timeUtil) {
        return redisTemplate.expire(key, time, timeUtil);
    }


    //========================集合=============================

    /**
     * 从左边插入一个
     *
     * @param key
     * @param value
     * @return
     */
    public Long leftPush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 从左边插入多个
     *
     * @param key
     * @param value
     * @return
     */
    public Long leftPushAll(String key, String... value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 从右边插入
     *
     * @param key
     * @param value
     * @return
     */
    public Long rightPush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 从右边插入多个
     *
     * @param key
     * @param value
     * @return
     */
    public Long rightPushAll(String key, String... value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 从左边弹出一个值
     *
     * @param key
     * @return
     */
    public String leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 等待并且从左边弹出一个元素，如果time时间内都没有值，那么返回null
     *
     * @param key
     * @param time
     * @return
     */
    public String leftPopByTimeout(String key, Duration time) {
        return redisTemplate.opsForList().leftPop(key, time);
    }

    /**
     * 从左边弹出一定数量的集合
     *
     * @param key
     * @param count
     * @return
     */
    public List<String> leftPopList(String key, int count) {
        return redisTemplate.opsForList().leftPop(key, count);
    }

    /**
     * 从右边弹出一个值
     *
     * @param key
     * @return
     */
    public String rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 等待并且从右边弹出一个元素，如果time时间内都没有值，那么返回null
     *
     * @param key
     * @param time
     * @return
     */
    public String rightPopTimeout(String key, Duration time) {
        return redisTemplate.opsForList().rightPop(key, time);
    }

    /**
     * 从右边弹出指定数量的集合
     *
     * @param key
     * @param count
     * @return
     */
    public List<String> rightPopList(String key, int count) {
        return redisTemplate.opsForList().rightPop(key, count);
    }

    /**
     * 去除范围外所有的数据
     *
     * @param key
     * @param start
     * @param end
     */
    public void trimList(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 移除集合里面count个指定值
     *
     * @param key
     * @param count > 0 从头开始的count个元素， < 0 从尾部开始的count个值  = 0 移除所有
     * @param value
     * @return
     */
    public Long remove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }


    //==========================hash========================

    /**
     * 存入一个对象
     *
     * @param key
     * @param objectName  对象名
     * @param objectValue 对象值
     */
    public void put(String key, Object objectName, Object objectValue) {
        redisTemplate.opsForHash().put(key, objectName, objectValue);
    }

    /**
     * 存入多个对象
     *
     * @param key
     * @param value
     */
    public void putAll(String key, Map value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    /**
     * 删除一个字典对象
     *
     * @param key
     */
    public Long deleteObj(String key) {
        return redisTemplate.opsForHash().delete(key);
    }


    //==========================zset========================

    /**
     * 排序集合添加元素
     *
     * @param key
     * @param value
     * @param score 分数
     */
    public Boolean zAdd(String key, String value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 排序集合删除key中的一些值
     *
     * @param key
     * @param values
     */
    public Long zRemove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 查询出一个范围内的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 查询出一个key对应的value的分数
     *
     * @param key
     * @param value
     * @return
     */
    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }
}
