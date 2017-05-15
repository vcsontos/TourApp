package hu.bme.aut.mobsoft.tourapp.model;

import com.google.gson.annotations.SerializedName;

import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(name = "Tour")
@ApiModel(description = "")
public class Tour {

    @SerializedName("tour_id_c")
    private Long id;

    @SerializedName("tour_id")
    private String tourId = null;

    @SerializedName("tour_name")
    private String tourName = null;

    @SerializedName("category")
    private Category category = null;

    @SerializedName("start_date")
    private Date startDate = null;

    @SerializedName("difficulty")
    private Difficulty difficulty = null;

    @SerializedName("distance")
    private Double distance = null;

    @SerializedName("description")
    private String description = null;

    @SerializedName("tour_location")
    private String tourLocation = null;

    @SerializedName("image_url")
    private String imageUrl = null;

    @SerializedName("tour_leader")
    private User tourLeader = null;

    @Ignore
    @SerializedName("members")
    private List<User> members = new ArrayList<User>();

    @SerializedName("members_str")
    private String membersStr;

    public Tour() {
    }

    public Tour(Long id, String tourId, String tourName) {
        this.id = id;
        this.tourId = tourId;
        this.tourName = tourName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Unique identifier representing a specific tour.
     **/
    @ApiModelProperty(required = true, value = "Unique identifier representing a specific tour.")
    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }


    /**
     * Display name of tour.
     **/
    @ApiModelProperty(required = true, value = "Display name of tour.")
    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }


    /**
     * Category of tour [walking, cycling, water tour, mountain]
     **/
    @ApiModelProperty(required = true, value = "Category of tour [walking, cycling, water tour, mountain]")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    /**
     * Start date of tour
     **/
    @ApiModelProperty(required = true, value = "Start date of tour")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    /**
     * Difficulty of tour [easy, medium, hard]
     **/
    @ApiModelProperty(value = "Difficulty of tour [easy, medium, hard]")
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }


    /**
     * Distance of tour
     **/
    @ApiModelProperty(value = "Distance of tour")
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }


    /**
     * Description of tour.
     **/
    @ApiModelProperty(value = "Description of tour.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Location of tour.
     **/
    @ApiModelProperty(required = true, value = "Location of tour.")
    public String getTourLocation() {
        return tourLocation;
    }

    public void setTourLocation(String tourLocation) {
        this.tourLocation = tourLocation;
    }


    /**
     * Image URL representing the tour.
     **/
    @ApiModelProperty(value = "Image URL representing the tour.")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    /**
     **/
    @ApiModelProperty(required = true, value = "")
    public User getTourLeader() {
        return tourLeader;
    }

    public void setTourLeader(User tourLeader) {
        this.tourLeader = tourLeader;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getMembersStr() {
        return membersStr;
    }

    public void setMembersStr(String membersStr) {
        this.membersStr = membersStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        if (tourId != null ? !tourId.equals(tour.tourId) : tour.tourId != null) return false;
        if (tourName != null ? !tourName.equals(tour.tourName) : tour.tourName != null)
            return false;
        if (category != tour.category) return false;
        if (startDate != null ? !startDate.equals(tour.startDate) : tour.startDate != null)
            return false;
        if (difficulty != tour.difficulty) return false;
        if (distance != null ? !distance.equals(tour.distance) : tour.distance != null)
            return false;
        if (description != null ? !description.equals(tour.description) : tour.description != null)
            return false;
        if (tourLocation != null ? !tourLocation.equals(tour.tourLocation) : tour.tourLocation != null)
            return false;
        if (imageUrl != null ? !imageUrl.equals(tour.imageUrl) : tour.imageUrl != null)
            return false;
        if (tourLeader != null ? !tourLeader.equals(tour.tourLeader) : tour.tourLeader != null)
            return false;
        return members != null ? members.equals(tour.members) : tour.members == null;

    }

    @Override
    public int hashCode() {
        int result = tourId != null ? tourId.hashCode() : 0;
        result = 31 * result + (tourName != null ? tourName.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tourLocation != null ? tourLocation.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (tourLeader != null ? tourLeader.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Tour {\n");

        sb.append("    tourId: ").append(toIndentedString(tourId)).append("\n");
        sb.append("    tourName: ").append(toIndentedString(tourName)).append("\n");
        sb.append("    category: ").append(toIndentedString(category)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
        sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    tourLocation: ").append(toIndentedString(tourLocation)).append("\n");
        sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
        sb.append("    tourLeader: ").append(toIndentedString(tourLeader)).append("\n");
        sb.append("    members: ").append(toIndentedString(members)).append("\n");
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
