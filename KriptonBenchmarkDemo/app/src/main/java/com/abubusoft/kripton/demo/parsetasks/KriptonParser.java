package com.abubusoft.kripton.demo.parsetasks;


import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.demo.model.Response;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

/**
 * Created by xcecso on 22/12/2016.
 */

public class KriptonParser extends Parser {
    private final KriptonJsonContext kriptonContext;

    public KriptonParser(ParseListener parseListener, String jsonString) {
        super(parseListener, jsonString);

        kriptonContext=KriptonBinder.jsonBind();
    }

    @Override
    protected int parse(String json) {
        try {
            //InputStream input=new ByteArrayInputStream(json.getBytes());
           //StringReader sr=new StringReader(json);
            Response response = kriptonContext.parse(json
                    , Response.class);
           // sr.close();
            //input.close();
            return response.users.size();
        } catch (Exception e) {
            return 0;
        } finally {
            System.gc();
        }
    }
}
