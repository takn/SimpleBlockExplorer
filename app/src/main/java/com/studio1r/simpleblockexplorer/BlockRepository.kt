package com.studio1r.simpleblockexplorer

import io.reactivex.Observable

interface BlockRepository {
    fun getLastNBlocks(blockCount: Int): Observable<List<Block>>
}
