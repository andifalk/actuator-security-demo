# Spring Actuator Security

This is a demo app showing how to configure actuator security in spring boot 2.x

Following urls are available:

* http://localhost:8080/hello - This is secured and only accessable after authentication
* http://localhost:8080/actuator/health - This is accessable without any authentication
* http://localhost:8080/actuator/info - This is accessable without any authentication
* http://localhost:8080/actuator/... - All other actuator endpints are secured and only accessable after authentication

User credentials are _user_ (user name) and _secret_ (password).
