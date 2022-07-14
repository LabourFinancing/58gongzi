package com.qucai.sample.pymt;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.exception.RetEnumIntf;
import com.qucai.sample.util.JsonTool;
import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PymtChannelChk {

    public Map<String, Object> TreasuryPoolChk(String ret, String msg) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ret", ret);
        map.put("msg", msg);
        return map;
    }

    public Map<String, Object> TreasuryPoolBalChk(RetEnumIntf rei)
        throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ret", rei.getRet());
        map.put("msg", rei.getMsg());
        return map;
    }


    public String mapToJson(Map<String, Object> jsonMap) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("{");
        Iterator<Map.Entry<String, Object>> entryIterator = jsonMap.entrySet().iterator();

        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {

            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                System.out.println("\n Object Key : " + key);
                String mapJson = mapToJson((Map<String, Object>) value);
                result.append("\"").append(key).append("\":").append(mapJson);
            } else if (value instanceof List) {
                System.out.println("\n Array Key : " + key);
                String listJson = listToJson((List) value);
                result.append("\"").append(key).append("\":").append(listJson);
            } else {
                System.out.println("key : " + key + " value : " + value);
                result.append("\"").append(key).append("\":\"").append(value).append("\"");
            }
            // 最后一个去掉 逗号
            entryIterator.next();
            if (entryIterator.hasNext()) {
                result.append(",");
            }
        }
        result.append("}");
        return result.toString();
    }

    /**
     * 将List转成Json
     *
     * @param jsonList
     * @return
     * @throws Exception
     */
    public String listToJson(List jsonList) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("[");
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.opt(i) instanceof JSONObject) {
                String mapJson = mapToJson((Map<String, Object>) jsonList.get(i));
                result.append(mapJson);
            } else if (jsonArray.opt(i) instanceof JSONArray) {
                String listJson = listToJson((List) jsonList.get(i));
                result.append(listJson);
            } else {
                result.append("\"").append(jsonArray.opt(i)).append("\"");
            }

            // 最后去掉 逗号
            if (i < jsonArray.size() - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }
    
}
