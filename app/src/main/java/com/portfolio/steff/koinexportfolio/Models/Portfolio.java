package com.portfolio.steff.koinexportfolio.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by steff on 28-Dec-17.
 */
@Entity
public class Portfolio implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Double ripple;
    public Double iota;
    public Double bitcoin;
    public Double ethereum;

    public Portfolio() {

    }

    public Portfolio(Double ripple, Double iota, Double bitcoin, Double ethereum) {
        this.ripple = ripple;
        this.iota = iota;
        this.bitcoin = bitcoin;
        this.ethereum = ethereum;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                ", ic_ripple=" + ripple +
                ", ic_iota=" + iota +
                ", bitcoin=" + bitcoin +
                ", ic_ethereum=" + ethereum +
                '}';
    }
}
