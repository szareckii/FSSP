package com.szareckii.searchinthebasefssp.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.result.ResultDetail
import kotlinx.android.synthetic.main.activity_main_recyclerview_item.view.*

class MainAdapter() :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: DataModelResult? = null

    fun setData(data: DataModelResult) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        data?.responseResult?.resultList?.get(0)?.resultDetailList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return data?.responseResult?.resultList?.get(0)?.resultDetailList?.size ?: 0
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: ResultDetail) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.name_textview_recycler_item.text = data.name
                itemView.production_textview_recycler_item.text = data.exe_production
                itemView.details_textview_recycler_item.text = data.details
                itemView.bailiff_textview_recycler_item.text = data.bailiff
            }
        }
    }
}