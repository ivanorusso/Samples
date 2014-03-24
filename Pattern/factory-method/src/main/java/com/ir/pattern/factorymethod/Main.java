package com.ir.pattern.factorymethod;

public class Main {
	public static void main(String[] args) {
		//posso prescindere dall oggetto che richiamo
		Dog dog = DogFactory.getDog("small");
		dog.abbaia();
	}
}
