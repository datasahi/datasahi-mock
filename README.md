## datasahi mock

[Home Page](https://datasahi.com)

`datasahi mock` mocks data to test datasahi flow server

---
### Features
- [x] creates data in redis hash format
- [x] uses spring boot repository to create data in redis
- [x] amount of data to create is controlled in the api call

---
### Usage
- download the latest zip file from the releases. The zip file contains the jar file, and a starter script
- needs jre 17 to execute
- create a env file or set these variables in environment
```shell
export DATASAHI_MOCK_PORT=8082
export DATASAHI_MOCK_WORKDIR=work
export REDIS_HOST=localhost
export REDIS_PORT=6379
```
Sample env file - `datasahi-mock.env`
```shell
DATASAHI_MOCK_PORT=8082
DATASAHI_MOCK_WORKDIR=work
REDIS_HOST=localhost
REDIS_PORT=6379
```
- use this command to start the server
 ```shell
  chmod +x start-datasahi-mock.sh
  ./start-datasahi-mock.sh
```
### APIs
This will simulate 100 records twice with a sleep of 10 milliseconds in between
```shell
curl -X POST 'http://localhost:8082/lead/simulate' -H 'Content-Type: application/json' -d '{"batchSize": 100, "times": 2, "sleepMillis": 10}'
```
