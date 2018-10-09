package cn.ac.ihep.spade.policy;

import cn.ac.ihep.spade.policy.Metadata3Impl;
import gov.lbl.nest.spade.policy.FailedPolicyException;
import gov.lbl.nest.spade.policy.PlacingPolicy;
import gov.lbl.nest.spade.services.MetadataManager;
import gov.lbl.nest.spade.registry.Metadata;
import gov.lbl.nest.spade.registry.ParseException;
import gov.lbl.nest.spade.registry.UtcPlacement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PmtPlacing implements PlacingPolicy
{
	private MetadataManager metadataManager;
	private static final Logger LOG = LoggerFactory.getLogger(PlacingPolicy.class);
	


	public File getCompressedPlacement(String fileName, File metadataFile)throws FailedPolicyException{
		
		return new File(path(metadataFile), fileName);
		}

	public File getDataPlacement(String fileName, File metadataFile)throws FailedPolicyException{
		return new File(path(metadataFile), fileName);
			}
    public File getMetadataPlacement(File metadataFile) throws FailedPolicyException{
    	return new File(path(metadataFile), metadataFile.getName());
		}
			  
	public File getWrappedPlacement(String fileName, File metadataFile) throws FailedPolicyException{
		
		return new File(path(metadataFile), fileName);
			  }
			   
	private static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("Etc/UTC");
  
    private File path(final File metadataFile) throws FailedPolicyException{
    	Metadata3Impl pmtMetadata;
    	LOG.info("PmtPlacing before try");
    	
    	
    	
    	try {
            final Metadata metadata = metadataManager.createMetadata(metadataFile);
            pmtMetadata = (Metadata3Impl) metadata;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Metadata file can not be found");
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can not parse metadata file",
                                               e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Metadata file can not be read");
        }
    	String semPath=pmtMetadata.getPath();
    	semPath=semPath.substring(semPath.lastIndexOf(":")+1,semPath.length());
    	semPath=semPath.substring(1,semPath.lastIndexOf("/")+1);     	
    	semPath=semPath.substring(1,semPath.lastIndexOf("/")+1);        	
    	
    	File path;
    	path = new File(semPath);
        return path;
        /*
    	     try
    	     {
    	       Metadata metadata = metadataManager.createMetadata(metadataFile);
    	       UtcPlacement utcPlacement = (UtcPlacement)metadata;
    
    	Metadata metadata1 = metadataManager.createMetadata(metadataFile);
        pmtMetadata = (Metadata3Impl) metadata1;
    	     Calendar calendar = new GregorianCalendar(UTC_TIMEZONE);
    	     calendar.setTime(utcPlacement.getUtcDateTime());   
    	     File year = new File(String.format("%04d", new Object[] { new Integer(calendar
    	       .get(1)) }));
    	     LOG.info("year="+String.format("%04d", new Object[] { new Integer(calendar
    	    	       .get(1)) }));
    	  String semPath=pmtMetadata.getPath();
    	semPath=semPath.substring(semPath.lastIndexOf(":")+1,semPath.length());     	
    	semPath=semPath.substring(0,semPath.lastIndexOf("/")+1);        	
    	LOG.info(semPath);
    	File path;
    	path = new File(year,semPath);
    	     return path;
    	   }catch(Exception e){
    		         File f=null;
    		         LOG.info("PmtPlacing exception");
    	             return f;
    	             
    	}
    	*/
    	}
    	
    	      
    
 
    public void setMetadataManager(MetadataManager manager) {
        metadataManager = manager;
    }
 }
	   
