# superflight-app

## Starting application

In a docker installed system go to the root of the project and run the following command for starting the postgres database:

```shell
docker-compose up -d
```

Database will be available at `localhost:5432` with user `user` and password `super` which get the username and password from application.properties file.

After that, you can start the spring application. The app will start to work.
After app will start, you can access the swagger documentation at `http://localhost:8080/swagger-ui/index.html#/` and test the endpoints.
Every time app starts, it first runs the method inside InitService and fill the airport table in the database with AirportCode Enum values.
Starting from 5 seconds later the app start, importFlightsTask method in FlightFetchService.java will fetch data from the mock api and it will fill the flight table in the database.
This importFlightsTask method will run every 3 hours after first run.

Check the data in the database before testing out!
