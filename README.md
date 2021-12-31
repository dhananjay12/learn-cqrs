# learn-cqrs
CQRS microservice example using spring and axon

See Axon server - http://localhost:8024/


## Product Service

Create product:
```
curl --location --request POST 'localhost:8080/product' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title":"xbox-x",
    "price":"499.00",
    "quantity":"1"
}'
```

Get all products:
```
curl --location --request GET 'localhost:8080/products'
```

See H2 database - http://localhost:8080/h2-console/