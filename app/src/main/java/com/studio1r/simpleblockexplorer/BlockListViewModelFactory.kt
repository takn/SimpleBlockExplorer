package com.studio1r.simpleblockexplorer

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class BlockListViewModelFactory(private val repository: BlockRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BlockListViewModel(repository) as T
    }
}
