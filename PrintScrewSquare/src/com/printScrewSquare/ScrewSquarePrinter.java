package com.printScrewSquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScrewSquarePrinter {
	/**
	 * 
	 * @param elementCount
	 *            sequence of characters to print
	 */
	public void printScrewSqare(int elementCount) {
		ArrayList<String> elements = generatePrintList(elementCount);
		int screwLayerCount = calculateScrewLayerCount(elements.size());

		Point maxPoint = new Point(screwLayerCount * 4, screwLayerCount * 4 + 1);
		Map<String, String> printMap = generatePrintMap(elements);
		
		for (int y = 0; y < maxPoint.getY(); y++) {
			for (int x = 0; x <= maxPoint.getX(); x++) {
				String key = new Point(x, y).toString();

				if (printMap.containsKey(key)) {
					System.out.print(" " + printMap.get(key) + " ");
				} else {
					System.out.print("   ");
				}
			}

			System.out.println();
		}
	}

	/**
	 * Save string pairs of number and its coordinate into print map
	 * 
	 * @param elements
	 *            sequence of characters to print
	 * @return a map contains pairs of x.y coordinates string and character
	 */
	private Map<String, String> generatePrintMap(ArrayList<String> elements) {
		Map<String, String> printMap = new HashMap<String, String>();
		int screwLayerCount = calculateScrewLayerCount(elements.size());

		int edgeElementCount = 3;
		Point printPoin = new Point(screwLayerCount * 2, screwLayerCount * 2);
		String[] printDirections = { "Right", "Down", "Left", "Up" };
		int directionIndex = 0;

		// The first screw edge is special, it need to print 1 more point, so
		// print the start point separately.
		// Then other point follows the same rule, and handled with a loop
		printMap.put(printPoin.toString(), elements.get(0));

		// Print edges by the sequence of Right, Down, Left and Up.
		// Repeat this process until all number in list are printed.
		for (int i = 1; i < elements.size();) {
			switch (printDirections[directionIndex]) {
			case "Right":
				for (int pi = 1; pi < edgeElementCount && i < elements.size(); pi++) {
					printPoin.move(1, 0);
					printMap.put(printPoin.toString(), elements.get(i++));
				}
				break;
			case "Down":
				for (int pi = 1; pi < edgeElementCount && i < elements.size(); pi++) {
					printPoin.move(0, 1);
					printMap.put(printPoin.toString(), elements.get(i++));
				}
				edgeElementCount += 2; // Increase by 2 before print Left edge.
				break;
			case "Left":
				for (int pi = 1; pi < edgeElementCount && i < elements.size(); pi++) {
					printPoin.move(-1, 0);
					;
					printMap.put(printPoin.toString(), elements.get(i++));
				}
				break;
			case "Up":
				for (int pi = 1; pi < edgeElementCount && i < elements.size(); pi++) {
					printPoin.move(0, -1);
					printMap.put(printPoin.toString(), elements.get(i++));
				}
				edgeElementCount += 2; // Increase by 2 before print Right edge.
				break;
			}

			directionIndex = (directionIndex + 1) % 4;
		}

		return printMap;
	}

	/**
	 * 
	 * @param elementCount
	 *            the count of elements to print in the screw square.
	 * @return screw layer count
	 */
	private int calculateScrewLayerCount(int elementCount) {
		// Number count that the current screw edge has. It increases by 2
		// every double edges. e.g. 3, 3, 5(3+2), 5, 7(5+2), 7...
		int edgeElementCount = 1;

		// The count of numbers that are used by screw
		int usedelementCount = 0;

		// Get the max edgeElementCount with a loop
		while (usedelementCount < elementCount) {
			edgeElementCount += 2;
			usedelementCount += edgeElementCount * 2;
		}

		int screwLayerCount = (edgeElementCount - 1) / 4;

		return screwLayerCount;
	}

	/**
	 * Generate a string list whose element count is elementCount
	 * 
	 * @param elementCount
	 *            the count of elements to print in the screw square.
	 * @return list of elements to print
	 */
	private ArrayList<String> generatePrintList(int elementCount) {
		ArrayList<String> elements = new ArrayList<String>();

		for (int asciiCode = 48; elements.size() <= elementCount; asciiCode++) {
			if (asciiCode > 122) {
				asciiCode = 48;
			}

			// numbers from 0 to 9, or upper chars from A to Z, or lower chars
			// from a to z
			if (asciiCode >= 48 && asciiCode <= 57 || asciiCode >= 65
					&& asciiCode <= 90 || asciiCode >= 97 && asciiCode <= 122) {
				elements.add(((char) asciiCode) + "");
			}
		}

		return elements;
	}
}