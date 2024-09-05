package com.example.navigationgraphwhole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.navigationgraphwhole.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            btnSendMoney.setOnClickListener {
                val navOptions  = NavOptions.Builder()
                    .build()
               // navController.navigate(R.id.chooseReceiverFragment,null,navOptions)
                /**
                 *
                 * Either way possible
                 *
                 */
              //  navController.navigate(R.id.action_homeFragment_to_chooseReceiverFragment)
                /**
                 *
                 * Or by this way
                 *
                 */
                val action = HomeFragmentDirections.actionHomeFragmentToChooseReceiverFragment()
                navController.navigate(action)
            }
            btnTransaction.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_viewTransactionFragment)
            }
            btnViewBalance.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_viewBalanceFragment)
            }
        }
    }

}
