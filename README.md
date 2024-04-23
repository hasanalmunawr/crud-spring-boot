# Crud SpringBoot List of Student

This project demontrations the way of spring working with Crud basic
## Depedencies Implemented

* Spring security
* Spring Web
* Spring Data JPA
* Mysql Driver
* Validation
* Lombok
* JsonWebToken

## Getting Started

To start this project,make sure you have installed on your local mechine :

* Jdk 17+
* Maven 3+
* Mysql

This project will be running on http://localhost:8081.

## Documentations of project

### Auth Controller

#### Register

EndPoint : POST http://localhost:8081/api/v1/auth/register

Request Body : 
```json
{
    "firstName":"hasun",
    "email":"hasun@gmail.com",
    "password":"rahasia",
    "role":"ADMIN" // There is 3 Role on this system such as (ADMIN, MANAGER, USER)
}
```

Response Body (Succes) :
```json
{
  "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXN1bkBnbWFpbC5jb20iLCJpYXQiOjE3MTM4Mzg3MzMsImV4cCI6MTcxMzkyNTEzM30.gTg01YB1cAjAyIlLg26DjRWWdiiHA4hBVj_fIB5DgVY",
  "access_token_expiry": 86400000
}
```

#### Login 

EndPoint : POST http://localhost:8081/api/v1/auth/login

Request Body :
```json
{
     "email":"hasun@gmail.com",
    "password":"rahasia"
}
```

Response Body (Succes) :
```json
{
  "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXN1bkBnbWFpbC5jb20iLCJpYXQiOjE3MTM4Mzg3MzMsImV4cCI6MTcxMzkyNTEzM30.gTg01YB1cAjAyIlLg26DjRWWdiiHA4hBVj_fIB5DgVY",
  "access_token_expiry": 86400000
}
```

### Admin Controller

#### Create Student

EndPoint : POST http://localhost:8081/api/v1/admin

Request Body :
```json
{

  "firstName": "hasan",  
  "lastName": "almu",    
  "birthDate": "2004-07-23",  
  "major": "Computer Science",
  "address": {
    "street": "123 mangga St",
    "city": "Jambi",
    "province": "Jambi",
    "country": "Indonesia",
    "postalCode": "12345"
  }
}
```

Response Body (Succes) :
```json
{
    "id": "851962223",
    "firstName": "hasan",
    "lastName": "almu",
    "birthDate": "2004-07-23",
    "major": "Computer Science",
    "address": {
        "street": "123 mangga St",
        "city": "jabi",
        "province": "Jambi",
        "country": "Indonesia",
        "postalCode": "12345"
    }
}
```

#### Read Student

To read student date there is 2 option that are reading by student id or all of student as list

EndPoint : GET http://localhost:8081/api/v1/admin/students?id=851962223

Response Body (Succes) :
```json
{
    "id": "851962223",
    "firstName": "hasan",
    "lastName": "almu",
    "birthDate": "2004-07-23",
    "major": "Computer Science",
    "address": {
        "street": "123 mangga St",
        "city": "jabi",
        "province": "Jambi",
        "country": "Indonesia",
        "postalCode": "12345"
    }
}
```

EndPoint : GET http://localhost:8081/api/v1/admin/all-students

Response Body (Succes) :
```json
[
    {
        "id": "507113475",
        "firstName": "hasan",
        "lastName": "almu",
        "birthDate": "2004-07-23",
        "major": "Computer Science",
        "address": {
            "id": 2,
            "street": "123 mangga St",
            "city": "Jambi",
            "province": "Jambi",
            "country": "Indonesia",
            "postalCode": "12345"
        }
    },
    {
        "id": "815899060",
        "firstName": "John",
        "lastName": "Doe",
        "birthDate": "2000-01-01",
        "major": "Computer Science",
        "address": {
            "id": 1,
            "street": "123 Main St",
            "city": "Anytown",
            "province": "here",
            "country": "USA",
            "postalCode": "12345"
        }
    }
]
```

#### Update Student

EndPoint : PUT http://localhost:8081/api/v1/admin/students?id=851962223

Request Body :
```json
{
  "firstName": "hasan", 
  "lastName": "almunawar",    // Replace from "almu"
  "birthDate": "2004-07-23",  
  "major": "Information System",  // Replace from "Computer Science"
  "address": {
    "street": "Jalan dua", // replace from "123 mangga St"
    "city": "sarolangun", // replace from "Jambi"
    "province": "Jambi",
    "country": "Indonesia",
    "postalCode": "33425" // replace from "12345"
  }
}
```

Response Body (Succes) :
```json
{
  "id": "851962223",
  "firstName": "hasan", 
  "lastName": "almunawar",    
  "birthDate": "2004-07-23",  
  "major": "Information System", 
  "address": {
    "street": "Jalan dua", 
    "city": "sarolangun", 
    "province": "Jambi",
    "country": "Indonesia",
    "postalCode": "33425" 
  }
}
```

#### Delete Student
EndPoint : DELETE http://localhost:8081/api/v1/admin/students?id=369258998

Response Body (Succes) :
```text
Succes deleted Student Id : 369258998
```
