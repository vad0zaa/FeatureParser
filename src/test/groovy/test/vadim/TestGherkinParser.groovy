package test.vadim

import groovy.io.FileType
import org.junit.Assert

/**
 * Created by Vadim on 11/08/16.
 */
class TestGherkinParser {


    @org.testng.annotations.Test
    public void start(){

        boolean  tagFound = false

        List tag = []

        tag.add('@android')
        tag.add('@ios')

        //String tag = '@android'

        String featuresHomeDirectory = 'src/test/resources/features'

        String projectDirecory = System.getProperty("user.dir");

        def features = []

        def featuresDirectory = new File(projectDirecory + '/' + featuresHomeDirectory)

        featuresDirectory.eachFileRecurse (FileType.FILES) { file ->
            features << file
        }

        features.each {

            String featureName = it.name.take(it.name.lastIndexOf('.'))
            String featurePath = it.path

            tagFound = GherkinParser.findFeatureTag(featurePath,tag)

            println ''
            println '-----------------------------------------------'
            println ' featureName = ' + featureName + '  ,tag list = ' + tag + ' , found = ' + tagFound

        }

    }

}
