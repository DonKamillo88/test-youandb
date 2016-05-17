package com.waracle.androidtest.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.waracle.androidtest.R;
import com.waracle.androidtest.util.DataManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    public static class PlaceholderFragment extends ListFragment {

        private static final String TAG = PlaceholderFragment.class.getSimpleName();

        private MyAdapter mAdapter;

        public PlaceholderFragment() { /**/ }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // Create and set the list adapter.
            mAdapter = new MyAdapter(getActivity());
            setListAdapter(mAdapter);


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
}
