package uz.androdev.wallet.ui.debt

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import uz.androdev.shared.model.Expense
import uz.androdev.wallet.R
import uz.androdev.wallet.databinding.FragmentDebtBinding
import uz.androdev.wallet.base.BaseFragment

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@AndroidEntryPoint
class DebtFragment : BaseFragment<FragmentDebtBinding>(FragmentDebtBinding::inflate) {

    private val viewModel: DebtViewModel by viewModels()
    private var debtsAdapter: DebtAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeDebts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        debtsAdapter = DebtAdapter(::onDebtClicked)
        binding.recyclerView.adapter = debtsAdapter
    }

    private fun observeDebts() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.debts.distinctUntilChanged().collect {
                debtsAdapter?.submitList(it)
            }
        }
    }

    private fun onDebtClicked(expense: Expense) {
        var dialog: Dialog? = null
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.delete)
            setMessage(R.string.ask_to_remove)
            setPositiveButton(R.string.yes) { _, _ ->
                viewModel.removeDebt(expense)
                dialog?.dismiss()
            }
            setNegativeButton(R.string.no) { _, _ ->
                dialog?.dismiss()
            }

            dialog = create()
            dialog?.show()
        }
    }

    override fun onDestroyView() {
        debtsAdapter = null
        super.onDestroyView()
    }
}