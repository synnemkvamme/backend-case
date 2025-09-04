Currently, Gjensidige’s sales-team is building a new CRUD-API for one of its many product tables and need it to be finished as soon as possible. Unfortunately, the programmer responsible for this has
turned in sick and left with an incomplete project that needs to be completed. On the phone the developer said that GET and POST methods are fully implemented. And that it was created tests for the DELETE operation, but no implementation for delete and update operations.

## System Description
There is a single entity model, “Product”, which contains some simple fields including summarized inventory and sales numbers.

## Exercise 1 – Missing operations
Finish up the missing part of the implementation by implementing PUT and DELETE operations.
- Complete the PUT operation to update an existing product with new values.
- Complete the DELETE operation to remove a product from storage.

## Exercise 2 – Financial report
The finance division in Gjensidige wants to know more about our products’ revenue and would like us to generate a report they can use in their weekly meeting. The report generator should iterate the full product list and produce a report including the following:
- Total revenue – sum of units sold multiplied by unit price.
- Total cost – sum of units sold multiplied by unit cost.
- Total margin – revenue minus cost.
- The product information of the most sold product.
- The product information of the least sold product.
- The product information of the product with highest contribution to the margin.
- The product information of the product with lowest contribution to the margin.
- Timestamp of when the report was generated.

It should be possible to generate this report based on the numbers we already have in our product table. A model class and entry point for a financial report is already in place, but the service performing the report generation is empty. Your task is to implement this service.

## Bonus task – Modernize the project
The programmer that turned in sick was unfortunately using some older versions of spring-boot, java and 3rd party dependencies. 

In this exercise we want to upgrade java to at least 11, spring boot to the latest version and all the 3rd party dependencies as high as possible. This so that security issues in these older libraries can be addressed. This will probably result in some things that will break. The task is to modify the code so that the financial report and the product CRUD service will still work with these modernizations
taken place.

## Bonus task - Docker
Create a dockerized version of the application

<br><br>
For all tasks, good test coverage is expected.
