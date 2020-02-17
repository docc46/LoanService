# LoanService
Application lets users apply for loan and evaluates the loan request. It also lets user prolong the loan term.
Loan request is refused if:
- Loan application is done for max amount and loan application is created between 0:00 – 6:00
- Client Applied 3 times using the same IP address

### Installation

Simply run:
```
mvn spring-boot:run
```

### Using the App

Request a loan:
```
$ curl -X POST localhost:8080/askforloan -H 'Content-type:application/json' -d '{"clientName": "John", "clientSurname": "Snow", "periodOfMonths": "12", "amount": "1000.00"}'
```

View loan requests:
```
$ curl localhost:8080/loanrequests
```

View accepted loans:
```
$ curl localhost:8080/loans
```

Request a loan term prolonging:
```
$ curl -X POST localhost:8080/prolong -H 'Content-type:application/json' -d '{"loanId": "1", "prolongDays": "35"}'
```
