package com.project.model;

import javax.persistence.*;

/**
 * Created by BABAWANDE on 12/19/2016.
 */
@Entity
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numericalValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumericalValue() {
        return numericalValue;
    }

    public void setNumericalValue(Integer numericalValue) {
        this.numericalValue = numericalValue;
    }
}
