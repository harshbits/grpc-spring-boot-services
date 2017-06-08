gRPC- Spring Boot - A Spring Boot implementation 
================================================


<table>
  <tr>
    <td><b>gRPC Homepage:</b></td>
    <td><a href="http://www.grpc.io/">www.grpc.io</a></td>
  </tr>
  <tr>
    <td><b>Spring Boot Homepage:</b></td>
    <td><a href="https://projects.spring.io/spring-boot/">https://spring.io/</a></td>
  </tr>
</table>

[![Join the chat at https://gitter.im/grpc/grpc](https://badges.gitter.im/grpc/grpc.svg)](https://gitter.im/grpc/grpc?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)


Reference Links:
----------------

### Protobuf

* [Google - protocol-buffers](https://developers.google.com/protocol-buffers/)
* [Github - protocol-buffers](https://github.com/google/protobuf/)


Usage:
------
Clone [the repository](https://github.com/harshbits/grpc-spring-boot-services.git), and add to your `pom.xml`:

```xml
<!-- Spring gRPC boot -->
<dependency>
  <groupId>com.grpc.service</groupId>
  <artifactId>grpc-services-boot-starter</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <exclusions>
     <exclusion>
        <groupId>io.grpc</groupId>
	<artifactId>grpc-netty</artifactId>
     </exclusion>
     <exclusion>
        <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter</artifactId>
     </exclusion>
  </exclusions>
</dependency>

```
below property in `application.yml`

``` yaml
grpc:
  port: <port>
```

Note: specifying above property (i.e. port) is optional. Default port is '7565'
