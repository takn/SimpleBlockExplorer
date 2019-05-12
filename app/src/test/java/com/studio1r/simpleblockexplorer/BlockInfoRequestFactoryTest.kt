package com.studio1r.simpleblockexplorer

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BlockInfoRequestFactoryTest {

    private lateinit var sut: BlockInfoRequestFactory

    @Before
    fun setUp() {
        sut = BlockInfoRequestFactory()
    }

    @Test
    fun sanity() {
        Assert.assertEquals(1, sut.fromId(1).block_num_or_id)
    }
}