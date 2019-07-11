package com.crecrew.crecre.UI.Fragment.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.crecrew.crecre.Data.CurrentRankData
import com.crecrew.crecre.R

class RankRecyclerViewAdapter(val list: List<CurrentRankData>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): CurrentRankData {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            holder = ViewHolder(convertView!!)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val item = getItem(position)
        holder.title.setText(item.rank)
        holder.text.setText(item.name)
        return convertView
    }
    inner class ViewHolder(view: View) {
        val title: TextView = view.findViewById(R.id.title) as TextView
        val text: TextView = view.findViewById(R.id.text) as TextView
    }
}