package com.printScrewSquare;

public class PrintScrewSquare {
	/**
	 * @param args
	 *            input elements count that used to print screw square
	 */
	public static void main(String[] args) {
		ScrewSquarePrinter printer = new ScrewSquarePrinter();
		int elementCount=200;

		if (args.length > 0) {
			try {
				elementCount = Integer.parseInt(args[0]);
			} catch (NumberFormatException exp) {
				System.out.println("Exception: " + exp.getMessage());
			}
		} 

		printer.printScrewSqare(elementCount);
	}
}
