package hungnn.example.cloud.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hungnn.example.cloud.Activity.DanhsachbaihatActivity;
import hungnn.example.cloud.Model.Album;
import hungnn.example.cloud.R;

public class AlbumAdapter  extends  RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> mangalbum;


    public AlbumAdapter(Context context, ArrayList<Album> mangalbum){
        this.context=context;
        this.mangalbum=mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Album album=mangalbum.get(position);
    holder.txtcasialbum.setText(album.getTencasiAlbum());
    holder.txttenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhanhAlbum()).into(holder.imghinhalbum);
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttenalbum, txtcasialbum;
        ImageView imghinhalbum;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
txttenalbum=itemView.findViewById(R.id.textviewtenalbum);
txtcasialbum=itemView.findViewById(R.id.textviewtencasialbum);
            imghinhalbum=itemView.findViewById(R.id.imageviewalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album", mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }

}
