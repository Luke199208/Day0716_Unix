package com.luke.cost.bean;

/***
 * com.luke.cost.bean
 * dllo
 * 18/7/16
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
public class Cost {

    private String cost_id,name,base_duration,
            base_cost,unit_cost,status,descr,
            creatime,startime,cost_type;

    @Override
    public String toString() {
        return "Cost{" +
                "cost_id='" + cost_id + '\'' +
                ", name='" + name + '\'' +
                ", base_duration='" + base_duration + '\'' +
                ", base_cost='" + base_cost + '\'' +
                ", unit_cost='" + unit_cost + '\'' +
                ", status='" + status + '\'' +
                ", descr='" + descr + '\'' +
                ", creatime='" + creatime + '\'' +
                ", startime='" + startime + '\'' +
                ", cost_type='" + cost_type + '\'' +
                '}';
    }

    public Cost(String cost_id, String name, String base_duration,
                String base_cost, String unit_cost, String status,
                String descr, String creatime, String startime, String cost_type) {
        this.cost_id = cost_id;
        this.name = name;
        this.base_duration = base_duration;
        this.base_cost = base_cost;
        this.unit_cost = unit_cost;
        this.status = status;
        this.descr = descr;
        this.creatime = creatime;
        this.startime = startime;
        this.cost_type = cost_type;
    }

    public Cost() {
    }


    public String getCreatime() {
        return creatime;
    }

    public void setCreatime(String creatime) {
        this.creatime = creatime;
    }

    public String getCost_id() {

        return cost_id;
    }

    public void setCost_id(String cost_id) {
        this.cost_id = cost_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase_duration() {
        return base_duration;
    }

    public void setBase_duration(String base_duration) {
        this.base_duration = base_duration;
    }

    public String getBase_cost() {
        return base_cost;
    }

    public void setBase_cost(String base_cost) {
        this.base_cost = base_cost;
    }

    public String getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(String unit_cost) {
        this.unit_cost = unit_cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }



    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }
}
