package com.portfolio.steff.koinexportfolio;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.portfolio.steff.koinexportfolio.Models.Result;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentPriceFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.textViewRipple)
    TextView textViewRipple;

    @BindView(R.id.textViewIota)
    TextView textViewIota;

    @BindView(R.id.textViewBitcoin)
    TextView textViewBitcoin;

    @BindView(R.id.textViewEthereum)
    TextView textViewEthereum;

    public CurrentPriceFragment() {
        // Required empty public constructor
    }

    public static CurrentPriceFragment newInstance() {
        CurrentPriceFragment fragment = new CurrentPriceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_price, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_red_dark),
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_orange_dark));
        return view;
    }

    @Override
    public void onRefresh() {
        new HttpRequestTask().execute();
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

            try {
                double ripplePrice = Double.valueOf(result.getPrices().getXRP());
                double iotaPrice = Double.valueOf(result.getPrices().getMIOTA());
                double bitcoinPrice = Double.valueOf(result.getPrices().getBTC());
                double ethereumPrice = Double.valueOf(result.getPrices().getETH());

                textViewRipple.setText(ripplePrice + "");
                textViewIota.setText(iotaPrice + "");
                textViewBitcoin.setText(bitcoinPrice + "");
                textViewEthereum.setText(ethereumPrice + "");
            } catch (Exception e) {
                textViewRipple.setText("Exception Occurred");
                textViewIota.setText("Exception Occurred");
                textViewBitcoin.setText("Exception Occurred");
                textViewEthereum.setText("Exception Occurred");
            }

            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }
}
