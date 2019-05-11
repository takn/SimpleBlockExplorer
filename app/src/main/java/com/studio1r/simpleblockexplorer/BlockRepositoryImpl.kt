package com.studio1r.simpleblockexplorer

import io.reactivex.Observable

class BlockRepositoryImpl : BlockRepository {

    override fun getNBlocks(startBlock: Int, blockCount: Int): Observable<List<Block>> {
        if (startBlock < 1) {
            throw IllegalArgumentException(INVALID_BLOCK_ID)
        }
        if (blockCount > startBlock) {
            throw IllegalArgumentException(INVALID_BLOCK_COUNT_REQUEST)
        }
        return Observable.just(emptyList())
    }

    companion object {
        const val INVALID_BLOCK_ID = "Block id must be larger than zero"
        const val INVALID_BLOCK_COUNT_REQUEST = "blockCount cannot be a value larger thant startBlock. " +
                "This is because block numbers increase by one and the entire list cannot be populated."
    }
}
