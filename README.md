# lending
-
### Features:

* Create a loan
* Get a loan and the amount invested
* Delete a loan
* Create an investment in a loan

### How to run:

1. Clone the repository
2. Run mvn clean install in the project directory
3. From the project directory run java -jar target/lending-1.0-SNAPSHOT.jar

### How to:

* Create a loan

**HTTP request method:** POST

**URL:** http://localhost:8080/create

**Request Body:** 

{
 "borrower" : "Borrower",
 "amount" : 50.0
}
                           
-

* Get a loan and the amount invested

**HTTP request method:** GET

**URL:** http://localhost:8080/retrieve/{loanId}
                           
-

* Delete a loan

**HTTP request method:** DELETE

**URL:** http://localhost:8080/delete/{loanId}
                           
-

* Create an investment in a loan

**HTTP request method:** POST

**URL:** http://localhost:8080/invest

**Request Body:** 

{ 
 "loanId": {loanId}
 "borrower" : "Borrower",
 "amount" : 50.0
}
                           
-

