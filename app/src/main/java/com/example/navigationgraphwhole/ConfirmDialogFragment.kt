package com.example.navigationgraphwhole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.navigationgraphwhole.databinding.FragmentConfirmDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmDialogFragment : BottomSheetDialogFragment() {

    private val args: ConfirmDialogFragmentArgs by navArgs()
    lateinit var binding: FragmentConfirmDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentConfirmDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receiverName = args.receiverName
        val amount = args.amount
        binding.apply {
            message.text = "Do you want to send ${amount} to ${receiverName} ?"
            yesBtn.setOnClickListener {
                Toast.makeText(
                    context,
                    "${amount} has been sent to ${receiverName}",
                    Toast.LENGTH_SHORT
                ).show()
                dismiss()
            }
            noBtn.setOnClickListener {
                dismiss()
            }

        }
    }
}