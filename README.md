# Spring Actuator Security

This is a demo app showing how to configure actuator security in spring boot 2.x

Following urls are available:

| URL                                               | Secured | Roles         |      
| --------------------------------------------------| --------|----------------
| http://localhost:8080                             |   Yes   | USER          |
| http://localhost:8080/hello                       |   Yes   | USER          |
| http://localhost:8080/admin                       |   Yes   | ADMIN         |
| http://localhost:8080/actuator/health             |   No    | ---           |
| http://localhost:8080/actuator/health (+ details) |   Yes   | MONITOR_ADMIN |
| http://localhost:8080/actuator/info               |   No    | ---           |
| http://localhost:8080/actuator/auditevents        |   Yes   | MONITOR_ADMIN |
| http://localhost:8080/actuator/...                |   Yes   | USER          |

## User credentials

| Username   | Password | Roles         |      
| -----------| ---------|---------------|
| user       |   secret | USER          |
| admin      |   secret | USER, ADMIN   |
| monitor    |   secret | MONITOR_ADMIN |

## Example requests

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
$ localhost:8080/actuator/auditevents --auth: monitor:secret

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
