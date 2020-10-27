package hungnn.example.cloud.appnhac.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hungnn.example.cloud.appnhac.Adapter.BaihathotAdapter;
import hungnn.example.cloud.appnhac.Model.Baihat;
import hungnn.example.cloud.appnhac.Service.APIService;
import hungnn.example.cloud.appnhac.Service.Dataservice;
import hungnn.example.cloud.appnhac.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewbaihathot;
    BaihathotAdapter baihathotAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    view=inflater.inflate(R.layout.fragment_bai_hat_thich_nhat, container, false);
    recyclerViewbaihathot=view.findViewById(R.id.recycleviewbaihathot);
    GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();

        Call<List<Baihat>> callback=dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList= (ArrayList<Baihat>) response.body();

                baihathotAdapter=new BaihathotAdapter(getActivity(), baihatArrayList);

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewbaihathot.setLayoutManager(linearLayoutManager);
                recyclerViewbaihathot.setAdapter(baihathotAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
