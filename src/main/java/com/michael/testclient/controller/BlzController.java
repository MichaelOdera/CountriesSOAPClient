package com.michael.testclient.controller;


import com.michael.testclient.models.CountryRequest;
import com.michael.testclient.service.BlzServiceAdapter;
import org.oorsprong.websamples.CountryCurrency;
import org.oorsprong.websamples.CountryCurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BlzController {

    @Autowired
    BlzServiceAdapter blzServiceAdapter;

    @PostMapping("name")
    public CountryCurrencyResponse sum(@RequestBody CountryRequest countryRequest) throws Exception {
//        ObjectFactory objectFactory = new ObjectFactory();
        System.out.println("My bank code >>>> "+ countryRequest.getCode());
        CountryCurrency type = new CountryCurrency();
        type.setSCountryISOCode(countryRequest.getCode());
        CountryCurrencyResponse response =  blzServiceAdapter.getCurrencyDetails("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso", type);
        return response;
    }
}
