package fanshuaiko.redisredisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.IOException;

/**
 * @Description
 * @Author zhouqifan
 * @Date 2018/12/11 0011 上午 11:30
 **/
@Configurable
public class Redisson_Config {
    public static RedissonClient getRedisson() throws IOException {
//          可以从yaml文件导入配置，但此处失败了，暂未找到原因，以后更新吧
//          Config config = Config.fromYAML(new File(String.valueOf(Redisson_Config.class.getClassLoader().getResource("redisson-config.yml"))));
        Config config = new Config();
        config.useClusterServers().addNodeAddress( "redis://192.168.139.130:7001"
                ,"redis://192.168.139.130:7002"
                , "redis://192.168.139.130:7003"
                , "redis://192.168.139.130:7004"
                , "redis://192.168.139.130:7005"
                , "redis://192.168.139.130:7006").setScanInterval(5000);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
