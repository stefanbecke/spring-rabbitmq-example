# spring-rabbitmq-example
The following project was created to get a general understanding about AMQP (by RabbitMQ). 
This project shows the usage of header-exchanges but could be enhanced for other exchange types as well.  
 
## How to start?
0) Build the project first `mvn clean install` on project root-level
1) Start the Spring Boot Applications by running it as a Java Application. 
1) Start rabbitmq - `docker-compose up` and verify, that [rabbitmq](http://localhost:15672/) was started and login via `rabbitmq`/`rabbitmq`
5) GOTO [Swagger](http://localhost:9081/swagger-ui.html#!/booking-controller/createBookingUsingGET) to create a booking with some consignee-name e.g. foo or bar
6) Check out the console of the according spring-boot applications

## TODO
- Tests 
- Persistent events and deletion after successful publishing of a message 

## Links
- https://github.com/rabbitmq/rabbitmq-jms-client-spring-boot-trader-demo
- https://www.rabbitmq.com/jms-client.html