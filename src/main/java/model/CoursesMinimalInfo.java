package model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "minimal", types = Course.class)
public interface CoursesMinimalInfo {

    String getTitle();

}
