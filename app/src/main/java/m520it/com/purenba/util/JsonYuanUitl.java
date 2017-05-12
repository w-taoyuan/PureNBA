package m520it.com.purenba.util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by 亮 on 2017/4/26.
 */

public class JsonYuanUitl {

    //解析出id
    public static ArrayList<String> getIndexId(String jsonStr) {
        ArrayList<String> idList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray lists = dataObject.getJSONArray("list");
            for (int i = 0; i < lists.length(); i++) {
                JSONObject object = lists.getJSONObject(i);
                String id = object.getString("id");
                idList.add(id);
            }
        } catch (JSONException e) {
            //            e.printStackTrace();
            return idList;
        }
        return idList;
    }

    public static ArrayList<String> getId(String data, String list) {
        ArrayList<String> idList = null;
        try {
            JSONObject jsonOb = new JSONObject(data);
            JSONObject jsonObject = jsonOb.getJSONObject("data");
            JSONArray lists = jsonObject.getJSONArray(list);
            int length = lists.length();
            idList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject object = lists.getJSONObject(i);
                String id = object.getString("id");
                idList.add(id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idList;
    }

    public interface OnHttpGetArrayListResultListener {
        void onFailre(IOException e);

        void onSuccess(ArrayList<String> result);
    }

    //解析列表数据 得到进一步的String数据
    public static void getItemdate(final int position, final ArrayList<String> idList, String url, final OnHttpGetArrayListResultListener listener) {

        String ids = StringUtils.getIds(idList, position);
        url = url + ids;
        final ArrayList<String> infoStr = new ArrayList<>();
        OkHttpUtils.getInstance(UIUtils.getContext()).requestGETStringResult(url, new OkHttpUtils.OnHttpGetStringResultListener() {
            @Override
            public void onFail(IOException e) {
                listener.onFailre(e);
            }

            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject data = jsonObject.getJSONObject("data");
                    for (int i = position; i < 20 + position; i++) {
                        String listJsonStr = data.getJSONObject(idList.get(i)).toString();
                        infoStr.add(listJsonStr);
                    }
                    listener.onSuccess(infoStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
