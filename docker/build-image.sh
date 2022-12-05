# Build app
cd ..
mvn clean install
cp app/target/*.jar docker/word-matcher.jar
cd docker

# Build docker image
docker build --tag=word-matcher-img:latest .