package com.fh.categorg.service;


import com.fh.categorg.mapper.CategorgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategorgServiceImpl implements CategorgService {
    @Autowired
    private CategorgMapper cm;

    @Override
    public List<Map<String, Object>> queryCategorgList() {
        List<Map<String, Object>> shopTypeList = cm.queryCategorgList();
        List<Map<String, Object>> shopMapList = new ArrayList<Map<String, Object>>();
        for (Map map : shopTypeList) {
            if(map.get("pid").equals(0)){
                shopMapList.add(map);
            }
        }
        selectChildren(shopTypeList,shopMapList);
        return shopMapList;

    }
    public void selectChildren(List<Map<String, Object>> shopTypeList,List<Map<String, Object>> shopMapList){
        for (Map<String, Object> shopMap : shopMapList) {
            List<Map<String, Object>> childrenList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> typeList : shopTypeList) {
                if(shopMap.get("id").equals(typeList.get("pid"))){
                    childrenList.add(typeList);
                }
            }
            if(childrenList!=null && childrenList.size()>0){
                shopMap.put("children",childrenList);
                selectChildren(shopTypeList,childrenList);
            }
        }

    }

}
