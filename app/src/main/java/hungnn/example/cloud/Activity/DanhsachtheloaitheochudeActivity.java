package hungnn.example.cloud.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hungnn.example.cloud.Adapter.DanhsachtheloaitheochudeAdapter;
import hungnn.example.cloud.Model.ChuDe;
import hungnn.example.cloud.Model.TheLoai;
import hungnn.example.cloud.Service.APIService;
import hungnn.example.cloud.Service.Dataservice;
import hungnn.example.cloud.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

     ChuDe chuDe;
    RecyclerView recyclerViewtheloaitheochude;
    Toolbar toolbartheloaitheochude;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        init();
        GetData();
        linearLayout.setBackgroundResource(MainActivity.getRandomBackground());

    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<TheLoai>> callback=dataservice.GetTheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai= (ArrayList<TheLoai>) response.body();
//                Log.d("SSS", mangtheloai.get(0).getTenTheLoai());
                danhsachtheloaitheochudeAdapter=new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this, mangtheloai);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this, 2));
                recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        linearLayout=findViewById(R.id.linearlayoutDanhsachtheloaitheochude);
        recyclerViewtheloaitheochude=findViewById(R.id.recycleviewtheloaitheochude);
        toolbartheloaitheochude=findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            chuDe=(ChuDe)intent.getSerializableExtra("chude");
        }

    }
}
