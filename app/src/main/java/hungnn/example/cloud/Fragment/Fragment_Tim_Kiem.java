package hungnn.example.cloud.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hungnn.example.cloud.Activity.MainActivity;
import hungnn.example.cloud.Adapter.SearchBaiHatAdapter;
import hungnn.example.cloud.Model.Baihat;
import hungnn.example.cloud.Service.APIService;
import hungnn.example.cloud.Service.Dataservice;
import hungnn.example.cloud.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewsearchbaihat;
    TextView txtkhongcodulieu;
    SearchBaiHatAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar=view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearchbaihat=view.findViewById(R.id.recycleviewsearchbaihat);

        recyclerViewsearchbaihat.setBackgroundResource(MainActivity.getRandomBackground());
        txtkhongcodulieu=view.findViewById(R.id.textviewkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Click search icon to find a song.");
        setHasOptionsMenu(true);
        return view;
    }

    // fix error here
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem=menu.findItem(R.id.menu_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTuKhoaBaiHat(String query){
        Dataservice dataservice= APIService.getService();
        Call<List<Baihat>> callback=dataservice.GetSearchBaiHat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihat= (ArrayList<Baihat>) response.body();
                if(mangbaihat.size()>0){
                    searchBaiHatAdapter=new SearchBaiHatAdapter(getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                    recyclerViewsearchbaihat.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchbaihat.setAdapter(searchBaiHatAdapter);

                    txtkhongcodulieu.setVisibility(View.GONE);
                    recyclerViewsearchbaihat.setVisibility(View.VISIBLE);

                }
                else{
                    recyclerViewsearchbaihat.setVisibility(View.GONE);
                    txtkhongcodulieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
