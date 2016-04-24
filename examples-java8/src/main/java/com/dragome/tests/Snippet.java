package com.dragome.tests;

import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import junit.framework.TestCase;

public class Snippet extends TestCase
{
	public void testGetElements() throws Exception
	{
		Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);

		WebClient webClient= new WebClient(BrowserVersion.CHROME);
		final HtmlPage page= webClient.getPage("file:///home/fernando/detodo/desarrollo/dragome/fix-examples/dragome-examples/examples-java8/target/examples-java8/timer-demo.html");
		page.executeJavaScript("");
		final HtmlButton div= page.getDocumentElement().querySelector("button[data-template='replaced: moveButton']");
		div.click();
		for (int i= 0; i < 10; i++)
		{
			final HtmlImage ball= page.getDocumentElement().querySelector("img[data-template='replaced: ball-image']");
			Thread.sleep(500);
			System.out.println(ball);
		}
	}
}
