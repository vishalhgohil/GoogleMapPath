package com.app.ui.home.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.databinding.HomeLocationListingBinding
import com.app.db.database.AppDatabase
import com.app.db.entity.LocationDetailsRoom
import com.app.di.component.FragmentComponent
import com.app.ui.base.BaseFragment
import com.app.ui.home.adapter.LocationListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationListingFragment : BaseFragment<HomeLocationListingBinding>()  {

    private val arrayList :  ArrayList<LocationDetailsRoom> = arrayListOf()
    private val locationListAdapter by lazy {
            LocationListAdapter(arrayList)
    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.baseFragment()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): HomeLocationListingBinding {
        return HomeLocationListingBinding.inflate(layoutInflater)
    }

    override fun bindData() {
        CoroutineScope(Dispatchers.IO).launch {
            arrayList.addAll(AppDatabase.getInstance(requireContext()).locationDao.getAll())
        }

        CoroutineScope(Dispatchers.Main).launch {
            with(binding) {
                recyclerViewList.apply {
                    adapter = locationListAdapter
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                }
            }
        }
    }
}