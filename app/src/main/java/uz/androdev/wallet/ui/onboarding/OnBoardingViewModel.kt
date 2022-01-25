package uz.androdev.wallet.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.androdev.shared.repository.PreferencesRepository
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    fun setOnBoarded(onBoarded: Boolean, callback: () -> Unit) = viewModelScope.launch {
        preferencesRepository.setOnBoarded(onBoarded)
        callback.invoke()
    }

}