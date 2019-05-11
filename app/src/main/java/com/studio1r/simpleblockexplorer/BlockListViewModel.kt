package com.studio1r.simpleblockexplorer

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlockListViewModel(val repository: BlockRepository) : ViewModel() {
    private val blockLiveData: MutableLiveData<List<Block>> = MutableLiveData()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getBlocks(): LiveData<List<Block>> {
        Log.d("Hello", "get Blocks")
        repository.getLastNBlocks(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.d("Hello", "result :" + result)
                    blockLiveData.value = result
                },
                        { t ->
                            Log.d("Hello", "error ::" + t.message)
                            errorLiveData.value = t.message
                        })
        return blockLiveData
    }

}
