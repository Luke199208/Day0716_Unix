package com.luke.service.bean;

import com.luke.account.bean.Account;
import com.luke.cost.bean.Cost;

import java.util.List;

/***
 * com.luke.service.bean
 * dllo
 * 18/7/21
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
public class ServiceBean {
    private String id,account_id,unix_host,
            os_username,login_passwd,service_status,
            create_date,pause_date,close_date,cost_id;

    @Override
    public String toString() {
        return "ServiceBean{" +
                "id='" + id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", unix_host='" + unix_host + '\'' +
                ", os_username='" + os_username + '\'' +
                ", login_passwd='" + login_passwd + '\'' +
                ", service_status='" + service_status + '\'' +
                ", create_date='" + create_date + '\'' +
                ", pause_date='" + pause_date + '\'' +
                ", close_date='" + close_date + '\'' +
                ", cost_id='" + cost_id + '\'' +
                ", cost=" + cost +
                ", account=" + account +
                ", host=" + host +
                ", relogin_passwd='" + relogin_passwd + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //根据前端需要的数据声明
    private Cost cost;//资费名称
    private Account account;//账务
    private Host host;//ip
    private String relogin_passwd;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public String getRelogin_passwd() {
        return relogin_passwd;
    }

    public void setRelogin_passwd(String relogin_passwd) {
        this.relogin_passwd = relogin_passwd;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getUnix_host() {
        return unix_host;
    }

    public void setUnix_host(String unix_host) {
        this.unix_host = unix_host;
    }

    public String getOs_username() {
        return os_username;
    }

    public void setOs_username(String os_username) {
        this.os_username = os_username;
    }

    public String getLogin_passwd() {
        return login_passwd;
    }

    public void setLogin_passwd(String login_passwd) {
        this.login_passwd = login_passwd;
    }

    public String getService_status() {
        return service_status;
    }

    public void setService_status(String service_status) {
        this.service_status = service_status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getPause_date() {
        return pause_date;
    }

    public void setPause_date(String pause_date) {
        this.pause_date = pause_date;
    }

    public String getClose_date() {
        return close_date;
    }

    public void setClose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getCost_id() {
        return cost_id;
    }

    public void setCost_id(String cost_id) {
        this.cost_id = cost_id;
    }
}
