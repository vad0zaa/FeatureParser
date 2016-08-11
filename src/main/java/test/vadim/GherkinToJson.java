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
import gherkin.parser.Parser;
import gherkin.util.FixJava;

public class GherkinToJson {

    private boolean saveJsonToFileEnabled;

    public GherkinToJson(boolean saveJsonToFileEnabled) {

        this.saveJsonToFileEnabled = saveJsonToFileEnabled;
    }

    public boolean isSaveJsonToFileEnabled(){
        return saveJsonToFileEnabled;
    }

    public String gherkinTojson(String featurePath, String jsonPath) {

        StringBuilder json = null;
        String gherkin = null;

        gherkin = readFromFile(featurePath);

        json = new StringBuilder();
        JSONFormatter formatter;
        formatter = new JSONFormatter(json);

        Parser parser = new Parser(formatter);
        parser.parse(gherkin, featurePath, 0);
        formatter.done();
        formatter.close();

        if(isSaveJsonToFileEnabled())
        saveToFile(json.toString(), jsonPath);

        if(json != null) { return json.toString();}
        else {return null;}
    }


    public String readFromFile(String path){

        String output = null;

        try {
            output = FixJava.readReader(new InputStreamReader(
                    new FileInputStream(path), "UTF-8"));

            return output;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return output;
    }


    public void saveToFile(String input, String path){
        try {
            FileWriter file = new FileWriter(path);
            file.write(input);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
