# Spring Actuator Security

This is a demo app showing how to configure actuator security in spring boot 2.x

Following urls are available:

| URL                                        | Secured |
| -------------------------------------------| --------|
| http://localhost:8080                      |   Yes   |
| http://localhost:8080/hello                |   Yes   |
| http://localhost:8080/actuator/health      |   No    |
| http://localhost:8080/actuator/info        |   No    |
| http://localhost:8080/actuator/auditevents |   No    |
| http://localhost:8080/actuator/env         |   Yes   |

User credentials for authentication are _user_ and _secret_.

If you have installed [httpie](https://httpie.org/) you may try the
following examples.


```
$ http localhost:8080/hello --auth user:secret

HTTP/1.1 200 
...
It works!
```

```
$ http localhost:8080/hello

HTTP/1.1 401 
...
{
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/hello",
    "status": 401,
    "timestamp": "2019-02-05T20:10:14.338+0000"
}
```

```
$ localhost:8080/actuator/health

HTTP/1.1 200 
...
{
    "status": "UP"
}
```

```
$ localhost:8080/actuator/info

HTTP/1.1 200 
...
{
    "application": {
        "name": "actuator security demo"
    }
}
```

```
$ localhost:8080/actuator/auditevents

HTTP/1.1 200 
...
{
    "events": [
{
            "data": {
                "details": {
                    "remoteAddress": "127.0.0.1",
                    "sessionId": null
                }
            },
            "principal": "user",
            "timestamp": "2019-02-05T20:07:51.012Z",
            "type": "AUTHENTICATION_SUCCESS"
        },
        ...
    ]
}

```


```
 http localhost:8080/actuator/env --auth user:secret
 
 HTTP/1.1 200
 ...
 {
     "activeProfiles": [],
     "propertySources": [
         {
             "name": "server.ports",
             "properties": {
                 "local.server.port": {
                     "value": 8080
                 }
             }
         },
  ...
```

```
 http localhost:8080/actuator/env
 
 HTTP/1.1 401
 ...
 {
     "error": "Unauthorized",
     "message": "Unauthorized",
     "path": "/actuator/env",
     "status": 401,
     "timestamp": "2019-02-05T20:22:18.084+0000"
 }
```

