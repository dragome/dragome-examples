package com.dragome.examples.canvas.impl;

import org.w3c.dom.Element;

import com.dragome.commons.javascript.ScriptHelper;
import com.dragome.examples.canvas.delegate.CanvasGradient;
import com.dragome.examples.canvas.delegate.CanvasImageSource;
import com.dragome.examples.canvas.delegate.CanvasPattern;
import com.dragome.examples.canvas.delegate.CanvasRenderingContext2D;
import com.dragome.examples.canvas.delegate.HTMLCanvasElement;
import com.dragome.examples.canvas.delegate.ImageData;
import com.dragome.examples.canvas.delegate.ImageElement;
import com.dragome.examples.canvas.delegate.TextMetrics;

public class Context2d implements CanvasRenderingContext2D
{
	public static String CONTEXT_ID= "2d";

	public Context2d()
	{
	}

	public Context2d(Object context)
	{
		ScriptHelper.put("context", context, this);
		ScriptHelper.eval("this.jsDelegate= context", this);
	}

	public void arc(double x, double y, double radius, double startAngle, double endAngle)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("radius", radius + "", this);
		ScriptHelper.put("startAngle", startAngle + "", this);
		ScriptHelper.put("endAngle", endAngle + "", this);
		ScriptHelper.put("clock", false + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.arc(x, y, radius, startAngle, endAngle, clock)", this);

	}

