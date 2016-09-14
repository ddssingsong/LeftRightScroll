package com.jhs.leftrightscroll;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhs.leftrightscroll.adapter.ALeftAdapter;
import com.jhs.leftrightscroll.adapter.ARightAdapter;
import com.jhs.leftrightscroll.bean.Fund;
import com.jhs.leftrightscroll.view.LeftListView;
import com.jhs.leftrightscroll.view.RightListView;
import com.jhs.leftrightscroll.view.SyncHorizontalScrollView;
import com.jhs.leftrightscroll.view.UtilTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dds on 2016/9/7.
 *
 * @TODO
 */
public class Tab2Fragment extends Fragment {
    //左侧内容
    private LeftListView leftListView;
    private List<String> leftlList;
    //右侧可左右上下滑动的内容
    private RightListView rightListView;
    private List<Fund> models;
    //滑动控件
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;

    //数据的适配器
    private ALeftAdapter leftAdapter;
    private ARightAdapter rightAdapter;

    private View view = null;

    private String url = "http://182.92.172.134:8014/ifoutservice.aspx?StockID=&scn=&doa=&op=fja";


    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            leftAdapter.addDataToAdapter(leftlList);
            rightAdapter.addDataToAdapter(models);


            UtilTools.setListViewHeightBasedOnChildren(leftListView);
            UtilTools.setListViewHeightBasedOnChildren(rightListView);



        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tab2, container, false);
            initView(view);
        }

        initData();
        return view;
    }


    private void initView(View rootView) {
        leftListView = (LeftListView) rootView.findViewById(R.id.left_container_listview);
        rightListView = (RightListView) rootView.findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) rootView.findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) rootView.findViewById(R.id.content_horsv);
    }


    private void initData() {
        leftAdapter = new ALeftAdapter(getContext());
        leftListView.setAdapter(leftAdapter);
        rightAdapter = new ARightAdapter(getContext());
        rightListView.setAdapter(rightAdapter);


        //设置相互滑动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);



        //获取数据
        getAsynHttp();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }

    }


    //获取数据
    private void getAsynHttp() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("StockID", "")
                .add("scn", "")
                .add("doa", "")
                .add("op", "fja")
                .build();
        Request request = new Request.Builder()
                .url("http://182.92.172.134:8014/ifoutservice.aspx")
                .post(formBody)
                .build();
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String str = response.body().string();
                try {
                    JSONObject object = new JSONObject(str);
                    int code = object.getInt("Code");
                    if (code == 0) {
                        models = new ArrayList<Fund>();
                        leftlList = new ArrayList<String>();
                        JSONArray array = object.getJSONArray("Data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = (JSONObject) array.get(i);
                            Fund fund = new Fund();
                            fund.setJsonObject(object1);
                            leftlList.add(fund.getFundAName());
                            models.add(fund);
                        }

                        handler.sendEmptyMessage(1);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });
    }
}
