package com.example.consumer.Controller;

//import com.eureka.common.redisOneNoPool.RedisBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.consumer.Controller
 * @Author tanjianglong
 * @CreatedTime 2017/8/11.
 * @Description :Plase give some message
 * 修改记录：
 * 1 可以看到这里，我们注入了LoadBalancerClient和RestTemplate，并在/consumer接口的实现中，
 * 先通过loadBalancerClient的choose函数来负载均衡的选出一个eureka-client的服务实例，
 * 这个服务实例的基本信息存储在ServiceInstance中，然后通过这些对象中的信息拼接出访问/dc接口的详细地址，
 * 最后再利用RestTemplate对象实现对服务提供者接口的调用。
 */
@RestController
public class DcController {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;
    //@Autowired
    //private ComRedisTemplate comRedisTemplate;


    /**
     * 消费测试类
     * @return
     */
    @GetMapping("/consumer")
    public String dc() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    //最简单的连接使用 不需要config.java
    //@Autowired
    //private StringRedisTemplate stringRedisTemplate;
    /*@GetMapping("/redisTest")
    public String redisTest() {
        //comRedisTemplate.set(new RedisKey(RedisKeyPrefixEnum.PAY_KEY,"111"),"test====");
        //String s = redisTemplate.get(ConsumerRedisTemplate.Consumer_prefix,"111");
        List<String> list =new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("v");
        stringRedisTemplate.opsForValue().set("abc", "测试");
        stringRedisTemplate.opsForList().leftPushAll("qq",list); // 向redis存入List

        return "111111";
    }*/

    /*@Autowired
    private JedisCluster jedisCluster;
    @GetMapping("/jedisClusterTest")
    public String jedisClusterTest() throws Exception {
        int t = 10000;
        redisBusiness.setEx("test_jedis_Stand-alone", "38967",t);
        //Assert.("38967", jedisCluster.get("test_jedis_cluster"));
        //jedisCluster.del("test_jedis_cluster");

        return "111111" + redisBusiness.get("test_jedis_Stand-alone");
    }*/

    /*@Autowired
    private RedisBusiness redisBusiness;

    @GetMapping("/jedisStandAloneTest")
    public String jedisClusterTest() throws Exception {
        int t = 10000;
        redisBusiness.setEx("test_jedis_Stand-alone", "38967",t);
        //Assert.("38967", jedisCluster.get("test_jedis_cluster"));
        //jedisCluster.del("test_jedis_cluster");

        return "111111" + redisBusiness.get("test_jedis_Stand-alone");
    }*/
}
