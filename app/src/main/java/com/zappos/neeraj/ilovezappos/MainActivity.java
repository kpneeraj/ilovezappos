package com.zappos.neeraj.ilovezappos;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.zappos.neeraj.ilovezappos.Product";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final MainActivity thisObject = this;
        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ZapposService.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ZapposService zappos = retrofit.create(ZapposService.class);

            // Create a call instance for looking up Retrofit contributors.
            Call<SearchResult> call = zappos.products(query,ZapposService.KEY);

            call.enqueue(new Callback<SearchResult>() {
                @Override
                public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                    SearchResult searchResult  = response.body();
                    Intent intent = new Intent(thisObject, Details.class);
                    //handle empty results
                    if(searchResult.results.size()==0){
                        //show no results. Update someother field for showing NO result
                    } else{
                         intent.putExtra(EXTRA_MESSAGE, (Serializable) searchResult.results.get(0));
                         startActivity(intent);
                         finish();
                    }
                }
                @Override
                public void onFailure(Call<SearchResult> call, Throwable t) {
                    //update some field on the screen saying there was an error
                    int i=11;
                }
            });
        }
    }

    public void showSearchBar(View view){
        onSearchRequested();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
             case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                 onSearchRequested();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}