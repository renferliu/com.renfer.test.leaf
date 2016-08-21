package com.renfer.test.leaf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.renfer.test.leaf.product.FallingViews;
import com.xingfu.test.leaf.R;

public class MainActivity extends Activity {
	
	private int rValue;
	private int gValue;
	private int bValue;

	private TextView tvNum;
	private LinearLayout container;
	private FallingViews view;
	private SeekBar seekBarR;
	private SeekBar seekBarG;
	private SeekBar seekBarB;
	
	private TextView tvR;
	private TextView tvG;
	private TextView tvB;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvNum = TextView.class.cast(findViewById(R.id.leafNum));
		container = LinearLayout.class.cast(findViewById(R.id.container));
		seekBarR = SeekBar.class.cast(findViewById(R.id.seebarR));
		seekBarG = SeekBar.class.cast(findViewById(R.id.seebarG));
		seekBarB = SeekBar.class.cast(findViewById(R.id.seebarB));
		tvR = TextView.class.cast(findViewById(R.id.seebar_tvR));
		tvG = TextView.class.cast(findViewById(R.id.seebar_tvG));
		tvB = TextView.class.cast(findViewById(R.id.seebar_tvB));
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
		
		Button.class.cast(findViewById(R.id.sure)).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						view.settingColor(rValue, gValue, bValue);
					}
				});
		seekBarR.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				rValue = progress;
				tvR.setText("Red范围："+progress+"-256");
			}
		});
		seekBarG.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				gValue = progress;
				tvG.setText("Green范围："+progress+"-256");
			}
		});
		seekBarB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				bValue = progress;
				tvB.setText("Blue范围："+progress+"-256");
			}
		});
	}

}
