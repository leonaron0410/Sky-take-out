package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    //定时检查订单状态，如果已经未支付超过15分钟，则取消订单
    @Scheduled(cron = "0 * * * * ? ")
    //   @Scheduled(cron = "1/5 * * * * ? ")
    public void processTimeoutOrder() {
        log.info("开始检查订单状态,检查支付超时");
        LocalDateTime Time = LocalDateTime.now().plusMinutes(-15);

        List<Orders> ordersList = orderMapper.getOrderByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, Time);

        if (ordersList != null && ordersList.size() > 0) {
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelTime(LocalDateTime.now());
                orders.setCancelReason("订单超时取消");
                orderMapper.update(orders);
            }
        }
    }

    //每天凌晨一点检查昨日订单派送状态，如果订单状态为派送中，则修改为已完成
    @Scheduled(cron = "0 0 1 * * ? ")
    //   @Scheduled(cron = "0/5 * * * * ? ")
    public void processDeliveryOrder() {
        log.info("开始检查订单状态，检查派送后未完成订单");
        LocalDateTime time = LocalDateTime.now().plusHours(-1);
        List<Orders> ordersList = orderMapper.getOrderByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if (ordersList != null && ordersList.size() > 0) {
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }
    }
}
