package com.example.navigationgraphwhole

import android.Manifest
import android.app.PendingIntent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.navigationgraphwhole.databinding.FragmentChooseReceiverBinding

class ChooseReceiverFragment : Fragment(R.layout.fragment_choose_receiver) {
    lateinit var binding: FragmentChooseReceiverBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChooseReceiverBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            btnNext.setOnClickListener {
                val receiverName = receiverName.text.toString()

                /**
                 *
                 * We can send data by the bundle method or by Safe Args method
                 *
                 */
//                val args = Bundle()
//                args.putString("Name", receiverName)
//                navController.navigate(R.id.sendCashFragment, args)


                /**
                 *
                 * Notification and Deep link handling
                 *
                 */
                val pendingIntent = navController
                    .createDeepLink()
                    .setGraph(R.navigation.main_nav)
                    .setDestination(R.id.sendCashFragment)
                    .setArguments(SendCashFragmentArgs(receiverName).toBundle())
                    .createPendingIntent()

                showNotification(pendingIntent, receiverName)

                val action =
                    ChooseReceiverFragmentDirections.actionChooseReceiverFragmentToSendCashFragment(
                        receiverName
                    )
                navController.navigate(action)
            }
            /**
             *
             * Will navigate to Home screen
             *
             */
            btnCancel.setOnClickListener {
                navController.popBackStack()
            }
        }
    }

    private fun showNotification(pendingIntent: PendingIntent, receiverName: String) {
        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Complete transaction")
            .setContentText("Send money to $receiverName")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(requireContext()).notify(0, notification)

    }
}
