package com.appdev.basedintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class BasedListFragment extends Fragment {

    private RecyclerView mBasedRecyclerView;
    private BasedAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mBasedRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);

        mBasedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class BasedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Based mBased;
        private TextView mTitleTextView;
        private TextView mDateTextView;


        public BasedHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_based, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }
        public void bind(Based based) {
            mBased = based;
            mTitleTextView.setText(mBased.getTitle());
            mDateTextView.setText(mBased.getDate().toString());
        }
        public void onClick(View view){
            Intent intent = BasedActivity.newIntent(getActivity(), mBased.getId());
            startActivity(intent);
        }
    }


    private class BasedAdapter extends RecyclerView.Adapter<BasedHolder> {
        private List<Based> mBaseds;

        public BasedAdapter(List<Based> crimes) {
            mBaseds = crimes;
        }

        @NonNull
        @Override
        public BasedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new BasedHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BasedHolder holder, int position) {
            Based based = mBaseds.get(position);
            holder.bind(based);
        }

        @Override
        public int getItemCount() {
            return mBaseds.size();
        }
    }

    private void updateUI(){
        BasedLab basedLab = BasedLab.get(getActivity());
        List<Based> baseds = basedLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new BasedAdapter((baseds));
            mBasedRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}
