package cn.ac.ihep.spade.policy;

import cn.ac.ihep.spade.policy.Metadata3Impl;
import gov.lbl.nest.spade.policy.FailedPolicyException;
import gov.lbl.nest.spade.policy.PlacingPolicy;
import gov.lbl.nest.spade.services.MetadataManager;
import gov.lbl.nest.spade.registry.Metadata;
import gov.lbl.nest.spade.registry.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeijingPlacing implements PlacingPolicy
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
    	Calendar calendar = new GregorianCalendar(UTC_TIMEZONE);
    	calendar.setTime(pmtMetadata.getWhenEntered());
    	calendar.add(Calendar.HOUR, 8);
        File year = new File(String.format("%04d", new Object[] { new Integer(calendar
          .get(1)) }));
        
    	File month = new File(year, String.format("%02d", new Object[] { new Integer(calendar.get(2) + 1) }));
     
    	File day = new File(month, String.format("%02d", new Object[] { new Integer(calendar.get(5)) }));
    	return day;
    	
 
        }
    	      
    
 
    public void setMetadataManager(MetadataManager manager) {
        metadataManager = manager;
    }
 }
	   
