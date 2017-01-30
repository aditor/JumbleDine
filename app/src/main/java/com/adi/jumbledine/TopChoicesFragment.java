package com.adi.jumbledine;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

/**
 * Created by Adi on 2017-01-29.
 */
public class TopChoicesFragment extends Fragment {

    private RecyclerView mChoicesRecyclerView;
    private ChoiceAdapter mAdapter;
    private static final String TAG = "TopChoicesFragment";

    public static TopChoicesFragment newInstance() {
        return new TopChoicesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_top_choices, container, false);
        mChoicesRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_choices_recycler_view);
        mChoicesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        //getting the singleton
        ChoiceLab choiceLab = ChoiceLab.get(getActivity());
        List<Choice> choices = choiceLab.getChoices();
        mAdapter = new ChoiceAdapter(choices);
        mChoicesRecyclerView.setAdapter(mAdapter);
        mAdapter.setTasks(choices);
    }



    //ViewHolder!
    private class ChoiceHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
        //public TextView mTitleTextView;
        private TextView mTypeTextView; //breakfast/lunch/dinner/dessert
        private TextView mTitleTextView; //name of restaurant
        private TextView mAddressTextView; //address
        private TextView mDistanceTextView; //distance from where you are

        private Choice mChoices;

        public ChoiceHolder(View itemView) {
            //make a viewholder with given View (itemview)
            super(itemView);
            //itemView.setOnClickListener(this);
            //mTitleTextView = (TextView) itemView;
            mTypeTextView = (TextView) itemView.findViewById(R.id.list_item_choice_type_text_view);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_choice_title_text_view);
            mAddressTextView= (TextView) itemView.findViewById(R.id.list_item_choice_address_text_view);
            mDistanceTextView= (TextView) itemView.findViewById(R.id.list_item_choice_distance_text_view);
        }

      /*  //TOAST
        @Override
        public void onClick(View v) {
            //get the ID of the task, call newIntent, and SEND the intent
            Intent intent = TaskActivity.newIntent(getActivity(), mTask.getId());
            startActivity(intent);
        }*/

        public void bindTask(Choice choice) {
            mChoices = choice;
            mTypeTextView.setText(mChoices.getType());
            mTitleTextView.setText(mChoices.getTitle());
            mAddressTextView.setText(mChoices.getAddress());
            mDistanceTextView.setText(mChoices.getDistance());
        }
    }

    //ADAPTER CLASS
    private class ChoiceAdapter extends RecyclerView.Adapter<ChoiceHolder> {
        private List<Choice> mChoices;

        public ChoiceAdapter(List<Choice> choices) {
            mChoices = choices;
        }

        //create a view and wrap it in a viewholder
        @Override
        public ChoiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //R.layout.list_item_task
            View view = layoutInflater.inflate(R.layout.list_item_choice, parent, false);
            return new ChoiceHolder(view);
        }
        //2. adapter looks up model OBJECT and BINDS it to the viewholder
        //position is INDEX of task in array
        @Override
        public void onBindViewHolder(ChoiceHolder holder, int position) {
            Choice choice = mChoices.get(position);
            //holder.mTitleTextView.setText(task.getTitle());
            holder.bindTask(choice);
        }

        //1. done FIRST
        @Override
        public int getItemCount() {
            return mChoices.size();
        }

        public void setTasks(List<Choice> choices) {
            mChoices = choices;
        }
    }

    //ASYNCHRONOUS
    private class FetchItemsTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String result = new YelpFetcher()
                        .getUrlString("https://www.bignerdranch.com");
                Log.i(TAG, "Fetched contents of URL: " + result);
            } catch (IOException ioe) {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
            return null;
        }
    }

}
