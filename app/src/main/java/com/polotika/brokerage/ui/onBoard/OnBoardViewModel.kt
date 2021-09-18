package com.polotika.brokerage.ui.onBoard

import androidx.lifecycle.MutableLiveData
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.pojo.models.BoardItem
import com.polotika.brokerage.utils.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnBoardViewModel @Inject constructor(private val prefs: PreferencesUtils) :
    BaseViewModel<BaseNavigator>() {


    val navigationEvent = MutableLiveData<OnBoardingNavigationEvent>()

    val currentPage = MutableLiveData(0)

    val pages = MutableLiveData(
        listOf(
            BoardItem(R.string.lorem_ipsum_title, R.string.lorem_ipsum_desc, R.drawable.img1),
            BoardItem(R.string.lorem_ipsum_title, R.string.lorem_ipsum_desc, R.drawable.img2),
            BoardItem(R.string.lorem_ipsum_title, R.string.lorem_ipsum_desc, R.drawable.img3)
        )
    )


    fun setCurrentPage(page: Int) {
        when (page) {
            in 0..2 -> {
                currentPage.value = page
            }
            else -> {
                completeOnBoarding()
            }
        }
    }

    fun completeOnBoarding() {
        CoroutineScope(IO).launch {
            prefs.completeOnBoarding()
        }
        navigationEvent.value = OnBoardingFinish
    }

    fun destroy() {
        this.onCleared()

    }

    override fun onCleared() {
        super.onCleared()
    }
}