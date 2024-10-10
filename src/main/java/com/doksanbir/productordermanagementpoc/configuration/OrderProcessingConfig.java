package com.doksanbir.productordermanagementpoc.configuration;

import com.doksanbir.productordermanagementpoc.application.strategy.order.OrderProcessingStrategy;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration class for Order Processing Strategies.
 */
@Configuration
public class OrderProcessingConfig {

    /**
     * Creates a map of OrderStatus to OrderProcessingStrategy.
     *
     * @param strategies the list of available OrderProcessingStrategy beans
     * @return a map associating each OrderStatus with its strategy
     */
    @Bean
    public Map<OrderStatus, OrderProcessingStrategy> orderProcessingStrategies(List<OrderProcessingStrategy> strategies) {
        Map<OrderStatus, OrderProcessingStrategy> map = new EnumMap<>(OrderStatus.class);
        for (OrderProcessingStrategy strategy : strategies) {
            map.put(strategy.getHandledStatus(), strategy);
        }
        return map;
    }
}
