package com.example.marketsimplified.ui.home.adepter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_VERTICAL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.marketsimplified.R
import com.example.marketsimplified.model.LocalRepoRespItem
import kotlinx.android.synthetic.main.repo_list_item.view.*


class ViewPagerAdapter(
    var context: Context,
    var item: ArrayList<LocalRepoRespItem>,
    var viewPager2: ViewPager2,
    val clickLister: (LocalRepoRespItem) -> Unit
) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.repo_list_item, parent, false)
        return MyViewHolder(
            listItem
        )
    }

    override fun getItemCount(): Int {
        Log.d("item", "item size=" + item.size)
        return item.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(item.get(position), clickLister, viewPager2)
        holder.view.container.setOnClickListener {
            clickLister(item.get(position))
        }
    }

}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        item: LocalRepoRespItem,
        clickLister: (LocalRepoRespItem) -> Unit,
        viewPager2: ViewPager2
    ) {
        try {

            try {

                Glide.with(this.view.context)
                    .load(item.owner.avatar_url)
                    .error(R.drawable.ic_launcher_background)
                    .transform(RoundedCorners(20))
                    .into(view.img)

            } catch (e: Exception) {
                e.printStackTrace()
            }

            view.name.text = item.full_name
            view.desc.text = item.description
            view.btnToggle.setOnClickListener {
                if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_VERTICAL) {
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
                    view.btnToggle.setText("Swipe Horizontal")
                } else {
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL)
                    view.btnToggle.setText("Swipe vertical")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}