package com.codephillip.endpointsultimate;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.codephillip.api.backend.quoteEndpoint.QuoteEndpoint;
import com.codephillip.api.backend.quoteEndpoint.model.Quote;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

class EndpointsAsyncTask extends AsyncTask<Void, Void, List<Quote>> {
    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();
    private static QuoteEndpoint myApiService = null;
    private Context context;

    EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<Quote> doInBackground(Void... params) {
        if(myApiService == null) {
            //Only do this once
            //devappserver
//            QuoteEndpoint.Builder builder = new QuoteEndpoint.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://192.168.1.128:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });

            //online server
            QuoteEndpoint.Builder builder = new QuoteEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://endpointsapp-1007.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            //delete first item in server DB
            //List<Quote> z = myApiService.listQuote().execute().getItems();
            //long id = z.get(0).getId();
            //myApiService.removeQuote(id).execute();
            //delete
            //myApiService.removeQuote(5076495651307520L).execute();
            //update
            //String x = String.valueOf(myApiService.updateQuote(new Quote().setId(5066549580791808L).setWho("mememe").setWhat("deleted and gone")).execute());
            //post
            //String x = String.valueOf(myApiService.insertQuote(new Quote().setWho("Makarov2").setWhat("Best in Call of duty")).execute());
            //multiple post
            Quote quote = new Quote();
            for (int i = 0; i < 4; i++){
                quote.setWho("Makarov"+i).setWhat("Bad guy"+i);
                myApiService.insertQuote(quote).execute();
            }

            //Log.d(TAG, "doInBackground: "+x);
            return null;
            //return myApiService.listQuote().execute().getItems();
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void onPostExecute(List<Quote> result) {
//        for (Quote q : result) {
//            Toast.makeText(context, q.getWho() + " : " + q.getWhat(), Toast.LENGTH_LONG).show();
//        }
        Toast.makeText(context, "finished", Toast.LENGTH_SHORT).show();
    }
}