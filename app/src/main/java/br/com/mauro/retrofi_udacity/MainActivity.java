package br.com.mauro.retrofi_udacity;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.mauro.retrofi_udacity.models.UdacityCatalog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import br.com.mauro.retrofi_udacity.models.Course;
import br.com.mauro.retrofi_udacity.models.Instructor;

public class MainActivity extends AppCompatActivity {

    private static final Object TAG ="suemar" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        UdacityService service = retrofit.create(UdacityService.class);
        Call<UdacityCatalog>  requestCatalog = service.listCatalog();

        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if (!response.isSuccessful()) {

                    Log.i("TAG","ERRO:" + response.code());

                }
                else {
                    //REQUISIÇÃO RETORNOu COM SUCESSO

                    UdacityCatalog catalog = response.body();

                    for(Course c : catalog.courses){
                        Log.i((String) TAG, String.format("%s: %s",c.title,c.subtitle));
                        for (Instructor i : c.instructors){
                            Log.i((String) TAG,i.name);


                        }

                        Log.i(String.valueOf(TAG),"-----");

                    }


                }


            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e((String) TAG, "ERRO: " + t.getMessage());

                
            }
        });
    }
}
