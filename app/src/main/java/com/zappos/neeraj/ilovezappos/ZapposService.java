package com.zappos.neeraj.ilovezappos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Neeraj on 2/5/2017.
 */

public interface ZapposService {
    public static final String API_URL = "https://api.zappos.com";
    public static final String KEY = "b743e26728e16b81da139182bb2094357c31d331";
        @GET("/Search")
        Call<SearchResult> products(
                @Query("term") String sort,@Query("key") String key
        );

}
