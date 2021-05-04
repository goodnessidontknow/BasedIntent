package com.appdev.basedintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

public class BasedListFragment extends Fragment {

    private RecyclerView mBasedRecyclerView;
    private BasedAdapter mAdapter;
    private int mLastClick = -1;
    private BasedLab mBasedLab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mBasedLab = BasedLab.get(getActivity());
        updateSubtitle();
    }

    private void updateSubtitle() {
        int basedCount = mBasedLab.getBasedActions().size();
        String subtitle = getString(R.string.based_count, basedCount);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(subtitle);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_based_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_based:
                Based based = new Based();
                mBasedLab.addBased(based);
                Intent intent = BasedPagerActivity.newIntent(getActivity(), based.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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
        updateSubtitle();
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
            mDateTextView.setText(DateFormat.getDateInstance().format(mBased.getDate()));
        }
        public void onClick(View view){
            mLastClick = getAdapterPosition();
            Intent intent = BasedPagerActivity.newIntent(getActivity(), mBased.getId());
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
        List<Based> baseds = basedLab.getBasedActions();

        if (mAdapter == null) {
            mAdapter = new BasedAdapter((baseds));
            mBasedRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
            /*if (mLastClick > 0){
                mAdapter.notifyItemChanged(mLastClick);
                mLastClick = -1;
            }*/
        }

        //
    }

}
