package com.abubusoft.kripton.benchmark;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bluelinelabs.logansquare.LoganSquare;
import com.abubusoft.kripton.benchmark.model.Response;
import com.abubusoft.kripton.benchmark.parsetasks.GsonParser;
import com.abubusoft.kripton.benchmark.parsetasks.JacksonDatabindParser;
import com.abubusoft.kripton.benchmark.parsetasks.KriptonParser;
import com.abubusoft.kripton.benchmark.parsetasks.LoganSquareParser;
import com.abubusoft.kripton.benchmark.parsetasks.MoshiParser;
import com.abubusoft.kripton.benchmark.parsetasks.ParseResult;
import com.abubusoft.kripton.benchmark.parsetasks.Parser;
import com.abubusoft.kripton.benchmark.parsetasks.Parser.ParseListener;
import com.abubusoft.kripton.benchmark.serializetasks.GsonSerializer;
import com.abubusoft.kripton.benchmark.serializetasks.JacksonDatabindSerializer;
import com.abubusoft.kripton.benchmark.serializetasks.KriptonSerializer;
import com.abubusoft.kripton.benchmark.serializetasks.LoganSquareSerializer;
import com.abubusoft.kripton.benchmark.serializetasks.MoshiSerializer;
import com.abubusoft.kripton.benchmark.serializetasks.SerializeResult;
import com.abubusoft.kripton.benchmark.serializetasks.Serializer;
import com.abubusoft.kripton.benchmark.serializetasks.Serializer.SerializeListener;
import com.abubusoft.kripton.benchmark.widget.BarChart;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.squareup.moshi.Moshi;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a test app we wrote in 10 minutes. Please do not write code like this, kiddos.
 */
public class BenchmarkActivity extends AppCompatActivity {

    private static final int ITERATIONS = 20;

    private BarChart mBarChart;
    private List<String> mJsonStringsToParse;
    private List<Response> mResponsesToSerialize;

