package com.cyparty.laihui.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyparty.laihui.db.AppDB;
import com.cyparty.laihui.domain.ErrorCode;
import com.cyparty.laihui.domain.User;
import com.cyparty.laihui.utilities.AppJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

/**
 * 个人资料模块
 */
@Controller
@ResponseBody
@RequestMapping(value = "/api/app",method = RequestMethod.POST)
public class PersonalInformationController {

    @Autowired
    AppDB appDB;

    @ResponseBody
    @RequestMapping(value = "/personal/show", method = RequestMethod.POST)
    public ResponseEntity<String> search(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String token = null;
        String json = "";
        String where = "";
        User user = new User();
        int user_id = 0;
        if (request.getParameter("token") != null) {
            token = request.getParameter("token");
            if (token != null && token.length() == 32) {
                user_id = appDB.getIDByToken(token);
                where = " where _id = " + user_id;
                try {
                    List<User> userList = appDB.getUserList(where);
                    if (userList.size() > 0) {
                        user = userList.get(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("error_code", ErrorCode.getToken_expired());
                    json = AppJsonUtils.returnFailJsonString(result, "非法token！");
                    return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                }
            }
        } else {
            result.put("error_code", ErrorCode.getToken_expired());
            json = AppJsonUtils.returnFailJsonString(result, "非法token！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        }
        result = AppJsonUtils.getPersonalInfo(user);
        json = AppJsonUtils.returnSuccessJsonString(result, "个人信息获取成功");
        return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
    }

    /**
     * 修改添加个人信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/personal/update", method = RequestMethod.POST)
    public ResponseEntity<String> update(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String token = null;
        String json = "";
        String  user_avater = request.getParameter("user_avater");
        String  user_name = request.getParameter("user_name");
        String  user_sex = request.getParameter("user_sex");
        String  user_signature = request.getParameter("user_signature");
        String  user_birthday = request.getParameter("user_birthday");
        String  user_home = request.getParameter("user_home");
        String  user_live_city = request.getParameter("user_live_city");
        String  user_company = request.getParameter("user_company");
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(user_birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int user_id = 0;
        if (request.getParameter("token") != null) {
            token = request.getParameter("token");
            if (token != null && token.length() == 32) {
                user_id = appDB.getIDByToken(token);

            } else {
                result.put("error_code", ErrorCode.getToken_expired());
                json = AppJsonUtils.returnFailJsonString(result, "非法token！");
                return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
            }
           }else {
            result.put("error_code", ErrorCode.getToken_expired());
            json = AppJsonUtils.returnFailJsonString(result, "非法token！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        }
        String where = " set user_name='"+user_name+"' , sex='"+user_sex+"' , signature='"+user_signature+"' , birthday='"+user_birthday+"' , home='"+user_home+"' , live_city='"+user_live_city+"' , company='"+user_company+"'";
        appDB.update("pc_user",where);
        json = AppJsonUtils.returnSuccessJsonString(result,"个人资料修改成功！");
        return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
    }
}