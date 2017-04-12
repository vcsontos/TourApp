package hu.bme.aut.mobsoft.tourapp.model;

import com.orm.dsl.Table;

import java.util.Date;
import java.util.List;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class Person {

    private Long id = null;
    private String personName;
    private String authToken;
    private Date expiredDate;
    private String profilePhotoUrl;
    private int age;
    private Gender gender;
    private Experience experience;
    private List<Tour> myTours;

    public Person() {
    }

    public Person(Long id, String personName) {
        this.id = id;
        this.personName = personName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public List<Tour> getMyTours() {
        return myTours;
    }

    public void setMyTours(List<Tour> myTours) {
        this.myTours = myTours;
    }
}
