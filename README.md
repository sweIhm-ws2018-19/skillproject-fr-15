# Alexa's Escape

Ein interaktives 'Room-Escape-Game' für die ganze Familie.

## Status
[![Travis-CI Status](https://travis-ci.org/sweIhm-ws2018-19/skillproject-fr-15.svg?branch=master)](https://travis-ci.org/sweIhm-ws2018-19/skillproject-fr-15) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=alexa-skills-kit-samples:alexasescape&metric=alert_status)](https://sonarcloud.io/dashboard?id=alexa-skills-kit-samples:alexasescape)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=alexa-skills-kit-samples%3Aalexasescape&metric=code_smells)](https://sonarcloud.io/dashboard?id=alexa-skills-kit-samples:alexasescape) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=alexa-skills-kit-samples%3Aalexasescape&metric=coverage)](https://sonarcloud.io/dashboard?id=alexa-skills-kit-samples:alexasescape) [![Duplicated lines](https://sonarcloud.io/api/project_badges/measure?project=alexa-skills-kit-samples%3Aalexasescape&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=alexa-skills-kit-samples:alexasescape) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=alexa-skills-kit-samples%3Aalexasescape&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=alexa-skills-kit-samples:alexasescape) [![Reliability](https://sonarcloud.io/api/project_badges/measure?project=alexa-skills-kit-samples%3Aalexasescape&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=alexa-skills-kit-samples:alexasescape)

## Description
Alexa's Escape ist ein interaktives Adventure-Spiel, in dem es darum geht, dem fiktiven Charakter Alexa über das Telefon zu helfen aus einem Haus zu entkommen. Alexa befindet sich in einem fremdem Haus, aus dem sie durch verschiedene Räume hindurch zum Ausgang gelangen muss. In jedem Zimmer gibt es Gegenstände, die Alexa dabei helfen den jeweiligen Raum und schlussendlich auch das Haus zu verlassen. Über die Auswahl der Gegenstände wird nun der Spieler am Telefon entscheiden und leitet Alexa somit aus dem Haus. Sobald Alexa das Haus verlassen kann, ohne das der Spieler zu viele falsche Entscheidungen getroffen hat, ist die Flucht geglückt und die benötigte Zeit wird gespeichert.

## Setup
To run this example skill you need to do two things. The first is to deploy the example code in lambda, and the second is to configure the Alexa skill to use Lambda.

### AWS Lambda Setup
Refer to [Hosting a Custom Skill as an AWS Lambda Function](https://developer.amazon.com/docs/custom-skills/host-a-custom-skill-as-an-aws-lambda-function.html) reference for a walkthrough on creating a AWS Lambda function with the correct role for your skill. When creating the function, select the “Author from scratch” option, and select the Java 8 runtime. 

Once you've created your AWS Lambda function and configured “Alexa Skills Kit” as a trigger, upload the JAR file produced by building the project and set the handler to the fully qualified class name of your handler function. Finally, copy the ARN for your AWS Lambda function because you’ll need it when configuring your skill in the Amazon Developer console.

### Alexa Skill Setup
Now that the skill code has been uploaded to AWS Lambda we're ready to configure the skill with Alexa. First, navigate to the [Alexa Skills Kit Developer Console](https://developer.amazon.com/alexa/console/ask). Click the “Create Skill” button in the upper right. Enter “AlexasEscape” as your skill name. On the next page,  select “Custom” and click “Create skill”.
 
Now we're ready to define the interaction model for the skill. Under “Invocation” tab on the left side, define your Skill Invocation Name to be `alexas escape`. 
 
Now it’s time to add an intent to the skill. Click the “Add” button under the Intents section of the Interaction Model. Leave “Create custom intent” selected, enter “ElexasEscape” for the intent name, and create the intent. Now it’s time to add some sample utterances that will be used to invoke the intent. For this example, we’ve provided the following sample utterances, but feel free to add others.

### Build
To build the sample, open a terminal and go to the directory containing pom.xml, and run: 
```
mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
```
This will generate a zip file named "alexasescape-1.0-jar-with-dependencies.jar" in the target directory.


## Running the tests
```
mvn test
```

## Deployment

Add additional notes about how to deploy this on a live system

## Authors

* **Daniel Seifert** - *Initial work* - [Daniel-Seifert](https://github.com/Daniel-Seifert)
* **Lenz Karbaumer** - *Initial work* - [Lenz-K](https://github.com/Lenz-K)
* **Julian Taxis** - *Initial work* - [Curlie](https://github.com/Curlie)
* **Paul Kaiser** - *Initial work* - [g3ner4l123](https://github.com/g3ner4l123)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
