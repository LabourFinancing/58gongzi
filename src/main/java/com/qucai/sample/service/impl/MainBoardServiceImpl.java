//package com.qucai.sample.service.impl;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.qucai.sample.dao.FinanceProductDao;
//import com.qucai.sample.entity.FinanceProduct;
//import com.qucai.sample.service.FinanceProductService;
//
//@Service("MainBoardServicService")
//@Transactional	
//public class MainBoardServiceImpl implements MainBoardServicService {
//
//
//    @Autowired
//    private FinanceProductDao financeProductDao;
//
///*
//    @Autowired
//    private TrRoleResourceDao trRoleResourceDao;
//*/
//
//
//   @Override
//   public int deleteByPrimaryKey(String t_FProd_ID) {
//       return financeProductDao.deleteByPrimaryKey(t_FProd_ID);
//   }
//       
//    @Override
//    public int insertSelective(FinanceProduct record) {
//        return financeProductDao.insertSelective(record);
//    }
//
//    @Override
//    public FinanceProduct selectByPrimaryKey(String t_FProd_ID) {
//        return financeProductDao.selectByPrimaryKey(t_FProd_ID);
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(FinanceProduct record) {
//        return financeProductDao.updateByPrimaryKeySelective(record);
//    }
//
//    @Override
//    public List<FinanceProduct> findAllList(Map<String, Object> paramMap) {
//        return financeProductDao.findAllList(paramMap);
//    }
//    
//    @Override
//    public List<FinanceProduct> findSearchList(Map<String, Object> paramMap) {
//        return financeProductDao.findSearchList(paramMap);
//    }
//
//
//    /*
//    public List<FinanceProduct> findTreetableList(Map<String, Object> paramMap) {
//        List<FinanceProduct> rList = financeProductDao.findAllList(paramMap);
//        if(CollectionUtils.isEmpty(rList)) {
//        	return null;
//        }else {
//        	return assembleTreeList(initGroupMap(rList), "");
//        }
//    }
//    
//    @Override
//    public List<FinanceProductGrant> findGrantTreetableList(String roleId, Integer platform) {
//        List<FinanceProductGrant> rList = financeProductDao.findManagerFinanceProductGrantAllList(roleId, platform);
//        if(CollectionUtils.isEmpty(rList)) {
//        	return null;
//        }else {
//        	return assembleTreeList2(initGroupMap(rList), "");
//        }
//    }
//    
//    @Override
//    public List<Resource> findAuthResourceListByManagerId(String managerId) {
//        List<Resource> rList = resourceDao.findAuthResourceListByManagerId(managerId);
//        if(CollectionUtils.isEmpty(rList)) {
//        	return null;
//        }else {
//        	return assembleTreeList(initGroupMap(rList), "");
//        }
//    }
//    
//    private <T extends Resource> Map<String, List<T>> initGroupMap(List<T> rList){
//        Map<String, List<T>> map = new HashMap<String, List<T>>();
//        for (T r : rList) {
//            if (StringUtils.isBlank(r.getParentId())) {
//                if (map.containsKey("")) {
//                    map.get("").add(r);
//                } else {
//                    List<T> t = new ArrayList<T>();
//                    t.add(r);
//                    map.put("", t);
//                }
//                continue;
//            } else {
//                if (map.containsKey(r.getParentId())) {
//                    map.get(r.getParentId()).add(r);
//                } else {
//                    List<T> t = new ArrayList<T>();
//                    t.add(r);
//                    map.put(r.getParentId(), t);
//                }
//            }
//        }
//        return map;
//    }
//
//    private <T extends Resource> List<T> assembleTreeList(Map<String, List<T>> map, String key){
//        List<T> rs = new ArrayList<T>();
//        for(T r : map.get(key)){
//            rs.add(r);
//            if(map.containsKey(r.getId())){
//                rs.addAll(assembleTreeList(map, r.getId()));
//            }
//        }
//        return rs;
//    }
//    
//    private List<ResourceGrant> assembleTreeList2(Map<String, List<ResourceGrant>> map, String key){
//        List<ResourceGrant> rs = new ArrayList<ResourceGrant>();
//        for(ResourceGrant r : map.get(key)){
//            rs.add(r);
//            if(map.containsKey(r.getId())){
//                rs.addAll(assembleTreeList2(map, r.getId()));
//            } else {
//            	r.setIsLeaf(true);
//            }
//        }
//        return rs;
//    }
//*/
//    @Override  
//     public boolean existFinanceProductName(String t_FProd_ID, String t_FProd_Name, Integer platform) {
//     return financeProductDao.existFinanceProductName(t_FProd_ID, t_FProd_Name, platform) == 1;
//    }
//    
//}
