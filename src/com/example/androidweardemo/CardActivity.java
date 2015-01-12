package com.example.androidweardemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.CardScrollView;
import android.view.Gravity;

public class CardActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.default_layout);

//	    CardScrollView cardScrollView =
//	        (CardScrollView) findViewById(R.id.card_scroll_view);
//	    cardScrollView.setCardGravity(Gravity.BOTTOM);
	}

}
