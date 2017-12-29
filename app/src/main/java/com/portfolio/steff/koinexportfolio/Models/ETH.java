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
        "last_traded_price",
        "lowest_ask",
        "highest_bid",
        "min_24hrs",
        "max_24hrs",
        "vol_24hrs"
})
public class ETH {

    @JsonProperty("last_traded_price")
    private String lastTradedPrice;
    @JsonProperty("lowest_ask")
    private String lowestAsk;
    @JsonProperty("highest_bid")
    private String highestBid;
    @JsonProperty("min_24hrs")
    private String min24hrs;
    @JsonProperty("max_24hrs")
    private String max24hrs;
    @JsonProperty("vol_24hrs")
    private String vol24hrs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("last_traded_price")
    public String getLastTradedPrice() {
        return lastTradedPrice;
    }

    @JsonProperty("last_traded_price")
    public void setLastTradedPrice(String lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    @JsonProperty("lowest_ask")
    public String getLowestAsk() {
        return lowestAsk;
    }

    @JsonProperty("lowest_ask")
    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    @JsonProperty("highest_bid")
    public String getHighestBid() {
        return highestBid;
    }

    @JsonProperty("highest_bid")
    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    @JsonProperty("min_24hrs")
    public String getMin24hrs() {
        return min24hrs;
    }

    @JsonProperty("min_24hrs")
    public void setMin24hrs(String min24hrs) {
        this.min24hrs = min24hrs;
    }

    @JsonProperty("max_24hrs")
    public String getMax24hrs() {
        return max24hrs;
    }

    @JsonProperty("max_24hrs")
    public void setMax24hrs(String max24hrs) {
        this.max24hrs = max24hrs;
    }

    @JsonProperty("vol_24hrs")
    public String getVol24hrs() {
        return vol24hrs;
    }

    @JsonProperty("vol_24hrs")
    public void setVol24hrs(String vol24hrs) {
        this.vol24hrs = vol24hrs;
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
        return "ETH{" +
                "lastTradedPrice='" + lastTradedPrice + '\'' +
                ", lowestAsk='" + lowestAsk + '\'' +
                ", highestBid='" + highestBid + '\'' +
                ", min24hrs='" + min24hrs + '\'' +
                ", max24hrs='" + max24hrs + '\'' +
                ", vol24hrs='" + vol24hrs + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
