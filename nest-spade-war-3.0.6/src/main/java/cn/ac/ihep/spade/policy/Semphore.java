package cn.ac.ihep.spade.policy;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pmtfts_metadata")
public class Semphore {
	private String path;

	protected Semphore() {
		
	}

	public Semphore(String path) {
		this.path = path;
	}

	@XmlElement
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
