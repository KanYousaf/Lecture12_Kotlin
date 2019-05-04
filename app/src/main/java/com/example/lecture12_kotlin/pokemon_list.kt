package com.example.lecture12_kotlin

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableRow
import kotlinx.android.synthetic.main.fragment_pokemon_list.view.*

class pokemon_list : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rowView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        for (i in 0..rowView.tableLayout.childCount -1) {
            val tableRow = rowView.tableLayout.getChildAt(i) as TableRow
            for (j in 0..tableRow.childCount - 1) {
                val imageButton = tableRow.getChildAt(j) as ImageButton
                imageButton.setOnClickListener { view: View ->
                    val bundle = Bundle()
                    val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
                    val pokemon_details  = fragment_pokemon_details()

                    val button = view as ImageButton
                    bundle.putString("pokemon_name", button.tag.toString())
                    pokemon_details.arguments = bundle

                    if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                        transaction.replace(R.id.container_frame, pokemon_details)
                    } else{
                        transaction.replace(R.id.display_detail_frame, pokemon_details)
                    }

                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
            }
        }
        // Inflate the layout for this fragment
        return rowView
    }
}
