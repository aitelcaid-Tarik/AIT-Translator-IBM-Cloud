# AIT Translator

## Abstract
The aim of this project is to develop an Android application that uses IBM’s Watson Language Translator service to translate text from a source to a target language.<br>

Translation is available among Arabic, Chinese, English, French, Portuguese, German, and Spanish.
The application uses the Material design system for the user interface design.<br>

We test our application using JUnit, Mockito and Espresso at three different levels:<br>
* Local Unit Tests
* Instrumented Tests
* UI Tests

<p align="center">
 <img src="Screenshot/1.png" width="32%" height="30%"/>
</p>
 Our application is composed of the following UI components:<br>
 <pre>

</pre>
<p align="center">
 <img src="Screenshot/2.png" width="32%" height="30%"/> 
</p>

**UI Component 1:** TextInput where the user submits the text to be translated. <br>
**UI Component 2 & 4:** Spinner that displays a list of available source and target languages respectively. <br>
**UI Component 3:** Button that allows user to switch the source language with the target language.<br>
**UI Component 5:** Button that starts the service which translates the textUI. <br>
**UI Component 6:** TextView to show the final result. <br>

## Local unit tests
In unit testing, we test the individual functions of some classes. Those tests are run on our machine's local Java Virtual Machine (JVM). <br>

 <img src="Screenshot/UnitTest.png"/>

In the screen we see that test classes are organized in their own package, Those tests are run using JUnit4. <br>

In this test, we test the connectivity to the ibm cloud service, and the translate function. <br>

The test results are shown down, (Tests passed: 2) <br>


## Instrumented tests
With instrumented testing we are able to verify app logic that needs a real device, so mostly we will verify the UI. We used junit and Espresso. <br>
 
 <img src="Screenshot/InstrumentedTest.png"/>

In this instrumented test, we submitted some text in the input field, verified that it was indeed written, changed both source and target languages and gave them the same values, clicked on the translate button,then we verified that an error alert is shown as the source and target languages cannot be the same, and finally we verified that the button is still displayed.


## UI tests
User interface (UI) testing lets us ensure that our application meets its functional requirements. UI tests are a complete use cases to simulate user interaction with the application:

 <img src="Screenshot/UITest.png"/>

In this Ui test, we submitted some text in the input field, verified that it was indeed written, changed both source and target languages, clicked on the translate button to start the background task, and finally we verified that result is displayed in its respective text view. <br>

We changed the languages again, and repeated the same scenario again just to double check. <br>

