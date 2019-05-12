package com.studio1r.simpleblockexplorer

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import io.reactivex.Scheduler

class BlockListViewModel(private val repository: BlockRepository,
                         private val executionScheduler: Scheduler,
                         private val observerScheduler: Scheduler) : ViewModel() {
    @VisibleForTesting
    val blockEntityLiveData: MutableLiveData<List<BlockEntity>> = MutableLiveData()
    @VisibleForTesting
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getBlocks(): LiveData<List<BlockEntity>> {
        repository.getLastNBlocks(10)
                .subscribeOn(executionScheduler)
                .observeOn(observerScheduler)
                .subscribe({ result ->
                    blockEntityLiveData.value = result
                },
                        { t ->
                            errorLiveData.value = t.message
                        })
        return blockEntityLiveData
    }

}
