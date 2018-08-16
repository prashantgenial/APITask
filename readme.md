Currently this project in development 

Following are the task that we are achiving in this Project:

1 - build a service, that will accept a request with text parameter on input.

2 - It will return maximum of 5 books and maximum of 5 albums that are related to the input term. 
3 - The response elements will only contain title, authors(/artists) and information whether it's a book or an album.

API
4 - For albums use the iTunes API: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching

5 - For books: use Google Books API: https://developers.google.com/books/docs/v1/reference/volumes/list
 
6 - Sort the result by title alphabetically.

7 - Results originating from one upstream service (and its stability / performance) may not affect the results originating from the other upstream service.
	 - Your service needs to respond within 3 seconds. [Multithreading with callable interface]
 
Make sure the service:
8 - is self-documenting [Add Swagger]
9 - exposes metrics on response times for upstream services
10 - exposes health check [Add accuator]
11 - Limit of results on upstream services must be configurable per environment and preconfigured to 5. [Add in Application.properties]
12 - Add the security mechanism to authenticate and authorize the service for added security. [JWT Token]
13 - document how we can run it. [GIT]
14 - document your justification of technology / mechanism choice.


The stability of the downstream service may not be affected by the stability of the upstream services.

How to run this project:

1.
clone the project : either download the project by clicking clone/download button  
OR open git bash (if git is installed on your machine) git clone https://github.com/prashantgenial/APITask

2. open a command prompt and go to the root directory project
3. compile "mvn clean package"
4. run "java -jar target/APITask-0.0.1-SNAPSHOT.jar"
or
run "java -jar target/APITask-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev"

6. open postman and run below URL

http://localhost:9191/media?input=road

Response recieved :

{
    "timestamp": "2018-08-16T09:40:51.013+0000",
    "status": 403,
    "error": "Forbidden",
    "message": "Access Denied",
    "path": "/media"
}

this service is accessible a secure token only, so first lets generate a token.
6.
To Access the Health Status of the API:
http://localhost:9191/actuator/health

POST localhost:9191/login
{"username":"admin","password":"password"} 

