package hu.bme.aut.mobsoft.tourapp.model;

import com.orm.dsl.Table;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

@Table
public class PersonHeader {

    private Long id = null;
    private String personName;

    public PersonHeader() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
