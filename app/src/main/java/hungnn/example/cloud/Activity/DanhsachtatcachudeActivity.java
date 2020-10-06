package hungnn.example.cloud.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hungnn.example.cloud.Adapter.DanhsachtatcachudeAdapter;
import hungnn.example.cloud.Model.ChuDe;
import hungnn.example.cloud.Service.APIService;
import hungnn.example.cloud.Service.Dataservice;
import hungnn.example.cloud.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {
    RecyclerView recyclerViewtatcacacchude;
    Toolbar toolbartatcachude;
    DanhsachtatcachudeAdapter danhsachtatcachudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<ChuDe>> callback=dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude= (ArrayList<ChuDe>) response.body();
                danhsachtatcachudeAdapter=new DanhsachtatcachudeAdapter(DanhsachtatcachudeActivity.this, mangchude);
                recyclerViewtatcacacchude.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this, 1));
                recyclerViewtatcacacchude.setAdapter(danhsachtatcachudeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }


    private void init() {
        recyclerViewtatcacacchude=findViewById(R.id.recyclerviewAllChude);
        toolbartatcachude=findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbartatcachude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All category");
        toolbartatcachude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
