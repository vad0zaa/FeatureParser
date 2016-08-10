package test.vadim

import groovy.json.JsonSlurper


/**
 * Created by Vadim on 10/08/16.
 */
class TestJsonString {

    String jsonString = '[\n' +
            '  {\n' +
            '    "line": 2,\n' +
            '    "elements": [\n' +
            '      {\n' +
            '        "examples": [\n' +
            '          {\n' +
            '            "line": 12,\n' +
            '            "name": "",\n' +
            '            "description": "",\n' +
            '            "id": "android-applications-and-browsers-tests;user-opens-native-application-and-clicks-button;",\n' +
            '            "rows": [\n' +
            '              {\n' +
            '                "cells": [\n' +
            '                  "application"\n' +
            '                ],\n' +
            '                "line": 13,\n' +
            '                "id": "android-applications-and-browsers-tests;user-opens-native-application-and-clicks-button;;1"\n' +
            '              },\n' +
            '              {\n' +
            '                "cells": [\n' +
            '                  "Android app"\n' +
            '                ],\n' +
            '                "line": 14,\n' +
            '                "id": "android-applications-and-browsers-tests;user-opens-native-application-and-clicks-button;;2"\n' +
            '              }\n' +
            '            ],\n' +
            '            "keyword": "Examples"\n' +
            '          }\n' +
            '        ],\n' +
            '        "line": 7,\n' +
            '        "name": "User opens native application and clicks button",\n' +
            '        "description": "",\n' +
            '        "id": "android-applications-and-browsers-tests;user-opens-native-application-and-clicks-button",\n' +
            '        "type": "scenario_outline",\n' +
            '        "keyword": "Scenario Outline",\n' +
            '        "steps": [\n' +
            '          {\n' +
            '            "line": 8,\n' +
            '            "name": "user opens \\u003capplication\\u003e",\n' +
            '            "keyword": "Given "\n' +
            '          },\n' +
            '          {\n' +
            '            "line": 9,\n' +
            '            "name": "user clicks the button",\n' +
            '            "keyword": "When "\n' +
            '          },\n' +
            '          {\n' +
            '            "line": 10,\n' +
            '            "name": "the button should be still visible",\n' +
            '            "keyword": "Then "\n' +
            '          }\n' +
            '        ]\n' +
            '      },\n' +
            '      {\n' +
            '        "examples": [\n' +
            '          {\n' +
            '            "line": 22,\n' +
            '            "name": "",\n' +
            '            "description": "",\n' +
            '            "id": "android-applications-and-browsers-tests;user-opens-google-homepage-and-does-maths;",\n' +
            '            "rows": [\n' +
            '              {\n' +
            '                "cells": [\n' +
            '                  "application",\n' +
            '                  "enter",\n' +
            '                  "result"\n' +
            '                ],\n' +
            '                "line": 23,\n' +
            '                "id": "android-applications-and-browsers-tests;user-opens-google-homepage-and-does-maths;;1"\n' +
            '              },\n' +
            '              {\n' +
            '                "cells": [\n' +
            '                  "Android browser",\n' +
            '                  "2 * 2",\n' +
            '                  "4"\n' +
            '                ],\n' +
            '                "line": 24,\n' +
            '                "id": "android-applications-and-browsers-tests;user-opens-google-homepage-and-does-maths;;2"\n' +
            '              }\n' +
            '            ],\n' +
            '            "keyword": "Examples"\n' +
            '          }\n' +
            '        ],\n' +
            '        "line": 17,\n' +
            '        "name": "User opens Google homepage and does maths",\n' +
            '        "description": "",\n' +
            '        "id": "android-applications-and-browsers-tests;user-opens-google-homepage-and-does-maths",\n' +
            '        "type": "scenario_outline",\n' +
            '        "keyword": "Scenario Outline",\n' +
            '        "steps": [\n' +
            '          {\n' +
            '            "line": 18,\n' +
            '            "name": "user opens Google homepage in \\u003capplication\\u003e",\n' +
            '            "keyword": "Given "\n' +
            '          },\n' +
            '          {\n' +
            '            "line": 19,\n' +
            '            "name": "user enters \\"\\u003center\\u003e\\" into the search input",\n' +
            '            "keyword": "When "\n' +
            '          },\n' +
            '          {\n' +
            '            "line": 20,\n' +
            '            "name": "the calculator is displayed",\n' +
            '            "keyword": "Then "\n' +
            '          },\n' +
            '          {\n' +
            '            "line": 21,\n' +
            '            "name": "result shown is \\u003cresult\\u003e",\n' +
            '            "keyword": "And "\n' +
            '          }\n' +
            '        ]\n' +
            '      }\n' +
            '    ],\n' +
            '    "name": "Android applications and browsers tests",\n' +
            '    "description": "\\nAs a tester I would like to open all applications and browsers\\nin order to test them.",\n' +
            '    "id": "android-applications-and-browsers-tests",\n' +
            '    "keyword": "Feature",\n' +
            '    "uri": "/Users/Vadim/FeatureParser/src/test/resources/features/android.feature",\n' +
            '    "tags": [\n' +
            '      {\n' +
            '        "line": 1,\n' +
            '        "name": "@android"\n' +
            '      }\n' +
            '    ]\n' +
            '  }\n' +
            ']'


    @org.testng.annotations.Test
    public void findJsonElement(){

        def jsonSlurper = new JsonSlurper()

        def object = jsonSlurper.parseText(jsonString)

        object.each {
            println('name='+ object.name);
            println('id='+ object.id);
            println('description='+ object.description);
            println('keyword='+ object.keyword);
            println('uri='+ object.uri);
            println('tags.name='+ object.tags.name);


        }

    }

}
