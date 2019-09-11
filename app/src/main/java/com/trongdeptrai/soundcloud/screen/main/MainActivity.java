package com.trongdeptrai.soundcloud.screen.main;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.screen.home.HomeFragment;
import com.trongdeptrai.soundcloud.screen.library.LibraryFragment;
import com.trongdeptrai.soundcloud.screen.search.SearchFragment;

import static com.trongdeptrai.soundcloud.utils.Navigator.loadFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.actionHome:
                loadFragment(getSupportFragmentManager(), R.id.frameLayoutMain,
                        HomeFragment.newInstance(), false, HomeFragment.class.getSimpleName());
                break;
            case R.id.actionSearch:
                loadFragment(getSupportFragmentManager(), R.id.frameLayoutMain,
                        SearchFragment.newInstance(), false, SearchFragment.class.getSimpleName());
                break;
            case R.id.actionLibrary:
                loadFragment(getSupportFragmentManager(), R.id.frameLayoutMain,
                        LibraryFragment.getInstance(), false,
                        LibraryFragment.class.getSimpleName());
                break;
        }
        return false;
    }

    private void initView() {
        BottomNavigationView navigationViewMain = findViewById(R.id.bottomNavigationMain);
        navigationViewMain.setOnNavigationItemSelectedListener(this);
        navigationViewMain.setSelectedItemId(R.id.actionHome);
    }
}
