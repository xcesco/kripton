package com.abubusoft.kripton.benchmark.serializetasks;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.benchmark.model.Response;

public class KriptonSerializer extends Serializer {

    private final KriptonJsonContext jsonContext;

    public KriptonSerializer(SerializeListener parseListener, Response response) {
        super(parseListener, response);
        jsonContext=KriptonBinder.jsonBind();
    }

    @Override
    protected String serialize(Response response) {
        try {
            return jsonContext.serialize(response);
        } catch (Exception e) {
            return null;
        } finally {
            System.gc();
        }
    }
}
