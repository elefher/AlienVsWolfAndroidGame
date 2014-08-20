package com.generic.framework;

import com.kilobolt.framework.Input.TouchEvent;

public interface Events {
	public boolean inBounds(TouchEvent event, int x, int y, int width, int height);
}
