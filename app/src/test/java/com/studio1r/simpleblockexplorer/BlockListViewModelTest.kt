package com.studio1r.simpleblockexplorer

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BlockListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Mock
    lateinit var repository: BlockRepository;

    @Mock
    lateinit var observer: Observer<List<Block>>

    private lateinit var sut: BlockListViewModel

    private val testScheduler: TestScheduler = TestScheduler();

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = BlockListViewModel(repository, testScheduler, testScheduler)
    }

    @Test
    internal fun canGetBlocksEmpty() {
        whenever(repository.getLastNBlocks(10)).thenReturn(Observable.just(emptyList()))
        sut.getBlocks().observeForever(observer)
        testScheduler.triggerActions()
        Assert.assertEquals(0, sut.blockLiveData.value?.size)
    }

    @Test
    internal fun canGetBlocks() {
        var blocks = mutableListOf<Block>()
        for (i in 1..10) {
            blocks.add(Block(i.toString()))
        }
        whenever(repository.getLastNBlocks(10)).thenReturn(Observable.just(blocks))
        sut.getBlocks().observeForever(observer)
        testScheduler.triggerActions()
        Assert.assertEquals(10, sut.blockLiveData.value?.size)

    }

    @Test
    fun canGetBlocksError() {
        whenever(repository.getLastNBlocks(10))
                .thenReturn(Observable.error(IllegalStateException("expected")))
        sut.getBlocks().observeForever(observer)
        testScheduler.triggerActions()
        Assert.assertEquals("expected", sut.errorLiveData.value)
    }
}