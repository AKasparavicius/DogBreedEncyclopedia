package lt.arnas.dogbreedencyclopedia

import androidx.appcompat.app.AppCompatActivity
import lt.arnas.dogbreedencyclopedia.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import lt.arnas.dogbreedencyclopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var breedViewModel: BreedViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        breedViewModel = ViewModelProvider(this).get(BreedViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE // Show progress bar
        binding.spinnerBreeds.visibility = View.GONE // Hide Spinner while loading

        // Observe the list of dog breeds
        breedViewModel.breeds.observe(this, { breeds ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, breeds)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerBreeds.adapter = adapter

            binding.progressBar.visibility = View.GONE
            binding.spinnerBreeds.visibility = View.VISIBLE
        })
    }
}