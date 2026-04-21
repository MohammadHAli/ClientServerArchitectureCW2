# ClientServerArchitectureCW2

Part 1: Service Architecture & Setup 

Question 1:
  Jax-Rs uses a per-request lifestyle naturally, meaning that a new instance of resource class would 
	be made for each HTTP request.
  This can be considered thread safe as each request would consequently have its own object, 
	but it does mean that for the instance variables, shared data cannot be stored, 
	as once the request finishes, the data would subsequently be lost. 
  To combat this, all the shared data is now stored in a separate class called "DataStore"
	rather than using static fields. 
	
Part 1: Service Architecture & Setup 

Question 1 (Project & Application Configuration):

  Jax-Rs uses a per-request lifestyle naturally, meaning that a new instance of resource class would be made 
	for each HTTP request.
  This can be considered thread safe as each request would consequently have its own object,
  but it does mean that for the instance variables, shared data cannot be stored, as once the request finishes, 
  the data would subsequently be lost.
  To combat this, all the shared data is now stored in a separate class called "DataStore" rather than using
  static fields. Since static fields belong to the class instead of an instance, they can remain for indefinitely
  and are shared across all requests.
  "ConcurrentHashMap" was used rather than a normal "HashMap" as it allows for multiple requests to be arrived 
  at once. Also, a normal "HashMap" is not thread-safe and therefore prone to corruption with multiple requests,
  which "ConcurrentHashMap" would not suffer from. 

Question 2 (The” Discovery” Endpoint):

  HATEOAS stands for Hypermedia As The Engine Of Application State. The API responses include 
  links to other parts of the API that relate to it, instead of forcing the client to know the URLs already. 
  As an example, "GET/api/v1", by looking at this, we can show that the project links to "api/v1/Rooms" and
  "api/v1/Sensors". Meaning that a client can simply start at the root endpoint and find all other details from it. 
  This is more suitable than static documenting as clients would not have to hardcode URLs, 
  and the API could describe itself via its own responses, also, 
  the potential errors could be reduced by not having to have clients guess URLs.

Part 2: Room Management

Question 1 (Room Resource Implementation):

  By returning only the ID's, responses are kept small and fast, which is useful for when there are a large
  number of rooms. However, the client would need to make separate requests for each individual room 
  to get the details, which would potentially greatly increase the network traffic. 
  By returning full room objects, a larger response would additionally be necessary, but the client 
  would receive all the needed information in a singular request. 
  For many cases, this is the more suitable approach, 
  as it is faster and simpler for the client to navigate, therefore, the API uses full objects. 

Question 2 (Room Deletion & Safety Logic):

  Idempotent means sending repeated requests multiple times has the same effect as only one request. 
	DELETE is idempotent as first request would remove the room and return a 200 OK. If this request is repeated the
  room would already have been deleted and therefore, a 404 Not Found error would most likely occur. Seeing that the 
  rooms state remains unchanged as it no longer exists. The only thing that changes is the status code, 
  so, 	the DELETE process would be idempotent. 

Part 3: Sensor Operations & Linking

Question 1 (Sensor Resource & Integrity):

  The use of "@Consumes(MediaType.APPLICATION_JSON)" tells JAX-RS that the endpoint only accepts JSON, meaning that 
  is a client sends data in an alternate format like "xml" or "plaintext", the request is automatically rejected. 
	Jersey would then return an error code of 415, meaning "Unsupported Media Type", as the resource method will not run.
	There is not necessarily a need for additional validation code. 

Question 2 (Filtered Retrieval & Search): 

  By using "?type = CO2" as a query parameter instead of having the filter in the path akin to "/Sensors/Type/CO2", 
  there are advantages such as: the path of "/api/v1/Sensors" now always refers to the sensors collection, multiple
  filters can also be added simply for example, "?type = CO2 & status = ACTIVE", this also does not change
  the URL structure.

Part 4: Deep Nesting with Sub - Resources

Question 1 (The Sub-Resource Locator Pattern):

  The Sub-Resource Locator Pattern allows for a parent resource to delegate handling nested paths to child 
  classes. So, "SensorResource" allocates all "/readings" requests to "SensorReadingResource".
  Some of the primary benefits are: The code is cleaner, since each class only handles a single resource type.
  The structure is more logical as it is more representative of a realistic relationship between data, considering
  that a reading belongs to a sensor, so it is more befitting that data is handled within its specific context.

Part 5: Advanced Error Handling, Exception Mapping & Logging

Question 1 (Dependency Validation):

  HTTP 404 means that a requested URL was not found, meaning that if a client gives a sensor with an invalid roomId, 
  the URL would still be fine since the problem is not the URL itself but the data. 
  Therefore, HTTP 442 which is an Unprocessable Entity is more accurate since it tells the client that the request
  had a valid JSON and URL, but the content itself could not be processed potentially due to an invalid reference.
  This therefore gives the client a clearer idea of the potential error. 

Question 2 (The Global Safety Net):

  By having stack traces exposed to external users, several cybersecurity issues can occur, such as:
    - having the class and package names be revealed, providing a map of the code structure
    - the libraries and framework versions can also be seen, therefore being able to see the potential 
      vulnerabilities linked
    - Allowing for sensitive information to be leaked like file paths and server config details which can help
      plan further attacks.
  Therefore, having the Global Exception Mapper can help log errors solely on the server side and for clients a
  plain message would be displayed instead. 

Question 3 (API Request & Response Logging Filters):

  It is more advantageous to use a filter for logging rather than using "Logger.info()" to every resource method as:
    - If logging needs to change, only one filter class needs to be updated
    - resource methods would only have business logic, therefore being easy to read and test
    - logging can also be centrally switched on or off.
