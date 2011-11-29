# Jersey(JAX-RS) application template with Spring Framework

## spring-jersey-archetype

### Jersey Server (1.10)

[http://jersey.java.net/nonav/documentation/1.10/user-guide.html](http://jersey.java.net/nonav/documentation/1.10/user-guide.html)

### Spring Framework (3.0.6.RELEASE)

[http://static.springsource.org/spring/docs/3.0.6.RELEASE/spring-framework-reference/html/](http://static.springsource.org/spring/docs/3.0.6.RELEASE/spring-framework-reference/html/)

## How to use

````sh
mvn archetype:generate -DarchetypeCatalog=http://seratch.github.com/mvn-repo/releases
```

```sh
Choose archetype:
1: http://seratch.github.com/mvn-repo/releases -> spring-jersey-archetype (Jersey App Template with Spring Framework)
Choose a number: : 1
Choose version:
1: 1.10
Choose a number: 1:
Define value for property 'groupId': : com.example
Define value for property 'artifactId': : spring-jersey-example
Define value for property 'version': 1.0-SNAPSHOT:
Define value for property 'package': com.example:
Confirm properties configuration:
groupId: com.example
artifactId: spring-jersey-example
version: 1.0-SNAPSHOT
package: com.example
Y: y
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESSFUL
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 20 seconds
[INFO] Finished at: Thu Sep 29 13:04:07 JST 2011
[INFO] Final Memory: 11M/27M
[INFO] ------------------------------------------------------------------------
```

````sh
cd spring-jersey-example
mvn jetty:run
curl http://localhost:8080/
```

