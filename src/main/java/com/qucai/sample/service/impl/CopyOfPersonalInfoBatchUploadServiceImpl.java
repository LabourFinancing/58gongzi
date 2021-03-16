//package com.qucai.sample.service.impl;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.support.ServletContextResource;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.qucai.sample.dao.FinanceProductDao;
//import com.qucai.sample.dao.ManagerDao;
//import com.qucai.sample.dao.PersonalInfoBatchUploadDao;
//import com.qucai.sample.dao.PersonalInfoDao;
//import com.qucai.sample.dao.ResourceDao;
//import com.qucai.sample.dao.RoleDao;
//import com.qucai.sample.dao.TrManagerRoleDao;
//import com.qucai.sample.dao.TrRoleResourceDao;
//import com.qucai.sample.entity.FinanceProduct;
//import com.qucai.sample.entity.Manager;
//import com.qucai.sample.entity.PersonalInfo;
//import com.qucai.sample.entity.PersonalInfoBatchUpload;
//import com.qucai.sample.entity.TrManagerRole;
//import com.qucai.sample.service.PersonalInfoBatchUploadService;
//import com.qucai.sample.util.PersonalInfoDataValidate;
//import com.qucai.sample.util.excel.CellData;
//import com.qucai.sample.util.excel.ExcelData;
//import com.qucai.sample.util.excel.FileUtil;
//import com.qucai.sample.util.excel.RowData;
//
////业务层实现类：
//@Service("orgService")
//@Transactional	
//public class CopyOfPersonalInfoBatchUploadServiceImpl implements PersonalInfoBatchUploadService {
//
//	@Autowired
//	private PersonalInfoBatchUploadDao personalInfoBatchUploadDao;
//	
//  @Autowired
//  private ManagerDao managerDao;
//
//  @Autowired
//  private PersonalInfoDao personalInfoDao;
//  
//  @Autowired
//  private FinanceProductDao financeProductDao;
//  
//  @Autowired
//  private ResourceDao resourceDao;
//  
//  @Autowired
//  private TrRoleResourceDao trRoleResourceDao;
//  
//  @Autowired
//  private RoleDao roleDao;
//  
//  @Autowired
//  private TrManagerRoleDao trManagerRoleDao;
//  
// 
//  
//  
//  // download Excel files
//  public ResponseEntity<byte[]> download(String path, String id, HttpServletRequest req) {
//	  
//	  System.out.println("【文件下载】 download --> 执行开始，请求文件相对路径：" + path);
//      File file = null;
//      try {
////          Resource resource = new ClassPathResource(path);
////          file = resource.getFile();
////          file=new File("c:/1.xlsx");
//      	ServletContextResource personalInfoBatchUpload = new ServletContextResource(req.getServletContext(),"files/personalInfoBatchUpload/员工信息表.xlsx");
//          file = personalInfoBatchUpload.getFile();
////      } catch (IOException e) {
//      } catch (Exception e) {
//    	  System.out.printf("【文件下载】 download -->执行异常，无法加载到服务端文件，请求文件相对路径：" + path, e);
//          return null;
//      }
////      RedisClient.getInstance().put("progress_" + id, 10, 60 * 60);
//      String fileName = null;
//      try {
//          fileName = new String(file.getName().getBytes("utf-8"), "ISO-8859-1");
//      } catch (UnsupportedEncodingException e) {
//    	  System.out.printf("【文件下载】 download -->执行异常，无法解析服务端文件，请求文件相对路径：" + path, e);
//          return null;
//      }
//
////      RedisClient.getInstance().put("progress_" + id, 20, 60 * 60);
//      HttpHeaders header = new HttpHeaders();
//      header.setContentDispositionFormData("attachment", fileName);
//      header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
////      RedisClient.getInstance().put("progress_" + id, 30, 60 * 60);
//      byte[] res = null;
//      try {
//          res = FileUtils.readFileToByteArray(file);
//      } catch (IOException e) {
//    	  System.out.printf("【文件下载】 download -->执行异常，解析服务端文件为字节数组异常，请求文件相对路径：" + path, e);
//          return null;
//      }
//      return new ResponseEntity<byte[]>(res, header, HttpStatus.CREATED);
//  }
//  
//	//*************************** 辅助方法一 *****************************************************************
//  /**
//   * 校验上传文件
//   * 参数：文件对象
//   *       组织id
//   *       部门id
//   */
//  private Map<String, Object> validUpload(File uploadFile, String orgId, String deptId) throws Exception{
//      Map<String, Object> map = new HashMap<String, Object>();
//      List<Manager> passed = null;
//      List<Manager> offed = null;
//      List<Manager> mobileList = managerDao.findAllList(map);//为了防止已注册用户再次注册
//      List<Manager> emailList = managerDao.findAllList(map);//  现在还是查询出所有的     ------需要优化标记
//      //获取上传的excel中的数据
//      //******辅助方法二*********
//      ExcelData uploaddata = getUploadUserData(uploadFile);//获取内存中解析好的excle数据对象
//      //校验上传数据
//      //******辅助方法三*********
//      Map<String, Object> dataMap = validUploadData(orgId, deptId, uploaddata, mobileList, emailList); //返回的是数据对象
//
//      return dataMap;
//}
//
//
////*************************** 辅助方法二 *****************************************************************
//  /**
//   * 获取上传文件的数据
//   * @param uploadFile
//   * @return
//   * @throws Exception
//   */
//  private ExcelData getUploadUserData(File uploadFile) throws Exception{
//      List<Manager> list = new ArrayList<Manager>();
//      String[] columnKey = {"userName", "mobile", "phone", "email", "QQ", "weixin", "job", "dept", "note"};
//      int startRow = 1;
//      ExcelData uploadData = FileUtil.parseExcelFile(uploadFile, startRow, columnKey);//将要抛出异常
//      return uploadData;
//  }
//
////*************************** 检验方法 *****************************************************************
///**
// *   方法说明：
// *              校验上传数据
// *
// *
// * @param orgId     其应该所属的组织
// * @param deptId    所选的部门id
// * @param excelData excel表格数据对象
// * @param mobileList   所有的手机号码 集合   用来判断手机是否注册
// * @param emailList    所有的邮箱号码 集合
// * @return
// */
//private  Map<String, Object> validUploadData(String orgId, String deptId,ExcelData excelData, List<Manager> mobileList, List<Manager> emailList) {
//    Map<String, Object> map = new HashMap<String, Object>();
//    List<RowData> passed = new ArrayList<RowData>();
//    List<Manager> passedUsers = new ArrayList<Manager>();  // 通过验证的  到 m_user
//    List<PersonalInfo> passedOrgUsers = new ArrayList<PersonalInfo>(); //通过验证的   到m_org_user
//    List<RowData> offed = new ArrayList<RowData>();
//    List<RowData> rows = null;//  所有数据
//    PersonalInfo personalInfo = null;
//    Manager manager = null;
//    
//    Date createTime = new Date();//创建时间
//    Date updateTime = new Date();  //更新时间
//    FinanceProduct deptList = null;  //当前组织的所有部门的   list集合
//    Map<String, FinanceProduct> deptMap = new HashMap<String, FinanceProduct>();
//    if(null != excelData && null != excelData.getRows() && !excelData.getRows().isEmpty()){  //如果传入对象不为空
//        rows = excelData.getRows();  //获取对象中的所有数据   类型应该是List集合 每个元素应该是一行数据 即：RowData
//        map.put("DATA_SIZE", rows.size());// 设置总数据有多少条
//        List<String> excelMobiles = new ArrayList<String>();  //用于存放excle表格中的电话号码
//        List<String> excelEmails = new ArrayList<String>(); //用于存放excle表格中的邮箱号码
////        deptList = financeProductDao.(t_FProd_ID);//获取所有部门？
////        for(Dept dept:deptList){
////            String deptName = dept.getDeptName();
////            deptMap.put(deptName, dept);//转成map了
////        }
////rowloop:     //行循环跳出坐标准备
//        for (int i = 0; i < rows.size(); i++) {//循环便利数据
//            PersonalInfo orgUser = new PersonalInfo();  //组织用户 实例化对象准备
//            Manager user = new Manager();  //用户 POJO准备
//            //获取行数据
//            RowData r = rows.get(i);  //  获取行数据
//            int rowIndex = r.getRowIndex();  //  获取当前行是第几行
//            List<CellData> cells = r.getCells();  //获取当前行的所有数据  cell 的s
//            boolean flag = true;
//            String userName="",mobile="",phone="",email="",qq="",weixin="",job="";
//            int mIndex = 0;
//            int eIndex = 0;
//columnloop: //列循环跳出坐标准备
//            for (int j = 0; j < cells.size(); j++) {  // 每一行单元格数据 遍历
//                CellData c = cells.get(j);  //获取出当前的 数据独立单元格
//                String key = c.getKey();  //属于哪一列？
//                String cellValue = c.getCellValue(); //值
//                if("userName".equals(key)){
//                    userName = cellValue;
//                    if(org.apache.commons.lang.StringUtils.isBlank(cellValue)){
//                        flag = false;
//                        c.setPassed(0);
//                        c.setExtraInfo("用户姓名不能为空");
//                        continue columnloop;
//                    }
//               	    manager.setRealName(cellValue);
//               	    personalInfo.setT_P_Name(cellValue);
//                } else if("mobile".equals(key)){  //手机相关验证
//                    mIndex = j;
//                    mobile = cellValue;
//                    if(!StringUtils.isBlank(cellValue)){
//                        if(!PersonalInfoDataValidate.isMobile(cellValue)){ //校验手机格式
//                            flag = false;
//                            c.setPassed(0);
//                            c.setExtraInfo("不正确的手机号");
//                            continue columnloop;
//                        }
//
//                        if(mobileList.contains(cellValue.trim())){// 比对数据库中的  是否已被注册
//                            flag = false;
//                            c.setPassed(0);
//                            c.setExtraInfo("该手机号已经被使用");
//                            continue columnloop;
//                        }
//                        if(excelMobiles.contains(cellValue.trim())){ // 当前表格 数据有重复
//                            flag = false;
//                            c.setPassed(0);
//                            c.setExtraInfo("重复的手机号码");
//                            continue columnloop;
//                        }
//                        manager.setUserName(cellValue);
//                    }
//                    manager.setMobile(cellValue);
//                } else if("phone".equals(key)){ //暂无
//                    phone = cellValue;
//                    if(!StringUtils.isBlank(cellValue)){
//                        //                            if(!PersonalInfoDataValidate.isPhone(cellValue)){
//                        //                                flag = false;
//                        //                                c.setPassed(0);
//                        //                                c.setExtraInfo("不正确的电话号");
//                        //                                continue columnloop;
//                        //                            }
//                    }
//                    manager.setMobile(cellValue);
//                } else if("email".equals(key)){  // 邮箱相关验证
//                    eIndex = j;
//                    email = cellValue;
//                    if(!StringUtils.isBlank(cellValue)){
//                        if(!PersonalInfoDataValidate.isEmail(cellValue)){
//                            flag = false;
//                            c.setPassed(0);
//                            c.setExtraInfo("邮箱格式不正确");
//                            continue columnloop;
//                        }
//                        if(emailList.contains(cellValue.trim())){
//                            flag = false;
//                            c.setPassed(0);
//                            c.setExtraInfo("该邮箱已经被使用");
//                            continue columnloop;
//                        }
//                        if(excelMobiles.contains(cellValue.trim())){
//                            flag = false;
//                            c.setPassed(0);
//                            c.setExtraInfo("重复的邮箱");
//                            continue columnloop;
//                        }
//                        manager.setUserName(cellValue);
//                    }
//                    manager.setMobile(cellValue);                 
//                } else if("QQ".equals(key)){
//                    qq = cellValue;
//                    manager.setTelephone(cellValue);   
//                } else if("weixin".equals(key)){
//                    weixin = cellValue;
//                    manager.setRealName(cellValue);
//                } else if("job".equals(key)){
//                    job = cellValue;
//                    manager.setCompany_name(cellValue);
//                    //暂无
//                } else if("note".equals(key)){
////                    manager.setNote(cellValue);
//                } else if("dept".equals(key)) {
//                    if(!StringUtils.isBlank(cellValue) && null!=deptMap.get(cellValue.trim())){
//                        FinanceProduct d = deptMap.get(cellValue.trim());
//                        personalInfo.setT_P_Probation(d.getT_FProd_PersPool());
//                    } else {
////                        personalInfo.setDeptId(deptId);
//                    }
//                } else {
//                    //暂无
//                }
//            }
//            //校验手机与邮箱是否同时为空
//            if(StringUtils.isBlank(mobile) && StringUtils.isBlank(email)){
//                flag = false;
//                CellData mobileCell = cells.get(mIndex);//所属的 行和列
//                CellData emailCell = cells.get(eIndex);
//                mobileCell.setPassed(0);  //设置是否通过了校验的标识   注：此标识是对单元格数据进行设置的
//                mobileCell.setExtraInfo("手机与邮箱不能同时为空"); //没有通过校验  进行
//                emailCell.setPassed(0);
//                emailCell.setExtraInfo("手机与邮箱不能同时为空");
//            }
//            if(flag){  //验证通过的话
//                //初始化user 和 orgUser对象
////                manager.setDefaultOrgId(orgId);
////                manager.setMultiLogin(0);
////                manager.setIsDistributor(0);
////                manager.setSrcOrg(orgId);
////                manager.setMobileBinded(0);
////                manager.setEmailBinded(0);
////                manager.setUtype(0);
////                manager.setUpdateTime(updateTime);
////                manager.setCreateTime(createTime);
//////                manager.setDel(Sys.UN_STOPED);
////                manager.setType(2);
////                manager.setSource(1);
////                manager.setIspremiumuser(true);
//                //                    manager.setNote("上传生成用户");
//                manager.setPassword("123456");
//
//                personalInfo.setT_P_Probation(orgId);
//                //                    personalInfo.setDeptId(deptId);
//                personalInfo.setCreate_time(createTime);
//                personalInfo.setModify_time(updateTime);
////                personalInfo.setDel(Sys.UN_STOPED);
////                personalInfo.setState(1);
////                personalInfo.setIsDataCommissioner(0);
////                personalInfo.setIsMarketCommissioner(0);
//                //向通过list里添加数据
//                passedUsers.add(user);  //添加到通过的  数据列表中去
//                passedOrgUsers.add(orgUser);
//                if(!StringUtils.isBlank(mobile)){
//                    excelMobiles.add(mobile);  //添加到 准备的 list中去  以防下面重复数据   在上面验证
//                }
//                if(!StringUtils.isBlank(email)){
//                    excelEmails.add(email);
//                }
//            } else {
//                offed.add(r);
//            }
//
//        }
//    }
//    map.put("PASSED_USERS", passedUsers); //
//    map.put("PASSED_ORGUSERS", passedOrgUsers);
//    map.put("OFFED_ROW", offed);
//    return map;
//}
// 
//
//  /**  批量导入 业务方法  **/
//  public Map<String, Object> uploadOrgUser(String targetPath, String orgId, String deptId, String password, MultipartFile upFile) {
//      Map<String,Object> rm = new HashMap<String,Object>();
//      PersonalInfoBatchUpload personalInfoBatchUpload = null;
//      String flag ="failure";
//      String msg = "上传失败";
//      File f = new File(targetPath); //实例硬盘中文件夹（路径）对象
//      if(!f.exists()){//判断此路径/文件夹是否存在
//          f.mkdirs(); //如果不存在  则创建文件夹/目录
//      }
//      String originalName = upFile.getOriginalFilename();//获取文件对象原始文件名
//      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//      String tag = sdf.format(new Date());
//      String upFileName = targetPath + File.separator+tag+"_"+originalName;// 拼接出文件的要存储的位置（全路径）
//      File file = new File(upFileName);//创建 内存中的File对象
//      if(file.exists()){ //判断是否存在
//          file.delete();//如果有重名文件存在  就删除文件
//          // 这个对象对应的硬盘必须删  不能存在  如果已经存在 则会抛出
//          // IOException异常
//      }
//      List<PersonalInfo> orgUsers = null;
//      List<Manager> users = null;
//      List<RowData> offedData = null;  //List 集合对象准备
//      try{
//          upFile.transferTo(file);//转存文件  写入硬盘  //这个  本质还是一样的打开流传文件  需要注意 file对应的硬盘中的文件不能存在 需要删除  否则会抛出 文件已经存在且不能删除 异常
//          // 校验上传数据
//          /**  辅助方法一 **/
//      Map<String,Object> validData = validUpload(file,orgId,deptId);// 校验数据    分类 返回 map形式
//      users = (List<Manager>) validData.get("PASSED_USERS");  //通过的user  是向 m_user 表的
//      orgUsers = (List<PersonalInfo>) validData.get("PASSED_ORGUSERS"); //  是向 m_org_user 表的
//      offedData = (List<RowData>) validData.get("OFFED_ROW");    //？
//      int rowNum = (Integer) validData.get("DATA_SIZE");   //  excle 数据总长度
//      rm.put("ROW_COUNT", rowNum);//业务类的总长度
//
//      List<FinanceProduct> ous = financeProductDao.findAllList(validData);//获取组织的所有用户
//      TrManagerRole sa = new TrManagerRole(); //权限对象
////      sa.setOrgId(orgId);  //  设置组织id
////      sa.setServiceCode(Sys.GROUP_ORG); //设置服务编码  多用户版基础服务
////      sa.setType(Sys.TYPE_BUY); //设置 类型 为购买类型
////      ServiceAuth nSa = authDao.getOrgServiceAuthInfo(sa); //获取组织服务等级 详细信息
//      int actSize = ous.size(); // 当前组织已有用户的总长度
////      int license = nSa.getLicense(); // 组织上限人数
//      int license = 100;
//      int totalNum = 0;  //设置总数为 0
//
////      Org o = orgDao.getOrgById(orgId);  //获取组织对象
////      Date duration = DateFormatter.stringToDate(o.getDuration(), "yyyy-MM-dd"); //获取服务到期时间
//      //上数据库插入数据
//      if(null!=users && !users.isEmpty()){
//          totalNum = actSize + users.size();  //总数现在等于 添加人数和已有人数
//          if(totalNum < license){//上传人数和原有人数之和小于组织服务人数上限
//              for(int i=0; i<users.size(); i++){
//                  Manager u = users.get(i);
//                  u.setPassword(password);
//                  PersonalInfo ou = orgUsers.get(i);
//                  managerDao.insertSelective(u);
//                  personalInfoBatchUploadDao.insertSelective(personalInfoBatchUpload);
//                  //添加到微信企业号
////                  addCpUser(u);
//                  //添加个人空间
//                  PersonalInfo selfOrg = new PersonalInfo();
//                  personalInfoBatchUpload.setBatch_PB_Name(u.getUserName());
////                  selfOrg.setType(Sys.ORG_TYPE_PER);
////                  selfOrg.setState(Sys.ORG_VERIFY_1);
////                  selfOrg.setAdminId(u.getUserId());
////                  selfOrg.setAdminName(u.getUserName());
////                  selfOrg.setDuration(duration);
////                  selfOrg.setDel(Sys.UN_STOPED);
////                  selfOrg.setCreateTime(new Date());
////                  selfOrg.setUpdateTime(new Date());
////                  selfOrg.setIdparent(0);
////                  orgDao.addOrg(selfOrg);
////                  Dept d = new Dept();
////                  d.setDeptId(0);
////                  addOrgUserRelation(selfOrg, d, u);
////                  if(null!=u.getUserId() && u.getUserName().equals(ou.getOrgUserName())){
////                      ou.setUserId(u.getUserId());
////                      orgUserDao.addOrgUser(ou);
////                  }
//              }
//              rm.put("PASSED_COUNT", users.size());//成功数据
//          } else {
//              rm.put("ORG_LICENSE", license);  //上限
//              rm.put("ORG_ACTSIZE", actSize);  //
//              rm.put("OVER_LICENSE", totalNum - license);
//          }
//
//      }
//      int offedCount = 0;
//      if(null!= offedData && !offedData.isEmpty()){
//          offedCount = offedData.size();
//          rm.put("OFFED_DATA", offedData);
//      }
//      rm.put("OFFED_COUNT", offedCount);
//
//      flag = "success";
//      msg = "上传成功";
//      }  catch (Exception e2) {
//          System.out.println("Exception while uploadOrgUser");
//          System.out.println(e2);
//      }
//      rm.put("flag", flag);
//      rm.put("msg", msg);
//
//      return rm;
//
//  }
//
//
//@Override
//public PersonalInfoBatchUpload selectByPrimaryKey(String batch_PB_company) {
//	// TODO Auto-generated method stub
//	return personalInfoBatchUploadDao.selectByPrimaryKey(batch_PB_company);
//}
//
//
//@Override
//public int insertSelective(PersonalInfoBatchUpload record) {
//	// TODO Auto-generated method stub
//	return 0;
//}
//
//
//}