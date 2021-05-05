package com.appdev.basedintent;

import androidx.fragment.app.Fragment;

public class BasedListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new BasedListFragment();
    }
}
