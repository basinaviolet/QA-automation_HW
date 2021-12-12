package runner;

import triangle.Triangle;

public class Main {


	public static void main(String[] args) {

		Triangle tr = new Triangle(1.0, 2.0, 2.0);
		System.out.println("1:" + tr.checkTriangle());
		System.out.println("2:" + tr.getMessage());
		System.out.println("3:" + tr.detectTriangle());
	}

}
