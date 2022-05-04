package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.ui.auth.sign_up

import android.content.Context
import androidx.lifecycle.*
import com.endava.internship.mobile.weatherapp.R
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.data.local.preferences.user.UserPreferences
import com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils.validators.LiveDataValidatorResolver
import com.endava.internship.mobile.weatherapp.utils.isValidEmail
import com.endava.internship.mobile.weatherapp.utils.validators.LiveDataValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val preferences: UserPreferences
) : ViewModel() {
    private val _isCreated: MutableLiveData<Boolean> = MutableLiveData()
    val isCreated: LiveData<Boolean> = _isCreated

    val email = MutableLiveData<String>()
    val emailValidator = LiveDataValidator(email).apply {
        addRule(context.getString(R.string.auth_sign_up_email_error)) {
            it?.isValidEmail() ?: false
        }
    }

    val password = MutableLiveData<String>()
    val passwordValidator = LiveDataValidator(password).apply {
        addRule(context.getString(R.string.auth_sign_up_password_error)) {
            if (it == null) false else it.length >= 6
        }
    }

    val confirmPassword = MutableLiveData<String>()
    val confirmPasswordValidator = LiveDataValidator(confirmPassword).apply {
        addRule(context.getString(R.string.auth_sign_up_confirm_password_error)) {
            if (it == null) false else it == password.value
        }
    }

    val isSignUpFormValidMediator = MediatorLiveData<Boolean>()

    init {
        _isCreated.value = false
        isSignUpFormValidMediator.value = false
        isSignUpFormValidMediator.addSource(email) { validateForm(isEmailChanged = true) }
        isSignUpFormValidMediator.addSource(password) { validateForm(isPasswordChanged = true) }
        isSignUpFormValidMediator.addSource(confirmPassword) { validateForm(isConfirmPasswordChanged = true) }
    }

    private fun validateForm(
        isEmailChanged: Boolean = false,
        isPasswordChanged: Boolean = false,
        isConfirmPasswordChanged: Boolean = false
    ) {
        val validators = listOf(
            Pair(emailValidator, isEmailChanged),
            Pair(passwordValidator, isPasswordChanged),
            Pair(confirmPasswordValidator, isConfirmPasswordChanged)
        )
        val validatorResolver = LiveDataValidatorResolver(validators)
        isSignUpFormValidMediator.value = validatorResolver.isValid()
    }

    fun signUpUser() = viewModelScope.launch {
        val emailString = email.value
        val passwordString = password.value

        if (emailString != null && passwordString != null) {
            preferences.saveEmail(emailString)
            preferences.savePassword(passwordString)
            _isCreated.value = true
        }
    }
}