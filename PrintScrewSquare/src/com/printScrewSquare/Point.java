package com.printScrewSquare;


class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void move(int xOffset, int yOffset) {
		x += xOffset;
		y += yOffset;
	}	
	

	/**
	 * Generate a unique string for the x and y combination
	 */
	public String toString() {
		return this.x + "." + this.y;
	}
}