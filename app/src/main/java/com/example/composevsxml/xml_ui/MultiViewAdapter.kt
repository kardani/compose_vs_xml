package com.example.composevsxml.xml_ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.composevsxml.DataModel
import com.example.composevsxml.R
import com.squareup.picasso.Picasso

class MultiViewAdapter(
    private var listener: OnItemClickListener? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = mutableListOf<DataModel>()

    companion object {
        private const val VIEW_TYPE_LARGE = 1
        private const val VIEW_TYPE_SMALL = 2
    }

    override fun getItemViewType(position: Int): Int {
        // Return the view type based on your data model
        return when (dataList[position]) {
            is DataModel.Large -> VIEW_TYPE_LARGE
            is DataModel.Small -> VIEW_TYPE_SMALL
        }
    }

    // Method to update data using DiffUtil
    fun updateData(newData: List<DataModel>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = dataList.size
            override fun getNewListSize(): Int = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataList[oldItemPosition].id == newData[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataList[oldItemPosition] == newData[newItemPosition]
            }
        })

        dataList.clear()
        dataList.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getBiggestId(): Int {
        return dataList.maxByOrNull { it.id.toInt() }?.id?.toInt() ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LARGE -> {
                val view = inflater.inflate(R.layout.list_item_large, parent, false)
                ListItemLargeViewHolder(view)
            }
            VIEW_TYPE_SMALL -> {
                val view = inflater.inflate(R.layout.list_item_small, parent, false)
                ListItemSmallViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]

        holder.itemView.findViewById<ImageView>(R.id.userImage)?.let {
            Log.d("ListItem", "onBindViewHolder: ${item.userImageUrl}")
            Picasso.get()
                .load(item.userImageUrl)
                .into(it)
        }

        holder.itemView.findViewById<TextView>(R.id.userName).text = "${item.id}_${item.userFullName}"
        holder.itemView.findViewById<TextView>(R.id.challengesCompleted).text = "Completed ${item.completedChallenges} of ${item.totalChallenges} challenges"

        holder.itemView.findViewById<RatingBar>(R.id.userRating).rating = item.rate

        holder.itemView.setOnClickListener {
            listener?.onItemClick(item)
        }

        holder.itemView.findViewById<ImageView>(R.id.deleteButton)?.setOnClickListener {
            listener?.onItemDeleteClick(item)
        }

    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onItemClick(item: DataModel)
        fun onItemDeleteClick(item: DataModel)
    }
}