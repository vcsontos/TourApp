package hu.bme.aut.mobsoft.tourapp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class InlineResponse201   {
  
  @SerializedName("tour_id")
  private String tourId = null;

  public InlineResponse201(String tourId) {
    this.tourId = tourId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InlineResponse201 that = (InlineResponse201) o;

    return tourId != null ? tourId.equals(that.tourId) : that.tourId == null;

  }

  @Override
  public int hashCode() {
    return tourId != null ? tourId.hashCode() : 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse201 {\n");
    
    sb.append("    tourId: ").append(toIndentedString(tourId)).append("\n");
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
