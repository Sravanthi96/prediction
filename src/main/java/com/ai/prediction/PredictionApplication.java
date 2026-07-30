package com.ai.prediction;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class PredictionApplication {
	public static void main(String[] args) {
        SpringApplication.run(PredictionApplication.class, args);
	}
}
