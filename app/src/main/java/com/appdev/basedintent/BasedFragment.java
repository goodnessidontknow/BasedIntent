package com.appdev.basedintent;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class BasedFragment extends Fragment implements DatePickerFragment.OnDateUpdateListener {

    private static final String ARG_BASED_ID = "based_id";
    private static final String DIALOG_DATE = "dialog_date";

    private static final int REQUEST_DATE = 0;

    private Based mBased;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mBasedCheckBox;

    public static BasedFragment newInstance(UUID basedId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BASED_ID, basedId);

        BasedFragment fragment = new BasedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID basedId = (UUID) getArguments().getSerializable(ARG_BASED_ID);
        mBased = BasedLab.get(getActivity()).getBased(basedId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_based,container,false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mBased.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBased.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(DateFormat.getDateInstance().format(mBased.getDate()));
        mDateButton.setOnClickListener((view) -> {
            DatePickerFragment dialog = DatePickerFragment.newInstance(mBased.getDate());
            dialog.setTargetFragment(BasedFragment.this, REQUEST_DATE);
            FragmentManager fm = getParentFragmentManager();
            dialog.show(fm, DIALOG_DATE);
        });

        mBasedCheckBox = v.findViewById(R.id.action_based);
        mBasedCheckBox.setChecked(mBased.isBased());
        mBasedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mBased.setBased(isChecked));

        return v;
    }

    @Override
    public void onDateUpdate(Date date) {
        mBased.setDate(date);
        mDateButton.setText(mBased.getDate().toString());
    }
}