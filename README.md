# Software_Development_1

## Running jar file on Windows & Mac
  1. Open Command Prompt (Windows) or Terminal (Mac)
  2. navagiate to location of jar file.
  3. Type in **java -jar Text_Analyzer.jar** to run jar file.
  4. Type in name of URL you wish to get the frequency of words for the website's p tag.
  
## Simple GUI added 
    A new simple GUI was added to new java file called URL_GUI.java, it function the same as the
    word occurences program but just has an added GUI that takes in a URL link and returns the most
    occurening words from descending order.
    
## JDBC support added
    JDBC support was added to the word occurence application. Word occurence now reads and stores frequency of words in a local database in MySQL.
    Program has the addition of two added methods which are getConnection which allows connection of MySQL database and getData which display
    a query command of the top 20 most reoccuring words from a given URL input.
    
## Java Doc added
    Java documentation was added to word occurences project changes can be seen in URL_GUI.java file.
    Output for Java doc HTML index can be found in the java doc image 01 & 02 PNG files.
    
## Junit Testing added
    A Junit test was added to the file and a simple test case was made to test the functionality of word occurrences
    a HashMap method called returiningMap that returns a HashMap was added to aid in the Junit Test
