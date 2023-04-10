package org.fd.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

    public static void main(String[] args) {

        JedisPool pool = new JedisPool("192.168.157.137", 6379);

        try(Jedis jedis = pool.getResource()){
            jedis.set("name", "fanwenpeng");
            jedis.expire("expire10", 10);
            System.out.println(jedis.get("name"));
            Thread.sleep(9000);
            System.out.println(jedis.get("expire10"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
