package com.polotika.brokerage.ui.onBoard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotika.brokerage.BR
import com.polotika.brokerage.R
import com.polotika.brokerage.pojo.models.BoardItem
import me.tatarka.bindingcollectionadapter2.ItemBinding


class OnBoardViewModel :ViewModel() {

    val currentPage = MutableLiveData<Int>()

    val itemBinding:ItemBinding<BoardItem> = ItemBinding.of(BR.board, R.layout.on_boarding_layout)

    val pages = MutableLiveData(listOf(
        BoardItem("Lorem Ipsum","",R.drawable.img1),
        BoardItem("Lorem Ipsum","",R.drawable.img2),
        BoardItem("Lorem Ipsum","",R.drawable.img3)
    )
    )

/*
    fun nextPage(current: Int, pages: List<BoardItem>) {
        if (current < pages.lastIndex) {
            currentPage.value = current.inc()
        } else {
            completeOnboarding()
        }
    }
    val buttonText: LiveData<CharSequence> =
        Lives.combineLatest(currentPage, pages) { currentPage, pages ->
            pages[currentPage].buttonText
        }
*/

}