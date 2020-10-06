package hungnn.example.cloud.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hungnn.example.cloud.Adapter.MainViewPagerAdapter;
import hungnn.example.cloud.Fragment.Fragment_Tim_Kiem;
import hungnn.example.cloud.Fragment.Fragment_Trang_Chu;
import hungnn.example.cloud.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Chúc các bạn một ngày tốt lành.\nNghe nhạc vui vẻ.", Toast.LENGTH_SHORT).show();

        anhxa();
        init();
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
