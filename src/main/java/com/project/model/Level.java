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
    Integer numericalValue;

}
