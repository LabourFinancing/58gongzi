package com.qucai.sample.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.FinanceProductDao;
import com.qucai.sample.dao.ManagerDao;
import com.qucai.sample.dao.PersonalInfoBatchUploadDao;
import com.qucai.sample.dao.PersonalInfoDao;
import com.qucai.sample.dao.ResourceDao;
import com.qucai.sample.dao.RoleDao;
import com.qucai.sample.dao.TrManagerRoleDao;
import com.qucai.sample.dao.TrRoleResourceDao;
import com.qucai.sample.entity.FinanceProduct;
import com.qucai.sample.entity.Manager;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.PersonalInfoBatchUpload;
import com.qucai.sample.entity.TrManagerRole;
import com.qucai.sample.service.PersonalInfoBatchUploadService;
import com.qucai.sample.service.PersonalInfoService;
import com.qucai.sample.util.PersonalInfoDataValidate;
import com.qucai.sample.util.excel.CellData;
import com.qucai.sample.util.excel.ExcelData;
import com.qucai.sample.util.excel.FileUtil;
import com.qucai.sample.util.excel.RowData;

//业务层实现类：
@Service("orgService")
@Transactional	
public class PersonalInfoBatchUploadServiceImpl implements PersonalInfoBatchUploadService {

  @Autowired
  private PersonalInfoBatchUploadDao personalInfoBatchUploadDao;
	
  @Autowired
  private ManagerDao managerDao;

  @Autowired
  private PersonalInfoDao personalInfoDao;
  
  @Autowired
  private FinanceProductDao financeProductDao;
  
  @Autowired
  private ResourceDao resourceDao;
  
  @Autowired
  private TrRoleResourceDao trRoleResourceDao;
  
  @Autowired
  private RoleDao roleDao;
  
  @Autowired
  private TrManagerRoleDao trManagerRoleDao;

  /*
      @Autowired
      private TrRoleResourceDao trRoleResourceDao;
  */
  
         
      @Override
      public int insertCustomerMachineByBatch(List<Map<String, Object>> cell) {
          return personalInfoBatchUploadDao.insertCustomerMachineByBatch(cell);
      }

      @Override
      public List<PersonalInfoBatchUpload>  duplicateDebitCardChk(String batch_PB_batchID) {
          return personalInfoBatchUploadDao.duplicateDebitCardChk(batch_PB_batchID);
      }
      
      @Override
      public List<PersonalInfoBatchUpload>  duplicateMobileChkTmanager(String batch_PB_batchID) {
          return personalInfoBatchUploadDao.duplicateMobileChkTmanager(batch_PB_batchID);
      }
      
      @Override
      public List<PersonalInfoBatchUpload>  duplicateMobileChkTperson(String batch_PB_batchID) {
          return personalInfoBatchUploadDao.duplicateMobileChkTperson(batch_PB_batchID);
      }
      
      
      @Override
      public List<PersonalInfoBatchUpload>  duplicatePIDChk(String batch_PB_batchID) {
          return personalInfoBatchUploadDao.duplicatePIDChk(batch_PB_batchID);
      }
      
      @Override
      public List<PersonalInfoBatchUpload>  checkDuplicateBatchUploadMobil(String batch_PB_batchID) {
          return personalInfoBatchUploadDao.checkDuplicateBatchUploadMobil(batch_PB_batchID);
      }
      
      @Override
      public List<PersonalInfoBatchUpload>  checkDuplicateBatchUploadDebitCard(String batch_PB_batchID) {
          return personalInfoBatchUploadDao.checkDuplicateBatchUploadDebitCard(batch_PB_batchID);
      }
      
      @Override
      public List<PersonalInfoBatchUpload>  checkDuplicateBatchUploadPID(String batch_PB_batchID) {
          return personalInfoBatchUploadDao.checkDuplicateBatchUploadPID(batch_PB_batchID);
      }
      
      @Override
      public int insertRoleTrManagerInfoTxn(Map<String, Object> paramMap){
    	  return personalInfoBatchUploadDao.insertRoleTrManagerInfoTxn(paramMap);
      }
      
      @Override
      public int insertRoleTrManagerInfoTxnview(Map<String, Object> paramMap){
    	  return personalInfoBatchUploadDao.insertRoleTrManagerInfoTxnview(paramMap);
      }
      
      @Override
      public int insertBatchPersonalInfo(Map<String, Object> paramMap){
    	  return personalInfoBatchUploadDao.insertBatchPersonalInfo(paramMap);
      }
	
    @Override
    public List<PersonalInfoBatchUpload> SelectCompanyBatch(Map<String, Object> paramSearchMap) {
        return personalInfoBatchUploadDao.SelectCompanyBatch(paramSearchMap);
    }
    
    @Override
    public List<PersonalInfoBatchUpload> SelectAllBatch(Map<String, Object> paramMap) {
        return personalInfoBatchUploadDao.SelectAllBatch(paramMap);
    }
    
    @Override
    public List<PersonalInfoBatchUpload> SelectAllBatchList(Map<String, Object> paramMap) {
        return personalInfoBatchUploadDao.SelectAllBatchList(paramMap);
    }
    
    @Override
    public int updateBatchPersonalStatusOpen(Map<String, Object> paramMap){
  	  return personalInfoBatchUploadDao.updateBatchPersonalStatusOpen(paramMap);
    }
    
    @Override
    public int updateBatchPersonalTxnClearing(Map<String, Object> paramMap){
  	  return personalInfoBatchUploadDao.updateBatchPersonalTxnClearing(paramMap);
    }
    
