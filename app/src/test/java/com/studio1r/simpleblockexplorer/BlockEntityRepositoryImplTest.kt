package com.studio1r.simpleblockexplorer

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class BlockEntityRepositoryImplTest {
    private lateinit var sut: BlockRepositoryImpl
    @Mock
    private lateinit var api: EOSApiService

    @Mock
    private lateinit var factory: BlockInfoRequestFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = BlockRepositoryImpl(api, factory)
    }

    @Test
    fun expectedNumberOfBlocks() {
        var request = BlockInfoRequest(1)

        whenever(factory.fromId(any())).thenReturn(request)
        whenever(api.info).thenReturn(Observable.just(BlockInfo(HEAD_BLOCK_NUM)))
        whenever(api.getBlock(request)).thenReturn(Observable.just(BlockEntity("1", "1", 1,
                arrayListOf(),
                "signature",
                "previous",
                "schedule")))
        sut.getLastNBlocks(EXPECTED_BLOCK_COUNT).subscribe()
        Mockito.verify<EOSApiService>(api, Mockito.atMost(1)).info
        Mockito.verify<EOSApiService>(api, Mockito.times(EXPECTED_BLOCK_COUNT)).getBlock(any())
    }

    @Test
    fun blockCountCannotBeLargerThanHighestBlock() {
        whenever(api.info).thenReturn(Observable.just(BlockInfo(HEAD_BLOCK_NUM)))
        sut.getLastNBlocks(HEAD_BLOCK_NUM + 1).subscribe({ }, { throwable ->
            Assert.assertEquals(throwable.message, BlockRepositoryImpl.ILLEGAL_BLOCK_COUNT_ERROR)
        })
    }

    companion object {
        private const val EXPECTED_BLOCK_COUNT = 10
        private const val HEAD_BLOCK_NUM = 10
    }
}