package com.szareckii.searchinthebasefssp.view.history

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.room.HistoryEntity
import com.szareckii.searchinthebasefssp.utils.regionMapNumber
import kotlinx.android.synthetic.main.activity_history_recyclerview_item.view.*
import java.util.*

class HistoryAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<HistoryEntity> = arrayListOf()

    fun setData(data: List<HistoryEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_history_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: HistoryEntity) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                val name = data.lastname.capitalize(Locale.ROOT) + " " +
                    data.firstname.capitalize(Locale.ROOT) + " " +
                    (data.secondname?.capitalize(Locale.ROOT) ?: "")
                itemView.name_history_textview_recycler_item.text = name
                if (data.birthdate == "") {
                    itemView.birthday_history_textview_recycler_item.visibility = GONE
                } else {
                    itemView.birthday_history_textview_recycler_item.text = data.birthdate
                }
                itemView.region_history_textview_recycler_item.text = regionMapNumber[data.region]

                itemView.setOnClickListener {
                    Log.e("11111111111111111", "setOnClickListener")
                    openInNewWindow(data)
                }
            }
        }
    }

    private fun openInNewWindow(listItemData: HistoryEntity) {
        onListItemClickListener.onItemClick(listItemData)
        Log.e("11111111111111111", "openInNewWindow")
    }

    interface OnListItemClickListener {
        fun onItemClick(data: HistoryEntity)
    }
}