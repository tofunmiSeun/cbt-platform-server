package com.project.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by BABAWANDE on 12/19/2016.
 */
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    Integer numericalValue;
    @OneToOne
    Level level;
}
