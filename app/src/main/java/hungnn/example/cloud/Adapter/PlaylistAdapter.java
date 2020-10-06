package hungnn.example.cloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import hungnn.example.cloud.Model.Playlist;
import hungnn.example.cloud.R;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.dong_playlist, null);
            
            viewHolder=new ViewHolder();
            viewHolder.txttenplaylist=convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgplaylist=convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgbackgound=convertView.findViewById(R.id.imageviewbackgroundplaylist);


            convertView.setTag(viewHolder);

        }
        else
            viewHolder= (ViewHolder) convertView.getTag();

        Playlist playlist=getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlayList()).into(viewHolder.imgbackgound);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgplaylist);
        viewHolder.txttenplaylist.setText(playlist.getTen());

        return  convertView;





    }

    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackgound, imgplaylist;
    }

}
