package com.dragome.example;

import java.io.IOException;

import proguard.ProGuard;

public class ProguardRunner
{
	public static void main(String[] args) throws IOException
	{
		ProGuard.main(new String[]{"@proguard.conf"});
	}

}
