package com.studio1r.simpleblockexplorer

import java.util.*

data class BlockEntity(val timestamp: String, val id: String, val block_num: Int,
                       val transactions: ArrayList<BlockTransaction>,
                       val producer_signature: String,
                       val previous: String,
                       val schedule_version: String)

data class BlockTransaction(val status: String, val cpu_usage_us: Int, val net_usage_words: Int)
