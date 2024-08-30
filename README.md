# rabbitmq-email
a email service and a email producer that applied aws mq service to do verification code sending while user registration

## step 1: aws mq service
## step 2: gmail settinmg to obtain verification code, all in ur chrome
## step 3: set ur application.properties file and add necessary dependencoes
## step 4: rabbimqConfig : queue's name/topic + converting method
## step 5: make sure the dto/entity u used in both side need to be the same
## step 6: add listener in email-service and corresponding action (send email)
## step 7: test it with postman -> POST http://127.0.0.1:8080/send-verification?email=kimwXXX@gmail.com
## step 8: check ur email

