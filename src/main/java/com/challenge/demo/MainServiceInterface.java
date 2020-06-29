package com.challenge.demo;

import java.util.List;

public interface MainServiceInterface {

    public List<CouponData> getAllCoupons();

    public List<CouponData> getAllCoupons(String competitor);

    public void updateCoupons();

    public void addDummy();
}
