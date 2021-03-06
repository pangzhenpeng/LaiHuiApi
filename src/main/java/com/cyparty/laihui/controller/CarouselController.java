package com.cyparty.laihui.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyparty.laihui.db.AppDB;
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

/**
 * app广告接口
 * Created YangGuang on 2017/4/25.
 */
@Controller
@RequestMapping(value = "/api/app", method = RequestMethod.POST)
public class CarouselController {

    @Autowired
    private AppDB appDB;

    /**
     * 闪屏,弹出广告接口
     */
    @ResponseBody
    @RequestMapping(value = "/splash_screen", method = RequestMethod.POST)
    public ResponseEntity<String> splashScreen(HttpServletRequest request){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String json = "";
        try {
            String action=request.getParameter("action");
            boolean is_success=false;
            switch (action) {
                case "show":
                    result = AppJsonUtils.getCarouselJson(appDB);
                    if (result.isEmpty()){
                        json = AppJsonUtils.returnFailJsonString(result, "没有图片数据");
                        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
                    }
                    json = AppJsonUtils.returnSuccessJsonString(result, "闪屏信息获取成功");
                    return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
            }
            json = AppJsonUtils.returnFailJsonString(result, "获取参数错误");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            json = AppJsonUtils.returnFailJsonString(result, "获取参数错误");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

}
