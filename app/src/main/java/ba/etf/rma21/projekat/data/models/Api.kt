package ba.etf.rma21.projekat.data.models

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    //PitanjeKvizRepo tj Pitanje (na Swaggeru)
    @GET("/kviz/{id}/pitanja")
    suspend fun getPitanja (@Path("id")idKviza : Int) : Response<List<Pitanje>>

    //TakeKvizRepo tj KvizTaken
    @GET ("/student/{id}/kviztaken")
    suspend fun getPocetniKvizovi (@Path("id") hashStudenta: String) : Response<List<KvizTaken>>

    @POST("/student/{id}/kviz/{kid}")
    suspend fun zapocniKviz(@Path("id") hashStudenta : String, @Path("kid") kvizId : Int) : Response<KvizTaken>


    //OdgovorRepo tj Odgovor
    @GET("/student/{id}/kviztaken/{ktid}/odgovori")
    suspend fun getOdgovoriKviz(@Path("id") hashStudenta : String, @Path("ktid") idKviza : Int) : Response<List<Odgovor>>

    @POST ("/student/{id}/kviztaken/{ktid}/odgovor")
    suspend fun postaviOdgovorKviz(@Path("id") hashStudenta : String, @Path("ktid") idPokusajaKviza : Int, @Body odgovorBody: OdgovorBody): Response<Odgovor> //ne znam


    //KvizRepo tj Kviz
    @GET("/kviz")
    suspend fun getAll(): Response<List<Kviz>>

    @GET("/kviz/{id}")
    suspend fun getById(@Path("id") id : Int): Response<Kviz>

    @GET("/grupa/{id}/kvizovi")
    suspend fun getUpisani(@Path("id") grupaId : Int) : Response<List<Kviz>>


    //PredmetIGrupaRepo tj Predmet + Grupa
    @GET("/predmet")
    suspend fun getPredmeti() : Response<List<Predmet>>

    @GET("/predmet/{id}")
    suspend fun getPredmetSaId(@Path("id") predmetId : Int) : Response<Predmet>

    @GET("/grupa")
    suspend fun getGrupe() : Response<List<Grupa>>

    @GET("/predmet/{id}/grupa")
    suspend fun getGrupeZaPredmet(@Path("id") idPredmet : Int) : Response<List<Grupa>>

    @GET("/kviz/{id}/grupa")
    suspend fun getGrupeZaKviz(@Path("id") idKviza: Int) : Response<List<Grupa>>

    @POST("/grupa/{gid}/student/{id}")
    suspend fun upisiUGrupu(@Path("gid") idGrupa : Int, @Path("id") hashStudenta: String) : Response<Poruka>

    @GET("/student/{id}/grupa")
    suspend fun getUpisaneGrupe(@Path("id") hashStudenta : String) : Response<List<Grupa>>




}