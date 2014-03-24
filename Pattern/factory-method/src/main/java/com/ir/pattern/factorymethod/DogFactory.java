package com.ir.pattern.factorymethod;

public class DogFactory {

	public static Dog getDog(String criteria) {
		if (criteria.equals("small"))
			return new Chiwawa();
		else if (criteria.equals("big"))
			return new Alano();
		else if (criteria.equals("medium"))
			return new Dalmata();

		return null;
	}

}
