# healthmonitor-be
Health Monitor BE

## Local run
```
./mvnw compile quarkus:dev
```

## Local run with heroku postgres DB
```
export JDBC_DATABASE_URL=$(heroku run  -a healthmonitor-be echo \$JDBC_DATABASE_URL); \
./mvnw compile quarkus:dev -Dquarkus.datasource.jdbc.url=$JDBC_DATABASE_URL
```

Using heroku cli to get psql cli:
```
heroku pg:psql
```

## Heroku deploy

Install heroku cli.

If no remote git yet (substitute app name for yours):
```
https://git.heroku.com/healthmonitor-be.git
```

Deploy new version
```
git push heroku master
```