    @Override
    public int deleteByRefreshBatchPersonalCredit(Map<String, Object> paramMap){
  	  return personalInfoBatchUploadDao.deleteByRefreshBatchPersonalCredit(paramMap);
    }
    
    
    @Override
    public int updateBatchPersonalStatusClose(Map<String, Object> paramMap){
  	  return personalInfoBatchUploadDao.updateBatchPersonalStatusClose(paramMap);
    }
    
    @Override
    public int updateBatchPersonalInfo(Map<String, Object> paramMap){
  	  return personalInfoBatchUploadDao.updateBatchPersonalInfo(paramMap);
    }
    
    // @Override
    // public int deleteByPrimaryKey(String batch_PB_batchID) {
    //     return personalInfoBatchUploadDao.deleteByPrimaryKey(batch_PB_batchID);
    // }
    @Override
    public int deleteByPrimaryKey(String t_batch_perslUploadStatus_id) {
        return personalInfoBatchUploadDao.deleteByPrimaryKey(t_batch_perslUploadStatus_id);
    }
    
      /*
      public List<FinanceProduct> findTreetableList(Map<String, Object> paramMap) {
          List<FinanceProduct> rList = financeProductDao.findAllList(paramMap);
          if(CollectionUtils.isEmpty(rList)) {
          	return null;
          }else {
          	return assembleTreeList(initGroupMap(rList), "");
          }
      }
      
      @Override
      public List<FinanceProductGrant> findGrantTreetableList(String roleId, Integer platform) {
          List<FinanceProductGrant> rList = financeProductDao.findManagerFinanceProductGrantAllList(roleId, platform);
          if(CollectionUtils.isEmpty(rList)) {
          	return null;
          }else {
          	return assembleTreeList2(initGroupMap(rList), "");
          }
      }
      
      @Override
      public List<Resource> findAuthResourceListByManagerId(String managerId) {
          List<Resource> rList = resourceDao.findAuthResourceListByManagerId(managerId);
          if(CollectionUtils.isEmpty(rList)) {
          	return null;
          }else {
          	return assembleTreeList(initGroupMap(rList), "");
          }
      }
      
      private <T extends Resource> Map<String, List<T>> initGroupMap(List<T> rList){
          Map<String, List<T>> map = new HashMap<String, List<T>>();
          for (T r : rList) {
              if (StringUtils.isBlank(r.getParentId())) {
                  if (map.containsKey("")) {
                      map.get("").add(r);
                  } else {
                      List<T> t = new ArrayList<T>();
                      t.add(r);
                      map.put("", t);
                  }
                  continue;
              } else {
                  if (map.containsKey(r.getParentId())) {
                      map.get(r.getParentId()).add(r);
                  } else {
                      List<T> t = new ArrayList<T>();
                      t.add(r);
                      map.put(r.getParentId(), t);
                  }
              }
          }
          return map;
      }

      private <T extends Resource> List<T> assembleTreeList(Map<String, List<T>> map, String key){
          List<T> rs = new ArrayList<T>();
          for(T r : map.get(key)){
              rs.add(r);
              if(map.containsKey(r.getId())){
                  rs.addAll(assembleTreeList(map, r.getId()));
              }
          }
          return rs;
      }
      
      private List<ResourceGrant> assembleTreeList2(Map<String, List<ResourceGrant>> map, String key){
          List<ResourceGrant> rs = new ArrayList<ResourceGrant>();
          for(ResourceGrant r : map.get(key)){
              rs.add(r);
              if(map.containsKey(r.getId())){
                  rs.addAll(assembleTreeList2(map, r.getId()));
              } else {
              	r.setIsLeaf(true);
              }
          }
          return rs;
      }

  */


	  // download Excel files
	  // download Excel files
	  public ResponseEntity<byte[]> download(String path, String id, HttpServletRequest req) {
		  
		  System.out.println("【文件下载】 download --> 执行开始，请求文件相对路径：" + path);
	      File file = null;
	      try {
//	          Resource resource = new ClassPathResource(path);
//	          file = resource.getFile();
//	          file=new File("c:/1.xlsx");
	      	ServletContextResource personalInfoBatchUpload = new ServletContextResource(req.getServletContext(),"files/personalInfoBatchUpload/员工信息表.xlsx");
	          file = personalInfoBatchUpload.getFile();
//	      } catch (IOException e) {
	      } catch (Exception e) {
	    	  System.out.printf("【文件下载】 download -->执行异常，无法加载到服务端文件，请求文件相对路径：" + path, e);
	          return null;
	      }
//	      RedisClient.getInstance().put("progress_" + id, 10, 60 * 60);
	      String fileName = null;
	      try {
	          fileName = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
	      } catch (UnsupportedEncodingException e) {
	    	  System.out.printf("【文件下载】 download -->执行异常，无法解析服务端文件，请求文件相对路径：" + path, e);
	          return null;
	      }

//	      RedisClient.getInstance().put("progress_" + id, 20, 60 * 60);
	      HttpHeaders header = new HttpHeaders();
	      header.setContentDispositionFormData("attachment", fileName);
	      header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//	      RedisClient.getInstance().put("progress_" + id, 30, 60 * 60);
	      byte[] res = null;
	      try {
	          res = FileUtils.readFileToByteArray(file);
	      } catch (IOException e) {
	    	  System.out.printf("【文件下载】 download -->执行异常，解析服务端文件为字节数组异常，请求文件相对路径：" + path, e);
	          return null;
	      }
	      return new ResponseEntity<byte[]>(res, header, HttpStatus.CREATED);
	  }

	@Override
	public int insertManagerInfo(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}
}