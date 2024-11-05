package br.edu.ifsp.dmo1.sorteador.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.dmo1.sorteador.R


class SorteioAdapter(private val context: Context, private val sorteios: List<Int>): ArrayAdapter<Int>(context, R.layout.list_item, sorteios) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        val textViewPosition = view.findViewById<TextView>(R.id.textview_position)
        val textViewNumber = view.findViewById<TextView>(R.id.textview_number)

        textViewPosition.text = "${position + 1}º sorteado:"
        textViewNumber.text = getItem(position).toString()

        return view
    }

}