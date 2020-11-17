# graphbom-microservices

# Docker build image
docker build . -t graphbom/micro

# Docker run container
docker run -it --rm -p8080:8080 graphbom/micro
