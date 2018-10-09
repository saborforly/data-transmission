package cn.ac.ihep.pmtfts.actions;
import gov.lbl.nest.jee.watching.SeenItem;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

@Entity
@Table(name = "sfo_tz_file")
//@NamedQueries({@javax.persistence.NamedQuery(name="getSfo_tz_file", query="SELECT m FROM sfo_tz_file m WHERE 1==1 ")})
public class Sfo_tz_file {
	private String lfn;
	private int filenr;
	private String sfohost;
	private int runnr;
	private String streamtype;
	private String stream;
	private String transferstate;
	private String filestate;
	private String filehealth;
	private String tzstate;
	private int filesize;
	private String guid;
	private String checksum;
	private String pfn;
	private String sfopfn;
	private int nrevents;
	private Date t_stamp;
	private Date opentime;
	private Date endTime;
	private int file_seqnum;
    //LastWatched(String role, Date dateTime)
    protected Sfo_tz_file() {}
    
	
	
	
	@Id
    @GeneratedValue
	public String getLfn()
	{
		return lfn;
	}
	
	@Column
	public int getFilenr()
	{
		return filenr;
	}
	
	@Column
	public String getSfohost()
	{
		return sfohost;
	}
	
	@Column
	public int getRunnr()
	{
		return runnr;
	}
	
	@Column
	public String getStreamtype()
	{
		return streamtype;
	}
	
	@Column
	public String getStream()
	{
		return stream;
	}
	
	@Column
	public String getTransferstate()
	{
		return transferstate;
	}
	
	@Column
	public String getFilestate()
	{
		return filestate;
	}
	@Column
	public String getFilehealth()
	{
		return filehealth;
	}
	@Column
	public String getTzstate()
	{
		return tzstate;
	}
	@Column
	public int getFilesize()
	{
		return filesize;
	}
	
	@Column
	public String getGuid()
	{
		return guid;
	}
	
	@Column
	public String getChecksum()
	{
		return checksum;
	}
	@Column
	public String getPfn()
	{
		return pfn;
	}
	@Column
	public String getSfopfn()
	{
		return sfopfn;
	}
	@Column
	public int getNrevents()
	{
		return nrevents;
	}
	@Column
	public Date getT_stamp()
	{
		return t_stamp;
	}
	@Column
	public Date getOpentime()
	{
		return opentime;
	}
	
	@Column
	public Date getEndtime()
	{
		return endTime;
	}
	
	@Column
	public int getFile_seqnum()
	{
		return file_seqnum;
	}
	
	
	
	
    protected void setLfn(String lfn)
   	{
       	this.lfn = lfn;
   	}
  
    protected void setFilenr(int filenr)
	{
		this.filenr= filenr;
	}
	
    protected void setSfohost(String sfohost)
	{
		this.sfohost=sfohost;
	}
	
    protected void setRunnr(int runnr)
	{
    	this.runnr=runnr;
	}
	
    protected void setStreamtype(String streamtype)
	{
    	this.streamtype=streamtype;
	}
	
    protected void setStream(String stream)
	{
    	this.stream=stream;
	}
	
    protected void setTransferstate(String transferstate)
	{
    	this.transferstate=transferstate;
	}
	
    protected void setFilestate(String filestate)
	{
    	this.filestate=filestate;
	}
    protected void setFilehealth(String filehealth)
	{
    	this.filehealth=filehealth;
	}
    protected void setTzstate(String tzstate)
	{
    	this.tzstate=tzstate;
	}
    protected void setFilesize(int filesize)
	{
    	this.filesize=filesize;
	}
	
    protected void setGuid(String guid)
	{
    	this.guid=guid;
	}
	
    protected void setChecksum(String checksum)
	{
    	this.checksum=checksum;
	}
    protected void setPfn(String pfn)
	{
    	this.pfn=pfn;
	}
    protected void setSfopfn(String sfopfn)
	{
    	this.sfopfn=sfopfn;
	}
    protected void setNrevents(int nrevents)
	{
    	this.nrevents=nrevents;
	}
    protected void setT_stamp(Date t_stamp)
	{
    	this.t_stamp=t_stamp;
	}
    protected void setOpentime(Date opentime)
	{
    	this.opentime=opentime;
	}
	
    protected void setEndtime(Date endTime)
	{
    	this.endTime=endTime;
	}
	
    protected void setFile_seqnum(int file_seqnum)
	{
    	this.file_seqnum=file_seqnum;
	}

  
}
