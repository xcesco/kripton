During development of an Android App, the developer has to persist some java class in different ways.

**Kripton** is a java library that was build with the aim to provide a simple and uniform way to manage persistence of Java classes in different flavours. 

![](https://github.com/xcesco/wikis/blob/master/kripton/immagine01.png)

Supported persistence format are:

* xml format
* json format

Moreover, there are other persistence mechanism supported:

* Shared preference
* SQLite database
* REST service, throws [Retrofit library](http://square.github.io/retrofit/) integration

To avoid boilerplate-code, Kripton use the power of annotation processor. With this power, is possible create code to persist a java class, simply with an annotation. There are many other libraries that do this, but Kripton allows to persists java object in different ways with an unique set of annotation and mechanism.

# Supported platforms
There are two platform: the android environment and generic Java environment. For each platform there is a version of library. Android platform already include a json library and xml parser library. Java JDK does not include a json library and have different xml parser libraries.

See (wiki)[https://github.com/xcesco/] for more informations.
