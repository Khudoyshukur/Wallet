package uz.androdev.wallet.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import uz.androdev.wallet.base.BaseFragment
import uz.androdev.wallet.databinding.FragmentHistoryBinding

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {
    private val viewModel: HistoryViewModel by viewModels()
    private var historyAdapter: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeUiState()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterTransition = MaterialFadeThrough()

        initUI()
    }

    private fun initUI() {
        historyAdapter = HistoryAdapter()
        binding.recyclerView.adapter = historyAdapter
    }

    private fun observeUiState() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.transactions.distinctUntilChanged().collect {
                historyAdapter?.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        historyAdapter = null
        super.onDestroyView()
    }
}