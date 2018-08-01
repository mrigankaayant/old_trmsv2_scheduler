package com.ayantsoft.scheduler.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.ayantsoft.schedule.hibernate.util.HibernateUtil;
import com.ayantsoft.scheduler.util.FreePoolCandidatesDto;

public class FollowUpDao implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1386879089382774632L;
	
	
	
	public List<FreePoolCandidatesDto> findFreePoolCandidates(Date checkingDate){

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<FreePoolCandidatesDto> candidates = null;
		try{

			String hql="SELECT "
					+ "c.candidateId as candidateId, "
					+ "c.currentLocation as currentLocation, "
					+ "c.firstName as candidateName, "
					+ "c.preferedLocation as preferedLocation, " 
					+ "c.immigrationType as visa, "
					+ "r.statusType as candidateRemarks, "
					+ "r.id as candidateRemarksId, "
					+ "add.email as candidateEmail, "
					+ "add.workMobile as candidateMobile, "
					+ "course.course as candidateCourse, "
					+ "course.id as candidateCourseId, "
					+ "emp.name as recruiterName, "
					+ "emp.empId as recruiterId,"
					+ "max(f.followUpDate) as lastFollowupDate "
			
			        + "FROM "
			        
			        + "FollowUp f "
			        + "INNER JOIN f.candidate c " 
			        + "INNER JOIN c.candidateRemarks r "
			        + "INNER JOIN c.contactAddress add " 
			        + "INNER JOIN c.employee emp " 
			        + "LEFT OUTER JOIN c.candidateCourse course " 
			        
			        + "GROUP BY "
			        
			        + "c.candidateId "
			        + "HAVING "
			        
			        + "(max(f.followUpDate) < :checkingDate "
			        + "AND r.statusType = 'New Entry' ) "
			        
                    + "OR (max(f.followUpDate) < :checkingDate "
                    + "AND r.statusType = 'Partially Interested' ) "
                    
                    + "OR (max(f.followUpDate) < :checkingDate "
                    + "AND r.statusType = 'Interested' ) "
                    
                    + "OR (max(f.followUpDate) < :checkingDate "
                    + "AND r.statusType = 'Pipeline' ) "
                    
                    + "OR (max(f.followUpDate) < :checkingDate "
                    + "AND r.statusType = 'On Training' ) "
                    
                    + "OR (max(f.followUpDate) < :checkingDate "
                    + "AND r.statusType = 'On Bench' ) "
                    
                    + "OR (max(f.followUpDate) < :checkingDate "
                    + "AND r.statusType = 'On Project' ) "
                    
                    + "OR (max(f.followUpDate) < :checkingDate "
                    + "AND r.statusType = 'Awaited' ) "
			        
			        + "OR (max(f.followUpDate) < :checkingDate "
			        + "AND r.statusType = 'Not Responding' ) "
			        
			        + "OR (max(f.followUpDate) < :checkingDate "
			        + "AND r.statusType = 'Voicemail' ) "
			        
                    + "OR (r.statusType='Not Interested') ";
			
			Query query = session.createQuery(hql)
					.setResultTransformer(Transformers.aliasToBean(FreePoolCandidatesDto.class));

				query.setParameter("checkingDate", checkingDate);

			candidates = query.list();

		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return candidates;
	}
	
	
	
	public List<FreePoolCandidatesDto> findCandidatesForFreePool(Date checkingDate){

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<FreePoolCandidatesDto> candidates = null;
		try{

			String hql="SELECT "
					+ "c.candidateId as candidateId, "
					+ "c.currentLocation as currentLocation, "
					+ "c.firstName as candidateName, "
					+ "c.preferedLocation as preferedLocation, " 
					+ "c.immigrationType as visa, "
					+ "r.statusType as candidateRemarks, "
					+ "r.id as candidateRemarksId, "
					+ "add.email as candidateEmail, "
					+ "add.workMobile as candidateMobile, "
					+ "course.course as candidateCourse, "
					+ "course.id as candidateCourseId, "
					+ "emp.name as recruiterName, "
					+ "emp.empId as recruiterId,"
					+ "f.candidate as candidate "
			
			        + "FROM "
			        
			        + "FollowUp f "
			        + "INNER JOIN f.candidate c " 
			        + "INNER JOIN c.candidateRemarks r "
			        + "INNER JOIN c.contactAddress add " 
			        + "INNER JOIN c.employee emp " 
			        + "LEFT OUTER JOIN c.candidateCourse course " 
			        
			        + "GROUP BY "
			        
			        + "c.candidateId "
			        + "HAVING "
			        
			        + "(max(f.followUpDate) < :checkingDate "
			        + "AND r.statusType = 'New Entry' ) "
			        
			        + "OR (max(f.followUpDate) < :checkingDate "
			        + "AND r.statusType = 'Not Responding' ) "
			        
			        + "OR (max(f.followUpDate) < :checkingDate "
			        + "AND r.statusType = 'Voicemail' ) "
			        
                    + "OR (r.statusType='Not Interested') ";
			
			Query query = session.createQuery(hql)
					.setResultTransformer(Transformers.aliasToBean(FreePoolCandidatesDto.class));

				query.setParameter("checkingDate", checkingDate);

			candidates = query.list();

		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return candidates;
	}

	

}
