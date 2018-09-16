### HW2 client
- to build a docker image:
```
docker build -t hw2c .
```
- to run the application:
```
docker run -it --network host --name hw2-spring-client hw2c <message>
```
