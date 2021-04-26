package com.appdev.basedintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class BasedPagerActivity extends AppCompatActivity {

    private static final String EXTRA_BASED_ID = "com.appdev.basedintent.based_id";

    private ViewPager mViewPager;
    private List<Based> mBased;

    public static Intent newIntent(Context packageContext, UUID basedId) {
        Intent intent = new Intent(packageContext, BasedPagerActivity.class);
        intent.putExtra(EXTRA_BASED_ID, basedId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_based_pager);

        UUID basedId = (UUID) getIntent().getSerializableExtra(EXTRA_BASED_ID);

        mViewPager = findViewById(R.id.based_view_pager);

        mBased = BasedLab.get(this).getBasedActions();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Based based = mBased.get(position);
                return BasedFragment.newInstance(based.getId());
            }

            @Override
            public int getCount() {
                return mBased.size();
            }
        });

        for (int i = 0; i < mBased.size(); i++) {
            if (mBased.get(i).getId().equals(basedId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
