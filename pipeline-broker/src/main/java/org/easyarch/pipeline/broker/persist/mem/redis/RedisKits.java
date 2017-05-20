package org.easyarch.pipeline.broker.persist.mem.redis;

/**
 * Created by xingtianyu on 17-4-20
 * 下午8:07
 * description:
 */

class RedisKits {

    public static RedisClient.Strings strings(){
        return RedisClient.getInstance().strings();
    }

    public static RedisClient.Keys keys(){
        return RedisClient.getInstance().keys();
    }

    public static RedisClient.Lists lists(){
        return RedisClient.getInstance().lists();
    }

    public static RedisClient.Sets sets(){
        return RedisClient.getInstance().sets();
    }

    public static RedisClient.Hash hash(){
        return RedisClient.getInstance().hash();
    }

    public static RedisClient.SortSet sortSet(){
        return RedisClient.getInstance().sortSet();
    }

    public static RedisClient.Publisher publisher(){
        return RedisClient.getInstance().publisher();
    }

    public static RedisClient.Subscriber subscriber(){
        return RedisClient.getInstance().subscriber();
    }
}
