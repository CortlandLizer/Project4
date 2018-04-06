package com.example.listview;

import java.util.Comparator;

public class ComparatorPrice implements Comparator<BikeData> {



    @Override
    public int compare(BikeData myData1, BikeData myData2) {

        if (myData1.equals(myData2)){
            return 0;
        }
        return(myData1.price.compareTo(myData2.price));
    }



}
