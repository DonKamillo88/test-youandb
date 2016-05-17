package com.waracle.androidtest.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waracle.androidtest.R;
import com.waracle.androidtest.util.DataManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by DonKamillo on 17.05.2016.
 */
public class MainFragment extends ListFragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    private CakeListAdapter mAdapter;

    public MainFragment() { /**/ }

    public void refreshData() {
        if (mAdapter != null) {
            mAdapter.refreshData();
            getData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create and set the list adapter.
        mAdapter = new CakeListAdapter(getActivity());
        setListAdapter(mAdapter);
        getData();
    }

    private void getData() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                // Load data from net.
                JSONArray array = null;
                try {
                    array = DataManager.loadData();
                } catch (IOException | JSONException e) {
                    Log.e(TAG, e.getMessage());
                }
                return array;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                mAdapter.setItems((JSONArray) o);
                mAdapter.notifyDataSetChanged();
            }
        };
        task.execute();
    }

}
