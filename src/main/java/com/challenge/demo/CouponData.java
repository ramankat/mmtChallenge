package com.challenge.demo;

import javax.persistence.*;

@Entity
@Table(name = "coupon_data")
public class CouponData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "competitor")
    private String competitor;

    public CouponData(String competitor) {
        this.competitor = competitor;
    }

    public CouponData() { }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }
}
