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
```xml
<dependencies>
		<!-- Spring gRPC boot -->
		<dependency>
			<groupId>com.grpc.service</groupId>
			<artifactId>grpc-services-boot-starter</artifactId>
			<version>${grpc.service.version}</version>
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
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

```
