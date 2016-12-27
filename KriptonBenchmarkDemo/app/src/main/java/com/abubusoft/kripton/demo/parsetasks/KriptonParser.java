package com.abubusoft.kripton.demo.parsetasks;


import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.demo.model.Response;

/**
 * Created by 908099 on 22/12/2016.
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
            Response response = kriptonContext.parse(json, Response.class);
            return response.users.size();
        } catch (Exception e) {
            return 0;
        } finally {
            System.gc();
        }
    }
}
