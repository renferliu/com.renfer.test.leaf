package com.renfer.test.leaf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.renfer.test.leaf.product.FallingViews;
import com.xingfu.test.leaf.R;

public class MainActivity extends Activity {

	private LinearLayout container;
	private FallingViews view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		container = LinearLayout.class.cast(findViewById(R.id.container));
		view = new FallingViews(MainActivity.this);
		container.addView(view);

		Button.class.cast(findViewById(R.id.addOneLeaf)).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						container.removeAllViews();
						view = new FallingViews(MainActivity.this);
						container.addView(view);
					}
				});
		Button.class.cast(findViewById(R.id.delOneLeaf)).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						container.removeAllViews();

					}
				});

	}

}
