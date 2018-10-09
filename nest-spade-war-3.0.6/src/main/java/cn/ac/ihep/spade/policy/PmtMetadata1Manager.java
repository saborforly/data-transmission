package cn.ac.ihep.spade.policy;



import gov.lbl.nest.spade.registry.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class PmtMetadata1Manager {
	public  PmtMetadata createMetadata(File file) throws ParseException, FileNotFoundException, IOException
	{
		return readMetaDataImpl(file);
		}

	private PmtMetadata readMetaDataImpl(File file)throws ParseException
	{
		 PmtMetadata pmtMetadata = null;  
		try
		{

			JAXBContext content = JAXBContext.newInstance(PmtMetadata.class );
			Unmarshaller unmarshaller = content.createUnmarshaller();
			pmtMetadata=(PmtMetadata)unmarshaller.unmarshal(file);
			return (PmtMetadata)unmarshaller.unmarshal(file);
			
		}
		catch (JAXBException e) {
		throw new ParseException(e);
		}
	}
}
