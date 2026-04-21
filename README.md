# ClientServerArchitectureCW2
The Smart Campus API and the readme file included. 

Question 1:
  Jax-Rs uses a per-request lifestyle naturally, meaning that a new instance of resource class would be made for each       HTTP request.
  This can be considered thread-safe as each request would consequently have its own object, but it does mean that for      the instance variables, shared data cannot be stored, as once the request finishes, thedata would subsequently be lost. 
  To combat this, all of the shared data is now stored in a seperate class called "DataStore" rather than using static      fields. 
