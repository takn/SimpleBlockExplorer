package com.studio1r.simpleblockexplorer

import java.util.*

data class BlockEntity(val timestamp: String, val id: String, val block_num: Int,
                       val actions: ArrayList<BlockAction>,
                       val producer_signature: String,
                       val previous: String,
                       val schedule_version: String)

data class BlockAction(val hex_data: String)
