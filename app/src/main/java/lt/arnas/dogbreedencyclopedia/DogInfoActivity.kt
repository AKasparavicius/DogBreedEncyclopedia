package lt.arnas.dogbreedencyclopedia

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import lt.arnas.dogbreedencyclopedia.databinding.ActivityDogInfoBinding
import lt.arnas.dogbreedencyclopedia.dogapiservice.DogApiResponse
import lt.arnas.dogbreedencyclopedia.dogapiservice.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDogInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedBreed = intent.getStringExtra("selectedBreed")

        binding.breedSelected.text = "Your breed is $selectedBreed"

        fetchBreedInformation(selectedBreed)

    }

    private fun fetchBreedInformation(breed: String?) {
        breed?.let {
            // Call the API to get information about the breed
            val breedInfoCall = RetrofitClient.dogApiService.getBreedPhoto(it)
            breedInfoCall.enqueue(object : Callback<DogApiResponse> {
                override fun onResponse(
                    call: Call<DogApiResponse>,
                    response: Response<DogApiResponse>
                ) {
                    if (response.isSuccessful) {

                        // Display the breed information in the TextView
                        val imageUrl = response.body()?.message
                        imageUrl?.let {
                            // Load the image using Glide
                            Glide.with(this@DogInfoActivity)
                                .load(it)
                                .into(binding.breedImage)
                        }

                    } else {
                        // Handle unsuccessful response here
                        Log.e(
                            "API_ERROR",
                            "Error code: ${response.code()}, Message: ${response.message()}"
                        )
                    }
                }

                override fun onFailure(call: Call<DogApiResponse>, t: Throwable) {
                    // Handle API call failure here
                    Log.e("API_ERROR", "Error: ${t.message}")
                }
            })
        }
    }
}