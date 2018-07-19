###Spring Security using JWT (Json Web Token) in Spring Boot
This Project uses JWT to secure the REST endpoints.

The Following are the REST end points available in the example.

/token - Generates the JWT token based on the JSON sent. Its a POST method which expects the JSON: { "username": "name", "id": 123, "role": "admin"}
/user/ - Requires a JWT Token with Header key - "Authorization" and value - "Inaam <JWT_Token>"