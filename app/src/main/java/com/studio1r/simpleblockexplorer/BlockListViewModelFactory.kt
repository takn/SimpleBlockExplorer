package com.studio1r.simpleblockexplorer

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Scheduler

class BlockListViewModelFactory(private val repository: BlockRepository,
                                private val executionScheduler: Scheduler,
                                private val observationScheduler: Scheduler)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BlockListViewModel(repository, executionScheduler, observationScheduler) as T
    }
}
