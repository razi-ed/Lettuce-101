package io;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

public class ConnectToRedis {
    public static void main(String[] args) {
        RedisClient redisClient =null;
        StatefulRedisConnection<String, String> connection = null;

        System.out.println("connected to Redis");

        try {
            redisClient = RedisClient.create("redis://localhost:6379");
            connection = redisClient.connect();
            RedisCommands<String, String> syncCommands = connection.sync();
            syncCommands.set("first-entry", "Hello, Redis!");
            String fromDB = syncCommands.get("first-entry");
            System.out.println("from DB :" + fromDB);
        } catch (Exception ex) {
            System.out.println("Entered Catch Block because of error");
            ex.printStackTrace();
            System.out.println("Caught Exception");
        } finally {
            System.out.println("Entered Finally");
            connection.close();
            redisClient.shutdown();
        }
    }
}