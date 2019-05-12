package com.studio1r.simpleblockexplorer

import io.reactivex.Observable

class BlockRepositoryImpl(private val api: EOSApiService,
                          private val blockInfoFactory: BlockInfoRequestFactory) : BlockRepository {
    companion object {
        const val ILLEGAL_BLOCK_COUNT_ERROR: String = "blockCount argument is larger than head block number"
    }

    /**
     * Gets the head_block_num from info and gets the previous N blocks as defined by blockCount
     */
    override fun getLastNBlocks(blockCount: Int): Observable<List<BlockEntity>> {
        var blocks = mutableListOf<BlockEntity>()
        return api.info
                .flatMap { blockInfo ->
                    if (blockCount > blockInfo.head_block_num) {
                        throw IllegalArgumentException(ILLEGAL_BLOCK_COUNT_ERROR)
                    }
                    Observable.range((blockInfo.head_block_num + 1) - blockCount,
                            blockCount)
                }.map { it ->
                    api.getBlock(blockInfoFactory.fromId(it))

                }.map { t -> blocks.add(t.blockingLast()) }
                .flatMap {
                    Observable.fromArray(blocks)
                }
    }

}



