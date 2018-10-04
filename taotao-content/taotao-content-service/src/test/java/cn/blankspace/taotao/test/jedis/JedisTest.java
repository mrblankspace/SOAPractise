package cn.blankspace.taotao.test.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    //测试单机版
    @Test
    public void testJedis(){
        Jedis js = new Jedis("118.126.110.120",7001);
        js.set("key1","123");
        System.out.print(js.get("key1"));
        js.close();
    }

    //测试jedis连接池    不需要密码啥的
    @Test
    public void testJedisPool(){
        JedisPool pool = new JedisPool("118.126.110.120",7001);
        Jedis resource = pool.getResource();

        System.out.print(resource.get("key1"));
    }

    /**
     * 测试jedis集群
     */
    @Test
    public void testJedisCluster() throws IOException {
        Set<HostAndPort> nodes = new HashSet<>();

        nodes.add(new HostAndPort("118.126.110.120",6379));
        nodes.add(new HostAndPort("149.28.25.213",6380));

        nodes.add(new HostAndPort("118.24.156.136",6379));
        nodes.add(new HostAndPort("118.126.110.120",6380));


        nodes.add(new HostAndPort("149.28.25.213",6379));
        nodes.add(new HostAndPort("118.24.156.136",6380));


        JedisCluster cluster = new JedisCluster(nodes );
        cluster.set("kc","cluster1");
        System.out.print(cluster.get("kc"));
      //  jedisCluster 内部封装了连接池
        cluster.close();
    }
}
