package com.misw.alarmify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomAdapter(
    context: Context,
    private val items: List<String>
) : ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_layout, parent, false)

        val textView = view.findViewById<TextView>(R.id.itemText)
        val checkbox = view.findViewById<CheckBox>(R.id.itemCheckbox)
        val arrow = view.findViewById<ImageView>(R.id.itemArrow)

        textView.text = items[position]
        textView.setTextColor(ContextCompat.getColor(context, R.color.quaternary))

        when (position) {
            0 -> {
                checkbox.visibility = View.VISIBLE
                arrow.visibility = View.GONE
            }
            else -> {
                checkbox.visibility = View.GONE
                arrow.visibility = View.VISIBLE
            }
        }

        return view
    }
}
