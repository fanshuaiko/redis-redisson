package fanshuaiko.redisredisson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisRedissonApplicationTests {
    @Autowired
    RedissonController redissonController;
    @Test
    public void contextLoads() throws IOException {
        redissonController.redissonTest();
    }

}
