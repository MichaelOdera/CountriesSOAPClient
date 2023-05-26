package com.michael.testclient.config;

import com.michael.testclient.service.BlzServiceAdapter;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.dom.DOMSource;
import java.io.IOException;

@Configuration
public class BeanConfig {


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("org.oorsprong.websamples");
        return marshaller;
    }

    @Bean
    public BlzServiceAdapter soapConnector(Jaxb2Marshaller marshallerItem) {
        //Jaxb2Marshaller marshallerItem = marshaller();
        BlzServiceAdapter client = new BlzServiceAdapter();
        client.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        client.setMarshaller(marshallerItem);
        client.setUnmarshaller(marshallerItem);
        return client;
    }

}
