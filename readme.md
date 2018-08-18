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
 
Make sure the service:<br />
8 - is self-documenting [Added Swagger]<br />
9 - exposes metrics on response times for upstream services [As of now added logs later on we can integrate ELK for monitoring]<br />
10 - exposes health check [Added accuator]<br />
11 - Limit of results on upstream services must be configurable per environment and preconfigured to 5. [Add in Application.properties]<br />
12 - Add the security mechanism to authenticate and authorize the service for added security. [JWT Token]<br />
13 - document how we can run it. [GIT]<br />
14 - document your justification of technology / mechanism choice.<br />
15- The stability of the downstream service may not be affected by the stability of the upstream services.<br />

<strong>Software</strong><br />
The software details used by the API Task Application are listed below:<br /><br />
1.	<strong>Spring Boot</strong>  : Framework<br />
2.	<strong>Swagger</strong> : API for automatic documentation of API<br />
3.	<strong>Java 1.8 64 bit</strong> <br />
4.	<strong>Git</strong> : Source Code Repository<br />
5.	<strong>Postman</strong> : for simulation of service<br />
6. <strong>Maven</strong><br /><br />

for Installing and running this project , please look at the <strong>Install and rund.docx</strong> in this repository