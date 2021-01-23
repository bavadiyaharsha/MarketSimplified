package com.example.marketsimplified.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.marketsimplified.R
import com.example.marketsimplified.databinding.FragmentDetailBinding
import com.example.marketsimplified.databinding.FragmentHomeBinding
import com.example.marketsimplified.model.LocalRepoRespItem


class DetailFragment : Fragment() {
    lateinit var databinding: FragmentDetailBinding

    var selectitem: LocalRepoRespItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        try {
            try {
                val bundle = arguments
                selectitem =
                    bundle!!.getSerializable("selsectItem") as LocalRepoRespItem?

            } catch (e: Exception) {
            }

            databinding.login.text = selectitem!!.owner.login
            databinding.nodeid.text = selectitem!!.owner.node_id
            databinding.type.text = selectitem!!.owner.type
            databinding.urls.text = selectitem!!.owner.url
            databinding.name.text = selectitem!!.name

        } catch (e: Exception) {
        }
        return databinding.root
    }


}