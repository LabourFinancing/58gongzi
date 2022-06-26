package com.qucai.sample.service.impl;

import com.qucai.sample.dao.*;
import com.qucai.sample.entity.PaymentBatchPayUpload;
import com.qucai.sample.service.PaymentBatchPayUploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

//业务层实现类：
@Service("PaymentBatchPayUploadService")
@Transactional	
public class PaymentBatchPayUploadServiceImpl implements PaymentBatchPayUploadService {

  @Autowired
  private PaymentBatchPayUploadDao paymentBatchPayUploadDao;
	
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
          return paymentBatchPayUploadDao.insertCustomerMachineByBatch(cell);
      }

      @Override
      public List<PaymentBatchPayUpload>  duplicateDebitCardChk(String batch_PB_batchID) {
          return paymentBatchPayUploadDao.duplicateDebitCardChk(batch_PB_batchID);
      }
      
      @Override
      public List<PaymentBatchPayUpload>  duplicateMobileChkTmanager(String batch_PB_batchID) {
          return paymentBatchPayUploadDao.duplicateMobileChkTmanager(batch_PB_batchID);
      }
      
      @Override
      public List<PaymentBatchPayUpload>  duplicateMobileChkTperson(String batch_PB_batchID) {
          return paymentBatchPayUploadDao.duplicateMobileChkTperson(batch_PB_batchID);
      }
      
      
      @Override
      public List<PaymentBatchPayUpload>  duplicatePIDChk(String batch_PB_batchID) {
          return paymentBatchPayUploadDao.duplicatePIDChk(batch_PB_batchID);
      }
      
      @Override
      public List<PaymentBatchPayUpload>  checkDuplicateBatchUploadMobil(String batch_PB_batchID) {
          return paymentBatchPayUploadDao.checkDuplicateBatchUploadMobil(batch_PB_batchID);
      }
      
      @Override
      public List<PaymentBatchPayUpload>  checkDuplicateBatchUploadDebitCard(String batch_PB_batchID) {
          return paymentBatchPayUploadDao.checkDuplicateBatchUploadDebitCard(batch_PB_batchID);
      }
      
      @Override
      public List<PaymentBatchPayUpload>  checkDuplicateBatchUploadPID(String batch_PB_batchID) {
          return paymentBatchPayUploadDao.checkDuplicateBatchUploadPID(batch_PB_batchID);
      }
      
      @Override
      public int insertRoleTrManagerInfoTxn(Map<String, Object> paramMap){
    	  return paymentBatchPayUploadDao.insertRoleTrManagerInfoTxn(paramMap);
      }
      
      @Override
      public int insertRoleTrManagerInfoTxnview(Map<String, Object> paramMap){
    	  return paymentBatchPayUploadDao.insertRoleTrManagerInfoTxnview(paramMap);
      }
      
      @Override
      public int insertBatchPersonalInfo(Map<String, Object> paramMap){
    	  return paymentBatchPayUploadDao.insertBatchPersonalInfo(paramMap);
      }
	
    @Override
    public List<PaymentBatchPayUpload> SelectCompanyBatch(Map<String, Object> paramSearchMap) {
        return paymentBatchPayUploadDao.SelectCompanyBatch(paramSearchMap);
    }
    
    @Override
    public List<PaymentBatchPayUpload> SelectAllBatch(Map<String, Object> paramMap) {
        return paymentBatchPayUploadDao.SelectAllBatch(paramMap);
    }
    
    @Override
    public List<PaymentBatchPayUpload> SelectAllBatchList(Map<String, Object> paramMap) {
        return paymentBatchPayUploadDao.SelectAllBatchList(paramMap);
    }
    
    @Override
    public int updateBatchPersonalStatusOpen(Map<String, Object> paramMap){
  	  return paymentBatchPayUploadDao.updateBatchPersonalStatusOpen(paramMap);
    }
    
    @Override
    public int updateBatchPersonalTxnClearing(Map<String, Object> paramMap){
  	  return paymentBatchPayUploadDao.updateBatchPersonalTxnClearing(paramMap);
    }
    
    @Override
    public int deleteByRefreshBatchPersonalCredit(Map<String, Object> paramMap){
  	  return paymentBatchPayUploadDao.deleteByRefreshBatchPersonalCredit(paramMap);
    }
    
    
    @Override
    public int updateBatchPersonalStatusClose(Map<String, Object> paramMap){
  	  return paymentBatchPayUploadDao.updateBatchPersonalStatusClose(paramMap);
    }
    
    @Override
    public int updateBatchPersonalInfo(Map<String, Object> paramMap){
  	  return paymentBatchPayUploadDao.updateBatchPersonalInfo(paramMap);
    }
    
    // @Override
    // public int deleteByPrimaryKey(String batch_PB_batchID) {
    //     return paymentBatchPayUploadDao.deleteByPrimaryKey(batch_PB_batchID);
    // }
    @Override
    public int deleteByPrimaryKey(String t_batch_perslUploadStatus_id) {
        return paymentBatchPayUploadDao.deleteByPrimaryKey(t_batch_perslUploadStatus_id);
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
	      	ServletContextResource paymentBatchPayUpload = new ServletContextResource(req.getServletContext(),"files/paymentBatchPayUpload/template.xlsx");
	          file = paymentBatchPayUpload.getFile();
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