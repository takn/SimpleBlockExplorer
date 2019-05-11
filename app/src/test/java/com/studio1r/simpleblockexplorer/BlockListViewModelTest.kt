package com.studio1r.simpleblockexplorer

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BlockListViewModelTest {

    @Mock
    lateinit var repository: BlockRepository;

    private lateinit var sut: BlockListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = BlockListViewModel(repository)
    }
}