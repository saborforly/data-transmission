package cn.ac.ihep.pmtfts.actions;

import java.util.Date;

import gov.lbl.nest.common.watching.ChangedItem;

public class SyphonableFile implements ChangedItem {

	private String item;
	private Date time;
	public SyphonableFile(String item, Date time) {
		this.item=item;
		this.time=time;
		
		
	}

	@Override
	//right hand side
	public int compareTo(ChangedItem rhs) {
		return getWhenItemChanged().compareTo(rhs.getWhenItemChanged());
	
	}

	@Override
	public String getItem() {
		return item;
	}

	@Override
	public Date getWhenItemChanged() {
		return time;
	}

}
