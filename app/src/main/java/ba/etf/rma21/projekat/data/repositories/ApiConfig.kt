package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object{
        var base_URL: String = "https://rma21-etf.herokuapp.com" //default url

        var retrofit: Api =  Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

         fun postaviBaseURL(baseUrl:String):Unit{
             base_URL = baseUrl
             retrofit =  Retrofit.Builder()
                .baseUrl(base_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build()
                     .create(Api::class.java)
         }

    }

}