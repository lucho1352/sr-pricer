### Design Aspects

* This Service implements a strategy pattern to segregate the calculation of the price
by brandId, bare in mind that if you add more strategies you  will need to use
qualifiers to inject the beans.

* This service also uses a H2 repository to store the prices for the given brand and product

* Finally, for tracking purposes the services has a Filter in which we are calculating and logging
the time taken to perform a given action, and in addition we send in the response a header
that represents the uuid of the transaction. 

### Pending Work

1. Implement logback to create a pattern to log in ay APM
2. Implement spring security base on your needs

### H2

H2 database can be found under next url

```
http://localhost:8080/h2
```

### Swagger

In next link you will be able tu explore the swagger locally, but remember to change the 
localhost and port for the one that you are exposing, in order to be able to read the swagger
from your corresponding environment

```
http://localhost:8080/swagger-ui/index.html
```

### Curl

Up next, you will find a curl command that will help you to interact with /v1/prices endpoint

```
curl --location 'http://localhost:8080/v1/prices?productId=35455&brandId=1&date=2023-11-30T11%3A02%3A31' \
--header 'accept: application/json'
```

### Test

The service has integration with jacoco to run a report with unit test, and for that you just need to execute next maven command

```
mvn clean install
```

In addition service is also integrated with pit test to check how strong those are, to run the mutation tests
you will need to execute next command.

```
mvn clean install org.pitest:pitest-maven:mutationCoverage
```