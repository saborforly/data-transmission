package cn.ac.ihep.spade.policy;
//package cn.ac.ihep.spade.policy;
/*    */ 
/*    */ import gov.lbl.nest.spade.registry.Bundle;
/*    */ import gov.lbl.nest.spade.registry.DataLocator;
/*    */ import gov.lbl.nest.spade.registry.DropLocation;

/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
import java.util.regex.*;
public class ReadFromSem
extends DataLocator
{
public Bundle createBundle(String name, DropLocation dropLocation, File file)
  {
	try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String rawLine = reader.readLine();
        Pattern pattern = Pattern.compile("(?=/)(.*)(</path>)");
        if (null == rawLine) {
        	reader.close();
            return null;
        }
    do{
	    //System.out.println(rawLine);
	    Matcher matcher = pattern.matcher(rawLine);	             
	    if (matcher.find()){
		    rawLine=matcher.group();
		    rawLine=rawLine.replace("</path>", "");
		    break;
		}
        rawLine = reader.readLine();
    }while(rawLine!=null);
    reader.close();  
    return new Bundle(name, normalizeLocation(dropLocation, rawLine
      .trim()));
     } catch (IOException e) {
            e.printStackTrace(); }
    return null;
}
}
