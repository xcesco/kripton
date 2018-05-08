[![Android Weekly](http://img.shields.io/badge/Android%20Weekly-%23301-2CB3E5.svg?style=flat)](http://androidweekly.net/issues/issue-301)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kripton%20Persistence%20Library-orange.svg?style=flat)](https://android-arsenal.com/details/1/5149)
[![download](https://api.bintray.com/packages/xcesco/kripton/kripton-android-library/images/download.svg)](https://bintray.com/xcesco/kripton/kripton-android-library/_latestVersion)
[![maven central](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton)
[![test coverage](https://img.shields.io/codecov/c/github/xcesco/kripton/master.svg?style=flat-square)](https://codecov.io/gh/xcesco/kripton?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e2b62de5f47d4be3b87a4a4e69810ca6)](https://www.codacy.com/app/abubusoft/kripton?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=xcesco/kripton&amp;utm_campaign=Badge_Grade)
[![Project Stats](https://www.openhub.net/p/kripton-persistence-library/widgets/project_thin_badge.gif)](https://www.openhub.net/p/kripton-persistence-library)

# Kripton Persistence Library
Kripton is a java library, for Android and Java platform, that provides a simple and uniform way to manage persistence of Java classes in different flavours through annotations and interface. Supported persistence format are:

<img src="https://github.com/xcesco/wikis/blob/master/kripton/overview4.0.png">

To get max performance and avoid boilerplate-code, Kripton use annotation processor. With the power of annotation processor is possible to create code to persist a java class, simply with an annotation. There are many other libraries that do this, but Kripton allows to persists java object without using reflection and with just few lines of code.

See [wiki](https://github.com/xcesco/kripton/wiki) for more informations.

See [benchmarks](https://github.com/xcesco/kripton/wiki/Performance) for more informations about SQLite and JSON persistence perfomance.

If you are interested in Kripton Persistence Library, visit [abubusoft.com](http://www.abubusoft.com/wp/)

# Setup
You can use Kritpon Annotation Processor and Kripton Library via maven

```xml
<dependencies>
  ...    
  <dependency>
    <groupId>com.abubusoft</groupId>
    <artifactId>kripton</artifactId>
    <version>4.0.0</version>
  </dependency>
  ...
</dependencies>
...		
<build> 
  <plugins>
    ...
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
	  <artifactId>maven-compiler-plugin</artifactId>
      <version>3.6.0</version>
	  <configuration>
	    <source>1.7</source>
		<target>1.7</target>
		<annotationProcessorPaths>
		  <path>
		    <groupId>com.abubusoft</groupId>
		    <artifactId>kripton-processor</artifactId>
		    <version>4.0.0</version>
		</path>
	    </annotationProcessorPaths>
	  </configuration>
    </plugin>
    ...
  </plugins>
</build>
```

or via gradle

```
// annotation processor
annotationProcessor "com.abubusoft:kripton-processor:4.0.0"

// https://mvnrepository.com/artifact/com.abubusoft/kripton
implements "com.abubusoft:kripton-android-library:4.0.0"
```

Snapshots of the development version are available in [Sonatype's snapshots repository](https://oss.sonatype.org/content/repositories/snapshots/com/abubusoft/).

Kritpon requires at minimum Java 7 or Android 3.0.

![logo](https://github.com/xcesco/wikis/blob/master/kripton/logo320_120.png)


# Build
To build entire library collections just download repository and launch from base directory 

```
mvn clean install -Prelease
```

# Supported platforms
There are two supported platforms: the android environment and generic Java environment. For each platform there is a version of library. Android platform already include a json library and xml parser library. Java JDK does not include a json library and have different xml parser libraries.

# Donate
If you like Kripton and you want to support its development, please donate!

<a href='https://pledgie.com/campaigns/33279'><img alt='Click here to lend your support to: Support Kripton Persistence Library and make a donation at pledgie.com !' src='https://pledgie.com/campaigns/33279.png?skin_name=chrome' border='0' ></a>

# License

```
Copyright 2015-2018 Francesco Benincasa.

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
