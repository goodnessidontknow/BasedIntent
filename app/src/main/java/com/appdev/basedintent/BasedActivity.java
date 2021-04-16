package com.appdev.basedintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.View;

import java.util.UUID;


public class BasedActivity extends SingleFragmentActivity {

    private static final String EXTRA_BASED_ID = "com.appdev.dasedintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID basedId) {
        Intent intent = new Intent(packageContext, BasedActivity.class);
        intent.putExtra(EXTRA_BASED_ID, basedId);
        return intent;
    }

    protected Fragment createFragment() {
        UUID basedId = (UUID) getIntent().getSerializableExtra(EXTRA_BASED_ID);
        return BasedFragment.newInstance(basedId);
    }
}