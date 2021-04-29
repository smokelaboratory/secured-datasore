package com.smokelaboratory.secureddatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.smokelaboratory.secureddatastore.databinding.ActivityMainBinding
import com.smokelaboratory.secureddatastore.util.DataStoreUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var dataStore: DataStoreUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.click = this
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btFetchData.id -> {
                lifecycleScope.launch {
                    dataStore.getData().collect {
                        binding.tvData.text = it
                    }
                }
            }
            binding.btStoreData.id -> {
                lifecycleScope.launch {
                    dataStore.setData(binding.etData.text.toString())
                }
            }
            binding.btFetchSecuredData.id -> {
                lifecycleScope.launch {
                    dataStore.getSecuredData().collect {
                        binding.tvSecuredData.text = it
                    }
                }
            }
            binding.btStoreSecuredData.id -> {
                lifecycleScope.launch {
                    dataStore.setSecuredData(binding.etSecuredData.text.toString())
                }
            }
        }
    }
}