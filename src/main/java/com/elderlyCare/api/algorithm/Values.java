package com.elderlyCare.api.algorithm;
import java.util.ArrayList;
import java.util.List;

public class Values {
    public String valueName;
    public List<String> classNames = new ArrayList<String>();
    public List<Integer> classCounts = new ArrayList<Integer>();
    public double gain = 0.0;
    public int totalInstances  = 0;

    public Values(String valName, String newClass){
        this.valueName = valName;
        this.classNames.add(newClass);
        this.classCounts.add(1);
    }

    public void calculateGain(int totalNumClasses) {
        double temp = 0.0;

        for (int count : classCounts) {
            double probability = (double) count / totalInstances;
            temp -= probability * (Math.log(probability) / Math.log(2));
        }
        gain = temp * ((double) totalInstances / totalNumClasses);
    }

    public void update(ValueNode valueNode) {
        int index = classNames.indexOf(valueNode.className);
        if (index != -1) {
            classCounts.set(index, classCounts.get(index) + 1);
        } else {
            classNames.add(valueNode.className);
            classCounts.add(1);
        }
        totalInstances++;
    }

}
