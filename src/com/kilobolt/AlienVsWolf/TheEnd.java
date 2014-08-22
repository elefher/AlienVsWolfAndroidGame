package com.kilobolt.AlienVsWolf;

import com.kilobolt.AlienVsWolf.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TheEnd extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.theend);		
		
		TextView tv = (TextView) findViewById(R.id.theend);
		tv.setText(Html.fromHtml(getString(R.string.theend)));
		tv.setMovementMethod(new ScrollingMovementMethod());
	}
}
