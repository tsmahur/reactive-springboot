package com.tsm.reactive.DAO;

import com.tsm.reactive.DTO.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {


    private static void sleepExecution(int i){ //// to created a delay between each object
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers()  {
        return IntStream.rangeClosed(1, 10) //generating integer stream
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("creating and getting customer List<Customer> stream with delay in dao : " + i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList());
    }


    public Flux<Customer> getCustomersStream()  {
        return Flux.range(1,10)  //generation of range using flux not IntStream
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("creating and getting customer Flux<Customer> stream  with delay in getCustomersStream(): " + i))
                .map(i -> new Customer(i, "customer" + i));
    }


    public Flux<Customer> getCustomerList()  { //no delay added in this method
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("creating and getting customer Flux<Customer> stream in getCustomerList() : " + i))
                .map(i -> new Customer(i, "customer" + i));
    }
}
