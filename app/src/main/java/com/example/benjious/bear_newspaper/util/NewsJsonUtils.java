package com.example.benjious.bear_newspaper.util;

import com.example.benjious.bear_newspaper.base.DataBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * 将获取到的Json对象转化为列表对象
 * Created by Benjious on 2017/1/13.
 */

public class NewsJsonUtils {
    public static List<DataBean> readJsonDataBeans(String res,String value){
        List<DataBean> dataBeanList = new ArrayList<>();

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(res).getAsJsonObject();
        JsonElement jsonElement = jsonObject.get(value);
        if (jsonObject==null) {
            return null;
        }
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for (int i=1; i<jsonArray.size(); i++) {
            JsonObject object = jsonArray.get(i).getAsJsonObject();
            if (object.has("skipType")&&"special".equals(object.get("skipType").getAsString())) {
                continue;
            }
            if (object.has("TAGS") && !object.has("TAG")) {
                continue;
            }

            if (!object.has("imgextra")) {
                try {
                    DataBean dataBean = JsonUtils.deserialize(object, DataBean.class);
                    dataBeanList.add(dataBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataBeanList;
    }


}
