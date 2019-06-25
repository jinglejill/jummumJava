package com.JummumCo.Jummum.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JummumCo.Jummum.Interface.IFragmentControlListener;
import com.android.jummum.R;

import java.util.Stack;

import butterknife.ButterKnife;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressLint("ValidFragment")
public class WrapperFragment
        extends BaseFragment
        implements IFragmentControlListener {

    private Fragment startFragment;
    private Stack<Fragment> fragmentStack;
    private FragmentManager fm;

    public WrapperFragment(Fragment startFragment) {
        super();
        this.startFragment = startFragment;
    }

    public static WrapperFragment newInstance(Fragment startFragment) {

        WrapperFragment fragment = new WrapperFragment(startFragment);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wrapper, container, false);
        initInstances(rootView, savedInstanceState);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        fragmentStack = new Stack<>();
        fm = getChildFragmentManager();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        fragmentStack.push(startFragment);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, startFragment);
        ft.commit();
    }

    @Override
    public void onChangeTitle(String title) {

    }

    @Override
    public void onGoNextFragment(BaseFragment fragment) {
        fragment.setFromBackPressed(false);
        fragmentStack.push(fragment);
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    @Override
    public void onGoBackFragment() {
        if (fragmentStack.size() > 1) {
            fragmentStack.pop();
            BaseFragment fragment = (BaseFragment)fragmentStack.get(fragmentStack.size() - 1);
            fragment.setFromBackPressed(true);
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

    public Fragment getCurrentFragment() {
        if (fragmentStack.size() == 0) {
            return null;
        }
        return fragmentStack.get(fragmentStack.size() - 1);
    }

}
