package com.luke.service.bean;

/***
 * com.luke.service.bean
 * dllo
 * 18/7/23
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
public class Host {
    private String hid,hname,location;

    @Override
    public String toString() {
        return "Host{" +
                "hid='" + hid + '\'' +
                ", hname='" + hname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
