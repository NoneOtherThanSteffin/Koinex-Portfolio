package com.portfolio.steff.koinexportfolio;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.portfolio.steff.koinexportfolio.Models.Portfolio;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by steff on 28-Dec-17.
 */
public class PortfolioFragment extends Fragment {

    private static final String TAG = PortfolioFragment.class.getSimpleName();

    @BindView(R.id.button)
    Button addPortfolio;

    @BindView(R.id.rippleCount)
    EditText rippleCount;

    @BindView(R.id.bitcoinCount)
    EditText bitcoinCount;

    @BindView(R.id.ethereumCount)
    EditText ethereumCount;

    @BindView(R.id.iotaCount)
    EditText iotaCount;

    private View inflatedView;

    public PortfolioFragment() {

    }

    public static PortfolioFragment newInstance() {
        PortfolioFragment fragment = new PortfolioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_portfolio, container, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }


    @OnClick(R.id.button)
    public void addPortfolio(Button button) {
        Log.e("Adding Portfolio", "Started");

        String stringRippleCount = rippleCount.getText().toString();
        String stringIotaCount = iotaCount.getText().toString();
        String stringBitcoinCount = bitcoinCount.getText().toString();
        String stringEthereumCount = ethereumCount.getText().toString();

        stringRippleCount = stringRippleCount.trim().equals("") ? "0.0" : stringRippleCount;
        stringIotaCount = stringIotaCount.trim().equals("") ? "0.0" : stringIotaCount;
        stringBitcoinCount = stringBitcoinCount.trim().equals("") ? "0.0" : stringBitcoinCount;
        stringEthereumCount = stringEthereumCount.trim().equals("") ? "0.0" : stringEthereumCount;

        final Portfolio mPortfolio = new Portfolio(Double.valueOf(stringRippleCount), Double.valueOf(stringIotaCount), Double.valueOf(stringBitcoinCount), Double.valueOf(stringEthereumCount));
        Log.e("Portfolio 1 :", mPortfolio.toString());
        final AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "portfolio-database").build();

        AsyncTask<Void, Void, Boolean> asyncTask;
        asyncTask = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                db.portfolioDao().insertPortfolios(mPortfolio);
                Log.e("PortFolios: ", db.portfolioDao().loadAllPortfolios().toString());
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean.booleanValue() == true) {
                    Snackbar.make(inflatedView, mPortfolio.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        }.execute();
    }

}
