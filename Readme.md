

Contains the micro-services of System. Follow instructions below to run each service / all services.

### Build a service

To build a service run the following command in the service directory

```sh
$ mvn clean install
```

### Building and running with docker

##### Building and running containers for each service manually

To build the docker image of the service execute the following command. Make sure that you have run the previous command in the service directory

```sh
$ docker build --t ${servicename} .
```

Run docker container from image

```sh
$ docker run -p ${exposing port}:${app port} ${servicename}
``` 

##### Building and running with docker-compose

To build and run docker images of all services use docker-compose. Make sure you have built the executable for all services using mvn clean install command.

Execute following commands in root repository

Build images
```sh
$ docker-compose build
```

Run all containers

```sh
$ docker-compose up
``` 

Check docker-compose file to see how ports are mapped to the host ports.

