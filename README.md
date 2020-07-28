# Software_Development_1

## Running jar file on Windows & Mac
  1. Open Command Prompt (Windows) or Terminal (Mac)
  2. navagiate to location of jar file.
  3. Type in **java -jar<name_of_jar_file>.jar** to run jar file.

## ClientSide/ServerSide
  client/server application that has client send a server request and compute if user input number is prime.
  The server responds with a yes or no responds while client displays the answer.
  Implementation of client/server will be added to WordOccurence application in the future.
  
 ## Running ClientSide/SeverSide jar files
    To know how to run the two jar files simply follow the steps in Running jar file on Windows & Mac section.
    Be sure to run the ServerSide jar file before attempting to run the ClientSide jar file.
    ClientSide.jar and ServerSide.jar were both added to the this branch(master) along with their respective java files and screenshots.
    After each number input Server will automatically display a disconnected message. Press Control c (windows) to stop running the server.
    Run again the ServerSide jar to run prime application. There is no need to run the ClientSide jar file again. 
    

## Simple GUI added 
    A new simple GUI was added to new java file called WordOccurenceApp.java, it function the same as the
    word occurences program but just has an added GUI that takes in a URL link and returns the most
    occurening words from descending order.
    
## JDBC support added
    JDBC support was added to the word occurence application. Word occurence now reads and stores frequency of words in a local database in MySQL.
    Program has the addition of two added methods which are getConnection which allows connection of MySQL database and getData which display
    a query command of the top 20 most reoccuring words from a given URL input.
    
## Java Doc added
    Java documentation was added to word occurences project changes can be seen in WordOccurenceApp.java file.
    Output for Java doc HTML index can be found in the java doc image 01 & 02 PNG files.
    
## Junit Testing added
    A Junit test was added to the file(URL_test.java) and a simple test case was made to test the functionality of word occurrences
    a HashMap method called returiningMap that returns a HashMap was added to aid in the Junit Test
