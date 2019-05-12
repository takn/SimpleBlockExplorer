package com.studio1r.simpleblockexplorer

open class BlockInfoRequestFactory {
    open fun fromId(blockId: Int): BlockInfoRequest {
        return BlockInfoRequest(blockId)
    }
}
