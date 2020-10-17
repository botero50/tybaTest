package org.tyba.common;

public class dataCalculationMethods {

	public static double round(double value, int places) {
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static String formatPriceValue(double number) {
		 return String.format("$ %,.0f", number);
		}
}