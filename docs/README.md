[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kripton%20Persistence%20Library-orange.svg?style=flat)](https://android-arsenal.com/details/1/5149)
[![download](https://api.bintray.com/packages/xcesco/kripton/kripton-android-library/images/download.svg)](https://bintray.com/xcesco/kripton/kripton-android-library/_latestVersion)
[![maven central](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton)
[![test coverage](https://img.shields.io/codecov/c/github/xcesco/kripton/master.svg?style=flat-square)](https://codecov.io/gh/xcesco/kripton?branch=master)
[![Project Stats](https://www.openhub.net/p/kripton-persistence-library/widgets/project_thin_badge.gif)](https://www.openhub.net/p/kripton-persistence-library)

# Kripton Persistence Library
It was the 2015, and i was working on an Android project. The tasks that application need to accomplish were always the same: get some data from REST Web services, store data locally, manage user’s preferences, display data and so on. I began to separate persistence code in a little library and then.. Kripton Persistence Library was born!
I write Kripton Library to simplify tasks about persist data on Android platform. First of all… what I mean with “persist”? The answer is:
 * on file system as a file
 * in a SQLite database
 * in a SharedPreferences
 * remotely with REST services

There are many other open source library to accomplish these tasks, but my goal was to simplify my life as developer, creating a library that allow to accomplish this task in a simply and uniform way… and with performance in mind.
I didn’t reinvent the wheel, Kripton is based on many great libraries like Jackson for data format conversion, Java poet, Xml pull parsing, Antlr and many others.
To work with REST service i simply wrote an integration library between Kripton with Retrofit.
As you know, reflection is bad thing if we talk about performance on Android Platform. This is the reason for which Kripton is heavily based on annotation processors that works at compile time an write for us the boilerplate code need to persist data.
Ok, now it’s time to show how Kripton works.

## Setup
To configure Kripton on an gradle project, you have to include these lines in you project’s dependencies:

```gradle
dependencies {
    ...
    // annotation processors
    annotationProcessor"com.abubusoft:kripton-processor:4.0.0"
    ...
    // dependencies
    implementation "com.abubusoft:kripton-android-library:4.0.0"    
    ...
}
```

The `Kripton-processor` is the artifact that contains annotation processors. `Kripton-android-libray` contains core classes and references to all-you-need to execute the generated code. 

## Persistence on file system
Just image that in your application you have to manage a `Person` object. You simply need to persist on a file, just to reload the data in a second time. We define the Person class in this way:

```java
@BindType
public class Person{
    public String name;
    public String surname;
    public String email;
    public Date birthDate;
}
```

When you compile your project, Kripton annotation processor will find Person class marked with `@BindType` annotation and will generate all the needed code for us. So, in your code to persist a Person’s instance you can simply write:

```java
// define the Person object
Person bean = new Person();
bean.name = "name";
bean.surname = "sunrame";
bean.birthday = new Date();
// get the persistence context
BinderContext context = KriptonBinder.jsonBind();
// persist on a file
context.serialize(person, new File(..));
To read from a file data and convert it in a Person instance
// get the persistence context
BinderContext context = KriptonBinder.jsonBind();
// read from a file
Person person=context.parse(new File(..), Person.class);
```

The conversion is based on code that Kripton write for us during compile time. The data format used in this case is JSON, but you can also use other data format like CBOR, YAML, XML and (Java) Properties. In our example we used String and Date type. Kripton supports many attribute type like: primitive type, collections, arrays, maps and so on. Moreover, it’s possible to extends set of supported type with TypeAdapters.
For more information about these features you can visit Kripton wiki .
Persistence on SQLite database
This is the persistence mechanism on which i worked a lot. You probably know that manage SQLite database on Android application is very very boring task. If you don’t know this, please read the official documentation. I used the DAO pattern to approach at the database management. In the DAO pattern we can found:

 * A **data model** composed by simple POJO objects in Java world, and tables in SQLite world.
 * **Data Access Object interfaces** that define how to access to database
 * **Data Access Object implementation** that implements the DAO interfaces
 * A **database** that is composed by DAOs and data model.

Kripton need the developer defines the data model with BindTable annotated java classes, the DAO’s interfaces with BindDao annotated Java interface and a data source (the database) by an BindDataSource annotated Java interface. At compile time, Kripton will generate all needed code to implements DAO interfaces and to manage data source.
We can take the previous Person example to see how define a SQLite database with a persons table, and a DAO interface with some methods to do CRUD operations (Create Read Update Delete). The data model is rapresented by Person class:

```java
@BindTable(name="persons")
public class Person{
  public long id;
  public String name;
  public String surname;
  public String email;
  public Date birthDate;
}
```

Just two things:
 * every SQLite table need a id column of type Long or long. It’s a constraint that Kripton required for every table and it is a best practice for SQLite databases.
 * BindTable is the annotation used to mark a data model that will be used in a SQLite database.

The DAO interface definition is:

```java
@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface PersonDao {
  @BindContentProviderEntry
  @BindSqlSelect(orderBy="name")
  List<Person> selectAll();
  @BindSqlSelect(jql="select * from person order by name")
  List<Person> selectTwo();
  @BindSqlSelect()
  List<Person> selectThree(@BindSqlDynamicOrderBy String orderBy);
  @BindSqlSelect(where = "id=${work.id}")
  List<E> selectById(@BindSqlParam("work") E bean);
  @BindContentProviderEntry
  @BindSqlInsert
  void insert(Person bean);
  @BindContentProviderEntry  
  @BindSqlUpdate(where = "id=${work.id}")
  boolean update(@BindSqlParam("work") Person bean);
  
  @BindContentProviderEntry  
  @BindSqlDelete(where = "id=${work.id}")
  boolean delete(@BindSqlParam("work") Person bean);
}
```

And the data source definition is:

```java
@BindContentProvider(authority="com.abubusoft.kripton")
@BindDataSource(daoSet= { PersonDao.class }, fileName = "person.db", log=true)
public interface PersonDataSource {
}
```

When the project is compiled, Kripton annotation processor will generate for us the code that implements the data source defined by the data model, the DAO and datasource interfaces.
The need annotations to define a data source with Kripton is:
 * **BindDataSource**: defines a datasource
 * **BindDao**: define the DAO interface
 * **BindTable**: associate a class to a table
 * **BindInsert**,**Update**,**Select**,**Delete**: defines SQL to manage tables

As you notice in the source code there are other used annotations, needed if you want to generate a Content Provider too:

 * **BindContentProvider**: allows to generate a content provider
 * **BindContentProviderPath**: include DAO in the content provider definition
 * **BindContentProviderEntry**: include DAO’s method in the content provider definition

Yes, given a data source definition, Kripton can generate a content provider just with a couple of extra annotations. In your application, to use generated implementation of datasource you can use code like this:

```java
// typically Kripton library is done in Application#onCreate
KriptonLibrary.init(context);
// usage example 1: open data source and insert somedata
try (BindPersonDataSource dataSource = BindPersonDataSource.open())
{
  dataSource.getPersonDAO().insert(person);
}
// usage example 2: using transaction
BindPersonDataSource.instance().execute(daoFactory -> {
    PersonDao dao=daoFactory.getPersonDao();
    dao.insert(person);
    ...
    return TransactionResult.COMMIT;
});
// usage example 3: using shared connection
BindPersonDataSource.instance().executeBatch(daoFactory -> {
    PersonDao dao=daoFactory.getPersonDao();
    dao.selectAll();
    ...

});
```

For a `PersonDataSource` interface, Kripton generate a `BindPersonDataSource` class that implements the datasource which allows to work in a thread-safe way and exposing all DAO defined in `PersonDataSource` interface. The generated datasource exposes some methods to work in a transation mode and in shared connection mode. You can see an example of generated DAO implementation and of generated Data Source implementation. As you can observe from source code, all generated classes are well documented!
There are other features that i want to mention: the capability to generate log of SQL operation, the capability to support RX library and the capability to test database version migration with helper classes.


## Persistence with SharePreferences
Shared Preferences is another standard way to persists data on Android Platform. In almost all the case, they are used to manage user’s application settings. Kripton allow to generate a Shared Preferences wrapper that allow to access to settings properties in a strong type way. Just an example: suppose to rapresents the application’s setting with the AppPreferences class:

```java
@BindSharedPreferences
public class AppPreferences {
  public float valueFloat=5.0f;
  @BindPreference(“value”)
  public boolean valueBoolean;
}
```

With BindSharedPreferences annotation,Kripton will generate a class named BindAppPreferences:
Generated SharedPreference wrapper class for AppPreferences classSo if you want to read or write properties from SharedPreference in the application, you can write:

```java
// get Shared Preference instance
BindAppPreferences prefs = BindAppPreferences.instance();
// read the value of valueBoolean property
...=prefs.valueBoolean();
// write the value of valueBoolean property
prefs.edit().putValueBoolean(true).commit();
```

You can use String, primitive types, List, Map, Sets or other object as SharedPreference’s attributes. The complex type will be converted in its JSON rapresentation.
Persistence with REST Web service
Last but not least persistence type covered by Kripton is the one which use REST service. There are many library that permits to generate REST service client easily. The one that I prefer is Retrofit.
So, I decide to integrate Kripton with Retrofit, just to use Kripton persistence mechanism in `Retrofit library`. I want to show you here how simple is to work with them.
For example we want to consune the REST service at

```
https://jsonplaceholder.typicode.com/posts/
```

So we define a `Post` class with Kripton `BindType` annotation

```java
@BindType
public class Post {
  public long userId;
  public long id;
  public String title;
  public String body;
}
```

And the we can define the REST client interface:

```java
public interface JsonPlaceHolderService {
  @POST(“/posts/”)
  Call<List<Post>> getAllPost();
}
```

The code to consume the REST service is:

```java
// create retrofit using Kripton converter factory
Retrofit retrofit = new Retrofit.Builder()
  .baseUrl(“https://jsonplaceholder.typicode.com/")
  .addConverterFactory(KriptonBinderConverterFactory.create())
  .build();
JsonPlaceHolderService service = Retrofit.create(JsonPlaceHolderService.class);
// consume service
Response<List<Post>> response = service.getAllPost().execute();
```

The integration between Kripton and Retrofit is done by KriptonBinderConverterFactory converter.

## Conclusion
This post was only an introduction on Kripton Persistence Library. It’s a powerfull library that can help development on Android Platform.
If you like Kripton, give it a star on github, thank you.
You can found Kripton Persistence Library on github. Moreover, you can consult my blob Abubusoft.com.

## Donate
If you like Kripton and you want to support its development, please donate!

<a href='https://pledgie.com/campaigns/33279'><img alt='Click here to lend your support to: Support Kripton Persistence Library and make a donation at pledgie.com !' src='https://pledgie.com/campaigns/33279.png?skin_name=chrome' border='0' ></a>

## License

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
