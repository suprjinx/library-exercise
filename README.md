Library App
===========

Here's the assignment:
----------------------

Design a service to manage a county-wide library system.

The system should:
 - track book availability by library
 - enable book check-out/renew/return (returns can be made at any library, regardless of 
   check-out location)
 - enable access to patron info (currently borrowed books)

Your implementation must:
 - be a RESTful API
 - be written in Java

Scope limits:
 - persistence in a db is not required (you can use an in-process, in-memory data store), 
   just expect that a database would be added in the future
 - mock patrons (name, email, id) books (title, author, isbn) and libraries (name, 
   address, id) as you wish
 - no specific Java framework is required (eg. Spring)
 - no security elements are required (authentication or authorization)


Notes on the solution:
----------------------

Use 'mvn tomcat7:run-war' to bring up the app. Here are the RESTful URLs for accomplishing the goals of the app.
Only JSON responses are available.

 - List library branches: GET http://localhost:8080/library/branch
 - List books at  a given branch: GET http://localhost:8080/library/branch/{id}/books
 - List patrons: GET http://localhost:8080/library/patron
 - List books held by a given patron: GET http://localhost:8080/library/patron/{id}/books
 - Check out book: PUT http://localhost:8080/library/patron/{id}/books/{bookId}
 - Renew book: PUT http://localhost:8080/library/book/{id}/renew
 - Return a book: PUT http://localhost:8080/library/branch/{id}/books/{bookId}

Glaring omissions:

 - There are some gaps in the URL space, so you will get a 404 at
   http://localhost:8080/library/patron/{id} but will get a book listing by 
   appending “/books”.
 - The JSON response are essentially serialized model objects, so they lack URL links and 
   such that a real API should provide.
 - There are no unit tests.