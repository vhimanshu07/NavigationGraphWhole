package com.example.navigationgraphwhole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationgraphwhole.databinding.FragmentSendCashBinding

class SendCashFragment : Fragment(R.layout.fragment_send_cash) {

    private val args: SendCashFragmentArgs by navArgs()
    lateinit var navController: NavController
    lateinit var binding: FragmentSendCashBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSendCashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         *
         * Step 1 Either by old school method or By safe args method
         *
         */
//        val name = arguments?.getString("Name")
//        binding.apply {
//            receiverNameTitle.text = "Send cash to $name"
//        }

        /**
         *
         * Step 2 Safe args method
         *
         */
        navController = findNavController()
        val receiverName = args.receiverName
        binding.apply {
            val amount = cash.text
            receiverNameTitle.text = "Send cash to ${receiverName} "
            btnNext.setOnClickListener {
                if (amount.isNullOrEmpty()) {
                    return@setOnClickListener
                }
                val action =
                    SendCashFragmentDirections.actionSendCashFragmentToConfirmDialogFragment22(
                        receiverName = receiverName,
                        amount = amount.toString().toLong()
                    )
                navController.navigate(action)
            }
            /**
             *
             * Using PopUpTo and PopUpToInclusive to overcome circular
             * navigation (Check Notes for clarification)
             *
             * Can be done via Navgraph (Click on action which is moving from Fragment A to B).
             *
             * Or Can be via code check Choose Receiver fragment
             *
             *
             */
            btnDone.setOnClickListener {
                val action = SendCashFragmentDirections.actionSendCashFragmentToHomeFragment()
                navController.navigate(action)
            }

            /**
             *
             * Will navigate to Home screen directly if we provide Id and popuptoinclusive true herre
             *
             */
            btnCancel.setOnClickListener {
                navController.popBackStack(R.id.homeFragment, true)
            }

            /**
             *
             * Getting data from live data
             *
             */
            cash.setText(SampleData.amount.toString())
            SampleData.amount.observe(viewLifecycleOwner) {
                cash.setText(it.toString())
            }

        }


    }

}
