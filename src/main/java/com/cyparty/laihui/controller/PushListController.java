package com.cyparty.laihui.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyparty.laihui.db.AppDB;
import com.cyparty.laihui.domain.ErrorCode;
import com.cyparty.laihui.domain.PushNotification;
import com.cyparty.laihui.utilities.AppJsonUtils;
import com.cyparty.laihui.utilities.NotifyPush;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 消息模块
 */
@Controller
@ResponseBody
@RequestMapping(value = "/api/app", method = RequestMethod.POST)
public class PushListController {
    @Autowired
    AppDB appDB;
    @Autowired
    NotifyPush notifyPush;

    /**
     * 获取消息列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/push/newList", method = RequestMethod.POST)
    public ResponseEntity<String> PushList(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String json = "";
        try {
            String action = request.getParameter("action");
            int page = 0;
            int size = 10;
            if (request.getParameter("page") != null && !request.getParameter("page").trim().equals("")) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    page = 0;
                    e.printStackTrace();
                }
            }
            if (request.getParameter("size") != null && !request.getParameter("size").trim().equals("")) {
                try {
                    size = Integer.parseInt(request.getParameter("size"));
                } catch (NumberFormatException e) {
                    size = 10;
                    e.printStackTrace();
                }
            }
            String token = request.getParameter("token");
            if (null != token && token.length() == 32) {
                int user_id = appDB.getIDByToken(token);
                int flag = 0;
                String msg = "";
                switch (action) {
                    case "show":
                        result.put("activity_msg", AppJsonUtils.getPushActivity(appDB,1,"精选活动"));
                        result.put("order_msg", AppJsonUtils.getPushAll(appDB,0,"车单状态",user_id));
                        result.put("system_msg", AppJsonUtils.getPushAll(appDB,2,"系统消息",user_id));
                        json = AppJsonUtils.returnSuccessJsonString(result, "推送消息列表获取成功！");
                        return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                    case "show_activity":
                        List<PushNotification> pushList = appDB.getPushList(" where flag=1 and is_enable=1 order by CONVERT (time USING gbk)COLLATE gbk_chinese_ci desc");
                        flag = 1;
                        msg = "activity_msg";
                        if (pushList.size() > 0) {
                            json = AppJsonUtils.returnSuccessJsonString(AppJsonUtils.getPushList(pushList,appDB,flag,msg), "精选活动消息列表获取成功！");
                            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                        }else{
                            json = AppJsonUtils.returnFailJsonString(result, "精选活动暂无消息！");
                            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                    }
                    case "show_order":
                        List<PushNotification> pushLists = appDB.getPushList(" where  receive_id=" + user_id + " and flag = 0 and is_enable=1 order by CONVERT (time USING gbk)COLLATE gbk_chinese_ci desc");
                        if (pushLists.size() > 0) {
                            json = AppJsonUtils.returnSuccessJsonString(AppJsonUtils.getPushOrder(pushLists,appDB), "车单状态列表获取成功！");
                            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                        } else {
                            json = AppJsonUtils.returnFailJsonString(result, "车单状态暂无消息！");
                            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                        }
                    case "show_system":
                        List<PushNotification> pushNotifications = appDB.getPushList(" where receive_id=" + user_id + " and flag=2 and is_enable=1 order by CONVERT (time USING gbk)COLLATE gbk_chinese_ci desc");
                        if (pushNotifications.size() > 0) {
                            flag = 2 ;
                            msg = "system_msg";
                            json = AppJsonUtils.returnSuccessJsonString(AppJsonUtils.getPushList(pushNotifications,appDB,flag,msg), "系统消息列表获取成功！");
                            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                        } else {
                            json = AppJsonUtils.returnFailJsonString(result, "暂无系统消息！");
                            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                        }
                }
                result.put("error_code", ErrorCode.PARAMETER_WRONG);
                json = AppJsonUtils.returnFailJsonString(result, "获取参数错误");
                return new ResponseEntity<>(json, responseHeaders, HttpStatus.BAD_REQUEST);
            } else {
                result.put("error_code", ErrorCode.TOKEN_EXPIRED);
                json = AppJsonUtils.returnFailJsonString(result, "无效的token");
                return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error_code", ErrorCode.PARAMETER_WRONG);
            json = AppJsonUtils.returnFailJsonString(result, "获取参数错误");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 获取消息列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/push/list", method = RequestMethod.POST)
    public ResponseEntity<String> getPush(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String json = "";
        try {
            String action = request.getParameter("action");
            int page = 0;
            int size = 10;
            if (request.getParameter("page") != null && !request.getParameter("page").trim().equals("")) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    page = 0;
                    e.printStackTrace();
                }
            }
            if (request.getParameter("size") != null && !request.getParameter("size").trim().equals("")) {
                try {
                    size = Integer.parseInt(request.getParameter("size"));
                } catch (NumberFormatException e) {
                    size = 10;
                    e.printStackTrace();
                }
            }
            JSONArray jsonArray = new JSONArray();
            switch (action) {
                case "show":
                    String token = request.getParameter("token");
                    if (null != token && token.length() == 32) {
                        int user_id = appDB.getIDByToken(token);
                        String where = " where receive_id=" + user_id + " and is_enable=1 order by CONVERT (time USING gbk)COLLATE gbk_chinese_ci desc";
                        List<PushNotification> pushList = appDB.getPushList(where);
                        if (pushList.size() > 0) {
                            for (PushNotification push : pushList) {
                                JSONObject pushJson = new JSONObject();
                                pushJson.put("message_id", push.get_id());
                                pushJson.put("order_id", push.getOrder_id());
                                pushJson.put("push_id", push.getPush_id());
                                pushJson.put("receive_id", push.getReceive_id());
                                pushJson.put("push_type", push.getPush_type());
                                pushJson.put("alert", push.getAlert());
                                pushJson.put("type", push.getType());
                                pushJson.put("sound", push.getSound());
                                pushJson.put("data", push.getData());
                                pushJson.put("time", push.getTime());
                                pushJson.put("status", push.getStatus());
                                pushJson.put("user_name", push.getUser_name());
                                jsonArray.add(pushJson);
                            }
                            result.put("push", jsonArray);
                            json = AppJsonUtils.returnSuccessJsonString(result, "推送消息列表获取成功！");
                            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
                        } else {
                            json = AppJsonUtils.returnFailJsonString(result, "还没有消息哦！");
                            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.OK);
                        }
                    } else {
                        result.put("error_code", ErrorCode.TOKEN_EXPIRED);
                        json = AppJsonUtils.returnFailJsonString(result, "无效的token");
                        return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
                    }
            }
            result.put("error_code", ErrorCode.PARAMETER_WRONG);
            json = AppJsonUtils.returnFailJsonString(result, "获取参数错误");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error_code", ErrorCode.PARAMETER_WRONG);
            json = AppJsonUtils.returnFailJsonString(result, "获取参数错误");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 判断消息是否已读
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/read/judgment", method = RequestMethod.POST)
    public ResponseEntity<String> isRead(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String json = "";
        try {
            String message_id = request.getParameter("message_id");
            String where = " set status=0 where _id=" + message_id;
            boolean is_success = appDB.update("pc_push_notification", where);
            if (is_success) {
                json = AppJsonUtils.returnSuccessJsonString(result, "消息已读状态设置成功！");
                return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
            } else {
                json = AppJsonUtils.returnFailJsonString(result, "消息已读状态设置失败！");
                return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error_code", ErrorCode.PARAMETER_WRONG);
            json = AppJsonUtils.returnFailJsonString(result, "获取参数错误");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }


    }

    /**
     * 消息列表清空
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/pushList", method = RequestMethod.POST)
    public ResponseEntity<String> delete(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String json = "";
        int user_id = 0;
        String token = request.getParameter("token");
        if (token != null && token.length() == 32) {
            user_id = appDB.getIDByToken(token);

        } else {
            result.put("error_code", ErrorCode.TOKEN_EXPIRED);
            json = AppJsonUtils.returnFailJsonString(result, "非法token！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        }
        String where = " set is_enable=0 where receive_id=" + user_id + " and is_enable=1 and flag=0";
        boolean is_success = appDB.update("pc_push_notification", where);
        if (is_success) {
            json = AppJsonUtils.returnSuccessJsonString(result, "清空列表成功！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        } else {
            json = AppJsonUtils.returnFailJsonString(result, "清空列表失败！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        }

    }


    /**
     * 判断是否有新消息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/message/judgment", method = RequestMethod.POST)
    public ResponseEntity<String> isMessage(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String json = "";

        String token = request.getParameter("token");
        if (null != token && token.length() == 32) {
            int user_id = appDB.getIDByToken(token);
            List<PushNotification> pushList = appDB.getPushList("where receive_id=" + user_id + " and status=1 and is_enable=1");
            if (pushList.size() > 0) {
                result.put("status", 1);
                json = AppJsonUtils.returnSuccessJsonString(result, "您有新的消息！");
                return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
            } else {
                result.put("status", 0);
                json = AppJsonUtils.returnFailJsonString(result, "您没有新的消息！");
                return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
            }
        } else {
            result.put("error_code", ErrorCode.TOKEN_EXPIRED);
            json = AppJsonUtils.returnFailJsonString(result, "无效的token");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * 消息列表清空
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/pushOne", method = RequestMethod.POST)
    public ResponseEntity<String> deletePushOne(HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=UTF-8");
        JSONObject result = new JSONObject();
        String json = "";
        int user_id = 0;
        String token = request.getParameter("token");
        String id = request.getParameter("message_id");
        if (token != null && token.length() == 32) {
            user_id = appDB.getIDByToken(token);

        } else {
            result.put("error_code", ErrorCode.TOKEN_EXPIRED);
            json = AppJsonUtils.returnFailJsonString(result, "非法token！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        }
        if(StringUtil.isBlank(id)||"".equals(id)){
            json = AppJsonUtils.returnFailJsonString(result, "参数错误");
            return new ResponseEntity<String>(json, responseHeaders, HttpStatus.BAD_REQUEST);
        }
        int push_id = Integer.parseInt(id);
        String where = " set is_enable=0 where receive_id=" + user_id + " and is_enable=1 and flag=0 and _id ="+push_id;
        boolean is_success = appDB.update("pc_push_notification", where);
        if (is_success) {
            json = AppJsonUtils.returnSuccessJsonString(result, "清空列表成功！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        } else {
            json = AppJsonUtils.returnFailJsonString(result, "清空列表失败！");
            return new ResponseEntity<>(json, responseHeaders, HttpStatus.OK);
        }

    }

}
