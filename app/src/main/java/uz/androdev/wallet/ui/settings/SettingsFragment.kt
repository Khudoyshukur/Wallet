package uz.androdev.wallet.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import uz.androdev.shared.model.CategoryType
import uz.androdev.wallet.R
import uz.androdev.wallet.databinding.FragmentSettingsBinding
import uz.androdev.wallet.base.BaseFragment
import uz.androdev.wallet.databinding.DialogAddCategoryBinding
import uz.androdev.wallet.databinding.DialogEditNameBinding
import uz.androdev.wallet.ui.transaction.TransactionType

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeUiState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.btnEditName.setOnClickListener {
            editName()
        }

        binding.btnAddCategory.setOnClickListener {
            addCategory()
        }
    }

    private fun addCategory() {
        var dialog: Dialog? = null
        AlertDialog.Builder(requireContext()).apply {
            val dBinding = DialogAddCategoryBinding.inflate(layoutInflater, binding.root, false)
            setView(dBinding.root)
            dBinding.categoryText.doOnTextChanged { _, _, _, _ ->
                dBinding.categoryInputLayout.error = null
            }
            dBinding.nameEdittext.doOnTextChanged { _, _, _, _ ->
                dBinding.nameInputLayout.error = null
            }

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_selectable_list_item,
                listOf(CATEGORY_INCOME, CATEGORY_EXPENSE)
            )
            (dBinding.categoryInputLayout.editText as? AutoCompleteTextView)?.setAdapter(adapter)

            dBinding.submit.setOnClickListener {
                if (dBinding.nameEdittext.text.isNullOrBlank()) {
                    dBinding.nameInputLayout.error = getString(R.string.enter_category_name)
                    return@setOnClickListener
                } else if (dBinding.categoryText.text.isNullOrBlank()) {
                    dBinding.categoryInputLayout.error = getString(R.string.choose_category_type)
                    return@setOnClickListener
                }

                viewModel.addCategory(
                    dBinding.nameEdittext.text.toString(),
                    if (dBinding.categoryText.text.toString() == CATEGORY_INCOME) CategoryType.INCOME else CategoryType.EXPENSE
                )
                dialog?.dismiss()
            }
            dialog = create()
            dialog?.show()
        }
    }

    private fun editName() {
        var dialog: Dialog? = null
        AlertDialog.Builder(requireContext()).apply {
            val dBinding = DialogEditNameBinding.inflate(layoutInflater, binding.root, false)
            setView(dBinding.root)

            dBinding.nameEdittext.doOnTextChanged { _, _, _, _ ->
                dBinding.nameInputLayout.error = null
            }

            dBinding.submit.setOnClickListener {
                if (dBinding.nameEdittext.text.isNullOrBlank()) {
                    dBinding.nameInputLayout.error = getString(R.string.enter_name)
                    return@setOnClickListener
                }

                viewModel.setUserName(dBinding.nameEdittext.text.toString())
                dialog?.dismiss()
            }
            dialog = create()
            dialog?.show()
        }
    }

    private fun observeUiState() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.username.distinctUntilChanged().collect {
                binding.txtName.text = it
            }
        }
    }

    companion object {
        private const val CATEGORY_INCOME = "Kirim"
        private const val CATEGORY_EXPENSE = "Chiqim"
    }
}