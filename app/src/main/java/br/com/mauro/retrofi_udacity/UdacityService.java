package br.com.mauro.retrofi_udacity;


import br.com.mauro.retrofi_udacity.models.UdacityCatalog;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UdacityService {

    public static final String BASE_URL = "https://www.udacity.com/public-api/v0/";


    @GET("courses")
    Call<UdacityCatalog> listCatalog();







}
