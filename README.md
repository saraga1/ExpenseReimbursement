# ExpenseReimbursement

## Project Description
The Expense Reimbursement System tracks the processes of which an employee can request reimbursement that incurred while on company time. An employee will be allowed to request a reimbursemnt as well as see their past/pending requests. A manager will then be able to look through all the request from the employee(s) and have the option to approve or deny reimburstment. This the backend for the expense reimbursements application.

## Technologies Used

* Java
* PostgresSQL
* Javalin
* RESTful API
* JDBC
* JWT(JSON Web Token)

## Features

An employee or manager can login in which a JWT is assigned to appropriate individuals.
A manager role for managers an employee role for employees. Only the managers will be able to approve or deny reimburstment request.

There are three valid status
* Pending
* Approved
* Denied

To-do list:
* Create/Register new managers
* Allow file uploads

## Getting Started

Requirement:
* JDK 8+
   
## Clone Repository

git clone https://github.com/saraga1/ExpenseReimbursement.git

## Run

java -jar build/libs/<name_of_jar_file>

## Usage

The main RESTful API endpoints provided are in the form:

* BASE_URL/users/login
* BASE_URL/users
* BASE_URL/users/:id/expenses (protected - requires JWT Authentication in header)

## Contributors

* Samuel A.

## License

This project is licensed under the MIT License - see the LICENSE file for details.


