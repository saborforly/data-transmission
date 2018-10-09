package cn.ac.ihep.pmtfts.actions;

import java.util.List;

import gov.lbl.nest.jee.watching.WatcherDB;
import gov.lbl.nest.sentry.Context;
import gov.lbl.nest.sentry.SentryDB;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public  class DaqInfo {
	
//	@Inject  //ctrl+shift+o 导包   alt+ <--   --> 前进或后退到上次修改的地方    ctrl+鼠标悬停 看 定义和实现方法
//	@WatcherDB
//	@SentryDB
//	@Any
	public  EntityManager entityManager;
	
	public  EntityManager getEntityManager(){
		return entityManager;    
	}
	
	private Context context;
	
	private static final Logger LOG = LoggerFactory.getLogger(DaqInfo.class);
	
	public DaqInfo(Context context){
		this.context = context;
	}

	
	
	public List<Sfo_tz_file> getSfo_tz_file(int filenr){
		
		EntityManager entityManager = context.getInstance(EntityManager.class);
		
		LOG.info("DaqInfo.........");
		LOG.info("entityManager="+entityManager);
	
//		
    	String getByFirstName = "SELECT m FROM Sfo_tz_file m WHERE m.filenr = :filenr";
    	TypedQuery<Sfo_tz_file> query = entityManager.createQuery(getByFirstName, Sfo_tz_file.class);
    	query.setParameter("filenr", filenr);
    	//Sfo_tz_file last=(Sfo_tz_file)query.getSingleResult();
    	//final String lastDateTime11 = last11.getSfopfn();
    	
    	 List<Sfo_tz_file> last=query.getResultList();
		
		
		
		return last;
		
		
	}
	

}
