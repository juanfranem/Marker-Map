
# Marker App

This app draws the markers it obtains from an api on the map

### Layers

  - **App:** This layer contains the logic of the framework and the view.
  - **Data:** That layer implements the repositories interfaces and contains the configuration of the http client, in this case, retrofit.
  - **Domain:** Contains all the models and the interfaces required to work. Only contains exceptions to validate the business logic.


### Tests
In this case the logic is very simple, for these reason i did some demo tests

###  Libraries used

* [JUnit](https://developer.android.com/training/testing/unit-testing/local-unit-tests)- Unit testing
* [Mockk](https://mockk.io/) - Library to mock interfaces in testing
* [Stetho](https://github.com/facebookarchive/stetho) - Network debugger
* [Retrofit](https://square.github.io/retrofit/) - Http client
* [Koin](https://github.com/InsertKoinIO/koin) - Dependency injector
* [Play services maps](https://developers.google.com/maps/documentation/android-sdk/start) - Google Maps for android


### How to use
To run this app you will need to configure some things.
First, you need to create a google account and enable Google Maps v2 for android.
Here you can follow the steps: [Get Started - Google Maps](https://developers.google.com/maps/documentation/android-sdk/start)

Copy the Api Key provided by Google

Then, fill the file local.properties with this configuration
```dsl
URL_BASE="https://URL_BASE/api/v1/"  
MAPS_API_KEY=AIz***************_**************o
```

### Todos

 - Write MORE Tests
 
License
----

MIT
