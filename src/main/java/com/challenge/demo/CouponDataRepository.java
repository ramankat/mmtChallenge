package com.challenge.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface CouponDataRepository extends CrudRepository<CouponData,Long> {

    @Query(value = "select * from coupon_data where competitor = :competitor" ,nativeQuery = true)
    List<CouponData> findDataByCompetitor(@Param("competitor") String competitor);

}
