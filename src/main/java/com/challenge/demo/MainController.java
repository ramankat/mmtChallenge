package com.challenge.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    MainServiceImpl mainService;

    @GetMapping(value = "/index")
    public String index()
    {
        return "Super Robot Monkey Team Hyper Force GO-MMT";
    }

    @GetMapping(value = "/addDummy")
    public void addDummy()
    {
        mainService.addDummy();
    }

    @GetMapping(value = "/updateCoupons")
    public String updateCoupons()
    {
        mainService.updateCoupons();
        return "Information Updated Successfully in database";
    }

    @GetMapping(value = "/getAllCoupons")
    public List<CouponData> getAllCoupons()
    {
        return mainService.getAllCoupons();
    }

    @GetMapping(value = "/getAllCouponsByCompetitor")
    public List<CouponData> getAllCouponsByCompetitor(@RequestParam("competitor") String competitor)
    {
        return mainService.getAllCoupons(competitor);
    }
}
