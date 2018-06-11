[![Android Weekly](http://img.shields.io/badge/Android%20Weekly-%23301-2CB3E5.svg?style=flat)](http://androidweekly.net/issues/issue-301)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Kripton%20Persistence%20Library-orange.svg?style=flat)](https://android-arsenal.com/details/1/5149)
[![download](https://api.bintray.com/packages/xcesco/kripton/kripton-android-library/images/download.svg)](https://bintray.com/xcesco/kripton/kripton-android-library/_latestVersion)
[![maven central](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.abubusoft/kripton)
[![test coverage](https://img.shields.io/codecov/c/github/xcesco/kripton/master.svg?style=flat-square)](https://codecov.io/gh/xcesco/kripton?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e2b62de5f47d4be3b87a4a4e69810ca6)](https://www.codacy.com/app/abubusoft/kripton?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=xcesco/kripton&amp;utm_campaign=Badge_Grade)
[![Project Stats](https://www.openhub.net/p/kripton-persistence-library/widgets/project_thin_badge.gif)](https://www.openhub.net/p/kripton-persistence-library)

# Kripton Persistence Library
Kripton is a java library, for Android and Java platform, that provides a simple and uniform way to manage persistence of Java classes in different flavours through annotations and interface.

<img src="https://github.com/xcesco/wikis/blob/master/kripton/overview4.0.png">

To get max performance and avoid boilerplate-code, Kripton use annotation processor. With the power of annotation processor is possible to create code to persist a java class, simply with an annotation. There are many other libraries that do this, but Kripton allows to persists java object without using reflection and with just few lines of code.

See [wiki](https://github.com/xcesco/kripton/wiki) for more informations.

See [benchmarks](https://github.com/xcesco/kripton/wiki/Performance) for more informations about SQLite and JSON persistence perfomance.

If you are interested in Kripton Persistence Library, visit [abubusoft.com](http://www.abubusoft.com/wp/)

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

# Quickstart - Persistence on a SQLite database
Kripton uses the DAO pattern to approach the database management. In the DAO pattern there are:

* A data model composed of simple POJO objects in Java world, and tables in the SQLite world.
* Data Access Object interfaces that define how to access to the database
* Data Access Object implementation that implements the DAO interfaces
* A database that is composed of DAOs and data model.

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
* **[BindInsert](@BindInsert)|[BindUpdate](@BindUpdate)|[BindSelect](@BindSelect)|[BindDelete](@BindDelete)**: defines SQL to manage tables

In the application, to use generated an implementation of data-source you can use code like this:


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

For a `PersonDataSource` interface, Kripton generates a `BindPersonDataSource` class that implements the data-source which allows to work in a thread-safe way and exposing all DAO defined in PersonDataSource interface. The generated data-source exposes some methods to work in a transaction mode and in shared connection mode.

To persist beans with _SharedPreference_, we need to define the class that contains properties that _SharedPreference_ will persist.

Kritpon uses an annotation processor to generate boilerplate code necessary to interact with shared preferences.

### Kripton initialization
Before use Kripton to persit on shared preferences, an Android app have to initialize Kripton library. To do this, just add
the following code to `Application` class (tipically on method onCreate):

```java
// set context for Kripton Library
KriptonLibrary.init(this);
```

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
# Quickstart - Persistence with Shared Preferences
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
 - `BindSecurityPreferences` contains a [Editor](https://developer.android.com/reference/android/content/SharedPreferences.Editor.html) specialized to work with `SecurityPreferences` fields.

The generated class is:

```java
/**
 * This class is the shared preference binder defined for SecuritySharedPreferences
 *
 * @see SecuritySharedPreferences
 */
public class BindSecuritySharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindSecuritySharedPreferences instance;

  /**
   * working instance of bean
   */
  private final SecuritySharedPreferences defaultBean;

  /**
   * PersonBindMap */
  private PersonBindMap personBindMap = AbstractContext.mapperFor(Person.class);

  /**
   * constructor
   */
  private BindSecuritySharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new SecuritySharedPreferences();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    SecuritySharedPreferences bean=new SecuritySharedPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public SecuritySharedPreferences read() {
    SecuritySharedPreferences bean=new SecuritySharedPreferences();
     {
      String temp=prefs.getString("person", null);
      bean.person=StringUtils.hasText(temp) ? parsePerson(temp): null;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(SecuritySharedPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    if (bean.person!=null)  {
      String temp=serializePerson(bean.person);
      editor.putString("person",temp);
    }  else  {
      editor.remove("person");
    }


    editor.commit();
  }

  /**
   * read property person
   *
   * @return property person value
   */
  public Person person() {
    String temp=prefs.getString("person", null);
    return StringUtils.hasText(temp) ? parsePerson(temp): null;

  }

  /**
   * write
   */
  protected String serializePerson(Person value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        personBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected Person parsePerson(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Person result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=personBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindSecuritySharedPreferences instance() {
    if (instance==null) {
      instance=new BindSecuritySharedPreferences();
    }
    return instance;
  }

  /**
   * editor class for shared preferences
   */
  public class BindEditor extends AbstractEditor {
    private BindEditor() {
    }

    /**
     * modifier for property person
     */
    public BindEditor putPerson(Person value) {
      if (value!=null)  {
        String temp=serializePerson(value);
        editor.putString("person",temp);
      }  else  {
        editor.remove("person");
      }

      return this;
    }
  }
}
```


# Setup
You can use Kritpon Annotation Processor and Kripton Persistence Library via gradle

```
// annotation processor
annotationProcessor "com.abubusoft:kripton-processor:4.0.0"

// https://mvnrepository.com/artifact/com.abubusoft/kripton
implements "com.abubusoft:kripton-android-library:4.0.0"
```

or via maven

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
