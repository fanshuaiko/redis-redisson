package fanshuaiko.redisredisson;


import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author zhouqifan
 * @Date 2018/12/11 0011 上午 11:44
 **/

/**
 * redisson提供了多种锁，详细可见 https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8
 */
@Controller
public class RedissonController{
    public void redissonTest() throws IOException {
        RedissonClient redissonClient = Redisson_Config.getRedisson();
        System.out.println("=======config设置成功======="+Redisson_Config.getRedisson().getConfig().toJSON());
        RBucket<Object> rBucket = redissonClient.getBucket("testNumber");
        rBucket.set(200);
        System.out.println("----------"+ rBucket.get());
        //获得重入锁
        RLock rLock = redissonClient.getLock("myTestKey");
//        //获得公平锁
//        RLock fairLock = redissonClient.getFairLock("myTestKey");
//        //获得读写锁
//        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("myTestKey");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("**********线程1开启***********"+Thread.currentThread().getName());
                try {
                    rLock.tryLock(5,10,TimeUnit.SECONDS);
//需要加锁的代码
                    System.out.println("1加锁是否成功："+rLock.isLocked());
                    RBucket<Integer> rBucket = redissonClient.getBucket("testNumber");
                    for (int i = 0; i < 20; i++) {
                        if(rBucket.get()>0){
                            rBucket.set(rBucket.get()-10);
                            System.out.println("线程1testNumber的值为："+rBucket.get());
                        }
                    }
                    System.out.println("**********1线程关闭***********"+Thread.currentThread().getName());
//解锁
                    rLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.run();
        }


}

