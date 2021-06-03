package com.qucai.sample.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.ProductMainDao;
import com.qucai.sample.entity.ProductMain;
import com.qucai.sample.service.ProductMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("ProductMainService")
@Transactional	
public class ProductMainServiceImpl implements ProductMainService {

    @Autowired
    private ProductMainDao productMainDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/

   @Override
   public int deleteByPrimaryKey(String t_Product_ID) {
       return productMainDao.deleteByPrimaryKey(t_Product_ID);
   }

    @Override
    public int insertSelective(ProductMain record) {
        return productMainDao.insertSelective(record);
    }

    @Override
    public ProductMain selectByPrimaryKey(String t_Product_ID) {
        return productMainDao.selectByPrimaryKey(t_Product_ID);
    }

    @Override
    public int updateByPrimaryKeySelective(ProductMain record) {
        return productMainDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ProductMain> findAllList(Map<String, Object> paramMap) {
        return productMainDao.findAllList(paramMap);
    }

    @Override
    public List<ProductMain> findSearchList(Map<String, Object> paramSearchMap) {
        return productMainDao.findSearchList(paramSearchMap);
    }

    @Override
    public PageInfo<ProductMain> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<ProductMain> list = productMainDao.findAllList(paramMap);
        return new PageInfo<ProductMain>(list);
    }

    @Override
    public PageInfo<ProductMain> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<ProductMain> list = productMainDao.findSearchList(paramSearchMap);
        return new PageInfo<ProductMain>(list);
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
    @Override  
     public boolean existFinanceProductName(String t_Product_ID, String t_Product_Name, Integer platform) {
     return productMainDao.existFinanceProductName(t_Product_ID, t_Product_Name, platform) == 1;
    }

}
