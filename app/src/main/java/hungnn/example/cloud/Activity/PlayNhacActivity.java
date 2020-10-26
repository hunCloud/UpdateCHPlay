package hungnn.example.cloud.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import hungnn.example.cloud.Adapter.ViewPagerPlaylistnhac;
import hungnn.example.cloud.Fragment.Fragment_Dia_Nhac;
import hungnn.example.cloud.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import hungnn.example.cloud.Model.Baihat;
import hungnn.example.cloud.R;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgpre, imgnext, imgrepeat, imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat=new ArrayList<>();
    public static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dia_Nhac fragmentDiaNhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragmentPlayDanhSachCacBaiHat;
    MediaPlayer mediaPlayer;
    int pos=0;
    boolean repeat=false;
    boolean checkrandom=false;
    boolean next=false;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);




        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        mediaPlayer=new MediaPlayer();
        GetDataFromIntent();
        init();
        linearLayout.setBackgroundResource(MainActivity.getRandomBackground());
        eventClick();


    }
    private void init() {
        linearLayout=findViewById(R.id.linearlayoutPlaynhac);
        toolbarplaynhac=findViewById(R.id.toolbarplaynhac);
        txtTotaltimesong=findViewById(R.id.textviewtotaltimesong);
        txtTimesong=findViewById(R.id.textviewtimesong);
        sktime=findViewById(R.id.seekbarsong);
        imgplay=findViewById(R.id.imagebuttonplay);
        imgnext=findViewById(R.id.imagebuttonnext);
        imgpre=findViewById(R.id.imagebuttonpre);
        imgrandom=findViewById(R.id.imagebuttonsuffle);
        imgrepeat=findViewById(R.id.imagebuttonrepeat);
        viewPagerplaynhac=findViewById(R.id.viewpagerplaynhac);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragmentDiaNhac=new Fragment_Dia_Nhac();
        fragmentPlayDanhSachCacBaiHat=new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac=new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragmentPlayDanhSachCacBaiHat);
        adapternhac.AddFragment(fragmentDiaNhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragmentDiaNhac= (Fragment_Dia_Nhac) adapternhac.getItem(1);

        if(mangbaihat.size()>0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }
    private void eventClick() {
         final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1)!=null){
                    if(mangbaihat.size()>0){
                        fragmentDiaNhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }
                    else{
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                }
                else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if(checkrandom == true){
                        checkrandom=false;
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                }
                imgrepeat.setImageResource(R.drawable.iconsyned);
                repeat = true;
            }
            else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat=false;
                }
            }

        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false) {
                    if(repeat == true){
                        repeat=false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }
                else{
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom=false;
                }

            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying()||mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(pos<mangbaihat.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        ++pos;
                        if(repeat==true){
                            if(pos==0){
                                pos=mangbaihat.size();
                            }
                            pos-=1;
                        }
                        if(checkrandom==true){
                            Random random=new Random();
                            int index=random.nextInt(mangbaihat.size());
                            if(index==pos){
                                index--;
                            }
                            pos=index;
                        }
                        if(pos>mangbaihat.size()-1){
                            pos=0;
                        }
                        new PlayMp3().execute(mangbaihat.get(pos).getLinkbaihat());
                        fragmentDiaNhac.PlayNhac(mangbaihat.get(pos).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(pos).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 1000);
            }
        });
        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying()||mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(pos<mangbaihat.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        --pos;
                        if(pos<0){
                            pos=mangbaihat.size()-1;
                        }
                        if(repeat==true){

                            pos+=1;
                        }
                        if(checkrandom==true){
                            Random random=new Random();
                            int index=random.nextInt(mangbaihat.size());
                            if(index==pos){
                                index--;
                            }
                            pos=index;
                        }

                        new PlayMp3().execute(mangbaihat.get(pos).getLinkbaihat());
                        fragmentDiaNhac.PlayNhac(mangbaihat.get(pos).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(pos).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1=new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 1000);
            }
        });

    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();

        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next=true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next==true){
//                    if(mangbaihat.size()>0){
//                        if(mediaPlayer.isPlaying()||mediaPlayer!=null){
//                            mediaPlayer.stop();
//                            mediaPlayer.release();
//                            mediaPlayer=null;
//                        }
                        if(pos<mangbaihat.size()){
                            imgplay.setImageResource(R.drawable.iconpause);
                            ++pos;

                            if(repeat==true){
                                if(pos==0){
                                    pos=mangbaihat.size();
                                }

                                pos--;
                            }
                            if(checkrandom==true){
                                Random random=new Random();
                                int index=random.nextInt(mangbaihat.size());
                                if(index==pos)
                                   index--;


                                pos=index;
                            }
                            if(pos>mangbaihat.size()-1)
                                pos=0;

                            new PlayMp3().execute(mangbaihat.get(pos).getLinkbaihat());
                            fragmentDiaNhac.PlayNhac(mangbaihat.get(pos).getHinhbaihat());
                            getSupportActionBar().setTitle(mangbaihat.get(pos).getTenbaihat());
                        }

                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1=new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 1000);

                    next=false;
                    handler1.removeCallbacks(this);

                }
                else{
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
