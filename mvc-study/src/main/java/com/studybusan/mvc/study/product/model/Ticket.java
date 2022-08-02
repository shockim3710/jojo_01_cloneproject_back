package com.studybusan.mvc.study.product.model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Ticket extends Product {

    private Date startDate;
    private String sNumber;

}
