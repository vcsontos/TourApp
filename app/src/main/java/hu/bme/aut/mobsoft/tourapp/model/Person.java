package hu.bme.aut.mobsoft.tourapp.model;

import com.orm.dsl.Table;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class Person {

    private Long id = null;
    private PersonHeader personHeader;
    private String authToken;
    private String profilePhotoUrl;
    private int age;
    private Gender gender;
    private Experience experience;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonHeader getPersonHeader() {
        return personHeader;
    }

    public void setPersonHeader(PersonHeader personHeader) {
        this.personHeader = personHeader;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
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
}
