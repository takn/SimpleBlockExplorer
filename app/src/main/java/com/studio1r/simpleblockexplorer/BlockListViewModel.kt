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
    public val blockLiveData: MutableLiveData<List<Block>> = MutableLiveData()
    @VisibleForTesting
    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getBlocks(): LiveData<List<Block>> {
        repository.getLastNBlocks(10)
                .subscribeOn(executionScheduler)
                .observeOn(observerScheduler)
                .subscribe({ result ->
                    blockLiveData.value = result
                },
                        { t ->
                            errorLiveData.value = t.message
                        })
        return blockLiveData
    }

}
