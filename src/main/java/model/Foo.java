package model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "foo", types = Teacher.class)
public interface Foo {
    String getFirstname();
}
