package test.vadim

import groovy.json.JsonSlurper

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import gherkin.formatter.JSONFormatter;
import gherkin.parser.Parser;
import gherkin.util.FixJava;

/**
 * Created by Vadim on 11/08/16.
 */

class GherkinParser {


    public static boolean findFeatureTag(String featurePath, List tagsToFind) {

        boolean output = false
        StringBuilder json = null;
        String gherkin = null;
        String featureTags = null

        gherkin = readFromFile(featurePath);
        if(gherkin == null) {
            return false
        }

        json = new StringBuilder();
        JSONFormatter formatter;
        formatter = new JSONFormatter(json);

        Parser parser = new Parser(formatter);
        parser.parse(gherkin, featurePath, 0);
        formatter.done();
        formatter.close();

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(json.toString())

        object.each {

            if(object.tags.name != null)
            { featureTags = object.tags.name}

        }

        if(featureTags != null) {
            tagsToFind.each { String tag ->
                if (featureTags.contains(tag)) {
                    output = true
                }
            }
        }

        return output
    }




    private static String readFromFile(String path){

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

}
