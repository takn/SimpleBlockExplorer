package com.studio1r.simpleblockexplorer

import io.reactivex.Observable

internal interface BlockRepository {
    fun getNBlocks(startBlock: Int, blockCount: Int): Observable<List<Block>>
}
