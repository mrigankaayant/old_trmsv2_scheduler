package com.mkyong.scheduler;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ayantsoft.scheduler.dao.EmployeeDao;
import com.ayantsoft.scheduler.dao.FollowUpDao;
import com.ayantsoft.scheduler.dao.FreePoolDao;
import com.ayantsoft.scheduler.hibernate.pojo.Employee;
import com.ayantsoft.scheduler.hibernate.pojo.Freepool;
import com.ayantsoft.scheduler.mail.MailService;
import com.ayantsoft.scheduler.util.FreePoolCandidatesDto;

public class Test implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		// 0 0 0 1/1 * ? * Fire at mid night 12 am (noon) every day
		
		
		System.out.println("Schedule Started......................");
		
		try{
			File file = new File("/home/ayant1/UPLOAD_FILES/schedule/freepool_candidates.xlsx");
			if(file.exists()){
				file.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		FollowUpDao followUpDao = new FollowUpDao();
		EmployeeDao employeeDao = new EmployeeDao();
		FreePoolDao freePoolDao = new FreePoolDao();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -30);
		Date dateBefore30Days = cal.getTime();

		try{

			// first task
			
			List<FreePoolCandidatesDto> candidates = followUpDao.findFreePoolCandidates(dateBefore30Days);
			List<Employee> employees = employeeDao.getRecruiter();

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Free Pool Candidates");
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			CellStyle styleLastRow = workbook.createCellStyle();
			styleLastRow.setFillForegroundColor(IndexedColors.RED.getIndex());
			styleLastRow.setFillPattern(CellStyle.SOLID_FOREGROUND);

			Font cellfont = workbook.createFont();
			cellfont.setBold(true);
			style.setFont(cellfont);

			int rowCounter = 0;

			if(candidates != null && candidates.size()>0){

				for(Employee e:employees){

					XSSFRow row0=sheet.createRow(rowCounter);
					row0.setRowStyle(style);
					XSSFCell cell1;
					cell1=row0.createCell(3);
					cell1.setCellValue(e.getName());
					cell1.setCellStyle(style);
					rowCounter ++;
					XSSFRow row=sheet.createRow(rowCounter);
					Font font = workbook.createFont();
					font.setBold(true);
					
					CellStyle backgroundStyle = workbook.createCellStyle();
					backgroundStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
					backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					backgroundStyle.setFont(font);
					
					XSSFCell cell;
					cell=row.createCell(0);
					cell.setCellValue("COUNT");
					cell.setCellStyle(backgroundStyle);
					cell=row.createCell(1);
					cell.setCellValue("CANDIDATE ID");
					cell.setCellStyle(backgroundStyle);
					cell=row.createCell(2);
					cell.setCellValue("CANDIDATE NAME");
					cell.setCellStyle(backgroundStyle);
					cell=row.createCell(3);
					cell.setCellValue("EMAIL");
					cell.setCellStyle(backgroundStyle);
					cell=row.createCell(4);
					cell.setCellValue("PHONE NO");
					cell.setCellStyle(backgroundStyle);
					cell=row.createCell(5);
					cell.setCellValue("STATUS");
					cell.setCellStyle(backgroundStyle);
					cell=row.createCell(6);
					cell.setCellValue("LAST FOLLOW UP");
					cell.setCellStyle(backgroundStyle);
					cell=row.createCell(7);
					cell.setCellValue("RECRUITER NAME");
					cell.setCellStyle(backgroundStyle);
					
					int totalCount = 0;

					for(FreePoolCandidatesDto candi:candidates){

						if(e.getName().equals(candi.getRecruiterName())){
							totalCount++;
							rowCounter++;
							row=sheet.createRow(rowCounter);
							cell=row.createCell(0);
							cell.setCellStyle(backgroundStyle);
							cell.setCellValue(totalCount);
							cell=row.createCell(1);
							cell.setCellValue(candi.getCandidateId());
							cell=row.createCell(2);
							cell.setCellValue(candi.getCandidateName());
							cell=row.createCell(3);
							cell.setCellValue(candi.getCandidateEmail());
							cell=row.createCell(4);
							cell.setCellValue(candi.getCandidateMobile());
							cell=row.createCell(5);
							cell.setCellValue(candi.getCandidateRemarks());
							cell=row.createCell(6);
							DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy");
							String fDate = df.format(candi.getLastFollowupDate());
							cell.setCellValue(fDate);
							cell=row.createCell(7);
							cell.setCellValue(candi.getRecruiterName());
						}
					}

				}

				FileOutputStream out = new FileOutputStream(new File("/home/ayant1/UPLOAD_FILES/schedule/freepool_candidates.xlsx"));
				workbook.write(out);
				out.close();
				
				List<String> files = new ArrayList<String>();
				files.add("/home/ayant1/UPLOAD_FILES/schedule/freepool_candidates.xlsx");
				
				MailService mailService = new MailService();
				//mailService.setParameters("mrigankakoleyjob@gmail.com","1990aknagirmyelok","Free Pool Candidates List","Free pool Candidates","anayak@globalitexperts.com","mriganka@ayantsoft.com",files);
				mailService.setParameters("mrigankakoleyjob@gmail.com","1990aknagirmyelok","Free Pool Candidates List","Free pool Candidates","mrigankakoleyjob@gmail.com","mriganka@ayantsoft.com",files);
				Thread t1 = new Thread(mailService);
			    t1.start();
			}
			
			
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
		    long counter=0l;
			
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
					counter++;
					
				}
			}
			
			// Third task
			
			System.out.println("Completed.............................Total : "+counter);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
