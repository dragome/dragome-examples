package org.jbox2d.gwt.showcase.client.rendering;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.dragome.services.ServiceLocator;
import com.dragome.web.enhancers.jsdelegate.JsDelegateFactory;
import com.dragome.web.html.dom.html5canvas.CanvasRenderingContext2D;
import com.dragome.web.html.dom.html5canvas.HTMLCanvasElement;

public class CanvasDebugDraw extends DebugDraw
{
	private Vec2 sp1= new Vec2();
	private Vec2 sp2= new Vec2();

	public static final int DEFAULT_CANVAS_WIDTH= 640;
	public static final int DEFAULT_CANVAS_HEIGHT= 480;
	private HTMLCanvasElement canvas;
	private CanvasRenderingContext2D context;

	private int canvasWidth= DEFAULT_CANVAS_WIDTH;
	private int canvasHeight= DEFAULT_CANVAS_HEIGHT;
	private static Document document= ServiceLocator.getInstance().getDomHandler().getDocument();

	public CanvasDebugDraw(Element element)
	{
		super(new OBBViewportTransform());
		viewportTransform.setYFlip(true);
		viewportTransform.setExtents(canvasWidth / 2, canvasHeight / 2);
		viewportTransform.setCenter(canvasWidth / 2, canvasHeight / 2);
//		canvas= new CanvasElement(element);
		//		canvas= ElementTransformer.transformElementTo(element, HTMLCanvasElement.class);
		canvas= JsDelegateFactory.createFromNode(element, HTMLCanvasElement.class);

		if (canvas == null)
		{
			// TODO(pruggia): handle nice message
			throw new RuntimeException("Canvas not supported");
		}

		// init the canvas
		canvas.setWidth(canvasWidth);
		canvas.setHeight(canvasHeight);
		canvas.setCoordinateSpaceWidth(canvasWidth);
		canvas.setCoordinateSpaceHeight(canvasHeight);
		context= canvas.getContext("2d");
		context.setFont("10pt Arial");
	}

	public HTMLCanvasElement getCanvas()
	{
		return canvas;
	}

	@Override
	public void drawPoint(Vec2 argPoint, float argRadiusOnScreen, Color3f color)
	{
		getWorldToScreenToOut(argPoint, sp1);
		context.setFillStyle(colorFor(color, .4f));
		context.beginPath();
		context.arc(sp1.x, sp1.y, argRadiusOnScreen, 0, Math.PI * 2.0, true);
		context.closePath();
		context.fill();
	}

	@Override
	public void drawPolygon(Vec2[] vertices, int vertexCount, Color3f color)
	{
		if (vertexCount == 1)
		{
			drawSegment(vertices[0], vertices[0], color);
			return;
		}

		context.setStrokeStyle(colorFor(color, 1));
		context.beginPath();
		getWorldToScreenToOut(vertices[0], sp1);
		context.moveTo(sp1.x, sp1.y);
		for (int i= 0; i < vertexCount - 1; i+= 1)
		{
			getWorldToScreenToOut(vertices[i + 1], sp1);
			context.lineTo(sp1.x, sp1.y);
		}
		context.closePath();
		context.stroke();
	}

	@Override
	public void drawSolidPolygon(Vec2[] vertices, int vertexCount, Color3f color)
	{
		if (vertexCount == 1)
		{
			drawSegment(vertices[0], vertices[0], color);
			return;
		}

		context.setFillStyle(colorFor(color, .4f));
		context.setStrokeStyle(colorFor(color, 1));
		context.beginPath();
		getWorldToScreenToOut(vertices[0], sp1);
		context.moveTo(sp1.x, sp1.y);
		for (int i= 0; i < vertexCount - 1; i+= 1)
		{
			getWorldToScreenToOut(vertices[i + 1], sp1);
			context.lineTo(sp1.x, sp1.y);
		}
		context.closePath();
		context.fill();
		context.stroke();
	}

	@Override
	public void drawCircle(Vec2 center, float radius, Color3f color)
	{
		getWorldToScreenToOut(center, sp1);
		sp2.x= radius;
		sp2.y= 0;
		getWorldToScreenToOut(sp2, sp2);
		context.setStrokeStyle(colorFor(color, 1));
		context.beginPath();
		context.arc(sp1.x, sp1.y, sp2.x - (canvasWidth / 2), 0, Math.PI * 2.0, true);
		context.closePath();
		context.stroke();
	}

	@Override
	public void drawSolidCircle(Vec2 center, float radius, Vec2 axis, Color3f color)
	{
		getWorldToScreenToOut(center, sp1);
		sp2.x= radius;
		sp2.y= 0;
		getWorldToScreenToOut(sp2, sp2);
		context.setStrokeStyle(colorFor(color, 1));
		context.setFillStyle(colorFor(color, .4f));
		context.beginPath();
		context.arc(sp1.x, sp1.y, sp2.x - (canvasWidth / 2), 0, Math.PI * 2.0, true);
		context.closePath();
		context.fill();
		context.stroke();
	}

	@Override
	public void drawSegment(Vec2 p1, Vec2 p2, Color3f color)
	{
		getWorldToScreenToOut(p1, sp1);
		getWorldToScreenToOut(p2, sp2);
		context.setStrokeStyle(colorFor(color, 1));
		context.beginPath();
		context.moveTo(sp1.x, sp1.y);
		context.lineTo(sp2.x, sp2.y);
		context.stroke();
	}

	@Override
	public void drawTransform(Transform xf)
	{
		//TODO(pruggia): implement this
	}

	@Override
	public void drawString(float x, float y, String s, Color3f color)
	{
		context.setStrokeStyle(colorFor(color, 1));
		context.strokeText(s, x, y);
	}

	public void clear()
	{
		context.clearRect(0, 0, canvasWidth, canvasHeight);
		context.setFillStyle("rgba(0, 0, 0, 0.8)");
		context.fillRect(0, 0, canvasWidth, canvasHeight);
	}

	private String colorFor(Color3f color, float alpha)
	{
		return "rgba(" + (int) (color.x * 255) + "," + (int) (color.y * 255) + "," + (int) (color.z * 255) + "," + alpha + ")";
		//return CssColor.make((int) (color.x * 255), (int) (color.y * 255), (int) (color.z * 255));
	}
}
