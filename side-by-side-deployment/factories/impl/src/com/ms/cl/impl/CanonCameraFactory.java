package com.ms.cl.impl;

import com.ms.cl.ICamera;
import com.ms.cl.ICameraFactory;

public class CanonCameraFactory implements ICameraFactory {

	@Override
	public ICamera createCamera() {
		return new CanonCamera();
	}
}
