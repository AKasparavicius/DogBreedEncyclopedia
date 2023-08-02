package lt.arnas.dogbreedencyclopedia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import lt.arnas.dogbreedencyclopedia.dogapiservice.DogBreedsResponse
import lt.arnas.dogbreedencyclopedia.dogapiservice.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedViewModel : ViewModel() {
    private val _breeds = MutableLiveData<List<String>>()
    val breeds: LiveData<List<String>>
        get() = _breeds

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        val dogApiService = RetrofitClient.dogApiService
        dogApiService.getBreeds().enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.isSuccessful) {
                    val breedsObject = response.body()

                    val breedList = breedsObject?.getAsJsonObject("message")?.keySet()?.toList()
                        ?: emptyList()
                    _breeds.value = breedList
                } else {
                    // Handle unsuccessful response here
                    Log.e("BreedViewModel", "API call failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                // Handle API call failure here
                Log.e("BreedViewModel", "API call failed: ${t.message}")
            }
        })
    }
}