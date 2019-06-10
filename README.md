<img alt="Icon" src="presentation/src/main/res/mipmap-xxhdpi/ic_app_launcher.png?raw=true" align="left" hspace="1" vspace="1">


# Integrity Control 🎓

My thesis work using Clean Architecture + Single Activity + MVVM + Kotlin + Kotlin Coroutines +Kotlinx.Android.Synthetic + Koin + Android Architecture Components + JUnit 🎓</br>

#### My thesis work
</br>

[![License Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=true)](http://www.apache.org/licenses/LICENSE-2.0)
![minSdkVersion 19](https://img.shields.io/badge/minSdkVersion-21-red.svg?style=true)
![compileSdkVersion 28](https://img.shields.io/badge/compileSdkVersion-28-yellow.svg?style=true)

<p align="center">
 <img alt='Sample' src="http://ipic.su/img/img7/fs/Bezymyannyj.1555824002.png">
  </br>
</p>

## Libraries and tools used in the project

### Android

* [AndroidX](https://developer.android.com/jetpack/androidx)
Provides additional convenience classes and features not available in the standard Framework API for easier development and support across more devices.
* [Data Binding](https://developer.android.com/topic/libraries/data-binding)
Write declarative layouts and minimize the glue code necessary to bind application logic and layouts.
* [Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html)
A set of Kotlin extensions for Android app development.

### Architecture and Design

* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
A collection of libraries that help you design robust, testable, and maintainable apps. 
I used  the Navigation Component,  the ViewModel,  the Room, the LiveData
Start with classes for managing your UI component lifecycle and handling data persistence.
* [Koin]( https://insert-koin.io)
A pragmatic lightweight dependency injection framework for Kotlin developers.
Written in pure Kotlin, using functional resolution only: no proxy, no code generation, no reflection.

### Junit Testing
* [Junit 4](https://junit.org/junit4/)
JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
* [Mockito](https://site.mockito.org/)
Tasty mocking framework for unit tests in Java
* [JunitParams](https://github.com/Pragmatists/JUnitParams)
Parameterised tests that don't suck
* [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
A small library that provides helper functions to work with Mockito in Kotlin.
* [MockK](https://mockk.io/)
Tasty mocking framework for unit tests in Kotlin
### View

* [ConstraintLayout](https://developer.android.com/training/constraint-layout/index.html)
Allows you to create large and complex layouts with a flat view hierarchy (no nested view groups).
* [Victor Loading](https://github.com/yankai-victor/Loading)
Just loadter

### Data Request
* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
A new way of managing background threads that can simplify code by reducing the need for callbacks. Coroutines are a Kotlin feature that convert async callbacks for long-running tasks, such as database or network access, into sequential code.

### Persistence

* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

## P.S.
There are bad ways to use unit testing in this project. It's first project where I tried the TDD. I need more practise with a unit testing in terms of android development.
Eventualy I realized the architecture still isn't perfect and enough clean 
 

## License

    Copyright 2019 Paul Karpukhin
 
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
