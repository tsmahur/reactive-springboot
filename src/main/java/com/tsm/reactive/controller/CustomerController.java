package com.tsm.reactive.controller;

import com.tsm.reactive.DTO.Customer;
import com.tsm.reactive.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    //NettyWebServer


    //Synchronous  and blocking
    @GetMapping("/withoutStream")
    public List<Customer> getAllCustomers() { //response will be generated only when the all data is buffered/loaded
        return service.loadAllCustomers();
    }


    //Asynchronous and non-blocking
//    @GetMapping(value = "/stream") //response sent when all response generated
    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE) //event stream is produced of Text(here)//publisher will now send stream instead of json object
    public Flux<Customer> getAllCustomersStream() {
        //also if request is canceled in between then respone processing is also cancelled in between
        return service.loadAllCustomersStream();
    }
}
