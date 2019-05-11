package com.studio1r.simpleblockexplorer

import io.reactivex.Observable

interface BlockRepository {
    fun getNBlocks(startBlock: Int, blockCount: Int): Observable<List<Block>>
}
