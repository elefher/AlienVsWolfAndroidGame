package com.generic.framework.implementation;

import android.graphics.Color;
import android.graphics.Paint;

import com.generic.framework.PaintStrings;

public class AndroidPaintStrings implements PaintStrings {

	@Override
	public Paint PaintStrings(int color, int size, Paint.Align align) {
		Paint paint = new Paint();
		paint.setTextSize(size);
		paint.setTextAlign(align);
		paint.setAntiAlias(true);
		paint.setColor(color);
		return paint;
	}
}
