package com.luke.account.mapper;


import com.luke.account.bean.Account;
import com.luke.account.bean.AccountPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * com.luke.account.controller
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
@Repository
public interface AccountMapper {
    List<Account> findAll();

    Account findAccountById(String id);

    //List<Account> ConditionQuery(AccountPage page);

    Account findAccountByIdcard(String idcard_no);

    int insertAccount(Account account);

    Account findDetail(String id);

    int setStatePause(@Param("id")String id,@Param("status") String status, @Param("pause_date") String dateFormat);

    int setStateStart(@Param("id")String id, @Param("status")String status, @Param("create_date") String dateFormat);

    boolean deleteAccount(String id);

    int modiAcc(Account account);

    /*----------------------------分页---------------------------*/
    int getCount();

    int getConditionQueryCount(AccountPage accountPage);

    List<Account> findAccountByLimit(AccountPage page);

    List<Account> ConditionQueryByLimit(AccountPage page);

    /*-----------------------------导入导出---------------------------*/
    void insertInfoBatch(List<Account> accounts);
}
