# reactive-springboot

##This project contains :

1. Asynchronous and non blocking vs Synchronous and blocking concept
2. Functional endpoints using handler and router
3. Reactive MongoDB
4. Event driven Stream
5. Demo of Flux and Mono in ReactIveTest.java file
6. BeanUtils.copyProperties() -> model to entity and vice-versa
7. NettyWebServer

##Endpoints:

###1. MongoDB Reactive Examples :->
    GET /products
    GET /products/{id}
    GET /products/product-range/?min=10&max=20
    POST /products {
       "name":"books",
       "qty":5,
       "price":501
      }
    PUT /products/{id} {
       "name":"books",
       "qty":5,
       "price":501
      }
    DELETE /products/{id}

###2. Asynchronous and non blocking vs Synchronous and blocking concept
    GET /customers/withoutStream -> conventional
    GET /customers/stream -> Reactive programming | Webflux
    
###3. Functional endpoints
    GET router/customers 
    GET /router/customer/{input}
    POST /router/customer/save
    GET /router/customers/stream -> async and non blocking
