package com.studio1r.simpleblockexplorer

import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BlockRepositoryImplTest {
    private lateinit var sut: BlockRepositoryImpl
    @Mock
    private lateinit var api: EOSApiService;

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = BlockRepositoryImpl(api)
    }

    @Test
    fun expectedNumberOfBlocks() {
        Mockito.`when`(api.info).thenReturn(Observable.just(BlockInfo(HEAD_BLOCK_NUM)))
        for (i in 1..10) {
            Mockito.`when`(api.getBlock(i)).thenReturn(Observable.just(Block(i)))
        }
        sut.getLastNBlocks(EXPECTED_BLOCK_COUNT).subscribe()
        Mockito.verify<EOSApiService>(api, Mockito.atMost(1)).info
        Mockito.verify<EOSApiService>(api, Mockito.times(EXPECTED_BLOCK_COUNT)).getBlock(Mockito.anyInt())
    }

    @Test
    fun blockCountCannotBeLargerThanHighestBlock() {
        Mockito.`when`(api.info).thenReturn(Observable.just(BlockInfo(HEAD_BLOCK_NUM)))
        sut.getLastNBlocks(HEAD_BLOCK_NUM + 1).subscribe({ }, { throwable ->
            Assert.assertEquals(throwable.message, BlockRepositoryImpl.ILLEGAL_BLOCK_COUNT_ERROR)
        })
    }

    companion object {
        private const val EXPECTED_BLOCK_COUNT = 10
        private const val HEAD_BLOCK_NUM = 10
    }
}