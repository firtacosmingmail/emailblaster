#EmailBlaster
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