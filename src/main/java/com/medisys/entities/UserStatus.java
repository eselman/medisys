package com.medisys.entities;

public enum UserStatus {

	ACTIVE("ACTIVE"), LOCKED("LOCKED");

	private String name;

	UserStatus(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
