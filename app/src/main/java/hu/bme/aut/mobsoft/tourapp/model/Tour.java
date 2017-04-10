package hu.bme.aut.mobsoft.tourapp.model;

import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class Tour {

    private Long id = null;
    private TourHeader tourHeader;
    private String description;
    private String location;
    private String imageUrl;
    private double distance;
    private Person tourLeader;
    private List<PersonHeader> members;

    public Tour() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TourHeader getTourHeader() {
        return tourHeader;
    }

    public void setTourHeader(TourHeader tourHeader) {
        this.tourHeader = tourHeader;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Person getTourLeader() {
        return tourLeader;
    }

    public void setTourLeader(Person tourLeader) {
        this.tourLeader = tourLeader;
    }

    public List<PersonHeader> getMembers() {
        return members;
    }

    public void setMembers(List<PersonHeader> members) {
        this.members = members;
    }
}
