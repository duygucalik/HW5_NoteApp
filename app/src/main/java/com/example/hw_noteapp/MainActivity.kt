package com.example.hw_noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.hw_noteapp.common.viewBinding
import com.example.hw_noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.navController)

            navHostFragment.navController.addOnDestinationChangedListener{
                controller,destination, arguments ->
                if(destination.id== R.id.btn_back){
                    binding.bottomNavigationView.visibility= View.GONE
                }
                else{
                    binding.bottomNavigationView.visibility= View.VISIBLE
                }

            }
        }


    }
}