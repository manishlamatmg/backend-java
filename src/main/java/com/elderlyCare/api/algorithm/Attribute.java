package com.elderlyCare.api.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attribute {

    public String attributeName;
    public List<Values> values = new ArrayList<Values>();
    public double informationGain = 0.0;

    public Attribute(String attributeName){
        this.attributeName = attributeName;
    }

    public double calculateInformationGain(double entropyOfDataset, int totalNumClasses){
        int totalValClasses = 0;
        for(Values v : values){
            v.calculateGain(totalNumClasses);
            for(int i : v.classCounts){
                totalValClasses += i;
            }
            informationGain += (totalValClasses/(double)totalNumClasses) * v.gain;
            totalValClasses = 0;
        }
        return entropyOfDataset - this.informationGain; // directly return informationGain
    }


    public void insertVal(ValueNode inValueNode){
        if(this.values.isEmpty()){
            values.add(new Values(inValueNode.valueName, inValueNode.className));
        }
        else{
            for(Values v : values){
                if(v.valueName.equals(inValueNode.valueName)){
                    v.update(inValueNode);
                    return;
                }
            }
            values.add(new Values(inValueNode.valueName, inValueNode.className));
        }
    }

    public String toString(){
        String out = new String("attribute: " + this.attributeName + "\n");
        for(Values v : values){
            out += "\tvalue: " + v.valueName + ", ";
            out += "\n\t\tclasses: ";
            for(String c : v.classNames){
                out += c + ", ";
            }
            out += "\n\t\tcounts: ";
            for(Integer i : v.classCounts){
                out += i + ", ";
            }
            out += "\n\t\tlevel: " + getLevel(); // Add level information
            out += "\n";
        }

        return out;
    }

    public String getLevel() {
        int maxIndex = 0;
        int maxCount = 0;
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).classCounts.size() > maxCount) {
                maxIndex = i;
                maxCount = values.get(i).classCounts.size();
            }
        }
        return values.get(maxIndex).valueName;
    }
}
