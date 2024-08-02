package com.elderlyCare.api.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C4_5 {

    public static double calculateEntropy(List<Integer> classesCount) {
        double entropy = 0.0;
        int totalNumClasses = classesCount.stream().mapToInt(Integer::intValue).sum();

        for (int count : classesCount) {
            double probability = (double) count / totalNumClasses;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }
        return entropy;
    }

    public static String usingAlgorithmToRecommendDiet(int age) throws FileNotFoundException {
        String files[] = {"src/main/resources/templates/elderData.csv"};

        Scanner scan = null;

        try {
            scan = new Scanner(new File(files[0]));
            String headerLine = scan.nextLine();
            String[] headers = headerLine.split(",");
            int classIndex = headers.length - 1;
            int ageIndex = -1; // Initialize age index
            int numAttributes = headers.length;

            // Find the index of the age attribute
            for (int i = 0; i < numAttributes; i++) {
                if (headers[i].equalsIgnoreCase("age")) {
                    ageIndex = i;
                    break;
                }
            }

            if (ageIndex == -1) {
                return "Error: Age attribute not found in dataset.";
            }

            Attribute[] attributes = new Attribute[numAttributes]; // Initialize attributes array

            List<String> classes = new ArrayList<>();
            List<Integer> classesCount = new ArrayList<>();

            while (scan.hasNextLine()) {
                String inLine = scan.nextLine();
                String[] lineData = inLine.split(",");

                // Check if the age matches the provided age parameter
                if (lineData[ageIndex].trim().equalsIgnoreCase(String.valueOf(age))) {
                    String classValue = lineData[classIndex];
                    int classCountIndex = classes.indexOf(classValue);
                    if (classCountIndex == -1) {
                        classes.add(classValue);
                        classesCount.add(1);
                    } else {
                        classesCount.set(classCountIndex, classesCount.get(classCountIndex) + 1);
                    }

                    for (int x = 0; x < numAttributes; x++) {
                        if (attributes[x] == null) {
                            attributes[x] = new Attribute(headers[x]);
                        }
                        ValueNode data = new ValueNode(lineData[x], lineData[classIndex]);
                        attributes[x].insertVal(data);
                    }
                }
            }

            double entropy = calculateEntropy(classesCount);
            // Assume further processing here for decision tree construction
            // For now, let's directly handle age

            // Handle case where no data is found for the provided age
            if (classes.isEmpty()) {
                return "No data found for the provided age.";
            }

            double ageGain = attributes[ageIndex].calculateInformationGain(entropy, classesCount.size());
            return suggestFoodBasedOnAge(ageGain, age);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file: " + e.getMessage();
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }

    public static String suggestFoodBasedOnAge(double ageGain, int age) {
        System.out.println(ageGain);
        if (age < 10) {
            return "Age out of range for dietary recommendation.";
        }

        if (age == 10) {
            if (ageGain < 0.3) {
                return " It is recommended to include a balanced diet with controlled carbohydrate intake.";
            } else {
                return "For your age group, focus on a diet rich in fruits, vegetables, and lean proteins to support growth and development.";
            }
        } else if (age <= 12) {
            if (ageGain < 0.5) {
                return "Considering your age, it is advised to focus on a diet rich in antioxidants and nutrients. Include more fruits, vegetables, and whole grains.";
            } else {
                return "For pre-teenagers, a well-balanced diet with adequate protein, healthy fats, and carbohydrates is essential for growth and energy.";
            }
        } else if (age <= 14) {
            if (ageGain < 0.7) {
                return "Given your age and blood pressure levels, it is suggested to follow a heart-healthy diet. Include foods with omega-3 fatty acids, such as fatty fish and nuts.";
            } else {
                return "For teenagers, a balanced diet with plenty of fruits, vegetables, whole grains, lean proteins, and healthy fats supports overall health and well-being.";
            }
        } else {
            if (ageGain < 0.5) {
                return "Based on your age and blood pressure levels, it is recommended to include a balanced diet with controlled carbohydrate intake.";
            } else {
                return "Focus on a diet rich in fruits, vegetables, and lean proteins";
            }
        }
    }
}