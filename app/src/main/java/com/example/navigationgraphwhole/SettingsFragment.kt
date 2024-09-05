package com.example.navigationgraphwhole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.navigationgraphwhole.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    lateinit var binding: FragmentSettingsBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.defaultAmount.setText(SampleData.amount.value.toString())
        binding.saveBtn.setOnClickListener {
            val amount = binding.defaultAmount.text.toString().toLong()
            SampleData.amount.value = amount
        }
        binding.aboutThisAppBtn.setOnClickListener {
            val action = MainNavDirections.actionGlobalAboutAppFragment()
            navController.navigate(action)
        }


    }

}
