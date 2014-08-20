package com.elefher.wolfhuntergame;

import com.elefher.wolfhuntergame.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Licences extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.licences);		
		
		TextView tv = (TextView) findViewById(R.id.gpl);
		tv.setText(Html.fromHtml(getString(R.string.GPL)));
		tv.setMovementMethod(new ScrollingMovementMethod());
	}
}
