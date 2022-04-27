package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.auth.sign_up

import androidx.lifecycle.*
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local.preferences.user.UserPreferences
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.validators.LiveDataValidatorResolver
import com.endava.internship.mobile.weatherapp.utils.Constants
import com.endava.internship.mobile.weatherapp.utils.isValidEmail
import com.endava.internship.mobile.weatherapp.utils.validators.LiveDataValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val preferences: UserPreferences
) : ViewModel() {
    private val _isCreated: MutableLiveData<Boolean> = MutableLiveData()
    val isCreated: LiveData<Boolean> = _isCreated

    val email = MutableLiveData<String>()
    val emailValidator = LiveDataValidator(email).apply {
        addRule(Constants.EMAIL_ERR) { it?.isValidEmail() ?: false }
    }

    val password = MutableLiveData<String>()
    val passwordValidator = LiveDataValidator(password).apply {
        addRule(Constants.PASSWORD_ERR) { if (it == null) false else it.length >= 6 }
    }

    val isSignUpFormValidMediator = MediatorLiveData<Boolean>()

    init {
        _isCreated.value = false
        isSignUpFormValidMediator.value = false
        isSignUpFormValidMediator.addSource(email) { validateForm(isEmailChanged = true) }
        isSignUpFormValidMediator.addSource(password) { validateForm(isPasswordChanged = true) }
    }

    private fun validateForm(
        isEmailChanged: Boolean = false,
        isPasswordChanged: Boolean = false
    ) {
        val validators = listOf(Pair(emailValidator, isEmailChanged), Pair(passwordValidator, isPasswordChanged))
        val validatorResolver = LiveDataValidatorResolver(validators)
        isSignUpFormValidMediator.value = validatorResolver.isValid()
    }

    fun signUpUser() = viewModelScope.launch {
        val emailString = email.value
        val passwordString = password.value

        if(emailString != null && passwordString != null) {
            preferences.saveEmail(emailString)
            preferences.savePassword(passwordString)
            _isCreated.value = true
        }
    }
}