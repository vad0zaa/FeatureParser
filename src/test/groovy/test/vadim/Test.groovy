package test.vadim

import groovy.io.FileType;
import groovy.json.JsonSlurper
import org.testng.Assert

/**
 * Created by Vadim on 10/08/16.
 */


public class Test {

    @org.testng.annotations.Test
    public void startParsing(){

        String parsingFormat = "ugly";
        boolean  saveJsonToFile = true;

        System.out.println("...startParsing");

        String projectDirecory = System.getProperty("user.dir");

        System.out.println("Current working directory : " + projectDirecory);

       // def featuress = fileTree(dir: 'src/test/resources').include '**/*.feature'

        def features = []

        def featuresDirectory = new File(projectDirecory + '/' + 'src/test/resources/features')

        featuresDirectory.eachFileRecurse (FileType.FILES) { file ->
            features << file
        }


        features.each {

            String featureName = it.name.take(it.name.lastIndexOf('.'))
            //println 'fileWithoutExt=' + featureName

            String featurePath = it.path
            //println 'featurePath=' + featurePath

            String jsonPath = projectDirecory + '/' + 'src/test/resources/json/' + featureName + '.json'
            //println 'jsonPath=' + jsonPath

            String jsonString

            GherkinToJson gtj = new GherkinToJson(parsingFormat, saveJsonToFile)
            jsonString =  gtj.gherkinTojson(featurePath, jsonPath)

            println '---------------------------------------'
            println 'is Android feature? ' +  (findTag(jsonString,"@android"))
            println 'is Desktop feature? ' +  (findTag(jsonString,"@desktop"))
            println 'is Ios feature? ' +  (findTag(jsonString,"@ios"))
            println '---------------------------------------'
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

                println('name = ' + object.name);
                println('id = ' + object.id);
                println('tags.name = ' + object.tags.name);

                return true;
            } else {
                return false;
            }
        }

}
