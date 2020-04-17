[![Android Weekly](http://img.shields.io/badge/Android%20Weekly-%23301-2CB3E5.svg?style=flat)](http://androidweekly.net/issues/issue-301)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kripton%20Persistence%20Library-orange.svg?style=flat)](https://android-arsenal.com/details/1/5149)
[![download](https://api.bintray.com/packages/xcesco/kripton/kripton-android-library/images/download.svg)](https://bintray.com/xcesco/kripton/kripton-android-library/_latestVersion)
[![maven central](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton)
[![test coverage](https://img.shields.io/codecov/c/github/xcesco/kripton/master.svg?style=flat-square)](https://codecov.io/gh/xcesco/kripton?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e2b62de5f47d4be3b87a4a4e69810ca6)](https://www.codacy.com/app/abubusoft/kripton?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=xcesco/kripton&amp;utm_campaign=Badge_Grade)
[![Project Stats](https://www.openhub.net/p/kripton-persistence-library/widgets/project_thin_badge.gif)](https://www.openhub.net/p/kripton-persistence-library)
[![Build Status](https://travis-ci.org/xcesco/kripton.svg?branch=master)](https://travis-ci.org/xcesco/kripton)

<img width="128px" src="https://github.com/xcesco/wikis/blob/master/kripton/jetbrains-variant-3.png">

<a href="https://www.jetbrains.com/?from=KritponPersistenceLibrary">Thanks to JetBrains for support Kripton Persistence Library project!</a>



# Kripton Persistence Library
Kripton is a java library, for Android platform, that provides a simple and uniform way to manage persistence of Java classes in different flavours through annotations and interface. Kripton can do for you:

- generate all the code necessary to persist your POJO in a SQLite database (Light-ORM functionality). It's support Live Data and Androidx JetPack.
- generate all the code necessary to convert POJO object in different data format: JSON, XML, CBOR, properties, YAML
- generate all the code to persist your POJO in Shared Preferences. It's support Live Data and Androidx JetPack.
- Kripton can be integrated to Retrofit library to generate all code necessary to JSON-2-Java convertion without any reflection code.
- You can use Kripton with Kotlin too.

Does it sound interesting? I hope so! :)

<img src="https://github.com/xcesco/wikis/blob/master/kripton/overview5.0.png">

To get max performance and avoid boilerplate-code, Kripton use annotation processor. With the power of annotation processor is possible to create code to persist a java class, simply with an annotation. There are many other libraries that do this, but Kripton allows to persists java object without using reflection and with just few lines of code.

Kripton is fully working with Kotlin too.

See [wiki](https://github.com/xcesco/kripton/wiki) for more informations.

See [benchmarks](https://github.com/xcesco/kripton/wiki/Performance) for more informations about SQLite and JSON persistence perfomance.

If you are interested in Kripton Persistence Library, visit [abubusoft.com](http://www.abubusoft.com/wp/)

Before use Kripton to persit on _SQLite_ and _Shared Preferences_, an Android app have to initialize Kripton library. To do this, just add
the following code to `Application` class (usually on method onCreate):

```java
// set context for Kripton Library
KriptonLibrary.init(this);
```

# Quickstart - Persistence on a SQLite database
Kripton uses the DAO pattern to approach the database management. In the DAO pattern there are:

* A data model composed of simple POJO objects in Java world, and tables in the SQLite world.
* Data Access Object interfaces that define how to access to the database
* Data Access Object implementation that implements the DAO interfaces
* A database that is composed of DAOs and data model.

## Support for ciphered database
In version 7 Kripton supports the [SQLCipher database](https://github.com/sqlcipher/android-database-sqlcipher). To enable this feature, just
include in your dependencies the sqlcipher aar:

```gradle
implementation "net.zetetic:android-database-sqlcipher:4.3.0"
```

And use the `KriptonSQLCipherHelperFactory` to as factory in your DataSources:

```java
KriptonSQLCipherHelperFactory factory = new KriptonSQLCipherHelperFactory(password.toCharArray());

BindAppDataSource.build(DataSourceOptions.builder()
        .populator(new AppPopulator())
        .openHelperFactory(factory)
        .build());
```

## Model definition example
Kripton needs the developer defines the data model with `@BindTable` annotated java classes, the DAO’s interfaces with BindDao annotated Java interface and a data source (the database) by a `BindDataSource `annotated Java interface. At compile time, Kripton will generate all needed code to implements DAO interfaces and for managing data source.

Suppose that our app data model has a `Person` entity that need to be persisted on a SQLite databas. In the following example it is explained how to define an SQLite database with a person table, and a DAO interface with some methods to do CRUD operations (Create Read Update Delete). The data model:

```java
@BindSqlType(name="persons")
public class Person{
  public long id;
  public String name;
  public String surname;
  public String email;
  public Date birthDate;
}
```

Just two things:

* every SQLite table needs an `id` column of type Long or long. It’s a constraint that Kripton required for every table and it is a best practice for SQLite databases.
* [@BindSqlType](@BindSqlType) is the annotation used to mark a data model that will be used in an SQLite database.

The DAO interface definition is:

```java
@BindDao(Person.class)
public interface PersonDao {

  @BindSqlSelect(orderBy="name")
  List<Person> selectAll();

  @BindSqlSelect(jql="select * from person order by name")
  List<Person> selectTwo();

  @BindSqlSelect()
  List<Person> selectThree(@BindSqlDynamicOrderBy String orderBy);

  @BindSqlSelect(where = "id=${work.id}")
  List<E> selectById(@BindSqlParam("work") E bean);

  @BindSqlInsert
  void insert(Person bean);

  @BindSqlUpdate(where = "id=${work.id}")
  boolean update(@BindSqlParam("work") Person bean);
  
  @BindSqlDelete(where = "id=${work.id}")
  boolean delete(@BindSqlParam("work") Person bean);
}
```

The data source definition:

```java
@BindDataSource(daoSet= { PersonDao.class }, fileName = "person.db", log=true)
public interface PersonDataSource {
}
```

When the project is compiled, Kripton annotation processor will generate for us the code that implements the data source defined by the data model, the DAO and data-source interfaces.

The need annotations to define a data source with Kripton are:

* **[BindDataSource](@BindDataSource)**: defines a data-source
* **[BindDao](@BindDao)**: define the DAO interface
* **[BindSqlType](@BindSqlType)**: associate a class to a table
* **[BindInsert](https://github.com/xcesco/kripton/wiki/SQL-Insert)|[BindUpdate](https://github.com/xcesco/kripton/wiki/SQL-Update)|[BindSelect](https://github.com/xcesco/kripton/wiki/SQL-Select)|[BindDelete](https://github.com/xcesco/kripton/wiki/SQL-Delete)**: defines SQL to manage tables

In the application, to use generated an implementation of data-source you can use code like this:


```java
// typically Kripton library is done in Application#onCreate
KriptonLibrary.init(context);

// usage example 1: using a transaction
BindPersonDataSource.instance().execute(daoFactory -> {
    PersonDao dao=daoFactory.getPersonDao();
    dao.insert(person);
    ...
    return TransactionResult.COMMIT;
});

// usage example 2: using shared connection
BindPersonDataSource.instance().executeBatch(daoFactory -> {
    PersonDao dao=daoFactory.getPersonDao();
    dao.selectAll();
    ...

});
```

For a `PersonDataSource` interface, Kripton generates a `BindPersonDataSource` class that implements the data-source which allows to work in a thread-safe way and exposing all DAO defined in PersonDataSource interface. The generated data-source exposes some methods to work in a transaction mode and in shared connection mode.


# Quickstart - data format and REST clients
Suppose that your application data model is composed by `User` entity so defined (getter and setter are omitted for simplicity):

```java
@BindType
public class User {
    public long id;
    public String name;
    public String username; 
}
```

To store or read an instance of `User` on a file, you can simply write:

```java
// HOW TO WRITE/READ ON A FILE IN JSON FORMAT
// define an object
User user=new User();
...
				
// WRITE ON A FILE				
BinderContext binder=KriptonBinder.jsonBind();
binder.parse(new File(...), user);
...

// DEFINE A PERSISTENCE BINDER
BinderContext binder=KriptonBinder.jsonBind();

// WRITE INTO A FILE				
binder.serialize(user, new File(..));

// READ FROM A FILE				
User newUser=binder.parse(new File(..), User.class);
```

To make same operation with different data format like XML, YAML, and so on just replace
```java
BinderContext binder=KriptonBinder.jsonBind();
```
with
```java
// XML binder
BinderContext binder=KriptonBinder.xmlBind();

// YAML binder
BinderContext binder=KriptonBinder.bind(BinderType.YAML);

// CBOR binder
BinderContext binder=KriptonBinder.bind(BinderType.CBOR);

// PROPERTY binder
BinderContext binder=KriptonBinder.bind(BinderType.PROPERTIES);

// SMILE binder
BinderContext binder=KriptonBinder.bind(BinderType.SMILE);
```

To integrate Kripton Persistence Library with Retrofit:
```java
// Client definition
public interface QuickStartService {
  @GET("users")
  Call<List<User>> listUsers();	
}

// Retrofit initialization (with Kripton converter factory)
Retrofit retrofit = new Retrofit.Builder().baseUrl(...)
  .addConverterFactory(KriptonBinderConverterFactory.create())
  .build();
```

// Retrofit usage
.. as usual.

To see how fast is Kripton to convert from/to JSON read this [wiki page](https://github.com/xcesco/kripton/wiki/PerformanceJSON).

# Quickstart - Persistence with Shared Preferences
To persist beans with _SharedPreference_, we need to define the class that contains properties that _SharedPreference_ will persist.

Kritpon uses an annotation processor to generate boilerplate code necessary to interact with shared preferences.

### Usage
Consider the follow bean definition:

```java
@BindType
public class User  {
    public long id;
    public String email;
    public String name;
    public String surname;
    public String username;
}
```

A share preferences definition can be done with a simple class:

```java
@BindSharedPreferences
public class SecurityPreferences {
  public User user;
}
```


The Kripton Annotation Processor will generate for us `BindSecurityPreferences` to work with shared preferences. In this way, after run the annotation processor, to work with shared preferences we will write:

```java
final User bean=new User();
...
BindSecurityPreferences security=BindSecurityPreferences.instance();
security.edit().putUser(bean).commit();	
```

There some feature of generated shared preference that we want to underline:

 - `BindSecurityPreferences` derives from Shared Preference, so it inherit all its method and its "way of working".
 - `BindSecurityPreferences` is a singleton.
 - Shared Preferences can be managed with Live Data and Observable pattern (since version 4).
 - `BindSecurityPreferences` contains a [Editor](https://developer.android.com/reference/android/content/SharedPreferences.Editor.html) specialized to work with `SecurityPreferences` fields.


# Setup
You can use Kritpon Annotation Processor and Kripton Persistence Library via gradle

```
// annotation processor
annotationProcessor "com.abubusoft:kripton-processor:7.0.0"

// https://mvnrepository.com/artifact/com.abubusoft/kripton
implements "com.abubusoft:kripton-android-library:7.0.0"
// if you want to use ciphered database, uncomment following line
// implementation "net.zetetic:android-database-sqlcipher:4.3.0"
```

Snapshots of the development version are available in [Sonatype's snapshots repository](https://oss.sonatype.org/content/repositories/snapshots/com/abubusoft/).

Kritpon requires at minimum Java 8 or Android 3.0.

![logo](https://github.com/xcesco/wikis/blob/master/kripton/logo320_120.png)


# Build
To build entire library collections just download repository and launch from base directory 

```
mvn clean install -Prelease
```

# Supported platforms
There are two supported platforms: the android environment and generic Java environment. For each platform there is a version of library. Android platform already include a json library and xml parser library. Java JDK does not include a json library and have different xml parser libraries.

# License

```
Copyright 2015-2020 Francesco Benincasa.

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
