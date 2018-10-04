package cn.blankspace.taotao.content.jedis;


public interface JedisClient {
	//增
	String set(String key, String value);
	//查
	String get(String key);
	//是否存在
	Boolean exists(String key);

	//设置过期时间
	Long expire(String key, int seconds);

	//?
	Long ttl(String key);
	//自增
	Long incr(String key);
	//增加哈希
	Long hset(String key, String field, String value);

	String hget(String key, String field);
	Long hdel(String key, String... field);//删除hkey
	
}
