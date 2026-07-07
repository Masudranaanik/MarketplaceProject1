package com.example.anik23

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anik23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sliderHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sliderImages = listOf(
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3
        )
        binding.viewPager.adapter = SliderAdapter(sliderImages)
        startAutoSlider(sliderImages.size)
        binding.viewPager.adapter = SliderAdapter(sliderImages)
        startAutoSlider(sliderImages.size)

        val products = listOf(

            Product("HeadePhone", "MRP 500 ", R.drawable.headphone),
            Product("Camera", "MRP 11,000 ", R.drawable.camera),

            Product("Light", "MRP 5,000TK ", R.drawable.light),

            Product("Sirum", "MRP 1000TK", R.drawable.sirum)
        )

        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewProducts.adapter = ProductAdapter(products)
    }
    private fun startAutoSlider(itemCount: Int) {
        val runnable = object : Runnable {
            override fun run() {
                var currentItem = binding.viewPager.currentItem
                currentItem = if (currentItem == itemCount - 1) 0 else currentItem + 1
                binding.viewPager.currentItem = currentItem
                sliderHandler.postDelayed(this, 3000)
            }
        }
        sliderHandler.postDelayed(runnable, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        sliderHandler.removeCallbacksAndMessages(null)
    }
}