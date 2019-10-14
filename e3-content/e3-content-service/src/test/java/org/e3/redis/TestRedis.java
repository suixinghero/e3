package org.e3.redis;

import org.e3.common.jedis.JedisClientCluster;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xujin
 * @package-name org.e3.redis
 * @createtime 2019-10-11 10:21
 */

public class TestRedis {
    @Test
    public void  testRedis1(){
        Jedis jedis=new Jedis("192.168.25.129",6379);
        jedis.auth("XUjin19980928");
        List<String> stringList=jedis.lrange("list1",0,-1);
        System.out.println(stringList);
        jedis.close();
    }

    @Test
    public void testRedisCluster() throws IOException {
        //创建一个jediscluster对象，有一个参数nodes是一个set类型。set中包含若干个HostAndPort对象。
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("192.168.25.130",7001));
        nodes.add(new HostAndPort("192.168.25.130",7002));
        nodes.add(new HostAndPort("192.168.25.130",7003));
        nodes.add(new HostAndPort("192.168.25.130",7004));
        nodes.add(new HostAndPort("192.168.25.130",7005));
        nodes.add(new HostAndPort("192.168.25.130",7006));
        JedisCluster jedisCluster=new JedisCluster(nodes);
        String s=jedisCluster.get("a");
        System.out.println(s);
        jedisCluster.close();

    }
    @Test
    public void testRedisClusterBySpring() throws IOException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/applicationContext-redis.xml");
        JedisClientCluster jedisClientCluster=applicationContext.getBean(JedisClientCluster.class);
        String a=jedisClientCluster.get("a");
        System.out.println(a);
    }



}
