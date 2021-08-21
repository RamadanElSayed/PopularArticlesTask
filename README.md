# PopularArticlesTask

A simple app to hit the NY Times Most Popular Articles API and show a list of articles, that shows details when items on the list are tapped
(a typical master/detail app), that implements MVVM architecture using Hilt, Retrofit, Coroutines,Flow, LiveData,shimmer layout,
DataBinding and Navigation Component ,unit testing ,Mockito , Espresso .

 1. Architecture Design Pattern
 2. MVVM
 2. Hilt (Dependency Injection)
 3. Live Data
 4. Coroutines
 5. Flow
 5. Retrofit
 6. Unit Testing
 7. Repository Pattern
 8. AndroidX
 9. Shimmer layout
 10. NYT News API
 11. JetPack Libraries
 12. Navigation Component

## The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Hilt.
3. **domain**: It contains dto classes and repositories.
4. **presentation**: View classes along with their corresponding Presenters.
5. **utils**: Utility classes.
#### Classes have been designed in such a way that it could be inherited and maximize the code reuse.
<br>


## Guide to app architecture
<p align="center">
    <img src="clean_architecture_bob.png"/>
</p>

<p align="center">
    <img src="architecture.png"/>
</p>

<br>

## Library reference resources:
1. Hilt : https://developer.android.com/training/dependency-injection/hilt-android
2. Coroutines: https://codelabs.developers.google.com/codelabs/kotlin-coroutines/
3. Retrofit: https://square.github.io/retrofit/
4. Flow: https://developer.android.com/kotlin/flow
5. DataBinding: https://developer.android.com/topic/libraries/data-binding
6. Navigation Component: https://developer.android.com/guide/navigation/navigation-getting-started
7. Espresso https://developer.android.com/training/testing/espresso
8. Mockito https://developer.android.com/training/testing/unit-testing/local-unit-tests
<br>


