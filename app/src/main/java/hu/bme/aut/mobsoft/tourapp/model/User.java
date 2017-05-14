package hu.bme.aut.mobsoft.tourapp.model;

import com.google.gson.annotations.SerializedName;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(name = "User")
@ApiModel(description = "")
public class User {

    @Column(name = "person_id", unique = true, notNull = true)
    @SerializedName("person_id")
    private String personId = null;

    @Column(name = "person_name", unique = true, notNull = true)
    @SerializedName("person_name")
    private String personName = null;

    @SerializedName("auth_token")
    private String authToken = null;

    @SerializedName("expired_date")
    private Date expiredDate = null;

    @SerializedName("profile_photo_url")
    private String profilePhotoUrl = null;

    @SerializedName("age")
    private Integer age = null;

    @SerializedName("gender")
    private Gender gender = null;

    @SerializedName("experience")
    private Experience experience = null;

    @SerializedName("my_tours")
    private List<Tour> myTours = new ArrayList<Tour>();

    public User() {
    }

    public User(String personId, String personName) {
        this.personId = personId;
        this.personName = personName;
    }

    /**
     * Unique identifier representing a specific person.
     **/
    @ApiModelProperty(required = true, value = "Unique identifier representing a specific person.")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }


    /**
     * Name of person
     **/
    @ApiModelProperty(required = true, value = "Name of person")
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }


    /**
     * Every login generate a new token which identify the person
     **/
    @ApiModelProperty(value = "Every login generate a new token which identify the person")
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    /**
     * Expired date of authorization token
     **/
    @ApiModelProperty(value = "Expired date of authorization token")
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }


    /**
     * Image URL representing the person.
     **/
    @ApiModelProperty(value = "Image URL representing the person.")
    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }


    /**
     * Age of the person
     **/
    @ApiModelProperty(value = "Age of the person")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    /**
     * Gender of the person [male, female]
     **/
    @ApiModelProperty(value = "Gender of the person [male, female]")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    /**
     * Experience of the person [beginner, advanced, master]
     **/
    @ApiModelProperty(value = "Experience of the person [beginner, advanced, master]")
    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public List<Tour> getMyTours() {
        return myTours;
    }

    public void setMyTours(List<Tour> myTours) {
        this.myTours = myTours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (personId != null ? !personId.equals(user.personId) : user.personId != null)
            return false;
        if (personName != null ? !personName.equals(user.personName) : user.personName != null)
            return false;
        if (authToken != null ? !authToken.equals(user.authToken) : user.authToken != null)
            return false;
        if (expiredDate != null ? !expiredDate.equals(user.expiredDate) : user.expiredDate != null)
            return false;
        if (profilePhotoUrl != null ? !profilePhotoUrl.equals(user.profilePhotoUrl) : user.profilePhotoUrl != null)
            return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        if (experience != null ? !experience.equals(user.experience) : user.experience != null)
            return false;
        return myTours != null ? myTours.equals(user.myTours) : user.myTours == null;

    }

    @Override
    public int hashCode() {
        int result = personId != null ? personId.hashCode() : 0;
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + (authToken != null ? authToken.hashCode() : 0);
        result = 31 * result + (expiredDate != null ? expiredDate.hashCode() : 0);
        result = 31 * result + (profilePhotoUrl != null ? profilePhotoUrl.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (myTours != null ? myTours.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    personId: ").append(toIndentedString(personId)).append("\n");
        sb.append("    personName: ").append(toIndentedString(personName)).append("\n");
        sb.append("    authToken: ").append(toIndentedString(authToken)).append("\n");
        sb.append("    expiredDate: ").append(toIndentedString(expiredDate)).append("\n");
        sb.append("    profilePhotoUrl: ").append(toIndentedString(profilePhotoUrl)).append("\n");
        sb.append("    age: ").append(toIndentedString(age)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
        sb.append("    experience: ").append(toIndentedString(experience)).append("\n");
        sb.append("    myTours: ").append(toIndentedString(myTours)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
