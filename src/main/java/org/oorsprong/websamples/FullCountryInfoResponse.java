//
// This file was generated by the Eclipse Implementation of JAXB, v2.3.6 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.26 at 03:13:57 PM EAT 
//


package org.oorsprong.websamples;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FullCountryInfoResult" type="{http://www.oorsprong.org/websamples.countryinfo}tCountryInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fullCountryInfoResult"
})
@XmlRootElement(name = "FullCountryInfoResponse")
public class FullCountryInfoResponse {

    @XmlElement(name = "FullCountryInfoResult", required = true)
    protected TCountryInfo fullCountryInfoResult;

    /**
     * Gets the value of the fullCountryInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link TCountryInfo }
     *     
     */
    public TCountryInfo getFullCountryInfoResult() {
        return fullCountryInfoResult;
    }

    /**
     * Sets the value of the fullCountryInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCountryInfo }
     *     
     */
    public void setFullCountryInfoResult(TCountryInfo value) {
        this.fullCountryInfoResult = value;
    }

}
