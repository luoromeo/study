package com.luoromeo.study.test.future.example;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月23日 09:54
 * @modified By
 */
public class Car {

    private Integer id;

    private Integer manufacturerId;

    private String model;

    private Integer year;

    private Float rating;

    public Car(Integer id, Integer manufacturerId, String model, Integer year) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car (id=" + id + ", manufacturerId=" + manufacturerId + ", model=" + model + ", year=" + year + ", rating=" + rating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Float getRating() {
        return rating;
    }
}
