package com.reportportal.launches.utils;

import java.util.List;
import java.util.Random;

import lombok.experimental.UtilityClass;


@UtilityClass
public class RandomUtils {

	private final Random rand = new Random();

	public static int getRandomValueExceptList(List<Integer> values) {
		int randomNum;
		do {
			randomNum = rand.nextInt();
		}
		while (values.contains(randomNum));
		return randomNum;
	}
}
