package com.studio1r.simpleblockexplorer

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class BlockListFragment : Fragment() {

    companion object {
        fun newInstance() = BlockListFragment()
    }

    private lateinit var viewModel: BlockListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.block_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BlockListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
