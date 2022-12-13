# ASOS-gRPC-demo
A project demonstrating gRPC streaming for the ASOS course.

[Documentation](./documentation/documentation.pdf)

[Presentation](./documentation/presentation.pdf)

# Using Gradle
Building the project
```
gradle clean build
```

Running the plane component
```
gradle :plane:run
```

Running the dispatcher component
```
gradle :dispatcher:run
```

# Using Gradle

Building all containers
```shell
docker-compose build
```

Running all containers
```shell
docker-compose up
```