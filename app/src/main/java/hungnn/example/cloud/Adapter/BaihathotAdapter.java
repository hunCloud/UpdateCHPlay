package hungnn.example.cloud.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hungnn.example.cloud.Activity.PlayNhacActivity;
import hungnn.example.cloud.Model.Baihat;
import hungnn.example.cloud.Service.APIService;
import hungnn.example.cloud.Service.Dataservice;
import hungnn.example.cloud.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> mangbaihathot;
    RecyclerView recyclerViewbaihathot;
    BaihathotAdapter baihathotAdapter;

    public  BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList){
        this.context=context;
        this.mangbaihathot=baihatArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_bai_hat_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat=mangbaihathot.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txtten.setText((baihat.getTenbaihat()));
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imghinh);

    }

    @Override
    public int getItemCount() {
        return mangbaihathot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten, txtcasi;
        ImageView imghinh, imgluotthich;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtten=itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi=itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh=itemView.findViewById(R.id.imageviewwbaihathot);
            imgluotthich=itemView.findViewById(R.id.imageviewluotthich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihathot.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice= APIService.getService();
                    Call<String> callback=dataservice.UpdateLuotThich("1", mangbaihathot.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            if(ketqua.equals("Success")){
                                Toast.makeText(context, "Liked", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(context, "Nothing", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);

                }
            });
        }
    }
}