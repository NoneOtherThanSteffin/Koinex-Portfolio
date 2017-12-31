//package com.portfolio.steff.koinexportfolio.Models;
//
//import android.app.Fragment;
//import android.arch.persistence.room.Room;
//import android.graphics.Color;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.CardView;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.portfolio.steff.koinexportfolio.AppDatabase;
//import com.portfolio.steff.koinexportfolio.R;
//
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//public class AllSavedPortfoliosFragmentBckup extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
//    @BindView(R.id.linearlayout)
//    LinearLayout linearLayout;
//
//    @BindView(R.id.swipe_container_saved_portfolios)
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    public AllSavedPortfoliosFragmentBckup() {
//        // Required empty public constructor
//    }
//
//    public static AllSavedPortfoliosFragmentBckup newInstance() {
//        AllSavedPortfoliosFragmentBckup fragment = new AllSavedPortfoliosFragmentBckup();
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_all_saved_portfolios, container, false);
//        ButterKnife.bind(this, view);
//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_green_dark),
//                getResources().getColor(android.R.color.holo_red_dark),
//                getResources().getColor(android.R.color.holo_blue_dark),
//                getResources().getColor(android.R.color.holo_orange_dark));
//        return view;
//    }
//
//    @Override
//    public void onRefresh() {
//        final AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
//                AppDatabase.class, "portfolio-database").build();
//        List<Portfolio> portfolioList = null;
//        try {
//            portfolioList = new getData().execute(db).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        if (!portfolioList.isEmpty()) {
//            int childCount = linearLayout.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View v = linearLayout.getChildAt(i);
//                v.setOnClickListener(this);
//            }
//        }
//    }
//
//    @Override
//    public void onClick(View view) {
//        Toast.makeText(getActivity(), view.getId() + "", Toast.LENGTH_SHORT).show();
//    }
//
//
//    private class getData extends AsyncTask<AppDatabase, Void, List<Portfolio>> {
//
//
//        @Override
//        protected List<Portfolio> doInBackground(AppDatabase... appDatabases) {
//            List<Portfolio> portfolioList = appDatabases[0].portfolioDao().loadAllPortfolios();
//            return portfolioList;
//        }
//
//        @Override
//        protected void onPostExecute(List<Portfolio> portfolios) {
//            int count = 0;
//            linearLayout.removeAllViews();
//            Result result = null;
//            try {
//                result = new HttpRequestTask().execute().get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//
//            for (Portfolio mPortfolio : portfolios) {
//                // Initialize a new CardView
//                CardView card = new CardView(getActivity().getApplicationContext());
//
//                // Set the CardView layoutParams
//                ViewGroup.LayoutParams cardParams = new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT
//                );
//                card.setLayoutParams(cardParams);
//                card.setPadding(8, 8, 8, 8);
//                card.setId(mPortfolio.id);
//                card.setElevation(5);
//                Log.e("Async: ", "Reached Here");
//
//                // Initialize a new TextView to put in CardView
//                TextView tv = new TextView(getActivity().getApplicationContext());
//                tv.setLayoutParams(cardParams);
//                Double total = null;
//                if (result != null) {
//                    total = (mPortfolio.ripple * Double.valueOf(result.getPrices().getXRP()))
//                            + (mPortfolio.ic_iota * Double.valueOf(result.getPrices().getMIOTA()))
//                            + (mPortfolio.ethereum * Double.valueOf(result.getPrices().getETH()))
//                            + (mPortfolio.bitcoin * Double.valueOf(result.getPrices().getBTC()));
//                }
//
//                tv.setText("Ripple : " + mPortfolio.ic_ripple +" - "+(mPortfolio.ic_ripple * Double.valueOf(result.getPrices().getXRP())) + "\n" +
//                        "IOTA : " + mPortfolio.ic_iota +" - "+(mPortfolio.ic_iota * Double.valueOf(result.getPrices().getMIOTA())) + "\n" +
//                        "Ethereum : " + mPortfolio.ic_ethereum+" - "+(mPortfolio.ic_ethereum * Double.valueOf(result.getPrices().getETH())) + "\n" +
//                        "Bitcoin : " + mPortfolio.bitcoin +" - "+(mPortfolio.bitcoin * Double.valueOf(result.getPrices().getBTC())) + "\n" +
//                        "Total : " + total);
//                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
//                tv.setTextColor(Color.RED);
//
//                // Put the TextView in CardView
//                card.addView(tv);
//
//                View view = new View(getActivity().getApplicationContext());
//                ViewGroup.LayoutParams viewParams = new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, 8
//                );
//                view.setLayoutParams(viewParams);
//                view.setId(count++);
//
//                linearLayout.addView(view);
//                linearLayout.addView(card);
//
//                if (swipeRefreshLayout.isRefreshing()) {
//                    swipeRefreshLayout.setRefreshing(false);
//                }
//            }
//        }
//    }
//
//
//    private class HttpRequestTask extends AsyncTask<Void, Void, Result> {
//        @Override
//        protected Result doInBackground(Void... params) {
//            try {
//                final String url = "https://koinex.in/api/ticker";
//                RestTemplate restTemplate = new RestTemplate();
//                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//                Result result = restTemplate.getForObject(url, Result.class);
//                return result;
//            } catch (Exception e) {
//                Log.e("CurrentPriceFragment", e.getMessage(), e);
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Result result) {
//
//            if (swipeRefreshLayout.isRefreshing()) {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        }
//    }
//}
