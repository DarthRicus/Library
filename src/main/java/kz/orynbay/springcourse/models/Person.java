package kz.orynbay.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {
    private int personId;
    @NotEmpty(message = "Your full name shouldn't be empty!")
    private String fullName;
    @Min(value =  1900,message = "Year of birth should be greater than 1900!")
    @Max(value = 2018, message = "Year of birth shouldn't be greater than 2018!")
    private int yob; // yob - year of birth

    public Person(){

    }

    public Person(int personId, String fullName, int yob) {
        this.personId = personId;
        this.fullName = fullName;
        this.yob = yob;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }
}
