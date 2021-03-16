/*
package com.qucai.sample.job

import com.qucai.sample.entity.Manager
import com.qucai.sample.entity.PersonalInfo
import com.qucai.sample.service.ManagerService
import com.qucai.sample.service.PersonalInfoService
import com.qucai.sample.util.Tool
import it.sauronsoftware.ftp4j.FTPClient
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils.getBaseName
import org.apache.commons.lang3.math.NumberUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import unzip.TripleDes
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

@Service
@Lazy(false)
class UserSyncJob {

    @Autowired
    private val managerService: ManagerService? = null

    @Autowired
    private val personalInfoService: PersonalInfoService? = null

    @Scheduled(cron = "0 0 1 * * ?")
    @Throws(Exception::class)
    fun syncUsersFromFTP() {
        logger.info("Sync Users from FTP start：" + System.currentTimeMillis())

        val client = FTPClient()

        client.isPassive = false
        client.connect(FTP_HOST, FTP_PORT)
        client.login(FTP_USERNAME, FTP_PASSWORD)
        client.changeDirectory(FTP_ROOT_PATH)

        val today = Date()
        val dateStr = SimpleDateFormat("yyyyMMdd").format(today)
        val pattern = FILE_PREFIX + dateStr + "_*.zip"
        val files = client.list(pattern)

        files.forEach { file ->
            val zipFilename = file.name
            val basename = getBaseName(zipFilename)
            val unzipFilename = "$basename.txt"

            val respBasename = FILE_PREFIX_RESP + basename
            val respUnzipFilename = FILE_PREFIX_RESP + unzipFilename
            val respZipFilename = FILE_PREFIX_RESP + zipFilename

            client.download(zipFilename, File(zipFilename))
            TripleDes.makeUnZipfile(FILE_TREE_DES_KEY, zipFilename, unzipFilename)

            PrintWriter(FileWriter(File(respUnzipFilename))).use { out ->
                val content = FileUtils.readFileToString(File(unzipFilename), "UTF-8")
                val lines = content.split('\n').dropLastWhile { it.isEmpty() }

                lines.forEach { line ->
                    val fields = line.split(FILE_SEP)
                    val workNum = fields[0]         //工号
                    val realName = fields[1]        //姓名
                    val userName = fields[2]        //用户名
                    val password = fields[3]        //密码
                    //val departmentName = fields[4]  //部门
                    //val jobTitle = fields[5]        //岗位
                    val mobile = fields[6]          //手机号码
                    val companyName = fields[7]     //公司名(发工资)
                    val clientCompany = fields[8]   //客户公司(发工资)
                    val idCardNum = fields[9]       //身份证号码
                    val gender = fields[10]          //性别
                    val age = fields[11]            //年龄
                    //val nation = fields[12]         //民族
                    //val nativePlace = fields[13]    //籍贯
                    val homeAddress = fields[14]    //现住址
                    val telephone = fields[15]      //联系电话
                    //val entryTime = fields[16]      //入职时间
                    val contactName = fields[17]    //紧急联系人姓名
                    val contactMobile = fields[18]  //紧急联系人联系方式（手机)
                    val salaryDate = fields[19]     //发放工资日(每月)
                    val accountBank = fields[20]    //开户行名
                    //val accountAddress = fields[21] //开户地
                    val accountNum = fields[22]     //工资卡号
                    val salary = fields[23]         //实发

                    managerService?.existManagerByMobile(mobile)?.let {
                        if (!it) {
                            val manager = Manager()
                            val personalInfo = PersonalInfo()

                            manager.id = Tool.uuid()
                            manager.mobile = mobile
                            manager.createTime = today
                            manager.userName = userName
                            manager.password = password
                            manager.realName = realName
                            manager.telephone = telephone
                            manager.company_name = companyName

                            personalInfo.t_P_id = Tool.uuid()
                            personalInfo.t_P_PID = idCardNum
                            personalInfo.t_P_Mobil = mobile
                            personalInfo.create_time = today
                            personalInfo.t_P_SysUpdateDate = today
                            personalInfo.creator = "system"
                            personalInfo.t_P_Name = realName
                            personalInfo.t_P_Sex = gender
                            personalInfo.t_P_Age = NumberUtils.toInt(age, 0)
                            personalInfo.t_P_HomeAddress = homeAddress
                            personalInfo.t_P_Phone = telephone
                            personalInfo.t_P_Contact1 = contactName
                            personalInfo.t_P_Contact1Mobil = contactMobile
                            personalInfo.t_P_Company = companyName
                            personalInfo.t_P_CompanyNum = workNum
                            personalInfo.t_P_VendorEmployeeName = clientCompany
                            personalInfo.t_P_PayrollDebitcardBankName = accountBank
                            personalInfo.t_P_PayrollDebitcardNum = accountNum
                            personalInfo.t_P_NetBaseSalary = salary
                            personalInfo.t_P_PayrollDate = NumberUtils.toInt(salaryDate, 1)

                            managerService.insertSelective(manager)
                            personalInfoService?.insertSelective(personalInfo)

                            val outline = listOf(workNum, realName, idCardNum, mobile, "S", "开户成功", userName, dateStr, "\r\n")

                            out.print(outline.joinToString(FILE_SEP))
                        }
                    }
                }

                out.flush()
                out.close()

                TripleDes.makeZipfile(FILE_TREE_DES_KEY, respZipFilename, respBasename, respUnzipFilename)
                client.upload(File(respZipFilename))
            }
        }

        client.disconnect(true)

        logger.info("Sync Users from FTP end：" + System.currentTimeMillis())
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserSyncJob::class.java)

        private const val FILE_TREE_DES_KEY = "123456789123456789123456"
        private const val FILE_PREFIX_RESP = "RESP_"
        private const val FILE_PREFIX = "EMAO_YHSY_"
        private const val FILE_SEP = "@!@"

        private const val FTP_HOST = "139.196.107.194"
        private const val FTP_PORT = 21
        private const val FTP_USERNAME = "root"
        private const val FTP_PASSWORD = "ZhcJHr201799a"
        private const val FTP_ROOT_PATH = "/pub/caijun"
    }
}

@Throws(Exception::class)
fun main(args: Array<String>) {
    val context = ClassPathXmlApplicationContext("classpath:applicationContext.xml")
    val bean = context.getBean("userSyncJob") as UserSyncJob
    bean.syncUsersFromFTP()
}
*/