package com.luke.account.service;

import com.luke.abstract_bean.Page;
import com.luke.account.bean.Account;
import com.luke.account.bean.AccountPage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

/***
 * com.luke.account.service
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
public interface AccountService {
    List<Account> findAll();

    boolean setState(String id);

    //List<Account> ConditionQuery(AccountPage page);

    boolean insertAccount(Account account);

    Account findDetail(String id);

    boolean deleteAccount(String id);

    boolean modiAcc(Account account);

    /*-------------------------------分页----------------------------------------*/
    //1.查询分页总页数
    AccountPage findAccountByLimit();

    //2.条件查询分页
    AccountPage ConditionQueryByLimit(AccountPage accountPage);

    /*----------------------------------数据库导入导出----------------------------------*/
    void importExcelInfo(InputStream in, MultipartFile file) throws Exception;

    XSSFWorkbook exportExcelInfo(String accountDate)
            throws InvocationTargetException, ClassNotFoundException,
            IntrospectionException, ParseException, IllegalAccessException;
}
