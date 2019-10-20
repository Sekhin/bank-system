# BankSystem

Java RESTful API for money transfers between customers accounts

## Technologies
- Spring Boot
- PostrgreSQL


## How to run
```sh
To launch classes Application.java in servecies billservice, accountservice and commonservice
```

Services commonservice, accountservice and billservice start on localhost ports 8888, 9999 and 11111 respectively. 
Postgre database initialized with some sample user.


## Available Services

### Bill Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /bills/{billId} | get bill by id | 
| GET | /bills | get all bills | 
| POST | /bills | create new bill | 
| GET | /bills_by_account/{accountId} | get bill by account id | 

#### Sample JSON for Bill Service
##### Create bill : 
```sh
{  
  "amount": 1000,
  "isOverdraftEnabled": true
} 
```

### Account Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /accounts/{id} | get account by id | 
| POST | /accounts | save account | 
| GET | accounts | get all accounts | 

#### Sample JSON for Account Service
##### Create account : 
```sh
{  
  "name": "Ivan",
  "phone": "749-83-74",
  "mail": "ivanpetrov@gmail.com"
} 
```

### Common Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /customer | get customer by id | 
| POST | /customer | create customer  |  

#### Sample JSON for Common Service
##### Create customer : 
```sh
{  
  "name": "Ivan",
  "phone": "749-83-74",
  "mail": "ivanpetrov@gmail.com",
  "amount": 1000,
  "isOverdraftEnabled": true
} 
```
