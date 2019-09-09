package com.trongdeptrai.soundcloud.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Navigator {

    public static  void loadChildFragment(FragmentManager fragmentManager, int containerViewId,
            Fragment fragment, boolean addToBackStack, String rootTag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
        if(currentFragment == null){
            currentFragment = fragment;
            transaction.add(containerViewId, fragment, fragment.getClass().getSimpleName());
        }
        if(addToBackStack){
            transaction.addToBackStack(rootTag);
        }else {
            showFragment(fragmentManager, currentFragment);
        }
        transaction.commit();
    }

    private static void showFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for(int i = 0; i < fragmentManager.getFragments().size(); i++){
            transaction.hide(fragmentManager.getFragments().get(i));
        }
        transaction.show(fragment);
        transaction.commit();
    }
    public static  void removeFragment(FragmentManager fragmentManager, String rootTag){
        fragmentManager.popBackStack(rootTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Fragment fragment = fragmentManager.findFragmentByTag(rootTag);
        showFragment(fragmentManager, fragment);
    }
}
