package cn.ac.ihep.spade.policy;
//package cn.ac.ihep.pmtfts.spade.policy;
import gov.lbl.nest.spade.registry.Metadata;
import gov.lbl.nest.spade.registry.UtcPlacement;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="metadata")
public class PmtMetadata
   implements Metadata, UtcPlacement
{
  private Date whenEntered;
  private String path;
  protected PmtMetadata() {}
  protected PmtMetadata(Date dateTime,String filePath)
  {
    setWhenEntered(dateTime);
    setPath(filePath);
  }

  public Date getUtcDateTime()
  {
   return getWhenEntered();
  }
  @XmlElement
  public Date getWhenEntered()
  {
    return whenEntered;
  }
  protected void setWhenEntered(Date dateTime)
  {
    whenEntered = dateTime;
  }
  @XmlElement
  public String getPath()
  {
    return path;
  }
  protected void setPath(String filePath)
  {
    path = filePath;
  }
}