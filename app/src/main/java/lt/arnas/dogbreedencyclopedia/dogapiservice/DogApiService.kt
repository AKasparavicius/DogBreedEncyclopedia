package lt.arnas.dogbreedencyclopedia.dogapiservice

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {
    @GET("breeds/list/all")
    fun getBreeds(): Call<JsonObject>

    @GET("breed/{breedName}/images/random")
    fun getBreedPhoto(@Path("breedName") breedName: String): Call<DogApiResponse>

    @GET("breed/{breedName}/images/random")
    fun getRandomPicture(@Path("breedName") breedName: String): Call<DogApiResponse>

}