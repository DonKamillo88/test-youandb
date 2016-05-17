package com.waracle.androidtest.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waracle.androidtest.R;
import com.waracle.androidtest.ui.model.Cake;
import com.waracle.androidtest.util.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DonKamillo on 17.05.2016.
 */
public class CakeListAdapter extends BaseAdapter {

    // Can you think of a better way to represent these items???
    private JSONArray mItems;
    private ImageLoader mImageLoader;
    private Activity activity;


    public CakeListAdapter(Activity activity) {
        this(new JSONArray(), activity);
    }

    public CakeListAdapter(JSONArray items, Activity activity) {
        mItems = items;
        mImageLoader = new ImageLoader();
        this.activity = activity;
    }

    public void refreshData() {
        if (mImageLoader != null) {
            mImageLoader.clearCache();
        }
    }

    @Override
    public int getCount() {
        if (mItems == null) return 0;
        return mItems.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return mItems.getJSONObject(position);
        } catch (JSONException e) {
            Log.e("", e.getMessage());
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View root = inflater.inflate(R.layout.list_item_layout, parent, false);
        if (root != null) {
            TextView title = (TextView) root.findViewById(R.id.title);
            TextView desc = (TextView) root.findViewById(R.id.desc);
            ImageView image = (ImageView) root.findViewById(R.id.image);

            Cake cake = Cake.cakeFactory((JSONObject) getItem(position));
            if (cake != null) {
                title.setText(cake.getTitle());
                desc.setText(cake.getDesc());
                mImageLoader.load(cake.getImage(), image);
            }
        }
        return root;
    }

    public void setItems(JSONArray items) {
        mItems = items;
    }
}