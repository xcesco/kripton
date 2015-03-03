# Kripton
A simple Java-XML-JSON binder for Android and Java platforms environment


## Introduction##
Kritpon is a small library with the aim to translate java POJO in XML/JSON and viceversa. The rules needed are expressed through annotations. I know there are many other framework and library do this for me, but my goal is build the smallest library to do the translation with good performance. The main platform is Android, but i choose to support generic java platform too.

I found some fantastic libraries that i partially reused to realize kripton

 - https://github.com/bulldog2011/nano
 - https://github.com/google/guava


## How it works? Just a simple example##
As example we take a simple java class:

    package com.abubusoft.kripton.examples;

    import com.abubusoft.kripton.binder.annotation.BindDefault;

    @BindDefault
    public class SimpleBean {
    
    	private long age;
    
    	private String name;
    
    	public long getAge() {
    		return age;
    	}
    
    	public String getName() {
    		return name;
    	}
    
    	public void setAge(long age) {
    		this.age = age;
    	}
    
    	public void setName(String name) {
    		this.name = name;
    	}
    }

With the annotation **@BindDefault** we persist every field in the pojo.  To convert in xml and json format we can use the following code:

        ...
        SimpleBean bean=new SimpleBean();
		
		bean.setAge(25);
		bean.setName("John");
		
		// create json binder
		BinderWriter jsonWriter=BinderFactory.getJSONWriter();
		String jsonBuffer=jsonWriter.write(bean);
		
		// create xml binder
		BinderWriter xmlWriter=BinderFactory.getXMLWriter();
		String xmlBuffer=xmlWriter.write(bean);
		...

After create binder for a specific format, you simple invoke write method to generate xml or json rapresentation of the bean.

The JSON translation of the initial bean is:

    {
        "simpleBean": {
            "age": 25,
            "name": "John"
        }
    }
 
While the XML translation of the initial bean is:

    <?xml version='1.0' encoding='utf-8' ?>
    <simpleBean>
      <age>25</age>
      <name>John</name>
    </simpleBean>

To convert json string to java pojo, you simply use:
   
    // create json reader binder
    BinderReader jsonReader=BinderFactory.getJSONReader();
    SimpleBean beanResult = jsonReader.read(SimpleBean.class, jsonBuffer);

To convert xml string to java pojo:

    // create xml reader binder
    BinderReader xmlReader=BinderFactory.getXMLReader();
    SimpleBean beanResult = xmlReader.read(SimpleBean.class, xmlBuffer);

Quite simple, isn't it?