    private final ParseListener mParseListener = new ParseListener() {
        @Override
        public void onComplete(Parser parser, ParseResult parseResult) {
            addBarData(parser, parseResult);
        }
    };
    private final SerializeListener mSerializeListener = new SerializeListener() {
        @Override
        public void onComplete(Serializer serializer, SerializeResult serializeResult) {
            addBarData(serializer, serializeResult);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmark);

        String s=" Device model: " + android.os.Build.MODEL;
        s += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;

        TextView tvDeviceInfo= (TextView) findViewById(R.id.tvDeviceInfo);
        tvDeviceInfo.setText(s);

        mJsonStringsToParse = readJsonFromFile();
        mResponsesToSerialize = getResponsesToParse();

        mBarChart = (BarChart)findViewById(R.id.bar_chart);
        mBarChart.setColumnTitles(new String[] {"GSON", "Jackson", "LoganSquare", "Moshi", "Kripton"});

        findViewById(R.id.btn_parse_tests).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                performParseTests();
            }
        });

        findViewById(R.id.btn_serialize_tests).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                performSerializeTests();
            }
        });
    }

    private void performParseTests() {
        mBarChart.clear();
        mBarChart.setSections(new String[] {"Parse 600 items","Parse 60 items", "Parse 20 items", "Parse 7 items", "Parse 2 items"});

        Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        Moshi moshi = new Moshi.Builder().build();
        List<Parser> parsers = new ArrayList<>();
        for (String jsonString : mJsonStringsToParse) {
            for (int iteration = 0; iteration < ITERATIONS; iteration++) {
                parsers.add(new GsonParser(mParseListener, jsonString, gson));
                parsers.add(new JacksonDatabindParser(mParseListener, jsonString, objectMapper));
                parsers.add(new LoganSquareParser(mParseListener, jsonString));
                parsers.add(new MoshiParser(mParseListener, jsonString, moshi));
                parsers.add(new KriptonParser(mParseListener, jsonString));
            }
        }

        for (Parser parser : parsers) {
            parser.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        }
    }

    private void performSerializeTests() {
        mBarChart.clear();
        mBarChart.setSections(new String[] {"Serialize 600 items", "Serialize 60 items", "Serialize 20 items", "Serialize 7 items", "Serialize 2 items"});

        Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        Moshi moshi = new Moshi.Builder().build();
        List<Serializer> serializers = new ArrayList<>();
        for (Response response : mResponsesToSerialize) {
            for (int iteration = 0; iteration < ITERATIONS; iteration++) {
                serializers.add(new GsonSerializer(mSerializeListener, response, gson));
                serializers.add(new JacksonDatabindSerializer(mSerializeListener, response, objectMapper));
                serializers.add(new LoganSquareSerializer(mSerializeListener, response));
                serializers.add(new MoshiSerializer(mSerializeListener, response, moshi));
                serializers.add(new KriptonSerializer(mSerializeListener, response));
            }
        }

        for (Serializer serializer : serializers) {
            serializer.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        }
    }

    private void addBarData(Parser parser, ParseResult parseResult) {
        int section;
        switch (parseResult.objectsParsed) {
            case 600:
                section = 0;
                break;
            case 60:
                section = 1;
                break;
            case 20:
                section = 2;
                break;
            case 7:
                section = 3;
                break;
            case 2:
                section = 4;
                break;
            default:
                section = -1;
                break;
        }

        if (parser instanceof GsonParser) {
            mBarChart.addTiming(section, 0, parseResult.runDuration / 1000f);
        } else if (parser instanceof JacksonDatabindParser) {
            mBarChart.addTiming(section, 1, parseResult.runDuration / 1000f);
        } else if (parser instanceof LoganSquareParser) {
            mBarChart.addTiming(section, 2, parseResult.runDuration / 1000f);
        } else if (parser instanceof MoshiParser) {
            mBarChart.addTiming(section, 3, parseResult.runDuration / 1000f);
        } else if (parser instanceof KriptonParser) {
            mBarChart.addTiming(section, 4, parseResult.runDuration / 1000f);
         }
    }

    private void addBarData(Serializer serializer, SerializeResult serializeResult) {
        int section;
        switch (serializeResult.objectsParsed) {
            case 600:
                section = 0;
                break;
            case 60:
                section = 1;
                break;
            case 20:
                section = 2;
                break;
            case 7:
                section = 3;
                break;
            case 2:
                section = 4;
                break;
            default:
                section = -1;
                break;
        }

        if (serializer instanceof GsonSerializer) {
            mBarChart.addTiming(section, 0, serializeResult.runDuration / 1000f);
        } else if (serializer instanceof JacksonDatabindSerializer) {
            mBarChart.addTiming(section, 1, serializeResult.runDuration / 1000f);
        } else if (serializer instanceof LoganSquareSerializer) {
            mBarChart.addTiming(section, 2, serializeResult.runDuration / 1000f);
        } else if (serializer instanceof MoshiSerializer) {
            mBarChart.addTiming(section, 3, serializeResult.runDuration / 1000f);
        } else if (serializer instanceof KriptonSerializer) {
            mBarChart.addTiming(section, 4, serializeResult.runDuration / 1000f);
        }
    }

    private List<Response> getResponsesToParse() {
        List<Response> responses = new ArrayList<>();

        try {
            for (String jsonString : mJsonStringsToParse) {
                responses.add(LoganSquare.parse(jsonString, Response.class));
            }
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("The serializable objects were not able to load properly. These tests won't work until you completely kill this benchmark app and restart it.")
                    .setPositiveButton("OK", null)
                    .show();
        }

        return responses;
    }

    private List<String> readJsonFromFile() {
        List<String> strings = new ArrayList<>();

        strings.add(readFile("sample600.json"));
        strings.add(readFile("sample60.json"));
        strings.add(readFile("sample20.json"));
        strings.add(readFile("sample7.json"));
        strings.add(readFile("sample2.json"));

        return strings;
    }

    private String readFile(String filename) {
        StringBuilder sb = new StringBuilder();

        try {
            InputStream json = getAssets().open(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));

            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }

            in.close();
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(
                        "The JSON file was not able to load properly. These tests won't work until you completely kill this benchmark app and restart it.")
                    .setPositiveButton("OK", null)
                    .show();
        }

        return sb.toString();
    }
}
