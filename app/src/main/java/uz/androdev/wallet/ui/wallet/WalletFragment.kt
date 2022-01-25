package uz.androdev.wallet.ui.wallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import uz.androdev.wallet.R
import uz.androdev.wallet.base.BaseFragment
import uz.androdev.wallet.databinding.FragmentWalletBinding
import uz.androdev.wallet.ui.transaction.TransactionType
import uz.androdev.wallet.ui.util.toFormattedBalance

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@AndroidEntryPoint
class WalletFragment : BaseFragment<FragmentWalletBinding>(FragmentWalletBinding::inflate) {

    private val viewModel: WalletViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeWalletState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!viewModel.isOnBoarded()) {
            findNavController().navigate(
                WalletFragmentDirections.actionWalletFragmentToOnBoardingFragment()
            )
            return
        }

        initUI()
    }

    private fun initUI() {
        binding.expenseCard.setOnClickListener {
            findNavController().navigate(
                WalletFragmentDirections.actionWalletFragmentToTransactionFragment(TransactionType.Expense)
            )
        }
        binding.incomeCard.setOnClickListener {
            findNavController().navigate(
                WalletFragmentDirections.actionWalletFragmentToTransactionFragment(TransactionType.Income)
            )
        }
    }

    private fun observeWalletState() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.wallet.collect {
                binding.txtBalance.text = it.balance.toFormattedBalance(requireContext())
            }
        }

        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.username.distinctUntilChanged().collect {
                binding.txtGreeting.text = String.format(getString(R.string.txt_greeting), it)
            }
        }
    }
}