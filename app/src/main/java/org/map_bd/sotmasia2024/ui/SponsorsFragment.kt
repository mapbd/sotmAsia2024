package org.map_bd.sotmasia2024.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.map_bd.sotmasia2024.R


class SponsorsFragment : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_sponsors, container, false)

        val hot: ImageView =view.findViewById(R.id.hotId)
        val meta: ImageView =view.findViewById(R.id.metaId)
        val barikoi: ImageView =view.findViewById(R.id.barikoiId)
        val boil: ImageView =view.findViewById(R.id.boilId)
        val tomtom: ImageView =view.findViewById(R.id.tomtomId)
        val bkash: ImageView =view.findViewById(R.id.bkashId)
        hot.setOnClickListener{
            openUrl("https://www.hotosm.org/")
        }
        meta.setOnClickListener{
            openUrl("https://www.meta.com/")
        }
        barikoi.setOnClickListener{
            openUrl("https://barikoi.com/")
        }
        boil.setOnClickListener{
            openUrl("https://boiledbhoot.org/")
        }
        tomtom.setOnClickListener{
            openUrl("https://www.tomtom.com/")
        }
        bkash.setOnClickListener{
            openUrl("https://www.bkash.com/")
        }

        return view
    }

    private fun openUrl(link: String) {
        val uri = Uri.parse(link)
        val inte = Intent(Intent.ACTION_VIEW, uri)
        startActivity(inte)
    }


}