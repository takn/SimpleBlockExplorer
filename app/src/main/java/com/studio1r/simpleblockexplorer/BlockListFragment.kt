package com.studio1r.simpleblockexplorer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.block_list_fragment.*
import kotlinx.android.synthetic.main.item_block.view.*


class BlockListFragment : Fragment() {

    private lateinit var viewModel: BlockListViewModel

    private lateinit var blockAdapter: BlockAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.block_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var a = activity as MainActivity
        var lm = LinearLayoutManager(activity)
        blockAdapter = BlockAdapter()
        recyclerView.apply {
            layoutManager = lm
            adapter = blockAdapter
        }
        var app = a.application as ResourceProvider
        var resourceLocator = app.resourceLocator
        viewModel = ViewModelProviders.of(this, resourceLocator.factory)
                .get(BlockListViewModel::class.java)
        viewModel.getBlocksLiveData().observe(this, Observer { data ->
            blockAdapter.updateData(data!!)
        })
        refresh.setOnClickListener { viewModel.refreshBlocks() }

    }

}

class BlockAdapter : RecyclerView.Adapter<BlockAdapter.ViewHolder>() {
    private var data = listOf<BlockEntity>()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_block, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    fun updateData(blockEntities: List<BlockEntity>) {
        this.data = blockEntities
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: BlockEntity) {
            itemView.timeStamp.text = String.format("timestamp: %s", data.timestamp)
            itemView.blockId.text = String.format("block num: %d", data.block_num)
            itemView.idHash.text = String.format("block id: %s", data.id)
            itemView.actionCount.text = String.format("transactions: %d", data.transactions.size)

            //populate details
            itemView.detail1.text = data.producer_signature
            itemView.detail2.text = data.schedule_version
            itemView.detail3.text = data.previous
            itemView.setOnClickListener {
                if (itemView.detailsContainer.visibility == View.VISIBLE) {
                    itemView.detailsContainer.visibility = View.GONE
                } else {
                    itemView.detailsContainer.visibility = View.VISIBLE
                }
            }
        }
    }

}
