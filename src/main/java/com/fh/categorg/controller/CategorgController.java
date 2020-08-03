package com.fh.categorg.controller;


import com.alibaba.fastjson.JSONObject;
import com.fh.categorg.service.CategorgService;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("categorg")
public class CategorgController {
    @Autowired
    private CategorgService cs;

    @RequestMapping("queryList")
    @ResponseBody
    @Ignore
    public ServerResponse queryList(){
        Boolean typologyList = RedisUtil.exists("typologyList");
        if(typologyList){
            String typologyList1 = RedisUtil.get("typologyList");
            List<Map> maps = JSONObject.parseArray(typologyList1, Map.class);
            return ServerResponse.success(maps);

        }
        List<Map<String,Object>> list = cs.queryCategorgList();
        String jsonString = JSONObject.toJSONString(list);
        RedisUtil.set("typologyList",jsonString);
        return ServerResponse.success(list);
    }

}
