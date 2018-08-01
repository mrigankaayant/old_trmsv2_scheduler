package com.ayantsoft.scheduler.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ayantsoft.schedule.hibernate.util.HibernateUtil;
import com.ayantsoft.scheduler.hibernate.pojo.Employee;

public class EmployeeDao implements Serializable {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 6621882437435352919L;
	
	public List<Employee> getRecruiter(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Employee> employees = null;
		try{
			
			String hql = "SELECT emp "
					   + "FROM Employee emp "
					   + "INNER JOIN emp.userProfile up "
					   + "INNER JOIN up.roles r "
					   + "WHERE r.roleType = 'ROLE_RECRUITER' AND emp.active = 1 and emp.name != 'Mriganka Koley'";
			
			Query query = session.createQuery(hql);
			employees = query.list();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return employees;
	}

}
