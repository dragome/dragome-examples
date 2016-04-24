package com.dragome.examples.service.serverside;

import java.io.File;

import com.dragome.web.helpers.serverside.StandaloneDragomeAppGenerator;

public class TestCompiler1
{
	public static void main(String[] args)
	{
		File destinationDirectory= new File("./tests");
		File webappDirectory= new File("./src/main/webapp");
		new StandaloneDragomeAppGenerator(destinationDirectory, webappDirectory, true, true).execute();
	}
}
