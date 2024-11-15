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


class CopFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_cop, container, false)


        val sotmAsia: ImageView = view.findViewById(R.id.sotmAsia)
        val sotmBd: ImageView = view.findViewById(R.id.sotmBd)

        sotmAsia.setOnClickListener{
            openUrl("https://sotm-asia.github.io/sotm-asia-2024-website/")
        }

        sotmBd.setOnClickListener{
            openUrl("https://sotmbd.org/")
        }


        return view
    }

    private fun openUrl(link: String) {
        val uri = Uri.parse(link)
        val inte = Intent(Intent.ACTION_VIEW, uri)
        startActivity(inte)
    }


}