package com.ms.cl.impl;

import com.ms.cl.ICamera;

public class CanonCamera implements ICamera {

	@Override
	public void takePhoto() {
		System.out.println("Photo taken from Canon camera!!");
	}
}
