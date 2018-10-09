//package cn.ac.ihep.spade.policy;
package cn.ac.ihep.spade.policy;
import gov.lbl.nest.spade.registry.Bundle;
import gov.lbl.nest.spade.registry.DropBox;
import gov.lbl.nest.spade.registry.LocalRegistration;
import gov.lbl.nest.spade.registry.Metadata;
import gov.lbl.nest.spade.registry.ParseException;
import gov.lbl.nest.spade.services.MetadataManager;
import gov.lbl.nest.spade.services.impl.MetadataManagerImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Priority;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Alternative
@Stateless
@Priority(value = 0)
public class Metadata3ManagerImpl extends MetadataManagerImpl implements MetadataManager
{
	protected Object convertFromMetadata(Metadata metadata)
	{
		return metadata;
	}
	private static final Logger LOG = LoggerFactory.getLogger(MetadataManagerImpl.class);
	
	public Metadata createMetadata(LocalRegistration registration, File dataFile, File semaphoreFile) throws FileNotFoundException, IOException
		{
		LOG.info("Successfully RUN Metadata3ManagerImpl \"");
		
		ReadFromSem dataLocator = new ReadFromSem();
		DropBox dropBox = registration.getDropBox();
		Bundle bundle = dataLocator.createBundle(dropBox.getBundleName(semaphoreFile.getPath()), dropBox
			     .getLocation(), semaphoreFile);      
		
		Metadata3Impl metadata = new Metadata3Impl(new Date(semaphoreFile.lastModified()),bundle.getLocation());
		
		return metadata;
		}


	public Metadata createMetadata(File file) throws ParseException,FileNotFoundException, IOException{
    	try{
    		JAXBContext content = JAXBContext.newInstance(new Class[] { Metadata3Impl.class });
			Unmarshaller unmarshaller = content.createUnmarshaller();
			return (Metadata3Impl)unmarshaller.unmarshal(file);
			}
			catch (JAXBException e) {
				throw new ParseException(e);
		    }
	}
  
}

