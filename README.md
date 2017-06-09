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

Version Details:
----------------

Current Version: 0.0.1-SNAPSHOT


Reference Links:
----------------

### Protobuf

* [Google - protocol-buffers](https://developers.google.com/protocol-buffers/)
* [Github - protocol-buffers](https://github.com/google/protobuf/)

### HTTP2
* [Documentation - HTTP2](https://http2.github.io/)
* [gRPC over HTTP2](https://github.com/grpc/grpc/blob/master/doc/PROTOCOL-HTTP2.md)

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

Note: specifying above property (i.e. port) is optional. Default port is `7565`

### Demo server project

* [grpc-service-demo-server](https://github.com/harshbits/grpc-spring-boot-services/tree/master/grpc-services/grpc-service-parent/grpc-service-demo-server)

### How to Build
Refer this github [grpc-java](https://github.com/grpc/grpc-java#download) page


Reference Projects/Repositories:
--------------------------------

* https://github.com/LogNet/grpc-spring-boot-starter
* https://github.com/ExampleDriven/spring-boot-grpc-example
