package test.vadim

import groovy.io.FileType;
import groovy.json.JsonSlurper
import org.testng.Assert

/**
 * Created by Vadim on 10/08/16.
 */


public class TestAll {

    @org.testng.annotations.Test
    public void startParsing(){

        boolean  saveJsonToFile = true;

        String featuresHomeDirectory = 'src/test/resources/features'

        String jsonTargetDirectory = 'src/test/resources/json/'

        String projectDirecory = System.getProperty("user.dir");

        def features = []

        def featuresDirectory = new File(projectDirecory + '/' + featuresHomeDirectory)

        featuresDirectory.eachFileRecurse (FileType.FILES) { file ->
            features << file
        }

        features.each {

            String featureName = it.name.take(it.name.lastIndexOf('.'))

            String featurePath = it.path

            String jsonPath = projectDirecory + '/' + jsonTargetDirectory + featureName + '.json'

            String jsonString

            GherkinToJson gtj = new GherkinToJson(saveJsonToFile)

            jsonString =  gtj.gherkinTojson(featurePath, jsonPath)

            // try to find some tag in given feature
             findTag(jsonString,"@android")
             findTag(jsonString,"@desktop")
             findTag(jsonString,"@ios")

        }

    }

    public boolean findTag(String jsonString, String tag){

        String resultString = null

        def jsonSlurper = new JsonSlurper()

        def object = jsonSlurper.parseText(jsonString)

            object.each {

            if(object.tags.name != null)
            { resultString = object.tags.name}

            }

            if(resultString != null && resultString.contains(tag)) {

                println '---------------------------------------'
                println tag + ' tag is found'
                println('feature name = ' + object.name);
                println('feature id = ' + object.id);
                println('tags.name = ' + object.tags.name);
                println '---------------------------------------'

                return true;
            } else {
                return false;
            }
        }

}
