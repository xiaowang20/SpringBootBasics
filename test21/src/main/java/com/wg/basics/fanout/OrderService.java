package com.wg.basics.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //定义交换机
    private String exchangeName = "fanout_order_exchange";
    //路由key
    private String routeKey = "";
    public void saveOrder(Long userId,Long productId,int num){
        //1.生成订单号
        String orderNumber = UUID.randomUUID().toString();
        //2.根据商品id productId查询商品库存
        // int numstore = productSerivce.getProductNum(productId);
        // 3:判断库存是否充足
        // if(num >  numstore ){ return  "商品库存不足..."; }
        // 4: 下单逻辑
        // orderService.saveOrder(order);
        // 5: 下单成功要扣减库存
        // 6: 下单完成以后
        System.out.println("用户:"+userId+",订单编号是:"+orderNumber);
        //发送订单信息给rabbitMQ fanout
        rabbitTemplate.convertAndSend(exchangeName,routeKey,orderNumber);
    }
}
