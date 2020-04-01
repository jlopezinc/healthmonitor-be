# healthmonitor-be
Health Monitor BE

## Local run
```
./mvnw compile quarkus:dev
```

## Local run with heroku postgres DB
```
heroku run  -a healthmonitor-be echo \$JDBC_DATABASE_URL

JDBC_DATABASE_URL=<see heroku jdbc url from above> ./mvnw compile quarkus:dev -Dquarkus.datasource.url=$JDBC_DATABASE_URL
```
