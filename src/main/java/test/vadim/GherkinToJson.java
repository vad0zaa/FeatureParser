package test.vadim; /**
 * Created by Vadim on 10/08/16.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import gherkin.formatter.JSONFormatter;
import gherkin.formatter.JSONPrettyFormatter;
import gherkin.parser.Parser;
import gherkin.util.FixJava;

public class GherkinToJson {

    private boolean saveJsonToFileEnabled;

    private String format;

    public GherkinToJson(String outFormat, boolean saveJsonToFileEnabled) {
        this.format = outFormat;
        this.saveJsonToFileEnabled = saveJsonToFileEnabled;
    }

    public String getOutFormat() {
        return format;
    }

    public boolean isSaveJsonToFileEnabled(){
        return saveJsonToFileEnabled;
    }

    public String gherkinTojson(String fPath, String jPath) {

        StringBuilder json = null;
        String gherkin = null;

        try {
            gherkin = FixJava.readReader(new InputStreamReader(
                    new FileInputStream(fPath), "UTF-8"));
        } catch (FileNotFoundException e) {
            System.out.println("Feature file not found");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        json = new StringBuilder();
        JSONFormatter formatter;

        // pretty or ugly selection, pretty by default
        if (format.equalsIgnoreCase("ugly")) {
            formatter = new JSONFormatter(json);// not pretty
        } else {
            formatter = new JSONPrettyFormatter(json);// pretty
        }

        Parser parser = new Parser(formatter);
        parser.parse(gherkin, fPath, 0);
        formatter.done();
        formatter.close();
        //System.out.println("json output: n" + json + "'");

        // Finally flush and close
        if(isSaveJsonToFileEnabled())
        try {
            FileWriter file = new FileWriter(jPath);
            file.write(json.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(json != null) { return json.toString();}
        else {return null;}
    }



}
