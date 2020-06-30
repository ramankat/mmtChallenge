package com.challenge.demo;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

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
         WebClient client = new WebClient();
         client.getOptions().setCssEnabled(false);
         client.getOptions().setJavaScriptEnabled(false);
          HtmlPage page = null;
        try {
            //srch=DEL-Delhi-India|BOM-Mumbai-India|01/07/2020-03/07/2020&px=1-0-0
            URIBuilder ub = new URIBuilder("https://flight.easemytrip.com/FlightListRT/Index");
            List<CouponData> couponDataList = new LinkedList<>();

            List<String> dateList = new LinkedList<>();
            dateList.add("01/07/2020");dateList.add("03/07/2020");dateList.add("06/07/2020");dateList.add("13/07/2020");
            String competitor = "easemytrip";
            String fromCity = "DEL-Delhi-India";
            String toCity = "BOM-Mumbai-India";
            for(int i = 0;i<dateList.size();i++)
            {
                for(int j = i+1; j<dateList.size();j++)
                {
                    ub.addParameter("srch",fromCity+"|"+toCity+"|"+dateList.get(i)+"-"+dateList.get(j));
                    ub.addParameter("px","1-0-0");
                    String searchUrl = ub.toString();
                    System.out.println(searchUrl);
                    page = client.getPage(searchUrl);
                    //System.out.println(page.asXml());
                    //System.out.println("\n\n\n\n");

                    List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//div[@class='cou-text-app']") ;
                    for(HtmlElement htmlElement : items)
                    {
                        System.out.println(htmlElement.asText());
                        couponDataList.add(new CouponData(competitor,fromCity,toCity,dateList.get(i),dateList.get(j),htmlElement.asText()));
                    }
                }
            }
            couponDataRepository.saveAll(couponDataList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addDummy() {
        CouponData couponData = new CouponData();
        couponData.setCompetitor("mmt");
        couponData.setFromCity("DEL");
        couponData.setToCity("BOM");
        couponData.setDeparture("01/07/2020");
        couponData.setArrival("07/07/2020");
        couponData.setCoupon("GO-MMT");
        couponDataRepository.save(couponData);
    }
}
