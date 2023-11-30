### Swagger

In next link you will be able tu explore the swagger locally, but remember to change the 
localhost and port for the one that you are exposing, in order to be able to read the swagger
from your corresponding environment

http://localhost:8080/swagger-ui/index.html


### Curl

Up next, you will find a curl command that will help you to interact with /v1/prices endpoint

```
curl --location 'http://localhost:8080/v1/prices?productId=35455&brandId=1&date=2023-11-30T11%3A02%3A31' \
--header 'accept: application/json'
```