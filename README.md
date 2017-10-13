gRPC- Spring Boot - A Spring Boot implementation 
================================================


<table>
  <tr>
    <td><b>gRPC Homepage:</b></td>
    <td><a href="http://www.grpc.io/">grpc.io</a></td>
  </tr>
  <tr>
    <td><b>Spring Boot Homepage:</b></td>
    <td><a href="https://projects.spring.io/spring-boot/">spring.io</a></td>
  </tr>
</table>

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fecf258dd5c54b2082400efdf8165fe7)](https://www.codacy.com/app/hbhavsar2110/grpc-spring-boot-services?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=harshbits/grpc-spring-boot-services&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/harshbits/grpc-spring-boot-services.svg?branch=master)](https://travis-ci.org/harshbits/grpc-spring-boot-services)
[![Join the chat at https://gitter.im/grpc/grpc](https://badges.gitter.im/grpc/grpc.svg)](https://gitter.im/grpc/grpc?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Version Details:
----------------

#### Current Version: `2.0.0-SNAPSHOT`

* What's new?
  * Support for [Consul](https://www.consul.io/)
  * Java 9 Support
  * io.grpc (grpc-java) version: `1.7.0`

#### Previous Version: `1.0.0.RELEASE`
* Code is in branch 1.0.0.RELEASE

#### Future Planning: `2.0.1-SNAPSHOT`
* [Java 9](http://openjdk.java.net/projects/jdk9/) Module Structure 


Reference Links:
----------------

#### Protobuf

* [Google - protocol-buffers](https://developers.google.com/protocol-buffers/)
* [Github - protocol-buffers](https://github.com/google/protobuf/)

#### HTTP2
* [Documentation - HTTP2](https://http2.github.io/)
* [gRPC over HTTP2](https://github.com/grpc/grpc/blob/master/doc/PROTOCOL-HTTP2.md)
* [Google Developers Page - HTTP2](https://developers.google.com/web/fundamentals/performance/http2/)

Usage:
------
Clone [the repository](https://github.com/harshbits/grpc-spring-boot-services.git), and add to your `pom.xml`:

```xml
<!-- Spring gRPC boot -->
<dependency>
  <groupId>com.grpc.service</groupId>
  <artifactId>grpc-services-boot-starter</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</dependency>

```
below property in `application.yml`

``` yaml
grpc:
  port: <port>
```

Note: specifying above property (i.e. port) is optional. Default port is `6565`

#### Demo server project

* [grpc-service-demo-server](https://github.com/harshbits/grpc-spring-boot-services/tree/master/grpc-services/grpc-service-parent/grpc-service-demo-server)

#### Demo client project

* [grpc-service-demo-client](https://github.com/harshbits/grpc-spring-boot-services/tree/master/grpc-services/grpc-service-parent/grpc-service-demo-client)

#### How to Build
* Refer this github [grpc-java](https://github.com/grpc/grpc-java#download) page
* Right click on project and run maven update command from IDE (STS/Eclipse). It will generate protobuf resources.

#### How to Run
* Import demo Server, Client and Eureka projects.
* Run Eureka Server.
* Run Demo Server project and wait for Eureka to register it.
* Once, Server project will get registered, start Demo Client project.

#### Sample Request
* http://localhost:19082/hello_grpc

#### Sample Response
```json
{
  "message": "Hello Harsh"
}
```

Prerequisites
-------------
In order to start work with project, make sure you have configured IDE/System with prerequisites steps.

#### Java Version
* Java SE Development Kit 8 (JDK 8) 

#### Lombok
* Install Lombok for your IDE (i.e. STS or Eclipse)
* Version to install: `1.16.16`
* Installation Guide: [Please refer this link](http://codeomitted.com/setup-lombok-with-stseclipse-based-ide/)

#### .proto file editor
* Install protobuf file editor for IDE (i.e. STS or Eclipse
* Eclipse Marketplace - [Please refer this link](https://marketplace.eclipse.org/content/protobuf-dt)

Reference Projects/Repositories:
--------------------------------

* https://github.com/LogNet/grpc-spring-boot-starter
* https://github.com/ExampleDriven/spring-boot-grpc-example

License
-------

`Apache 2.0`
