package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseModel {

@JsonProperty("name")
private String name;

@JsonProperty("price")
private Integer price;

public CourseModel(String name, Integer price) {
	this.name = name;
	this.price = price;
}

public CourseModel() {
}

@Override
public boolean equals(Object o) {
	if (this == o) return true;
	if (o == null || getClass() != o.getClass()) return false;
	CourseModel that = (CourseModel) o;
	return Objects.equals(name, that.name) && Objects.equals(price, that.price);
}

@Override
public int hashCode() {
	return Objects.hash(name, price);
}
}
