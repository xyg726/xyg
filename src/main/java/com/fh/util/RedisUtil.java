package com.fh.util;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisUtil {

    public static void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            //抛出异常
            throw new RuntimeException(e);
        } finally {
            if (null !=jedis){
                jedis.close();
            }
        }
    }

    public static String get(String key){
            Jedis jedis = null;
        try {
            jedis = RedisPool.getResource();
            String s = jedis.get(key);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null !=jedis){
                jedis.close();
            }
        }
    }



    public static void del(String key){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null !=jedis){
                jedis.close();
            }
        }
    }

    public static void del(String key,String field){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.del(key,field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null !=jedis){
                jedis.close();
            }
        }
    }

    public static void setex(String key, String value,int seconds) {
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.setex(key,seconds,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis !=null){
                jedis.close();
            }
        }
    }


    public static void hset(String key,String field,String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.hset(key,field,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }

    public static String hget(String key, String field){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            String hget = jedis.hget(key, field);
            return hget;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }
    public static List<String> hget(String key){
        Jedis resource = null;
        List<String> result = null;
        try {
            resource = RedisPool.getResource();
            result = resource.hvals(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(null != resource){
                resource.close();
            }
        }
        return result;
    }

    public static Boolean exists(String key){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            Boolean exists = jedis.exists(key);
            return exists;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }

    public static Boolean exists(String key,String field){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            Boolean exists = jedis.hexists(key, field);
            return exists;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            jedis.close();
        }
    }

}
