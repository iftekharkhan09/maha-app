<!-- ABOUT THE PROJECT -->
## About The Project

This is a simplified e-commerce API with a single endpoint that performs a checkout action. 
The single endpoint should take a list of watches and return the total cost.

### Prerequisites
* mvn

https://maven.apache.org/install.html

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/iftekharkhan09/maha-app.git
   ```
2. Navigate to maha-app
   ```sh
   cd maha-app
   ```
   
3. Clean & Install Maven packages
   ```sh
   mvn clean install
   ```
4. Start app
    ```sh
    mvn spring-boot:run
   ```
### API Endpoint

####Checkout API
curl -L -X POST "http://localhost:8080/checkout" -H "Content-Type: application/json" --data-raw ${list_of_productIds}

Here `list_of_productIds` is the checked out productIds and can be repeated.

sample request:-
```
curl -L -X POST "http://localhost:8080/checkout" -H "Content-Type: application/json" --data-raw "[\"001\", \"002\"]"
```

