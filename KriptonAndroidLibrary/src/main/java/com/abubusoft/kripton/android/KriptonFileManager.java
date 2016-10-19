package com.abubusoft.kripton.android;

import android.content.Context;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Simple file manager. Each class is automacally saved as json file with same name of class. 
 *
 * Created by xcesco on 11/04/2016.
 */
public class KriptonFileManager {

    protected static KriptonFileManager instance;

    protected final static String EXTENSION= ".json";

    /**
     * Get instance
     *
     * @return
     * 		instance
     */
    public static KriptonFileManager instance()
    {
        if (instance==null)
        {
            instance=new KriptonFileManager();
        }

        return instance;
    }

	private BinderWriter writer;
	
	private BinderReader reader;

    /**
     * Salva il security manager.
     *
     */
    public void save(Object bean)
    {
    	if (bean==null) return;
    	
    	if (writer==null)
    		writer=BinderFactory.getJSONWriter(BinderOptions.build().indent(true));

        try {
            writer.write(bean, KriptonLibrary.context().openFileOutput(bean.getClass().getSimpleName()+EXTENSION , Context.MODE_PRIVATE));
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load associated file
     * 
     * @param <E>
     *
     */
    public <E> E load(Class<E> clazz)
    {
        File file = new File(KriptonLibrary.context().getFilesDir(), clazz.getSimpleName());

        E bean=null;
        if (file.exists())
        {   
        	if (reader==null)
        		reader=BinderFactory.getJSONReader();
        	
            try {
                bean=reader.read(clazz, KriptonLibrary.context().openFileInput(clazz.getSimpleName()+EXTENSION));
                return bean;
            } catch (ReaderException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
                       
        } else {
            try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        }
        
        return bean;
    }
  
}
