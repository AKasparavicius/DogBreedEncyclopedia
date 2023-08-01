package lt.arnas.dogbreedencyclopedia.dogapiservice

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/list/all")
    fun getBreeds(): Call<JsonObject>
}