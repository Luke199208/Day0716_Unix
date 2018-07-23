package com.luke.account.service.impl;

import com.luke.account.bean.Account;
import com.luke.account.bean.AccountPage;
import com.luke.account.bean.ExcelBean;
import com.luke.account.mapper.AccountMapper;
import com.luke.account.service.AccountService;
import com.luke.account.bean.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/***
 * com.luke.account.service.impl
 * dllo
 * 18/7/17
 *             ,%%%%%%%%,
 *           ,%%/\%%%%/\%%
 *          ,%%%\c "" J/%%%
 * %.       %%%%/ 0  0 \%%%
 * `%%.     %%%%    _  |%%%
 *  `%%     `%%%%(__Y__)%%'
 *  //       ;%%%%`\-/%%%'
 * ((       /  `%%%%%%%'
 *  \\    .'     '%%%'|    攻
 *   \\  /       \  | |    城
 *    \\/         ) | |    湿
 *     \         /_ | |__
 *     (___________))))))) 
 *
 *       我湿一吼  BUG无有                        
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper mapper;

    @Override
    public List<Account> findAll() {
        return mapper.findAll();
    }

    @Override
    public boolean setState(String id) {
        //1 开启   2 暂停
        //获得创建时间
        Date date = new Date();
        String dateFormat = String.valueOf(DateFormat.getDateInstance().format(date));
        Account account = mapper.findAccountById(id);
        if (account.getStatus().equals("1")){
            int flag = mapper.setStatePause(id,"2",dateFormat);
            return flag == 1 ;
        }else if (account.getStatus().equals("2")){
            int flag = mapper.setStateStart(id,"1",dateFormat);
            return flag == 1 ;
        }
        return false;
    }

//    @Override
//    public List<Account> ConditionQuery(AccountPage page) {
//
//        if (page.getStatus().equals("0")){
//
//            page.setStatus("");
//        }
//        return mapper.ConditionQuery(page);
//    }

    @Override
    public boolean insertAccount(Account account) {

        //根据身份证号查询是否重复添加
        Account account1 = mapper.findAccountByIdcard(account.getIdcard_no());

        if (account1 == null){
            //获得创建时间
            Date date = new Date();
            String dateFormat = String.valueOf(DateFormat.getDateInstance().format(date));
            account.setCreate_date(dateFormat);

            //根据身份证号获取birthday
            String birthday = account.getIdcard_no().substring(6,14);
            account.setBirthdate(birthday);

            System.out.println(account);
            //可选未选设置为null
            int flag = mapper.insertAccount(account);
            return flag == 1 ;
        }

        return false;
    }

    @Override
    public Account findDetail(String id) {

        return mapper.findDetail(id);
    }

    @Override
    public boolean deleteAccount(String id) {
        return mapper.deleteAccount(id);
    }


    @Override
    public boolean modiAcc(Account account) {
        int flag = mapper.modiAcc(account);
        return flag == 1;
    }

   /*----------------------------------分页---------------------------------------*/
    //1.初始分页查询
    @Override
    public AccountPage findAccountByLimit() {
        AccountPage accountPage = new AccountPage();
        //获得总页数
        accountPage.setTotal(mapper.getCount());
        List<Account> list = mapper.findAccountByLimit(accountPage);
        accountPage.setList(list);
        return accountPage;
    }

    //2.条件查询分页
    @Override
    public AccountPage ConditionQueryByLimit(AccountPage accountPage) {
        //若选择全部  则设status为 ""
        if (accountPage.getStatus().equals("0")){
            accountPage.setStatus("");
        }
        //获得总页数
        accountPage.setTotal(mapper.getConditionQueryCount(accountPage));

        List<Account> list = mapper.ConditionQueryByLimit(accountPage);
        accountPage.setList(list);
        return accountPage;
    }

    /*----------------------------------数据库导入导出----------------------------------*/
    @Override
    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception{
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<Account> accounts = new ArrayList<>();
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            Account account = new Account();
            account.setAccount_id(String.valueOf(ob.get(0)));
            account.setRecommender_id(String.valueOf(ob.get(1)));
            account.setLogin_name(String.valueOf(ob.get(2)));
            account.setLogin_passwd(String.valueOf(ob.get(3)));
            account.setStatus(String.valueOf(ob.get(4)));
            account.setCreate_date(String.valueOf(ob.get(5)));
            account.setPause_date(String.valueOf(ob.get(6)));
            account.setClose_date(String.valueOf(ob.get(7)));
            account.setReal_name(String.valueOf(ob.get(8)));
            account.setIdcard_no(String.valueOf(ob.get(9)));
            account.setBirthdate(String.valueOf(ob.get(10)));
            account.setGender(String.valueOf(ob.get(11)));
            account.setOccupation(String.valueOf(ob.get(12)));
            account.setTelephone(String.valueOf(ob.get(13)));
            account.setEmail(String.valueOf(ob.get(14)));
            account.setMailaddress(String.valueOf(ob.get(15)));
            account.setZipcode(String.valueOf(ob.get(16)));
            account.setQq(String.valueOf(ob.get(17)));
            account.setLast_login_time(String.valueOf(ob.get(18)));
            account.setLast_login_ip(String.valueOf(ob.get(19)));
            accounts.add(account);
        }
        //批量插入
        mapper.insertInfoBatch(accounts);
    }


    //导出
    @Override
    public XSSFWorkbook exportExcelInfo(String accountDate)
            throws InvocationTargetException, ClassNotFoundException,
            IntrospectionException, ParseException, IllegalAccessException {
        //根据条件查询数据，把数据装载到一个list中
        List<Account> list = mapper.findAll();
//        for(int i=0;i<list.size();i++){
//            String adminName = mapper.selectAdminNameById(adminId);
//            list.get(i).setAdminName(adminName);
//            list.get(i).setId(i+1);
//        }
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("账务账号","account_id",0));
        excel.add(new ExcelBean("推荐人id","recommender_id",0));
        excel.add(new ExcelBean("登录名","login_name",0));
        excel.add(new ExcelBean("登录密码","login_passwd",0));
        excel.add(new ExcelBean("状态","status",0));
        excel.add(new ExcelBean("创建时间","create_date",0));
        excel.add(new ExcelBean("暂停时间","pause_date",0));
        excel.add(new ExcelBean("关闭时间","close_date",0));
        excel.add(new ExcelBean("姓名","real_name",0));
        excel.add(new ExcelBean("身份证号","idcard_no",0));
        excel.add(new ExcelBean("生日","birthdate",0));
        excel.add(new ExcelBean("性别","gender",0));
        excel.add(new ExcelBean("工作","occupation",0));
        excel.add(new ExcelBean("电话","telephone",0));
        excel.add(new ExcelBean("email","email",0));
        excel.add(new ExcelBean("通讯地址","mailaddress",0));
        excel.add(new ExcelBean("邮编","zipcode",0));
        excel.add(new ExcelBean("QQ","qq",0));
        excel.add(new ExcelBean("上次登录时间","last_login_time",0));
        excel.add(new ExcelBean("上次登录ip","last_login_ip",0));
        map.put(0, excel);
        String sheetName = accountDate + "账务";
        //调用ExcelUtil的方法
        xssfWorkbook = ExcelUtil.createExcelFile(Account.class, list, map, sheetName);
        return xssfWorkbook;
    }
}
