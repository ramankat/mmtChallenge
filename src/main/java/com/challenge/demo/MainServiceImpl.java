package com.challenge.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MainServiceImpl implements MainServiceInterface{

    @Autowired
    CouponDataRepository couponDataRepository;

    @SuppressWarnings("deprecation")
    @Override
    public List<CouponData> getAllCoupons() {
        Iterable<CouponData> it = couponDataRepository.findAll();
        List<CouponData> couponsDataList = new LinkedList<>();
        for(CouponData couponData : it)
        {
            couponsDataList.add(couponData);
        }
        return couponsDataList;
    }

    @Override
    public List<CouponData> getAllCoupons(String competitor) {
        return couponDataRepository.findDataByCompetitor(competitor);
    }

    @Override
    public void updateCoupons() {
        // Implement after finding about information retrieval
    }

    @Override
    public void addDummy() {
        CouponData couponData = new CouponData();
        couponData.setCompetitor("mmt");
        couponDataRepository.save(couponData);
    }
}
