package com.trongdeptrai.soundcloud.screen.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.trongdeptrai.soundcloud.R;
import com.trongdeptrai.soundcloud.screen.BaseFragment;

public class SearchFragment extends BaseFragment {

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void initView(View v) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
