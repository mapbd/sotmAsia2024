package org.map_bd.sotmasia2024.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.map_bd.sotmasia2024.NoteActivity
import org.map_bd.sotmasia2024.R


class CocFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_coc, container, false)
        val view: View = inflater.inflate(R.layout.fragment_coc, container, false)

        val wiki: TextView =view.findViewById(R.id.wikiIds)
        wiki.setOnClickListener{
            openUrl("https://wiki.openstreetmap.org/wiki/State_of_the_Map_Asia")
        }

        return view
    }


    private fun openUrl(link: String) {
        val uri = Uri.parse(link)
        val inte = Intent(Intent.ACTION_VIEW, uri)
        startActivity(inte)
    }


}