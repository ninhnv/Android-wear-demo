package com.example.androidweardemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class News_Activity extends Activity implements WearableListView.ClickListener  {

    private WearableListView mListView;
    private MyListAdapter mAdapter;

    private float mDefaultCircleRadius;
    private float mSelectedCircleRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mDefaultCircleRadius = getResources().getDimension(R.dimen.default_settings_circle_radius);
        mSelectedCircleRadius = getResources().getDimension(R.dimen.selected_settings_circle_radius);
        mAdapter = new MyListAdapter();

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mListView = (WearableListView) stub.findViewById(R.id.listView1);
                mListView.setAdapter(mAdapter);
                mListView.setClickListener(News_Activity.this);
            }
        });
    }

    private static ArrayList<Integer> listItems;
    static {
        listItems = new ArrayList<Integer>();
        listItems.add(R.drawable.vnexpress);
        listItems.add(R.drawable.bg);
        listItems.add(R.drawable.bg2);
        listItems.add(R.drawable.bg3);
        listItems.add(R.drawable.vne1);
        listItems.add(R.drawable.vne2);
        listItems.add(R.drawable.bg);
        listItems.add(R.drawable.vne2);
        listItems.add(R.drawable.vnexpress);
        listItems.add(R.drawable.bg2);
        listItems.add(R.drawable.bg3);
        listItems.add(R.drawable.vnexpress);
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
//        Toast.makeText(this, String.format("You selected item #%s", viewHolder.getPosition()), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTopEmptyRegionClick() {
        Toast.makeText(this, "You tapped Top empty area", Toast.LENGTH_SHORT).show();
    }

    public class MyListAdapter extends WearableListView.Adapter {

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new WearableListView.ViewHolder(new MyItemView(News_Activity.this));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {
            MyItemView itemView = (MyItemView) viewHolder.itemView;

            TextView txtView = (TextView) itemView.findViewById(R.id.text);
            txtView.setText(String.format("Tuần hành lớn nhất lịch sử Pháp, phản đối khủng bố"));
            
            TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
            txtTitle.setText(String.format(""+i));
            
            TextView txtCat = (TextView) itemView.findViewById(R.id.cat);
            txtCat.setText(String.format("Xã Hội"));

            Integer resourceId = listItems.get(i);
            ImageView imgView = (ImageView) itemView.findViewById(R.id.image);
            imgView.setImageResource(resourceId);
//            CircledImageView imgViewCircle = (CircledImageView) itemView.findViewById(R.id.image2);
//            imgViewCircle.setImageResource(resourceId);
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }
    }

    private final class MyItemView extends FrameLayout implements WearableListView.Item {

//    	final CircledImageView imgViewCircle;
    	final ImageView  imgView;
        final TextView txtView, txtTitle, txtCat;
        private float mScale;
        private final int mFadedCircleColor;
        private final int mChosenCircleColor;

        public MyItemView(Context context) {
            super(context);
            View.inflate(context, R.layout.news_item_layout, this);
            imgView = (ImageView) findViewById(R.id.image);
//            imgViewCircle = (CircledImageView) findViewById(R.id.image2);
            txtView = (TextView) findViewById(R.id.text);
            txtTitle = (TextView) findViewById(R.id.title);
            txtCat = (TextView) findViewById(R.id.cat);
            mFadedCircleColor = getResources().getColor(android.R.color.darker_gray);
            mChosenCircleColor = getResources().getColor(android.R.color.holo_blue_dark);
        }

        @Override
        public float getProximityMinValue() {
            return mDefaultCircleRadius;
        }

        @Override
        public float getProximityMaxValue() {
            return mSelectedCircleRadius;
        }

        @Override
        public float getCurrentProximityValue() {
            return mScale;
        }

        @Override
        public void setScalingAnimatorValue(float value) {
            mScale = value;
//            imgViewCircle.setCircleRadius(mScale);
//            imgViewCircle.setCircleRadiusPressed(mScale);
        }

        @Override
        public void onScaleUpStart() {
//            imgView.setAlpha(0.5f);
            txtView.setAlpha(1f);
            txtTitle.setAlpha(1f);
            txtCat.setAlpha(1f);
//            imgViewCircle.setCircleColor(mChosenCircleColor);
        }

        @Override
        public void onScaleDownStart() {
//            imgView.setAlpha(0.5f);
            txtView.setAlpha(0.5f);
            txtCat.setAlpha(0.5f);
            txtTitle.setAlpha(0.5f);
//            imgViewCircle.setCircleColor(mFadedCircleColor);
        }
    }
}
