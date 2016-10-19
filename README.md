[![maven central](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton)
[![build status](https://img.shields.io/travis/xcesco/kripton.svg?style=flat-square)](https://travis-ci.org/xcesco/kripton)
[![test coverage](https://img.shields.io/codecov/c/github/xcesco/kripton/master.svg?style=flat-square)](https://codecov.io/gh/xcesco/kripton?branch=master)

# Kripton 
Java library with the aim to provide a simple and uniform way to manage persistence of Java classes in different flavours. Supported persistence format are:

* SQLite database
* REST service, throws [Retrofit library](http://square.github.io/retrofit/) integration
* xml format
* json format
* Shared preference

To get performannce and avoid boilerplate-code, Kripton use the power of annotation processor. With the power of annotation processor is possible to create code to persist a java class, simply with an annotation. There are many other libraries that do this, but Kripton allows to persists java object in different ways with an unique set of annotation and mechanism.

See [wiki](https://github.com/xcesco/kripton/wiki) for more informations.


# Download
You can use Kritpon Annotation Processor and Kripton Library via maven

```xml
<!-- https://mvnrepository.com/artifact/com.abubusoft/kripton-processor -->
<dependency>
    <groupId>com.abubusoft</groupId>
    <artifactId>kripton-processor</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>

<!-- https://mvnrepository.com/artifact/com.abubusoft/kripton -->
<dependency>
    <groupId>com.abubusoft</groupId>
    <artifactId>kripton</artifactId>
    <version>1.0.0</version>
</dependency>
```

or via gradle

```
// https://mvnrepository.com/artifact/com.abubusoft/kripton-processor
compile group: 'com.abubusoft', name: 'kripton-processor', version: '1.0.0'

// https://mvnrepository.com/artifact/com.abubusoft/kripton
compile group: 'com.abubusoft', name: 'kripton', version: '1.0.0'
```

Snapshots of the development version are available in [Sonatype's snapshots repository](https://oss.sonatype.org/content/repositories/snapshots/com/abubusoft/).

Kritpon requires at minimum Java 7 or Android 2.3.


# Supported platforms
There are two platform: the android environment and generic Java environment. For each platform there is a version of library. Android platform already include a json library and xml parser library. Java JDK does not include a json library and have different xml parser libraries.

# License

```
Copyright 2015 Francesco Benincasa.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
