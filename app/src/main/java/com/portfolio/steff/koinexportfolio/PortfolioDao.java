package com.portfolio.steff.koinexportfolio;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.portfolio.steff.koinexportfolio.Models.Portfolio;

import java.util.List;

/**
 * Created by steff on 28-Dec-17.
 */
@Dao
public interface PortfolioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPortfolios(Portfolio... portfolios);

    @Delete
    public void deletePortfolios(Portfolio... portfolios);

    @Query("SELECT * FROM portfolio")
    public List<Portfolio> loadAllPortfolios();

    @Query("SELECT * FROM portfolio WHERE id = :portfolioId")
    public List<Portfolio> getPortofio(int portfolioId);

}
