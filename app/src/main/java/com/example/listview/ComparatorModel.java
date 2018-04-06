package com.example.listview;

import java.util.Comparator;

public class ComparatorModel implements Comparator<BikeData> {



    @Override
    public int compare(BikeData myData1, BikeData myData2) {

        if (myData1.equals(myData2)){
            return 0;
        }
        return(myData1.model.compareTo(myData2.model));
    }



}
