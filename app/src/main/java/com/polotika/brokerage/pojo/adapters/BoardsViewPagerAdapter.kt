package com.polotika.brokerage.pojo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.polotika.brokerage.R
import com.polotika.brokerage.databinding.OnBoardingLayoutBinding
import com.polotika.brokerage.pojo.models.BoardItem


class BoardsViewPagerAdapter(private val list: List<BoardItem>) :
    RecyclerView.Adapter<BoardsViewPagerAdapter.ViewHolder>() {

    /*   override fun getCount()= list.size

       override fun isViewFromObject(view: View, `object`: Any): Boolean {
           return view == `object`
       }

       override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
           container.removeView(`object` as View)
       }

       override fun instantiateItem(container: ViewGroup, position: Int): Any {
           val inflater = LayoutInflater.from(container.context)
           val item :OnBoardingLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.on_boarding_layout,container,false)

           item.board = list.get(position)
           container.addView(item.root)
           return item
       }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<OnBoardingLayoutBinding>(
            inflater,
            R.layout.on_boarding_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    class ViewHolder(private val binding: OnBoardingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: BoardItem) {
            binding.board = board
            binding.invalidateAll()
        }
    }

}

class FadePageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        if (position < -1 || position > 1) {
            view.alpha = 0f
        } else if (position <= 0 || position <= 1) {
            // Calculate alpha. Position is decimal in [-1,0] or [0,1]
            val alpha = if (position <= 0) position + 1 else 1 - position
            view.alpha = alpha
        } else if (position == 0f) {
            view.alpha = 1f
        }

    }


}