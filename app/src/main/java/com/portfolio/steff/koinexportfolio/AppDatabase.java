package com.portfolio.steff.koinexportfolio;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.portfolio.steff.koinexportfolio.Models.Portfolio;

/**
 * Created by steff on 28-Dec-17.
 */
@Database(entities = {Portfolio.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PortfolioDao portfolioDao();

}
