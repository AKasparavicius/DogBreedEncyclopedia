package lt.arnas.dogbreedencyclopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import lt.arnas.dogbreedencyclopedia.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import lt.arnas.dogbreedencyclopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var breedViewModel: BreedViewModel
    private lateinit var binding: ActivityMainBinding
    private var selectedBreed: String? = null

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

        binding.spinnerBreeds.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selected = parent?.getItemAtPosition(position).toString()
                if (selected != "Select a breed") {
                    selectedBreed = selected // Store the selected breed
                    binding.selectBreedButton.visibility = View.VISIBLE // Show the button
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.selectBreedButton.setOnClickListener {
            if (selectedBreed != null) {
                val intent = Intent(this, DogInfoActivity::class.java)
                intent.putExtra("selectedBreed", selectedBreed)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a breed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}