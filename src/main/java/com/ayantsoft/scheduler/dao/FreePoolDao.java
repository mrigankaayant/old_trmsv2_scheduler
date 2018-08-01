package com.ayantsoft.scheduler.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.ayantsoft.schedule.hibernate.util.HibernateUtil;
import com.ayantsoft.scheduler.hibernate.pojo.Freepool;

public class FreePoolDao implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -818190608150507697L;
	
	
	public List<Freepool> getAllFreePoolCandidates(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Freepool> candidates = null;
		try{
			String hql = "FROM Freepool f ";
			Query query = session.createQuery(hql);
			candidates = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return candidates;
	}
	
	
	public void save(Freepool freePool)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			session.saveOrUpdate(freePool);
			session.getTransaction().commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new HibernateException("Data save Exception");
		}finally{
			session.close();
		}
	}
}
