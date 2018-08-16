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
10 - is self-documenting [Add Swagger]
11 - exposes metrics on response times for upstream services
12 - exposes health check [Add accuator]
13 - Limit of results on upstream services must be configurable per environment and preconfigured to 5. [Add in Application.properties]
14 - Add the security mechanism to authenticate and authorize the service for added security. [JWT Token]
15 - document how we can run it. [GIT]
16 - document your justification of technology / mechanism choice.


The stability of the downstream service may not be affected by the stability of the upstream services.

How to run this project:

clone the project : 
either download the project by clicking clone/download button  
OR
open git bash (if git is installed on your machine)
git clone https://github.com/prashantgenial/APITask

http://localhost:9191/media?input=

To Access the Health Status of the API:
http://localhost:9191/actuator/health