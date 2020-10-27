package hungnn.example.cloud.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hungnn.example.cloud.appnhac.Activity.DanhsachbaihatActivity;
import hungnn.example.cloud.appnhac.Model.Quangcao;
import hungnn.example.cloud.appnhac.R;

public class BannerAdapter extends PagerAdapter {
    Context context;

    ArrayList<Quangcao> arrayListBanner;

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner){
        this.context=context;
        this.arrayListBanner=arrayListBanner;
    }
    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_banner, null);

        ImageView imgbackgroundbanner= view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgsongbanner= view.findViewById(R.id.imageviewbanner);
        TextView txttitlesongbanner=view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtnoidung=view.findViewById(R.id.textviewnoidung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhanh()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaiHat()).into(imgsongbanner);
        txttitlesongbanner.setText(arrayListBanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListBanner.get(position).getNoidung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

}
