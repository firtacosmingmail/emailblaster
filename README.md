#EmailBlaster

## Libraries

* The application uses Android Architecture components like `ViewModel` and `LiveData`.
* For Dependency injection I user `Hilt`. I chose `Hilt` because it is easy to use for small projects and it is supported by Google.
* For navigation I initially used Navigation Component with a Navigation graph for navigating from email list to email content.
After I implemented the landscape variant of the screen I realized that I do not need that anymore. The implementation of the initial navigation can be seen in [display-email branch](https://github.com/firtacosmingmail/emailblaster/tree/display-email)

## Architecture
* For the `Login`, `EmailListFragment` and `EmailFragment` I used **MVVM**. Each view has a `ViewModel` that manages the data and state flow.
* For the E`mailActivity` I used **MVP** because there was no data manipulated and I felt like **MVP** is enough.
* For the communication with the email service I user a `DataSource` object to actually request data from the service, and an `EmailRepository` to separate the library implementation from the rest of the application.
Also the `EmailRepository` can be extended to support storing the data locally in future implementations, without impacting the view.
* For displaying data on the XML I used `ViewBinding`. All the special binding adapters that I needed to implement are written in **~/utils/BindingAdapters**
* For navigation, I implemented a `NavigationViewModel` to which all the screens that manage navigation would register and change the screens in case something is changed in it. 
It would be injected into the `Fragment` or `Activitie` `ViewModels` that would make the decision of navigating but would not need to know exactly hot the navigation is done.
I got to use it in the [display-email branch](https://github.com/firtacosmingmail/emailblaster/tree/display-email) because after that I changed the implementation of the list to details navigation.
* All the data within the project flows using `LiveData` objects, from the repository all the way to the view.
* The data classes are help in `Singletons` by `Hilt` and injected in the objects that use them.

## Git
I used `GitFlow` for the implementations. 
I created branches for every feature I implemented and merged those branches from PR's using Squash. 
For [the authentication branch](https://github.com/firtacosmingmail/emailblaster/tree/authentication) I did not use Squash because it contained only 2 commits and they represented important steps in the implementation process. I just merged it because I wanted those steps to be visible in the `main` branch.
Usually after merging I delete the branches but this time I did not do that so that the progress is visible.
  
## What needs to be added

Unfortunately I did not have more time to spend on the project, so here are some points that would improve upon the project:
* Unit tests 
* Local storage of the user credentials
* Improved error management

## ews-android-api
ews-android-api is added as a git dubmodule to the project
to get the submodule run the following commands:
```
git submodule init
git submodule update
```

To create the EWS jar run the following:
```
cd ews-android-api
git submodule init
git submodule update
./gradlew copySources ews-android-api:build
```

The resulting jar files are located in `ews-android-api/build/libs`. They are to be copied into `libs` and imported into the gradle file along side with 2 aditional libraries:
```groovy
implementation files('libs/ews-android-api.jar')
implementation 'joda-time:joda-time:2.8'
implementation 'dnsjava:dnsjava:2.1.6'
```