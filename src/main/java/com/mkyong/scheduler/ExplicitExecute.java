package com.mkyong.scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ayantsoft.scheduler.dao.FollowUpDao;
import com.ayantsoft.scheduler.dao.FreePoolDao;
import com.ayantsoft.scheduler.hibernate.pojo.Freepool;
import com.ayantsoft.scheduler.util.FreePoolCandidatesDto;

public class ExplicitExecute {

	public static void main(String[] args){
		try{

			FollowUpDao followUpDao = new FollowUpDao();
			FreePoolDao freePoolDao = new FreePoolDao();

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -30);
			Date dateBefore30Days = cal.getTime();

			// second task

			List<FreePoolCandidatesDto> freePoolCandidates = followUpDao.findCandidatesForFreePool(dateBefore30Days);

			List<Freepool> totalCandidatesInFreepool = freePoolDao.getAllFreePoolCandidates();
			List<FreePoolCandidatesDto> removeCandidateList = new ArrayList<FreePoolCandidatesDto>();
			for(FreePoolCandidatesDto f:freePoolCandidates){
				for(Freepool fc:totalCandidatesInFreepool){
					if(f.getCandidateId() == fc.getCandidateId()){
						removeCandidateList.add(f);
					}
				}
			}

			freePoolCandidates.removeAll(removeCandidateList);
			
			System.out.println("SIZE OF ARRAYLIST: "+freePoolCandidates.size());


			if(freePoolCandidates != null && freePoolCandidates.size() >0){
				
				for(FreePoolCandidatesDto f:freePoolCandidates){

					Freepool free = new Freepool();
					free.setCandidateId(f.getCandidateId());
					free.setCandidateName(f.getCandidateName());
					free.setCandidateEmail(f.getCandidateEmail());
					free.setCandidateMobile(f.getCandidateMobile());
					free.setCurLocation(f.getCurrentLocation());
					free.setSkill(f.getCandidateCourse());
					free.setStatus(f.getCandidateRemarks());
					free.setVisa(f.getVisa());
					free.setRecruiterName(f.getRecruiterName());
					free.setRecruiterId(f.getRecruiterId());
					freePoolDao.save(free);
					
					System.out.println(f.getCandidateId()+"    DONE");
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
