package com.trongdeptrai.soundcloud.screen.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.trongdeptrai.soundcloud.R;
import java.util.Objects;

public class SplashFragment extends Fragment {
    private static final Long TIME_OUT = 2500L; // 2.5 giay

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Objects.requireNonNull(getActivity())
//                        .getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.relative_container, new HomeFragment())
//                        .commit();
                Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .remove(SplashFragment.this)
                        .commit();
            }
        }, TIME_OUT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
