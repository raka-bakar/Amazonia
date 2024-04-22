the project uses MVVM and Clean architecture and it has two modules :

data module -> it provides the access to data source (remote server and local database)
app -> it contains the app itself where there are all UIs and resources The project has 3 layers, the UI layer, the Domain layer and the Data layer. the data layer is inside the data module. While UI and Domain layer are in app module

This MVVM and modular architecture is created as if it is a real project to accomodate changes and future growth/expansion.
So, although the current state of the app is small, it is ready to be further expanded.
By using this app, a user can bookmark their favorite product and the app can be used offline.

techstack :
1. livedata
2. retrofit
3. Android view + xml
4. Moshi
5. Coil
6. Dagger Hilt
7. Room database
8. Mockito
9. Timber