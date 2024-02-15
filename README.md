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

## Init database

This uses flyway over mariadb.
Installing a docker container example:
```
docker run --name mariadb10 -p 0.0.0.0:3307:3306  --env MARIADB_ROOT_PASSWORD=root -v /home/joao/.mysql_mariadb/data/mysql:/var/lib/mysql -v /home/joao/.mysql/data_maria:/etc/mysql/conf.d  mariadb
```

But you need to init the new database/schema and a user like this
```
sudo -u postgres psql
postgres=# create database healthmonitor;
postgres=# create user monitor with encrypted password 'monitor_user';
postgres=# grant all privileges on database healthmonitor to monitor;
```

Currently this is deployed using elephantsql, so the default user and schema was changed.
