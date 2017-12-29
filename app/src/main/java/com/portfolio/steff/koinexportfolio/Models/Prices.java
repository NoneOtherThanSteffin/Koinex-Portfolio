package com.portfolio.steff.koinexportfolio.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "BTC",
        "ETH",
        "XRP",
        "BCH",
        "LTC",
        "MIOTA",
        "OMG",
        "GNT"
})
public class Prices {

    @JsonProperty("BTC")
    private String bTC;
    @JsonProperty("ETH")
    private String eTH;
    @JsonProperty("XRP")
    private String xRP;
    @JsonProperty("BCH")
    private String bCH;
    @JsonProperty("LTC")
    private String lTC;
    @JsonProperty("MIOTA")
    private String mIOTA;
    @JsonProperty("OMG")
    private String oMG;
    @JsonProperty("GNT")
    private String gNT;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("BTC")
    public String getBTC() {
        return bTC;
    }

    @JsonProperty("BTC")
    public void setBTC(String bTC) {
        this.bTC = bTC;
    }

    @JsonProperty("ETH")
    public String getETH() {
        return eTH;
    }

    @JsonProperty("ETH")
    public void setETH(String eTH) {
        this.eTH = eTH;
    }

    @JsonProperty("XRP")
    public String getXRP() {
        return xRP;
    }

    @JsonProperty("XRP")
    public void setXRP(String xRP) {
        this.xRP = xRP;
    }

    @JsonProperty("BCH")
    public String getBCH() {
        return bCH;
    }

    @JsonProperty("BCH")
    public void setBCH(String bCH) {
        this.bCH = bCH;
    }

    @JsonProperty("LTC")
    public String getLTC() {
        return lTC;
    }

    @JsonProperty("LTC")
    public void setLTC(String lTC) {
        this.lTC = lTC;
    }

    @JsonProperty("MIOTA")
    public String getMIOTA() {
        return mIOTA;
    }

    @JsonProperty("MIOTA")
    public void setMIOTA(String mIOTA) {
        this.mIOTA = mIOTA;
    }

    @JsonProperty("OMG")
    public String getOMG() {
        return oMG;
    }

    @JsonProperty("OMG")
    public void setOMG(String oMG) {
        this.oMG = oMG;
    }

    @JsonProperty("GNT")
    public String getGNT() {
        return gNT;
    }

    @JsonProperty("GNT")
    public void setGNT(String gNT) {
        this.gNT = gNT;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Prices{" +
                "bTC='" + bTC.toString() + '\'' +
                ", eTH='" + eTH.toString() + '\'' +
                ", xRP='" + xRP.toString() + '\'' +
                ", bCH='" + bCH.toString() + '\'' +
                ", lTC='" + lTC.toString() + '\'' +
                ", mIOTA=" + mIOTA.toString() +
                ", oMG=" + oMG.toString() +
                ", gNT=" + gNT.toString() +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
