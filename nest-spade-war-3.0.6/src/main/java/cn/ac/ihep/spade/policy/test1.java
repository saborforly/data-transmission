package cn.ac.ihep.spade.policy;

import gov.lbl.nest.spade.policy.FailedPolicyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class test1{
	 public static String XML_FORMAT_PROPERTY = "";

     public  void execute() {
        File semphoreFile = new File("G://data//1.sem");
        FileOutputStream out = null; 
        try {
        	//semphoreFile.createNewFile();
        	if(semphoreFile.exists()){
        	    semphoreFile.delete();
        	    semphoreFile.createNewFile();
        		System.out.println("file exists");
        	}
        	else{
        		semphoreFile.createNewFile();
        	}
			out = new FileOutputStream(semphoreFile);
			String s="12";
			out.write(s.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
     }

	   

public static void main(String args[]) throws FailedPolicyException, IOException{
	
	
	
	System.out.println("nihao");
	String semPath="path/sfas/dfas/1.txt";
	String[] parts = semPath.split(":", 2);
	semPath=semPath.substring(semPath.lastIndexOf(":")+1,semPath.length());
	
	
	semPath=semPath.substring(0,semPath.lastIndexOf("/")+1);
	
	
	/*
    Pattern pattern = Pattern.compile("(.*)(?=/$)");
 	Matcher matcher = pattern.matcher(semPath);	             
 	if (matcher.find()){
 		semPath=matcher.group();
 		semPath=semPath.replace(":", "");
 	}
 	//File file=new File("G://data//");
 	//file.mkdirs();
 	System.out.println(semPath);
    //test1 meta = new test1();  
    //meta.execute();
   */
	System.out.println(parts[0]);
   
    
        
            
        
    
}
}