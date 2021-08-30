package com.tsm.reactive.handler;

import com.tsm.reactive.DAO.CustomerDao;
import com.tsm.reactive.DTO.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao dao;

    //ServerRequest -> to get server meta data
    //ServerResponse -> to map all response meta data

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customerList = dao.getCustomerList();
        return ServerResponse.ok().body(customerList,Customer.class);
    }


    public Mono<ServerResponse> findCustomer(ServerRequest request){

        int customerId= Integer.valueOf( request.pathVariable("input"));
        Mono<Customer> customerMono = dao.getCustomerList()
                .filter(c -> c.getId() == customerId)
//                .next(); //to get single ->i.e, Mono from Flux
              .take(1).single(); //or use this
        return ServerResponse.ok().body(customerMono,Customer.class);
    }


    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }
}