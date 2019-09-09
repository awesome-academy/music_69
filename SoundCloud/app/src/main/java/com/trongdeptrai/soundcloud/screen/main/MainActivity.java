package com.trongdeptrai.soundcloud.screen.main;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.screen.home.HomeFragment;
import com.trongdeptrai.soundcloud.screen.library.LibraryFragmnet;
import com.trongdeptrai.soundcloud.screen.main.adapter.MainViewPagerAdapter;
import com.trongdeptrai.soundcloud.screen.search.SearchFragment;
import com.trongdeptrai.soundcloud.screen.splash.SplashFragment;

import static com.trongdeptrai.soundcloud.utils.Navigator.loadChildFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {
    private BottomNavigationView mBnvMain;
    private ViewPager mViewPager;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadChildFragment(getSupportFragmentManager(), R.id.relative_container,
                    new SplashFragment(), false, "");
        }
        initView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.mn_home:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.mn_search:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.mn_library:
                mViewPager.setCurrentItem(2);
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            mBnvMain.getMenu().getItem(0).setChecked(false);
        }
        Log.d("page", "onPageSelected: " + position);
        mBnvMain.getMenu().getItem(position).setChecked(true);
        prevMenuItem = mBnvMain.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initView() {
        mBnvMain = findViewById(R.id.btvMain);
        mViewPager = findViewById(R.id.viewpagerMain);
        mBnvMain.setOnNavigationItemSelectedListener(this);
        setupViewPager();
    }

    private void setupViewPager() {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.initFragment(new HomeFragment(), String.valueOf(R.string.tab_name_home));
        adapter.initFragment(new SearchFragment(), String.valueOf(R.string.tab_name_search));
        adapter.initFragment(new LibraryFragmnet(), String.valueOf(R.string.tab_name_library));
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
    }
}
