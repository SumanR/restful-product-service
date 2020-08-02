# restful-product-service

REST API code with Spring Boot and Redis as database.

## How to Build?

## Install prerequisite
The server is available for free here: https://redis.io/download. If you use a Mac, you can install it with homebrew: `brew install redis`

Once its insalled you can bring up the server using the command : `redis-server`

This project is a java project that builds on Maven.

## Running build
Prerequisites: Make sure `JAVA_HOME` and `M2_HOME` is configured and set in your `PATH` variable.

Run
```
git clone git@github.com:SumanR/restful-product-service.git
```

Go to project root directory and run:
```
mvn install
```

## How to Run?

From an IDE: Load the project into an IDE and run "RestServiceApplication.java". This will bring up the service.

From command line:
```
java -jar ./productserv-service/target/rest-service-0.0.1-SNAPSHOT.jar .
```

### Loading initial data to redis

Running the test class "RestServiceApplicationTests.java" helps load the initial sample data in redis.

Data inserted to redis db can be cross verified using redis-cli
```
[~/Downloads/redis-6.0.6]$ redis-cli
127.0.0.1:6379> keys *
1) "products:12954218"
2) "products:54456119"
3) "products:13860428"
4) "products:13264003"
```

### Available Endpoints

method |	resource|	description | returns |
--- | --- | :--- | --- |
GET	| /products/:id	| get the product | 200 (OK), 500 (internal server error) |
POST |	/products |	creates a product in the DB | 201 (created), 500 (internal server error) |
PUT	|  /products/:id |	updates a product in the DB | 204 (updated), 500 (internal server error), 400 (request body not available) |
DELETE | /products/:id | deletes a product from the DB | 204 (deleted), 500 (internal server error), 400 (request body not available) |

### Curl Commands
 
#### GET
```
curl --location --request GET 'http://localhost:8080/products/13860428' --header 'Accept: application/json'
```

#### PUT
```
curl --location --request PUT 'http://localhost:8080/product/13860428' --header 'Accept: application/json' --header 'Content-Type: application/json' --data-raw '{
  "id": "13860428", 
  "name": "The Big Lebowski (Blu-ray) dsadasd", 
  "current_price": {
    "value": 234.4, 
    "currency_code": "AUD"
   }
}
```
#### POST 
```
curl --location --request POST 'http://localhost:8080/products/' --header 'Accept: application/json' --header 'Content-Type: application/json' --data-raw '{
  "id": "13860488", 
  "name": "The Big Lebowski (Blu-ray) ", 
  "current_price": {
    "value": 888.4, 
    "currency_code": "SGD"
  } 
}'
```

#### DELETE
```
curl --location --request DELETE 'http://localhost:8080/products/13860488' --header 'Accept: application/json'
```

# Testing the Endpoints

Make sure the Service is running in another tab. See "How to Run"
Navigate to directory <project root directory>/productserv-FunctionalTests/ and run 
```
  mvn test
```
This would execute the GET, PUT, POST and DELETE test cases against the server
  


