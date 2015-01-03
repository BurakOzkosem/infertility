package com.genesearch.object;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.domain.Sort.Direction;

/**
 * Created with IntelliJ IDEA.
 * User: skiselev
 * Date: 10.10.13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility =  JsonAutoDetect.Visibility.NON_PRIVATE)
public class Sort {
    private String direction;
    private String property;

    public Sort() {
    }

    public Sort(String direction, String property) {
        this.direction = direction;
        this.property = property;
    }

    public Direction getSortDirection() {
        return Direction.valueOf(direction.toUpperCase());
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Sort{");
        sb.append("direction='").append(direction).append('\'');
        sb.append(", property='").append(property).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
