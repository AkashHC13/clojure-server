#1 
FROM clojure
#2 
LABEL maintainer="Akash H C"
#3
COPY . /usr/src/app
#4 
WORKDIR /usr/src/app
#5
EXPOSE 8080
#6
CMD ["lein","run"]