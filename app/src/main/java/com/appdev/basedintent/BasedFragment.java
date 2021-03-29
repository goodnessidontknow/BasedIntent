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

public class BasedFragment extends Fragment {

    private Based mBased;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mBasedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBased = new Based();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_based,container,false);

        mTitleField = v.findViewById(R.id.crime_title);
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
        mBasedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mBased.setBased(isChecked));

        return v;
    }
}