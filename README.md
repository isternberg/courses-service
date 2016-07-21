# courses-service
a fully RESTful api written in java and kotlin using spring-boot

## Work in Progress
The api and the documentation (HAL Browser) are available at:

```
http://localhost:8080
```

### Features
Querying i.e.:

```
http://localhost:8080/courses/search/findByTitleContaining?title=Jazz
```

Showing a partial view of a resource, using projection i.e.:

```
http://localhost:8080/courses?projection=minimal
```
