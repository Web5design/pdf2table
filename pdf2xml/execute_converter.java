/*
    Copyright 2005, 2005 Burcu Yildiz
    Contact: burcu.yildiz@gmail.com
    
    This file is part of pdf2table.
*/

package pdf2xml;

import java.io.*;
import java.lang.*;

public class execute_converter {
	
private FileOutputStream fos;
private OutputStreamWriter osw;

	public execute_converter(String f,String s, String t,String from,String to,boolean interactive_extraction) {
       try {
       System.out.println(t);
	   
       File my_file = new File(t);
       my_file.mkdirs();
	   
       File my_file2 = new File(t + "\\pdf2xml.dtd");
	   this.fos = new FileOutputStream(my_file2);
	   this.osw = new OutputStreamWriter(this.fos);
	   build_dtd();
       
	   String cmd = "";
       
		try {
   		    Runtime rt = Runtime.getRuntime();			
		  
			if (from.equals("") || to.equals("")) {
		      	  
	   	      cmd = "pdftohtml -xml " + s + " " +  t + "\\" + f;
			  System.out.println(cmd);
	   	      Process p = rt.exec(cmd);	   	    
              p.waitFor();
		    }
		    else {
		      try {		      	
		      	int a = Integer.parseInt(from);
		      	int b = Integer.parseInt(to);
			    cmd = "pdftohtml -f " + a + " -l " + b + " -xml " + s + " " + t + "\\" + f;	
				System.out.println(cmd);    	
			    Process p = rt.exec(cmd);
				p.waitFor();
			  }
			  catch(Exception e) {
                  System.out.println(e);
			  }
		    }		
    
		first_classification fc = new first_classification(interactive_extraction,t);
		fc.run(t + "\\" + f + ".xml");	

	    }
	    catch (IOException ie) {
	    	System.out.println("Error: " + ie);
	    }
      
	}
    catch (Exception e) {
       System.out.println("Exception in class: execute_converter and method: constructor. " + e);	    		 	        	
    }
        

 			
	}
	
	public void build_dtd() {
	
		String dtd = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n" +
"<!ELEMENT pdf2xml (page+,line*,fontspec*)>\n" + 
"<!ELEMENT page (fontspec*, text*)>\n" +
"<!ATTLIST page\n" +
	"number CDATA #REQUIRED\n" +
	"position CDATA #REQUIRED\n" + 
	"top CDATA #REQUIRED\n" + 
	"left CDATA #REQUIRED\n" + 
	"height CDATA #REQUIRED\n" + 
	"width CDATA #REQUIRED\n" +
">\n" + 
"<!ELEMENT fontspec EMPTY>\n" + 
"<!ATTLIST fontspec\n" +
	"id CDATA #REQUIRED\n" + 
	"size CDATA #REQUIRED\n" + 
	"family CDATA #REQUIRED\n" + 
	"color CDATA #REQUIRED\n" + 
">\n" + 
"<!ELEMENT text (#PCDATA | b | i)*>\n" + 
"<!ATTLIST text\n" + 
	"top CDATA #REQUIRED\n" + 
	"left CDATA #REQUIRED\n" + 
	"width CDATA #REQUIRED\n" + 
	"height CDATA #REQUIRED\n" + 
	"font CDATA #REQUIRED\n" +
">\n" +
"<!ELEMENT b (#PCDATA)>\n" + 
"<!ELEMENT i (#PCDATA)>\n" + 
"<!ELEMENT line (text+)>\n" +
"<!ATTLIST line\n" +
	"typ CDATA #REQUIRED\n" + 
	"top CDATA #REQUIRED\n" + 
	"left CDATA #REQUIRED\n" + 
	"font CDATA #REQUIRED\n" + 
">";
try {
	this.osw.write(dtd,0,dtd.length());
	this.osw.close();
	this.fos.close();	
}
catch(IOException ie) {
	
}
catch (Exception e) {
   System.out.println("Exception in class: execute_converter and method: build_dtd. " + e);	    		 		
}
    }
}