package hu.bme.aut.mobsoft.tourapp.model;

import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class Tour {

    private Long id = null;
    private String name;
    private Category category;
    private Date startDate;
    private Difficulty difficulty;
    private double distance;
    private String description;
    private String location;
    private String imageUrl;
    private Person tourLeader;
    private List<Person> members;

    public Tour() {

    }

    public Tour(Long id, String name) {
        this.id = id;
        this.name = name;
        members = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Person getTourLeader() {
        return tourLeader;
    }

    public void setTourLeader(Person tourLeader) {
        this.tourLeader = tourLeader;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        if (members == null) {
            this.members = new ArrayList<>();
        } else {
            this.members = members;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (id != null ? !id.equals(tour.id) : tour.id != null) return false;
        if (name != null ? !name.equals(tour.name) : tour.name != null) return false;
        return members != null ? members.equals(tour.members) : tour.members == null;

    }

}
