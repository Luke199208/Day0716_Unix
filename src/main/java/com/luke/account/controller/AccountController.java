package com.luke.account.controller;


import com.luke.account.bean.Account;
import com.luke.account.bean.AccountPage;
import com.luke.account.service.impl.AccountServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
@Controller
@RequestMapping("/account")
//@SessionAttributes("accountPage")
// 存放并刷新表单传回的accountPage,
// 则会取之前存放的accountPage取值执行操作,
//但不建议使用,因为很多项目使用前后端分离,则此方式无法成功
public class AccountController {

    @Resource
    private AccountServiceImpl service;

    @RequestMapping("/findAll.do")
    public String findAll(Model model){
        AccountPage account = service.findAccountByLimit();
        //System.out.println(account+"findAll");
        model.addAttribute("accountPage",account);

        return "account/account_list";
    }

    //开启或暂停
    @RequestMapping("/setState.do")
    public String setState(@RequestParam String id,Model model){
        boolean flag = service.setState(id);
        if (flag){
            model.addAttribute("msg","操作成功");

        }else {
            model.addAttribute("msg","操作失败");

        }
        System.out.println(id);
      return "redirect:findAll.do";
    }

    //删除
    @RequestMapping("/deleteAccount.do")
    public String deleteAccount(@RequestParam String id,Model model){
        boolean flag = service.deleteAccount(id);
        if (flag){
            AccountPage account = service.findAccountByLimit();
            model.addAttribute("accountPage",account);
            model.addAttribute("msg","删除成功");
        }else {
            AccountPage account = service.findAccountByLimit();
            model.addAttribute("accountPage",account);
            model.addAttribute("msg","删除失败");
        }
        return "account/account_list";
    }


    //条件分页查询
    @RequestMapping("/ConditionQueryByLimit.do")
    public String ConditionQueryByLimit(AccountPage page, Model model){
        //System.out.println(page+"Condition");

        AccountPage accountPage = service.ConditionQueryByLimit(page);

        if (accountPage!= null){
            model.addAttribute("accountPage",accountPage);
        }else {
            model.addAttribute("msg","未查询到符合条件的数据");
        }

        return "account/account_list";
    }

    //转到account_add.jsp
    @RequestMapping("/addAccountT.do")
    public String addAccountT(){
        return "account/account_add";
    }

    @RequestMapping("/addAccount.do")
    public String addAccount(Account account ,Model model){

       // System.out.println(account);

        boolean flag = service.insertAccount(account);
        if (flag){
            return "redirect:/account/account_add";
        }else {
            model.addAttribute("msg","保存失败，该身份证已经开通过账务账号！");
            model.addAttribute("flag",true);
        }
        return "account/account_add";
    }

    //detail
    @RequestMapping("/findDetail.do")
    public String findDetail(@RequestParam String id,Model model){
        Account account = service.findDetail(id);
        Account reAccount = service.findDetail(account.getRecommender_id());
        model.addAttribute("account",account);
        model.addAttribute("reAccount",reAccount);
        return "account/account_detail";
    }

    //modify
    @RequestMapping("/modiAccT.do")
    public ModelAndView modiAccT(@RequestParam String id){
        Account account = service.findDetail(id);
        Account reAccount = service.findDetail(account.getRecommender_id());
        ModelAndView modelAndView = new ModelAndView("account/account_modi");
        modelAndView.addObject("account",account);
        modelAndView.addObject("reaccount",reAccount);
        return modelAndView;
    }

    @RequestMapping("/modiAcc.do")
    public ModelAndView modiAcc(Account account){

        boolean flag = service.modiAcc(account);
        if (flag){
            ModelAndView modelAndView = new ModelAndView("redirect:findAll.do");
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("account/account_modi");
            modelAndView.addObject("flag",true);
            modelAndView.addObject("msg","修改失败");
            return modelAndView;
        }
    }

    //TODO:数据库导入  未实现
    @RequestMapping("/import.do")
    public String doImport(HttpServletRequest request, Model model) throws Exception {

        //获取上传的文件
       // MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        //MultipartFile file = multipart.getFile("upfile");
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile file = multipartRequest.getFile("upfile");

        InputStream in = file.getInputStream();
        //数据导入
        service.importExcelInfo(in,file);
        in.close();
        model.addAttribute("msg","导入成功");
        return "account/account.list";
    }

    @RequestMapping("/export.do")
    public @ResponseBody
    void export(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, ClassNotFoundException,
            IntrospectionException, IllegalAccessException, ParseException,
            InvocationTargetException  {
        String accountDate = request.getParameter("accountDate");
        if(accountDate != ""){
            response.reset(); //清除buffer缓存
            Map<String,Object> map=new HashMap<String,Object>();
            // 指定下载的文件名，浏览器都会使用本地编码，即GBK，浏览器收到这个文件名后，用ISO-8859-1来解码，然后用GBK来显示
            // 所以我们用GBK解码，ISO-8859-1来编码，在浏览器那边会反过来执行。
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(accountDate.getBytes("GBK"),"ISO-8859-1"));
            response.setContentType("application/vnd.excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            XSSFWorkbook workbook=null;
            //导出Excel对象
            workbook = service.exportExcelInfo(accountDate);
            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
