package org.map_bd.sotmasia2024

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import org.map_bd.sotmasia2024.databinding.FragmentWebBinding

class WebFragment : Fragment() {


    private var _binding: FragmentWebBinding? = null
    private val binding  get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentWebBinding.inflate(inflater,container,false)
        return binding.root


    }





}