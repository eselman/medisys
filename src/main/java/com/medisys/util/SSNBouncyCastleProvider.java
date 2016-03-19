package com.medisys.util;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SSNBouncyCastleProvider {
	public void init() {
		Security.addProvider(new BouncyCastleProvider());
	}
}
