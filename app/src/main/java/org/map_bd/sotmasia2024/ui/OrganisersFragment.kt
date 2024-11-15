package org.map_bd.sotmasia2024.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.map_bd.sotmasia2024.R


class OrganisersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_organisers, container, false)

        val boil: ImageView =view.findViewById(R.id.boilId)
        val osmbd: ImageView =view.findViewById(R.id.osmbdId)
        val ymbd: ImageView =view.findViewById(R.id.ymId)

        boil.setOnClickListener{
            openUrl("https://boiledbhoot.org/")
        }
        osmbd.setOnClickListener{
            openUrl("https://osmbd.org/")
        }
        ymbd.setOnClickListener{
            openUrl("https://www.facebook.com/groups/2923232317707849")
        }


        return view



    }

    private fun openUrl(link: String) {
        val uri = Uri.parse(link)
        val inte = Intent(Intent.ACTION_VIEW, uri)
        startActivity(inte)
    }

}