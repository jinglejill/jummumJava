package com.JummumCo.Jummum.Interface;


import com.JummumCo.Jummum.Fragment.BaseFragment;

/**
 * Created by Phitakphong on 16/6/2560.
 */

public interface IFragmentControlListener {
    void onChangeTitle(String title);
    void onGoNextFragment(BaseFragment fragment);
    void onGoBackFragment();
}

