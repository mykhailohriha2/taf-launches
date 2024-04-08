package com.reportportal.launches.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;


@UtilityClass
public class FileUtils {
	public static List<Object[]> readFromCsvFile(String csvFile){
		List<Object[]> list = new ArrayList<Object[]>();
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line.split(","));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
