package com.portfolio.steff.koinexportfolio;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.portfolio.steff.koinexportfolio.Models.Portfolio;
import com.portfolio.steff.koinexportfolio.Models.Result;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by steff on 28-Dec-17.
 */
public class AllSavedPortfoliosFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    @BindView(R.id.linearlayout)
    LinearLayout linearLayout;

    @BindView(R.id.swipe_container_saved_portfolios)
    SwipeRefreshLayout swipeRefreshLayout;

    View inflatedView;

    Portfolio getPortfolio;

    int portFolioId;

    public AllSavedPortfoliosFragment() {
        // Required empty public constructor
    }

    public static AllSavedPortfoliosFragment newInstance() {
        AllSavedPortfoliosFragment fragment = new AllSavedPortfoliosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView = inflater.inflate(R.layout.fragment_all_saved_portfolios, container, false);
        ButterKnife.bind(this, inflatedView);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));
        return inflatedView;
    }

    @Override
    public void onRefresh() {
        final AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "portfolio-database").build();
        List<Portfolio> portfolioList = null;
        try {
            portfolioList = new PopulateData().execute(db).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), view.getId() + "", Toast.LENGTH_SHORT).show();
    }


    private class PopulateData extends AsyncTask<AppDatabase, Void, List<Portfolio>> {
        private AppDatabase appDatabase;

        @Override
        protected List<Portfolio> doInBackground(AppDatabase... appDatabases) {
            appDatabase = appDatabases[0];
            List<Portfolio> portfolioList = appDatabase.portfolioDao().loadAllPortfolios();
            return portfolioList;
        }

        @Override
        protected void onPostExecute(List<Portfolio> portfolios) {
            int count = 0;
            TableLayout mHeadingTableLayout = (TableLayout) inflatedView.findViewById(R.id.headingtablelayout);
            linearLayout.removeAllViews();
            linearLayout.addView(mHeadingTableLayout);

            for (final Portfolio mPortfolio : portfolios) {
                LinearLayout linearLayout = (LinearLayout) inflatedView.findViewById(R.id.linearlayout);
                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final TableLayout tableView = (TableLayout) inflater.inflate(R.layout.tablelayout, null);
                tableView.setId(mPortfolio.id);
                final TableRow tableRow = (TableRow) tableView.getChildAt(0);
                TextView coin = (TextView) tableRow.getChildAt(0);
                coin.setText(String.valueOf(mPortfolio.bitcoin));
                coin = (TextView) (TextView) tableRow.getChildAt(1);
                coin.setText(String.valueOf(mPortfolio.ethereum));
                coin = (TextView) (TextView) tableRow.getChildAt(2);
                coin.setText(String.valueOf(mPortfolio.iota));
                coin = (TextView) (TextView) tableRow.getChildAt(3);
                coin.setText(String.valueOf(mPortfolio.ripple));
                ImageView coinImageView = (ImageView) tableRow.getChildAt(4);
                coinImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        portFolioId = mPortfolio.id;
                        try {
                            getPortfolio = new GetData().execute(appDatabase).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        new DeleteData().execute(appDatabase);
                        tableView.setVisibility(View.GONE);

                    }
                });
                linearLayout.addView(tableView);
            }

            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    private class DeleteData extends AsyncTask<AppDatabase, Void, Void> {

        @Override
        protected Void doInBackground(AppDatabase... appDatabases) {
            appDatabases[0].portfolioDao().deletePortfolios(getPortfolio);
            Snackbar.make(inflatedView, "Deleted Data : " + getPortfolio.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return null;
        }

    }

    private class GetData extends AsyncTask<AppDatabase, Void, Portfolio> {

        @Override
        protected Portfolio doInBackground(AppDatabase... appDatabases) {
            Portfolio portfolio = appDatabases[0].portfolioDao().getPortofio(portFolioId).get(0);
            return portfolio;
        }

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            try {
                final String url = "https://koinex.in/api/ticker";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Result result = restTemplate.getForObject(url, Result.class);
                return result;
            } catch (Exception e) {
                Log.e("CurrentPriceFragment", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Result result) {

            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }
}
