package com.appdev.basedintent;

public class BasedListActivity extends SingleFragmentActivity{

    @Override
    protected BasedListFragment createFragment(){
        return new BasedListFragment();
    }
}
