package hu.bme.aut.mobsoft.tourapp.model;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
public class InlineResponse200 {

    @SerializedName("tour_id")
    private String tourId = null;

    @SerializedName("members")
    private Integer members = null;

    public InlineResponse200(String tourId, Integer members) {
        this.tourId = tourId;
        this.members = members;
    }

    /**
     * Unique identifier representing a specific tour.
     **/
    @ApiModelProperty(value = "Unique identifier representing a specific tour.")
    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InlineResponse200 that = (InlineResponse200) o;

        if (tourId != null ? !tourId.equals(that.tourId) : that.tourId != null) return false;
        return members != null ? members.equals(that.members) : that.members == null;

    }

    @Override
    public int hashCode() {
        int result = tourId != null ? tourId.hashCode() : 0;
        result = 31 * result + (members != null ? members.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InlineResponse200 {\n");

        sb.append("    tourId: ").append(toIndentedString(tourId)).append("\n");
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
