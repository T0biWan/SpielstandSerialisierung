package tests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import classes.Latrunculi;

public class LatrunculiTest {
	
	private StringBuilder data = new StringBuilder();
	
	@BeforeClass
	public static void start(){
		
		try{
			 
    		File file = new File("src/output/Latrunculi.spielfeld");
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
	}
	
	public void setData(String s){
		
		if(data.length() == 0){
			data.append(s);
		}else{
			data.append(System.getProperty("line.separator") + s);
		}
		
	}
	
	public String getData(){
		return data.toString();
	}

	@Test
	public void testSetFirstName() {

		// Simulate input
		setData("John");
		setData("0");
		setData("6");
		setData("0");
		setData("5");
		
		InputStream stdin = System.in;
		
		try{

			System.setIn(new ByteArrayInputStream(getData().getBytes()));
	
			Latrunculi.main(new String[] {});
		} finally {
			System.setIn(stdin);
		}

	}
	
	@Test
	public void testSetSecondName() {

		data.setLength(0);
		// Simulate input
		setData("Ulli");
		setData("0");
		setData("0");
		setData("0");
		setData("1");

		InputStream stdin = System.in;
		
		try{
			System.setIn(new ByteArrayInputStream(getData().getBytes()));
	
			Latrunculi.main(new String[] {});


		} finally {
			System.setIn(stdin);
		}
	}
}