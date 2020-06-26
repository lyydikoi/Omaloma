## Omaloma is an Android Application. ##

## This App manages absences and holidays in Finland. ##

### In Finland there are quite clear rules on how to manage employees' holidays, f.e: ###
* Employees earns 2 days of holidays per month during trial period and 2,5 days after trial period. 
* If in some month employee was absent for a too long time, he/she does not get any holidays for this month.
* Usually users have summer and winter holidays.
* Sundays and public holidays are excluded from employee's holidays.
* Employee can take at least 6 days of holidays (including Saturday). 
* If employee plans 5 holidays the 6th day is added for the Saturday, etc.

In small companies holidays calculations could be quite messy, if there is no HR tool for that purpose.
With Omaloma App employees can see any time how many holidays they have earned already, how many are planned, spend and remaining.

### Planned functionality: ###
* Main view shows statistics about earned-, planned-, spent and remaining holidays.
* User can view and add new absences of different type (holidays, sick leaves etc.)
* Application calculates how many holidays employee has earned in the current year/ month already. 
* User can check information about public holidays, additional Wikipedia information abaut those days.

## Architecture, language, DI etc... ##
* This App is written in Kotlin.
* Has a multi-module structure.
* MVVM architectural pattern for presentation layer.
* Reactive pattern is implemented with Kotlin Coroutines and LiveData.
* Dagger dependency injection.
* ROOM DB
* Android Jetpack's Navigation

### Some of the branches contain experiments with technologies and archotecture ### 
* Clean architecture (Data layer with Repository pattern, Domain layer with use cases, MVVM-Presentation layer), Koin DI

## Summary of set up: ##
* This app is created with AndroidStudio using the Gradle build system.
* First download the source code.
* In Android Studio, use "Import Project" option. Next select the app directory that you downloaded from this repository. If prompted for a gradle configuration accept the default settings.
* Alternatively use the "gradlew build" command to build the project directly.

### Repo owner: Sergei Kasianov (lyydikoi@gmail.com) ###
