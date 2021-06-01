package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object{
        var baseURL: String = "https://rma21-etf.herokuapp.com" //default url

        var retrofit: Api =  Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

         fun postaviBaseURL(baseUrl:String):Unit{
             baseURL = baseUrl
             retrofit =  Retrofit.Builder()
                .baseUrl(baseURL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build()
                     .create(Api::class.java)
         }

    }

}