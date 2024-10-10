package com.doksanbir.productordermanagementpoc.configuration;

import com.doksanbir.productordermanagementpoc.application.strategy.order.OrderProcessingStrategy;
import com.doksanbir.productordermanagementpoc.domain.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration class for managing Order Processing Strategies.
 * <p>
 * This class defines a bean that provides a mapping between {@link OrderStatus}
 * and corresponding {@link OrderProcessingStrategy} implementations. The strategies
 * are automatically injected into the map based on the available Spring-managed beans.
 */
@Configuration
public class OrderProcessingConfig {

    /**
     * Creates a map of {@link OrderStatus} to {@link OrderProcessingStrategy}.
     * <p>
     * This map is used to dynamically assign the appropriate processing strategy
     * to an order based on its current status.
     *
     * @param strategies the list of available {@link OrderProcessingStrategy} beans
     * @return a map associating each {@link OrderStatus} with its processing strategy
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
