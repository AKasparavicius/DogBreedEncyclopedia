package lt.arnas.dogbreedencyclopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lt.arnas.dogbreedencyclopedia.databinding.ActivityDogInfoBinding

class DogInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDogInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedBreed = intent.getStringExtra("selectedBreed")

        binding.selectedBreed.text = selectedBreed
    }
}