	public void arc(double x, double y, double radius, double startAngle, double endAngle, boolean anticlockwise)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("radius", radius + "", this);
		ScriptHelper.put("startAngle", startAngle + "", this);
		ScriptHelper.put("endAngle", endAngle + "", this);
		ScriptHelper.put("anticlockwise", anticlockwise + "", this);
		ScriptHelper.eval("this.jsDelegate.arc(x, y, radius, startAngle, endAngle, anticlockwise)", this);

	}

	public void arcTo(double x1, double y1, double x2, double y2, double radius)
	{
		ScriptHelper.put("x1", x1 + "", this);
		ScriptHelper.put("y1", y1 + "", this);
		ScriptHelper.put("x2", x2 + "", this);
		ScriptHelper.put("y2", y2 + "", this);
		ScriptHelper.put("radius", radius + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.arcTo(x1, y1, x2, y2, radius)", this);

	}

	public void beginPath()
	{
		ScriptHelper.eval("this.jsDelegate.beginPath()", this);

	}

	public void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y)
	{
		ScriptHelper.put("cp1x", cp1x + "", this);
		ScriptHelper.put("cp1y", cp1y + "", this);
		ScriptHelper.put("cp2x", cp2x + "", this);
		ScriptHelper.put("cp2y", cp2y + "", this);
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y)", this);

	}

	public void clearRect(double x, double y, double w, double h)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("w", w + "", this);
		ScriptHelper.put("h", h + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.clearRect(x, y, w, h)", this);

	}

	public void clip()
	{
		ScriptHelper.evalNoResult("this.jsDelegate.clip()", this);

	}

	public void closePath()
	{
		ScriptHelper.eval("this.jsDelegate.closePath()", this);

	}

	public ImageData createImageData(ImageData imagedata)
	{
		ScriptHelper.put("imagedata", imagedata + "", this);
		return (ImageData) ScriptHelper.eval("this.jsDelegate.createImageData(imagedata)", this);

	}

	public ImageData createImageData(int w, int h)
	{
		ScriptHelper.put("w", w + "", this);
		ScriptHelper.put("h", h + "", this);
		return (ImageData) ScriptHelper.eval("this.jsDelegate.createImageData(w, h)", this);

	}

	public CanvasGradient createLinearGradient(double x0, double y0, double x1, double y1)
	{
		ScriptHelper.put("x0", x0 + "", this);
		ScriptHelper.put("y0", y0 + "", this);
		ScriptHelper.put("x1", x1 + "", this);
		ScriptHelper.put("y1", y1 + "", this);
		return (CanvasGradient) ScriptHelper.eval("this.jsDelegate.createLinearGradient(x0, y0, x1, y1)", this);

	}

	public CanvasPattern createPattern(ImageElement image, String repetition)
	{
		ScriptHelper.put("image", image + "", this);
		ScriptHelper.put("repetition", repetition + "", this);
		return (CanvasPattern) ScriptHelper.eval("this.jsDelegate.createPattern(image, repetition)", this);

	}

	public CanvasGradient createRadialGradient(double x0, double y0, double r0, double x1, double y1, double r1)
	{
		ScriptHelper.put("x0", x0 + "", this);
		ScriptHelper.put("y0", y0 + "", this);
		ScriptHelper.put("r0", r0 + "", this);
		ScriptHelper.put("x1", x1 + "", this);
		ScriptHelper.put("y1", y1 + "", this);
		ScriptHelper.put("r1", r1 + "", this);
		return (CanvasGradient) ScriptHelper.eval("this.jsDelegate.createRadialGradient(x0, y0, r0, x1, y1, r1)", this);

	}

	public void drawImage(CanvasElement image, double dx, double dy)
	{
		ScriptHelper.put("image", image + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.drawImage(image, dx, dy)", this);

	}

	public void drawImage(CanvasElement image, double dx, double dy, double dw, double dh)
	{
		ScriptHelper.put("image", image + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.put("dw", dw + "", this);
		ScriptHelper.put("dh", dh + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.drawImage(image, dx, dy, dw, dh)", this);

	}

	public void drawImage(CanvasElement image, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh)
	{
		ScriptHelper.put("image", image + "", this);
		ScriptHelper.put("sx", sx + "", this);
		ScriptHelper.put("sy", sy + "", this);
		ScriptHelper.put("sw", sw + "", this);
		ScriptHelper.put("sh", sh + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.put("dw", dw + "", this);
		ScriptHelper.put("dh", dh + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.drawImage(image, sx, sy, sw, sh, dx, dy, dw, dh)", this);

	}

	public void drawImage(ImageElement image, double dx, double dy)
	{
		ScriptHelper.put("image", image + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.drawImage(image, dx, dy)", this);

	}

	public void drawImage(ImageElement image, double dx, double dy, double dw, double dh)
	{
		ScriptHelper.put("image", image + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.put("dw", dw + "", this);
		ScriptHelper.put("dh", dh + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.drawImage(image, dx, dy, dw, dh)", this);

	}

	public void drawImage(ImageElement image, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh)
	{
		ScriptHelper.put("image", image + "", this);
		ScriptHelper.put("sx", sx + "", this);
		ScriptHelper.put("sy", sy + "", this);
		ScriptHelper.put("sw", sw + "", this);
		ScriptHelper.put("sh", sh + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.put("dw", dw + "", this);
		ScriptHelper.put("dh", dh + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.drawImage(image, sx, sy, sw, sh, dx, dy, dw, dh)", this);

	}

	public void fill()
	{
		ScriptHelper.evalNoResult("this.jsDelegate.fill()", this);

	}

	public void fillRect(double x, double y, double w, double h)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("w", w + "", this);
		ScriptHelper.put("h", h + "", this);
		ScriptHelper.eval("this.jsDelegate.fillRect(x, y, w, h)", this);

	}

	public void fillText(String text, double x, double y)
	{
		ScriptHelper.put("text", text + "", this);
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.fillText(text, x, y)", this);
	}

	public void fillText(String text, double x, double y, double maxWidth)
	{
		ScriptHelper.put("text", text + "", this);
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("maxWidth", maxWidth + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.fillText(text, x, y, maxWidth)", this);

	}

	public String getFont()
	{
		return (String) ScriptHelper.eval("this.jsDelegate.font", this);

	}

	public double getGlobalAlpha()
	{
		return ScriptHelper.evalDouble("this.globalAlpha", this);

	}

	public String getGlobalCompositeOperation()
	{
		return (String) ScriptHelper.eval("this.jsDelegate.globalCompositeOperation", this);

	}

	public ImageData getImageData(double sx, double sy, double sw, double sh)
	{
		ScriptHelper.put("sx", sx + "", this);
		ScriptHelper.put("sy", sy + "", this);
		ScriptHelper.put("sw", sw + "", this);
		ScriptHelper.put("sh", sh + "", this);
		return (ImageData) ScriptHelper.eval("this.jsDelegate.getImageData(sx, sy, sw, sh)", this);

	}

	public String getLineCap()
	{
		return (String) ScriptHelper.eval("this.jsDelegate.lineCap", this);

	}

	public String getLineJoin()
	{
		return (String) ScriptHelper.eval("this.jsDelegate.lineJoin", this);

	}

	public double getLineWidth()
	{
		return ScriptHelper.evalDouble("this.lineWidth", this);

	}

	public double getMiterLimit()
	{
		return ScriptHelper.evalDouble("this.miterLimit", this);

	}

	public double getShadowBlur()
	{
		return ScriptHelper.evalDouble("this.shadowBlur", this);

	}

	public String getShadowColor()
	{
		return (String) ScriptHelper.eval("this.jsDelegate.shadowColor", this);

	}

	public double getShadowOffsetX()
	{
		return ScriptHelper.evalDouble("this.shadowOffsetX", this);

	}

	public double getShadowOffsetY()
	{
		return ScriptHelper.evalDouble("this.shadowOffsetY", this);

	}

	public String getTextAlign()
	{
		return (String) ScriptHelper.eval("this.jsDelegate.textAlign", this);

	}

	public String getTextBaseline()
	{
		return (String) ScriptHelper.eval("this.jsDelegate.textBaseline", this);

	}

	public boolean isPointInPath(double x, double y)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		return ScriptHelper.evalBoolean("this.isPointInPath(x, y)", this);

	}

	public void lineTo(double x, double y)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.eval("this.jsDelegate.lineTo(x, y)", this);

	}

	public TextMetrics measureText(String text)
	{
		ScriptHelper.put("text", text + "", this);
		return (TextMetrics) ScriptHelper.eval("this.jsDelegate.measureText(text)", this);

	}

	public void moveTo(double x, double y)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.eval("this.jsDelegate.moveTo(x, y)", this);

	}

	public void putImageData(ImageData imagedata, double x, double y)
	{
		ScriptHelper.put("imagedata", imagedata + "", this);
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.putImageData(imagedata, x, y)", this);

	}

	public void quadraticCurveTo(double cpx, double cpy, double x, double y)
	{
		ScriptHelper.put("cpx", cpx + "", this);
		ScriptHelper.put("cpy", cpy + "", this);
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.quadraticCurveTo(cpx, cpy, x, y)", this);

	}

	public void rect(double x, double y, double w, double h)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("w", w + "", this);
		ScriptHelper.put("h", h + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.rect(x, y, w, h)", this);

	}

	public void restore()
	{
		ScriptHelper.evalNoResult("this.jsDelegate.restore()", this);

	}

	public void rotate(double angle)
	{
		ScriptHelper.put("angle", angle + "", this);
		ScriptHelper.eval("this.jsDelegate.rotate(angle)", this);

	}

	public void save()
	{
		ScriptHelper.eval("this.jsDelegate.save()", this);

	}

	public void scale(double x, double y)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.eval("this.jsDelegate.scale(x, y)", this);
	}

	public void setFillStyle(String fillStyleColor)
	{
		ScriptHelper.put("fillStyleColor", fillStyleColor + "", this);
		ScriptHelper.eval("this.jsDelegate.fillStyle = fillStyleColor", this);
	}

	public void setFont(String f)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.font = f", this);

	}

	public void setGlobalAlpha(double alpha)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.globalAlpha = alpha", this);

	}

	public void setGlobalCompositeOperation(String globalCompositeOperation)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.globalCompositeOperation = globalCompositeOperation", this);

	}

	public void setLineCap(String lineCap)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.lineCap = lineCap", this);

	}

	public void setLineJoin(String lineJoin)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.lineJoin = lineJoin", this);

	}

	public void setLineWidth(double lineWidth)
	{
		ScriptHelper.put("lineWidth", lineWidth + "", this);
		ScriptHelper.eval("this.jsDelegate.lineWidth = lineWidth", this);
	}

	public void setMiterLimit(double miterLimit)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.miterLimit = miterLimit", this);

	}

	public void setShadowBlur(double shadowBlur)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.shadowBlur = shadowBlur", this);

	}

	public void setShadowColor(String shadowColor)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.shadowColor = shadowColor", this);

	}

	public void setShadowOffsetX(double shadowOffsetX)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.shadowOffsetX = shadowOffsetX", this);

	}

	public void setShadowOffsetY(double shadowOffsetY)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.shadowOffsetY = shadowOffsetY", this);

	}

	public void setStrokeStyle(String strokeStyleColor)
	{
		ScriptHelper.put("strokeStyleColor", strokeStyleColor + "", this);
		ScriptHelper.eval("this.jsDelegate.strokeStyle = strokeStyleColor", this);
	}

	public void setTextAlign(String align)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.textAlign = align", this);

	}

	public void setTextBaseline(String baseline)
	{
		ScriptHelper.evalNoResult("this.jsDelegate.textBaseline = baseline", this);

	}

	public void setTransform(double m11, double m12, double m21, double m22, double dx, double dy)
	{
		ScriptHelper.put("m11", m11 + "", this);
		ScriptHelper.put("m12", m12 + "", this);
		ScriptHelper.put("m21", m21 + "", this);
		ScriptHelper.put("m22", m22 + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.setTransform(m11, m12, m21, m22, dx, dy)", this);

	}

	public void stroke()
	{
		ScriptHelper.eval("this.jsDelegate.stroke()", this);

	}

	public void strokeRect(double x, double y, double w, double h)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("w", w + "", this);
		ScriptHelper.put("h", h + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.strokeRect(x, y, w, h)", this);

	}

	public void strokeText(String text, double x, double y)
	{
		ScriptHelper.put("text", text + "", this);
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.strokeText(text, x, y)", this);

	}

	public void strokeText(String text, double x, double y, double maxWidth)
	{
		ScriptHelper.put("text", text + "", this);
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.put("maxWidth", maxWidth + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.strokeText(text, x, y, maxWidth)", this);

	}

	public void transform(double m11, double m12, double m21, double m22, double dx, double dy)
	{
		ScriptHelper.put("m11", m11 + "", this);
		ScriptHelper.put("m12", m12 + "", this);
		ScriptHelper.put("m21", m21 + "", this);
		ScriptHelper.put("m22", m22 + "", this);
		ScriptHelper.put("dx", dx + "", this);
		ScriptHelper.put("dy", dy + "", this);
		ScriptHelper.evalNoResult("this.jsDelegate.transform(m11, m12, m21, m22, dx, dy)", this);

	}

	public void translate(double x, double y)
	{
		ScriptHelper.put("x", x + "", this);
		ScriptHelper.put("y", y + "", this);
		ScriptHelper.eval("this.jsDelegate.translate(x, y)", this);

	}

	@Override
	public boolean isPointInStroke(double x, double y)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void scrollPathIntoView()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public ImageData createImageData(double width, double height)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CanvasPattern createPattern(CanvasImageSource image, String repetition)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void drawImage(CanvasImageSource image, double dx, double dy)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void drawImage(CanvasImageSource image, double dx, double dy, double dw, double dh)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void drawImage(CanvasImageSource image, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean drawCustomFocusRing(Element element)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void drawSystemFocusRing(Element element)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void putImageData(ImageData imagedata, double dx, double dy, double dirtyX, double dirtyY, double dirtyWidth, double dirtyHeight)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void strokeText(String text, float x, float y, float maxWidth)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void strokeText(String text, float x, float y)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getFillStyle()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFillStyle(CanvasGradient gradient)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setFillStyle(CanvasPattern pattern)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public float getLineDashOffset()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLineDashOffset(float lineDashOffset)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public String getStrokeStyle()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStrokeStyle(CanvasGradient gradient)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setStrokeStyle(CanvasPattern pattern)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public HTMLCanvasElement getCanvas()
	{
		// TODO Auto-generated method stub
		return null;
	}
}