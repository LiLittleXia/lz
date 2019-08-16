package com.lz.library.http;

import com.lz.library.base.bean.BaseResponseBody;
import com.lz.library.config.AppConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("onebox/weather/query")
    Observable<BaseResponseBody<AppConfig>> queryWeather(@Query("cityname") String cityName);

}
