package com.trongdeptrai.soundcloud.screen;

import android.view.View;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment  extends Fragment {
    public abstract  void initView(View v);
    public  abstract void initData();
    public  abstract void initListener();

}
