package uz.androdev.wallet.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.androdev.wallet.R
import uz.androdev.wallet.databinding.FragmentTransactionBinding
import java.io.Serializable

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@AndroidEntryPoint
class TransactionFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransactionViewModel by viewModels()
    private val navArgs by navArgs<TransactionFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchCategories(navArgs.transactionType)
        observeUiState()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.map { it.loading }
                    .distinctUntilChanged()
                    .collect {
                        binding.loading.isVisible = it
                        binding.root.descendantFocusability = if (it) {
                            ViewGroup.FOCUS_BLOCK_DESCENDANTS
                        } else {
                            ViewGroup.FOCUS_AFTER_DESCENDANTS
                        }
                    }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.map { it.categories }
                    .distinctUntilChanged()
                    .collect {
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_selectable_list_item,
                            it.map { it.title })
                        (binding.categoryInputLayout.editText as? AutoCompleteTextView)?.setAdapter(
                            adapter
                        )
                    }
            }
        }
    }

    private fun initUI() {
        binding.title.text = when (navArgs.transactionType) {
            TransactionType.Expense -> getString(R.string.expense)
            TransactionType.Income -> getString(R.string.income)
        }

        binding.categoryInputLayout.editText?.doOnTextChanged { _, _, _, _ ->
            binding.categoryInputLayout.error = null
        }
        binding.amountInputLayout.editText?.doOnTextChanged { _, _, _, _ ->
            binding.amountInputLayout.error = null
        }
        binding.detailsInputLayout.editText?.doOnTextChanged { _, _, _, _ ->
            binding.detailsInputLayout.error = null
        }

        binding.btnEnter.setOnClickListener {
            when {
                binding.amountEdittext.text.isNullOrBlank() -> {
                    binding.amountInputLayout.error = getString(R.string.please_set_amount)
                }
                binding.amountEdittext.text.toString().toDoubleOrNull() == null -> {
                    binding.amountInputLayout.error = getString(R.string.please_set_correct_amount)
                }
                binding.categoryInputLayout.editText?.text.isNullOrBlank() -> {
                    binding.categoryInputLayout.error = getString(R.string.please_choose_category)
                }
                binding.detailsEdittext.text.isNullOrBlank() -> {
                    binding.detailsInputLayout.error = getString(R.string.please_enter_details)
                }
                else -> {
                    viewModel.enterTransaction(
                        details = binding.detailsEdittext.text.toString(),
                        amount = binding.amountEdittext.text.toString().toDouble(),
                        categoryName = binding.categoryInputLayout.editText?.text.toString(),
                        type = navArgs.transactionType,
                        onComplete = { dismiss() }, onNotEnoughMoney = {
                            binding.amountInputLayout.error = getString(R.string.not_enough_money)
                        })
                }
            }
        }
    }
}

sealed class TransactionType : Serializable {
    object Income : TransactionType()
    object Expense : TransactionType()
}