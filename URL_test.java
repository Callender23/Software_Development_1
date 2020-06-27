package gui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class URL_test extends URL_GUI {

	@Test
	void test() throws Exception {
		
		
		URL_GUI test = new URL_GUI();
		
		// insert values into test case HashMap
		HashMap<Object, Object> expected = new HashMap<>();
		
		expected.put("for",3);
		expected.put("him",2);
		expected.put("the",2);
		expected.put("sing",2);
		expected.put("dog",2);
		expected.put("song",1);
		expected.put("big",1);
		expected.put("plates",1);							
		expected.put("topology",1);
		expected.put("count",1);
		expected.put("house",1);
		expected.put("cats",1);
		expected.put("her",1);
		expected.put("cat",1);
		expected.put("hello",1);
		expected.put("word",1);
		
		
		// Made HashMap object  equal to the value of the return of returningMap function in our main java class
		HashMap output = test.returningMap();
		assertEquals(output,expected);
	
		
		// index file made and tested locally on home machine http://127.0.0.1:5500/index.html

		
		
	}
}
