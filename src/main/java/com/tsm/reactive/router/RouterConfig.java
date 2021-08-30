package com.tsm.reactive.router;

import com.tsm.reactive.handler.CustomerHandler;
import com.tsm.reactive.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {


    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    //based on incoming url that will be redirected to handler
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()

                //implementing API using functional endpoint
                .GET("router/customers",handler::loadCustomers)
                .GET("/router/customer/{input}",handler::findCustomer) //path variable is passed in request
                .POST("/router/customer/save",handler::saveCustomer)

                .GET("/router/customers/stream",streamHandler::getCustomers)

                .build();

    }
}
