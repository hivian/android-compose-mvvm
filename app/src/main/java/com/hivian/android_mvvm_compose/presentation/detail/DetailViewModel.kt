package com.hivian.android_mvvm_compose.presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hivian.android_mvvm_compose.core.base.ViewModelBase
import com.hivian.android_mvvm_compose.domain.mappers.ImageSize
import com.hivian.android_mvvm_compose.domain.services.application.IRandomUsersService
import com.hivian.android_mvvm_compose.presentation.di.UserId
import com.hivian.android_mvvm_compose.ui.services.navigation.INavigationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @UserId private val userId: Int,
    private val randomUsersService: IRandomUsersService,
    private val navigationService: INavigationService
): ViewModelBase() {

    val picture = mutableStateOf("")

    val name = mutableStateOf("")

    val email = mutableStateOf("")

    val cell = mutableStateOf("")

    val phone = mutableStateOf("")

    override fun initialize() {
        if (isInitialized.value == true) return

        viewModelScope.launch {
            randomUsersService.getUserById(userId, ImageSize.LARGE).let { userDomain ->
                picture.value = userDomain.picture
                name.value = userDomain.fullName
                email.value = userDomain.email
                cell.value = userDomain.cell
                phone.value = userDomain.phone
            }
        }

        isInitialized.value = true
    }

    fun navigateBack() {
        navigationService.navigateBack()
    }

}