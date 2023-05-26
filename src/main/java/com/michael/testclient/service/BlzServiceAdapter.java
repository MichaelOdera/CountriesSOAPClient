package com.michael.testclient.service;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.soap.*;
import org.json.JSONObject;
import org.json.XML;
import org.oorsprong.websamples.CountryCurrency;
import org.oorsprong.websamples.CountryCurrencyResponse;
import org.oorsprong.websamples.TCurrency;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class BlzServiceAdapter extends WebServiceGatewaySupport {

    String soapEnvelop = "soap:Envelope";
    String soapBody = "soap:Body";

    String resultCountryDetails = "m:CountryCurrencyResponse";


    public CountryCurrencyResponse getCurrencyDetails(String url, CountryCurrency request) {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.TEXT_XML);

       CountryCurrencyResponse rest = new CountryCurrencyResponse();

        String soapEndpointUrl = url;
        String soapAction = "";

        rest = callSoapWebService(soapEndpointUrl, soapAction, request.getSCountryISOCode());
        return rest;
    }

    private CountryCurrencyResponse callSoapWebService(String soapEndpointUrl, String soapAction, String sCountryISOCode) {

        CountryCurrencyResponse response = new CountryCurrencyResponse();
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, sCountryISOCode), soapEndpointUrl);


            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            response = processResult(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }

        return response;
    }

    private CountryCurrencyResponse processResult(SOAPMessage soapResponse) throws SOAPException, IOException {
        CountryCurrencyResponse countryCurrencyResponse = new CountryCurrencyResponse();


        //Change the SOAPMessage Object to string
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapResponse.writeTo(out);
        String strMsg = out.toString();

        try {
            JSONObject jsonObj = XML.toJSONObject(strMsg);


            System.out.println(jsonObj);
            JSONObject body = jsonObj.getJSONObject(soapEnvelop).getJSONObject(soapBody)
                    .getJSONObject(resultCountryDetails);

            JSONObject countryContent = body.getJSONObject("m:CountryCurrencyResult");
            String currencyCode = countryContent.get("m:sISOCode").toString();
            String currencyName = countryContent.get("m:sName").toString();

            System.out.println("My currency Code ::: "+ currencyCode);
            System.out.println("My currency name ::: "+ currencyName);
            TCurrency currency = new TCurrency();

            currency.setSISOCode(currencyCode);
            currency.setSName(currencyName);

            countryCurrencyResponse.setCountryCurrencyResult(currency);


        } catch (Exception e){
            System.out.println("My errors  :::: "+ e.getMessage());

        }

        return countryCurrencyResponse;

    }

    private static SOAPMessage createSOAPRequest(String soapAction, String sCountryISOCode) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage, sCountryISOCode);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println("\n");

        return soapMessage;
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage, String sCountryISOCode) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "web";
        String myNamespaceURI = "http://www.oorsprong.org/websamples.countryinfo";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        /*
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://www.oorsprong.org/websamples.countryinfo">
            <soapenv:Header/>
            <soapenv:Body>
                <web:CountryCurrency>
                    <web:sCountryISOCode>GB-ENG</web:sCountryISOCode>
                </web:CountryCurrency>
            </soapenv:Body>
        </soapenv:Envelope>
        */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("CountryCurrency", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("sCountryISOCode", myNamespace);
        soapBodyElem1.addTextNode(sCountryISOCode);
    }
}
