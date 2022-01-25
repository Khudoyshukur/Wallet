package uz.androdev.wallet.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.androdev.wallet.databinding.FragmentOnBoardingBinding
import uz.androdev.wallet.base.BaseFragment

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@AndroidEntryPoint
class OnBoardingFragment :
    BaseFragment<FragmentOnBoardingBinding>(FragmentOnBoardingBinding::inflate) {

    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            start()
        }
    }

    private fun start() {
        viewModel.setOnBoarded(true) {
            findNavController().navigate(
                OnBoardingFragmentDirections.actionOnBoardingFragmentToWalletFragment()
            )
        }
    }
}