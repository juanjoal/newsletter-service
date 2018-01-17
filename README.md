# newsletter-service  (incomplete)
REST service secured (apikey)
logback for logging

# newsletter-frontend (incomplete)
JSF - Primefaces applicacion. Shows new subscriber form and call newsletter-service REST POST 
logback for logging


## Development environment
- Jboss Developer Studio 11.1.0.GA
- Jboss EAP 7.0
- MySQL 5.7.21

## Technical requirements:
- Expected SLA response time of the new service: 300ms
The service has to make three calls
Event service: 100ms
Insert into Database: 100ms
Email service: 2 seconds
So is not possible to call de emal service in a synchronous call:
Solution A: use a jms queue which finally would make the email service call.
Solucion B: use Async call to de email service generating backlog in case of
failure.
- Database as a costly resource:
Switch form classic many to many relational model to one table (denormalization of
the model). Other option could be a noSQL database.
At least one index for the email field.
- The service must be secure.
In this scenario the user is not logged so the best option is to use an API KEY. Must be
used over HTTPS (TLS).
- SLA monthly uptime
  - EAP must be installed in domain mode, with a group of servers clustering in
more than one host (HA).
  - Web.xml of the front-end app must contain <distributable/> tag
  - The deploy must be done with rollout which it means it deploy one by one
host with no lost of service.
- Scalability: 
Adding more nodes (hosts) to de domain or more resources to each host. Vertical,
horizontal

## Deploy strategy
Rollout, using CLIFirst deploy:
```
deploy newsletter-frontend.war --name= newsletter-frontend.war --
runtime-name= newsletter-frontend.war --server-groups=ha-server-group -
-headers={rollout ha-server-group(rolling-to-servers=true)}
```
next updates
```
deploy newsletter-frontend.war --name= newsletter-frontend.war --
runtime-name= newsletter-frontend.war --headers={rollout ha-servergroup(rolling-to-servers=true)} --force
```

## CI proposal
Basic Jenkins:
- download de code from git
- fire sonar analysis for quality
- generate war artifacts.
- Send war files over the network and install using CLI
