package com.appdev.basedintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class BasedFragment extends Fragment {

    private static final String ARG_BASED_ID = "based_id";

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
        mBased = BasedLab.get(getActivity()).getCrime(basedId);
    }

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
            public void afterTextChanged(Editable s) {}
        });

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mBased.getDate().toString());
        mDateButton.setEnabled(false);

        mBasedCheckBox = v.findViewById(R.id.action_based);
        mBasedCheckBox.setChecked(mBased.isBased());
        mBasedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mBased.setBased(isChecked));

        return v;
    }
}