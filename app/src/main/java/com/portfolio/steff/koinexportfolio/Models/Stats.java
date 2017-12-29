package com.portfolio.steff.koinexportfolio.Models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steff on 28-Dec-17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ETH",
        "BTC",
        "LTC",
        "XRP",
        "BCH"
})
public class Stats {

    @JsonProperty("ETH")
    private ETH eTH;
    @JsonProperty("BTC")
    private BTC bTC;
    @JsonProperty("LTC")
    private LTC lTC;
    @JsonProperty("XRP")
    private XRP xRP;
    @JsonProperty("BCH")
    private BCH bCH;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ETH")
    public ETH getETH() {
        return eTH;
    }

    @JsonProperty("ETH")
    public void setETH(ETH eTH) {
        this.eTH = eTH;
    }

    @JsonProperty("BTC")
    public BTC getBTC() {
        return bTC;
    }

    @JsonProperty("BTC")
    public void setBTC(BTC bTC) {
        this.bTC = bTC;
    }

    @JsonProperty("LTC")
    public LTC getLTC() {
        return lTC;
    }

    @JsonProperty("LTC")
    public void setLTC(LTC lTC) {
        this.lTC = lTC;
    }

    @JsonProperty("XRP")
    public XRP getXRP() {
        return xRP;
    }

    @JsonProperty("XRP")
    public void setXRP(XRP xRP) {
        this.xRP = xRP;
    }

    @JsonProperty("BCH")
    public BCH getBCH() {
        return bCH;
    }

    @JsonProperty("BCH")
    public void setBCH(BCH bCH) {
        this.bCH = bCH;
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
        return "Stats{" +
                "eTH=" + eTH.toString() +
                ", bTC=" + bTC.toString() +
                ", lTC=" + lTC.toString() +
                ", xRP=" + xRP.toString() +
                ", bCH=" + bCH.toString() +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
