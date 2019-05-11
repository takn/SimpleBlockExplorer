package com.studio1r.simpleblockexplorer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class BlockListFragment : Fragment() {

    private lateinit var viewModel: BlockListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.block_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var a = activity as MainActivity
        viewModel = ViewModelProviders.of(this, a.resourceLocator.factory).get(BlockListViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.getBlocks().observe(this, Observer { Log.d("HI", "got something") })
    }

}
