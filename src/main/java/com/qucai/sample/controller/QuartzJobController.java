//package com.qucai.sample.controller;
//
//import org.quartz.Job;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
// 
// 
// 
//@Service
//@Controller
//public class QuartzJobController implements Job {
//	
// 
//    private static final Logger logger= LoggerFactory.getLogger(QuartzJobTest.class);
// 
//    @Autowired
//    private QuartzJobService quartzJobService;
// 
// 
//    /**
//     * 执行 job
//     * @param jobExecutionContext
//     * @throws JobExecutionException
//     */
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        try{
//            JobDataMap map =   jobExecutionContext.getJobDetail().getJobDataMap();
//            String payCompanyCode= (String) map.get("payCompanyCode");
// 
//            PayCompany payCompany = quartzJobService.getCompanyByCode(payCompanyCode);
// 
//        }catch (Exception e){
//            logger.error("QuartzJob execute error",e);
//        }
//    }
// 
//}//package com.qucai.sample.controller;
//
//import org.quartz.Job;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
// 
// 
// 
//@Service
//@Controller
//public class QuartzJobController implements Job {
//	
// 
//    private static final Logger logger= LoggerFactory.getLogger(QuartzJobTest.class);
// 
//    @Autowired
//    private QuartzJobService quartzJobService;
// 
// 
//    /**
//     * 执行 job
//     * @param jobExecutionContext
//     * @throws JobExecutionException
//     */
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        try{
//            JobDataMap map =   jobExecutionContext.getJobDetail().getJobDataMap();
//            String payCompanyCode= (String) map.get("payCompanyCode");
// 
//            PayCompany payCompany = quartzJobService.getCompanyByCode(payCompanyCode);
// 
//        }catch (Exception e){
//            logger.error("QuartzJob execute error",e);
//        }
//    }
// 
//}