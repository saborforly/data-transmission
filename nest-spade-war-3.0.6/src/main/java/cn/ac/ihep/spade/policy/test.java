package cn.ac.ihep.spade.policy;

import gov.lbl.nest.spade.bpmn.tasks.ExaminerException;
import gov.lbl.nest.spade.policy.FailedPolicyException;
import gov.lbl.nest.spade.policy.PlacingPolicy;
import gov.lbl.nest.spade.registry.Metadata;
import gov.lbl.nest.spade.registry.ParseException;
import gov.lbl.nest.spade.registry.UtcPlacement;
import gov.lbl.nest.spade.services.MetadataManager;








import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;




import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="metadata")
 public class test implements PlacingPolicy
{
	//private static final Logger LOG = LoggerFactory.getLogger(test.class);
		//@XmlElement(name="path", required=true)
	    protected String path;
	    private static MetadataManager metadataManager;
	    protected test() {
			
		}



	    public String getPath()
	    {
	    	return path; 
	    	}
	    
	    public void setPath(String path) 
	    {
	    	this.path=path;
	    	}
	    
	    public test(String path) {
			this.path = path;
		}
	   
	   
	    
	    
	    
	   
	 public static void main(String args[]) throws FailedPolicyException, IOException, JAXBException,ExaminerException{
		 File metadataFile=new File("3.sem");
		 TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("Etc/UTC");
		 Metadata3Impl pmtMetadata;
		 Metadata3ManagerImpl metadataManager=new Metadata3ManagerImpl();
	    	try {
	    		
	        } catch (Throwable t)
	    	     {
	        	     throw new ExaminerException(t, metadataManager.getSuffix());
                 }
	    	Calendar calendar = new GregorianCalendar(UTC_TIMEZONE);
	    	calendar.setTime(pmtMetadata.getWhenEntered());
	    	//calendar.add(Calendar.HOUR, 8);
            //System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
	        System.out.println(String.format("%02d", new Object[] { new Integer(calendar.get(5)) }));
		 /*
		 try
		      { Metadata3ManagerImpl  pmtMetadata3=new Metadata3ManagerImpl();
		        metadataManager=pmtMetadata3;
		        Metadata metadata = metadataManager.createMetadata(metadataFile);
		        UtcPlacement utcPlacement = (UtcPlacement)metadata;
		       		 		
		        Calendar calendar = new GregorianCalendar(UTC_TIMEZONE);
                calendar.setTime(utcPlacement.getUtcDateTime());
                calendar.add(Calendar.HOUR, 14);
                System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		        System.out.println(String.format("%02d", new Object[] { new Integer(calendar.get(5)) }));
                
		        File year = new File(String.format("%04d", new Object[] { new Integer(calendar.get(1)) }));
		        } catch (FileNotFoundException e) { 
			    throw new IllegalArgumentException("Metadata file can not be found");
			    } catch (ParseException e) {
			    throw new IllegalArgumentException("Can not parse metadata file", e);
			    }
			    catch (IOException e) {
			    	throw new IllegalArgumentException("Metadata file can not be read"); }*/
	 }
	 public void setMetadataManager(MetadataManager manager) {
	        metadataManager = manager;
	    }



	@Override
	public File getCompressedPlacement(String paramString, File paramFile)
			throws FailedPolicyException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public File getDataPlacement(String paramString, File paramFile)
			throws FailedPolicyException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public File getMetadataPlacement(File paramFile)
			throws FailedPolicyException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public File getWrappedPlacement(String paramString, File paramFile)
			throws FailedPolicyException {
		// TODO Auto-generated method stub
		return null;
	}
	 }

