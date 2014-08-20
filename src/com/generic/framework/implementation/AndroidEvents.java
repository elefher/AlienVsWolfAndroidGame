package com.generic.framework.implementation;

import com.generic.framework.Events;
import com.kilobolt.framework.Input.TouchEvent;

public class AndroidEvents implements Events {
	@Override
	public boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}
}
