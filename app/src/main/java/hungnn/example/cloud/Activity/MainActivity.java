package hungnn.example.cloud.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Random;

import hungnn.example.cloud.Adapter.MainViewPagerAdapter;
import hungnn.example.cloud.Fragment.Fragment_Tim_Kiem;
import hungnn.example.cloud.Fragment.Fragment_Trang_Chu;
import hungnn.example.cloud.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

     public static final ArrayList<Integer> arrayBackground= new ArrayList<Integer>();

    public static final Integer getRandomBackground() {
        Random randomPic=new Random();
        return  arrayBackground.get(randomPic.nextInt(arrayBackground.size()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeArrayBackgrouund();
        Toast.makeText(this, ""+MainActivity.getRandomBackground(),Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Chúc các bạn một ngày tốt lành.\nNghe nhạc vui vẻ.", Toast.LENGTH_SHORT).show();
        anhxa();
        init();
    }

    private void makeArrayBackgrouund() {
        arrayBackground.add(R.drawable.bg1);
        arrayBackground.add(R.drawable.bg2);
        arrayBackground.add(R.drawable.bg3);
        arrayBackground.add(R.drawable.bg4);
        arrayBackground.add(R.drawable.bg5);
        arrayBackground.add(R.drawable.bg6);
        arrayBackground.add(R.drawable.bg7);
        arrayBackground.add(R.drawable.bg8);
        arrayBackground.add(R.drawable.bg9);
        arrayBackground.add(R.drawable.bg10);
        arrayBackground.add(R.drawable.bg11);
        arrayBackground.add(R.drawable.bg12);
        arrayBackground.add(R.drawable.bg13);
        arrayBackground.add(R.drawable.bg14);
        arrayBackground.add(R.drawable.bg15);
        arrayBackground.add(R.drawable.bg16);
        arrayBackground.add(R.drawable.bg17);
        arrayBackground.add(R.drawable.bg18);
        arrayBackground.add(R.drawable.bg19);
        arrayBackground.add(R.drawable.bg20);
        arrayBackground.add(R.drawable.bg21);
    }


    private void init() {

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Home page");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Search");


        viewPager.setAdapter(mainViewPagerAdapter);
//        viewPager.setBackgroundResource(R.drawable.bg_search);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        tabLayout.setBackgroundResource(R.drawable.bg_search);
    }
    private void anhxa(){
        tabLayout=findViewById(R.id.myTabLayout);
        viewPager=findViewById(R.id.myViewPager);

    }
}
