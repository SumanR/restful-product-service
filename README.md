# restful-product-service

REST API code with Spring Boot and Redis as database.

# How to Build

This project is a java project that builds in Maven.

Prerequisites: Make sure JAVA_HOME and M2_HOME is configured and set in your PATH variable.

Go to project root directory and run 

```
mvn package
```

# How to Run

From an IDE: Load the project into an IDE and run "RestServiceApplication.java". This will bring up the service.

From command line : java -jar [root path of the project]/productserv-service/target/rest-service-0.0.1-SNAPSHOT.jar .

```
[~]$ java -jar <root path of the project>/productserv-service/target/rest-service-0.0.1-SNAPSHOT.jar .

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.2.RELEASE)

2020-08-01 17:23:17.164  INFO 57724 --- [           main] c.c.restservice.RestServiceApplication   : Starting RestServiceApplication v0.0.1-SNAPSHOT on LM-BGL-265
```

# Setting up DB

The server is available for free here (https://redis.io/download).

If you use a Mac, you can install it with homebrew: brew install redis

Once its insalled you can bring up the server using the command : redis-server

```[~/Downloads/redis-6.0.6]$ redis-server 
55045:C 31 Jul 2020 15:06:47.202 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
55045:C 31 Jul 2020 15:06:47.202 # Redis version=6.0.6, bits=64, commit=00000000, modified=0, pid=55045, just started
55045:C 31 Jul 2020 15:06:47.202 # Warning: no config file specified, using the default config. In order to specify a config file use redis-server /path/to/redis.conf
55045:M 31 Jul 2020 15:06:47.204 * Increased maximum number of open files to 10032 (it was originally set to 256).
                _._                                                  
           _.-``__ ''-._                                             
      _.-``    `.  `_.  ''-._           Redis 6.0.6 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._                                   
 (    '      ,       .-`  | `,    )     Running in standalone mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6379
 |    `-._   `._    /     _.-'    |     PID: 55045
  `-._    `-._  `-./  _.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |           http://redis.io        
  `-._    `-._`-.__.-'_.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |                                  
  `-._    `-._`-.__.-'_.-'    _.-'                                   
      `-._    `-.__.-'    _.-'                                       
          `-._        _.-'                                           
              `-.__.-'                                               

55045:M 31 Jul 2020 15:06:47.206 # Server initialized
```

To check the keys in the redis server, use the redis-cli commands. Command : redis-cli

```
[~/Downloads/redis-6.0.6]$ redis-cli
127.0.0.1:6379> KEYS *
(empty array)
127.0.0.1:6379> get product:13860428
(nil)
```

# Loading initial data to redis

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

# Available Endpoints

method |	resource|	description |
--- | --- | :--- | 
GET	| /products/:id	| get the product -> 200(OK), 500(internal server error) |
POST |	/products |	creates a product in the DB -> 201(created), 500(internal server error) |
PUT	|  /products/:id |	updates a product in the DB -> 204(updated), 500(internal server error), 400(request body not available) |
DELETE | /products/:id | deletes a user from the DB -> 204(deleted), 500(internal server error), 400(request body not available) |

# Curl Command 

method |	curl command |
--- | :--- | 
GET	|curl --location --request GET 'http://localhost:8080/products/13860428' --header 'Accept: application/json'|
PUT | curl --location --request PUT 'http://localhost:8080/product/13860428' --header 'Accept: application/json' --header 'Content-Type: application/json' <br /> --data-raw '{  <br />    "id": "13860428",  <br />    "name": "The Big Lebowski (Blu-ray) dsadasd", <br />    "current_price": { <br />        "value": 234.4, <br />        "currency_code": "AUD" <br />    } <br />}' |
POST | curl --location --request POST 'http://localhost:8080/products/' --header 'Accept: application/json' --header 'Content-Type: application/json' <br /> --data-raw '{ <br />    "id": "13860488", <br />    "name": "The Big Lebowski (Blu-ray) ", <br />    "current_price": { <br />        "value": 888.4, <br />        "currency_code": "SGD" <br />    } <br />}' |
DELETE |  curl --location --request DELETE 'http://localhost:8080/products/13860488' --header 'Accept: application/json' |

# Testing the Endpoints

Make sure the Service is running in another tab. See "How to Run"
Navigate to directory <project root directory>/productserv-FunctionalTests/ and run 
```
  mvn test
```
This would execute the GET, PUT, POST and DELETE test cases against the server
  
