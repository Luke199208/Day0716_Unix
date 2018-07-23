package com.luke.account.bean;

import com.luke.abstract_bean.Page;

/***
 * com.luke.account.bean
 * dllo
 * 18/7/19
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
public class AccountPage extends Page{
    private String idcard_no,real_name,login_name,status;


    public AccountPage() {
    }

    @Override
    public String toString() {
        return "AccountPage{" +
                "idcard_no='" + idcard_no + '\'' +
                ", real_name='" + real_name + '\'' +
                ", login_name='" + login_name + '\'' +
                ", status='" + status + '\'' +
                "} " + super.toString();
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
