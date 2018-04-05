package com.example.listview;

import android.graphics.Picture;

/**
 * See builderpattern example project for how to do builders
 * they are essential when constructing complicated objects and
 * with many optional fields
 */
public class BikeData {
    public static final int COMPANY = 0;
    public static final int MODEL = 1;
    public static final int PRICE = 2;
    public static final int LOCATION = 3;


    String company;
    String model;
    Double price;
    String description;
    String location;
    String date;
    String picture;
    String link;
    //TODO make all BikeData fields final

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String finish = "";
        String temp = String.valueOf(price);
        finish = "Company:" + company + "\n" + "Model:" + model + "\n" + "Price:" + "$" + temp + "Location:" + location
        + "Date Listed:" + date + "Description" + description + "Link" + link;

        return finish;
    }

    private BikeData(Builder b) {
        //TODO
        company = b.Company;
        model = b.Model;
        price = b.Price;
        description = b.Description;
        location = b.Location;
        date = b.Date;
        picture = b.Picture;
        link = b.Link;


    }

    public String getCompany() {
        return company;
    }

    public Double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getLocation() {
        return location;
    }

    public String getModel() {
        return model;
    }

    public String getPicture() {
        return picture;
    }

    /**
     * @author lynn builder pattern, see page 11 Effective Java UserData mydata
     *         = new
     *         UserData.Builder(first,last).addProject(proj1).addProject(proj2
     *         ).build()
     */
    public static class Builder {
        final String Company;
        final String Model;
        final Double Price;
        String Description;
        String Location;
        String Date;
        String Picture;
        String Link;

        // Model and price required
        Builder(String Company, String Model, Double Price) {
            this.Company = Company;
            this.Model = Model;
            this.Price = Price;
        }


        // the following are setters
        // notice it returns this builder
        // makes it suitable for chaining
        Builder setDescription(String Description) {
            //TODO manage this
            this.Description = Description;
            return this;
        }

        Builder setLocation(String Location) {
            this.Location = Location;
            return this;
        }

        Builder setDate(String Date) {
            this.Date = Date;
            return this;
        }

        Builder setPicture(String Picture) {
            this.Picture = Picture;
            return this;
        }

        Builder setLink(String Link) {
            this.Link = Link;
            return this;
        }

        // use this to actually construct Bikedata
        // without fear of partial construction
        public BikeData build() {
            return new BikeData(this);
        }
    }
}
