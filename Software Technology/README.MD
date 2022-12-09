# SOFTWARE TECHNOLOGY

## In the assignment of this class we were tasked to create our own application.

### Some basic information about this project are presented below. In case you want to read more about the functionality of the application, you can read the pdf file with all the details.

#### The application GUI was implemented using the Java Swing API. The application is compatible with Java 11. Text to speech translation is based at the [Free TTS API](https://freetts.sourceforge.io/)
- Eclipse
- JUnit
- Java 11
- Swing
- FreeTTS

##### **1 Introduction**
This document defines the basic requirements for Text2SpeechEditor, an application for writing documents and transforming them to audio.

###### **1.1 Purpose**
The objective of this project is to develop a simple editor that allows preparing text documents and transforming them to audio. Such an application can be of much help for people who have speaking problem. Moreover, it could be a useful tool for preparing presentations that are automatically played without a physical speaker. The application consists of a graphical user interface, a back-end that enables the text editing process and the part that transforms text to speech, which will be based on external text to speech libraries

##### **2 Domain Properties**
The Text2SpeechEditor enables the creation and management of text documents. A text document is characterized by the following basic properties:
- The author.
- The title
- The date that it was created.
- The date that it was last saved.
- The contents of the document. The contents of the document are structured as a list of lines. Each line consists of a list of words.

