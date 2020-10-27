package hungnn.example.cloud.appnhac.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hungnn.example.cloud.appnhac.Activity.DanhsachtatcaAlbumActivity;
import hungnn.example.cloud.appnhac.Adapter.AlbumAdapter;
import hungnn.example.cloud.appnhac.Model.Album;
import hungnn.example.cloud.appnhac.Service.APIService;
import hungnn.example.cloud.appnhac.Service.Dataservice;
import hungnn.example.cloud.appnhac.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewalbum;
    TextView txtxemthemalbum;
    AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_album_hot, container, false);
        recyclerViewalbum=view.findViewById(R.id.recycleviewAlbum);
        txtxemthemalbum=view.findViewById(R.id.textviewxemthemAlbum);
//        mappingToLayout();

        txtxemthemalbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), DanhsachtatcaAlbumActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void mappingToLayout() {
        recyclerViewalbum=view.findViewById(R.id.recycleviewAlbum);
        txtxemthemalbum=view.findViewById(R.id.textviewxemthemAlbum);
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callback=dataservice.GetAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();

                albumAdapter=new AlbumAdapter(getActivity(), albumArrayList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager((getActivity()));
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerViewalbum.setLayoutManager(linearLayoutManager);
                recyclerViewalbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
