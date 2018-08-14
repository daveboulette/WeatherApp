package com.whweather.springboot.model;

public class BadZip extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8447171051177607436L;

	public BadZip(String s) {
        // Call constructor of parent Exception
        super(s);
    }
}