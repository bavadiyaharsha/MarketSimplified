package com.example.marketsimplified.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.marketsimplified.R
import com.example.marketsimplified.databinding.FragmentHomeBinding
import com.example.marketsimplified.model.LocalRepoRespItem
import com.example.marketsimplified.ui.home.adepter.ViewPagerAdapter


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var progressDialog: ProgressDialog
    lateinit var databinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)


        homeViewModel.getLocalRepo()



            homeViewModel.isLoading.observe(this.viewLifecycleOwner, Observer {

                if (it) {
                    progressDialog = ProgressDialog(this.requireContext())
                    progressDialog.setMessage("loading...")
                    progressDialog.show()
                } else {
                    if (progressDialog != null) {
                        progressDialog.dismiss()
                        databinding.viewPager2.setAdapter(ViewPagerAdapter(this.requireContext(),homeViewModel.repolist.value as ArrayList<LocalRepoRespItem>, databinding.viewPager2,{ selsectItem: LocalRepoRespItem -> listItemClick(selsectItem) }))

                    }
                }
            })
        return databinding.root
    }

    private fun listItemClick(selsectItem: LocalRepoRespItem) {
        try {
            val bundle = Bundle()
            bundle.putSerializable("selsectItem", selsectItem!!)

            findNavController().navigate(R.id.action_navigation_home_to_detailFragment, bundle)
        }catch (e:Exception){}
    }
}