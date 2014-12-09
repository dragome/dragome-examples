package com.dragome.examples.canvas.delegate;

import org.w3c.dom.html.HTMLElement;

public interface HTMLCanvasElement extends HTMLElement, CanvasImageSource
{
	int getWidth();
	void setWidth(int width);
	int getHeight();
	void setHeight(int height);
	CanvasRenderingContext2D getContext(String contextId);
	void setCoordinateSpaceWidth(int canvasWidth);
	void setCoordinateSpaceHeight(int canvasHeight);
}