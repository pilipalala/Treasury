package com.umeng.soexample.object;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ItemDecorationActivity extends BaseActivity {

    @BindView(R.id.object_recycleView)
    RecyclerView objectRecycleView;
    /*请求联网的url*/
    public String url = "http://api.meituan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi20CGXQPPZvhCU3%2FkzdE%3D";

    private ItemDecorationAdapter adapter;
    List<WaitMVBean.DataBean.ComingBean> comingList;
    ArrayList<NameBean> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_decoration);
        ButterKnife.bind(this);
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ItemDecorationActivity.this, "联网失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //联网成功后使用fastjson解析
                processData(response.body().string());
            }
        });


    }

    private void processData(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /*使用GsonFormat生成对应的bean类  */
                JSONObject object = JSON.parseObject(string);
                String data = object.getString("data");

                JSONObject dataObject = JSON.parseObject(data);
                String coming = dataObject.getString("coming");

                comingList = JSON.parseArray(coming, WaitMVBean.DataBean.ComingBean.class);
                setPullAction(comingList);
                adapter = new ItemDecorationAdapter(ItemDecorationActivity.this, comingList);

                objectRecycleView.addItemDecoration(new SectionDecoration(dataList, ItemDecorationActivity.this, new SectionDecoration.DecorationCall() {
                    @Override
                    public String getGroupId(int position) {
                        if (dataList.get(position).getName() != null) {
                            return dataList.get(position).getName();
                        }
                        return "-1";
                    }

                    @Override
                    public String getGroupFirstLine(int position) {
                        if (dataList.get(position).getName() != null) {
                            return dataList.get(position).getName();
                        }
                        return "";
                    }
                }));
                objectRecycleView.setAdapter(adapter);
                objectRecycleView.setLayoutManager(new GridLayoutManager(ItemDecorationActivity.this, 1));

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setPullAction(List<WaitMVBean.DataBean.ComingBean> comingslist) {
        dataList = new ArrayList<>();
        for (int i = 0; i < comingslist.size(); i++) {
            NameBean nameBean = new NameBean();
            String name0 = comingslist.get(i).getComingTitle();
            nameBean.setName(name0);
            dataList.add(nameBean);
        }
    }
}
