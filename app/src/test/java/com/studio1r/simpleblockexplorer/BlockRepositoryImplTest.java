package com.studio1r.simpleblockexplorer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class BlockRepositoryImplTest {

    protected static final int EXPPECTED_BLOCK_COUNT = 10;
    protected static final int HEAD_BLOCK_NUM = 10;
    private BlockRepositoryImpl sut;
    @Mock
    EOSApiService api;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new BlockRepositoryImpl(api);
    }

    @Test
    public void expectedNumberOfBlocks() {
        Mockito.when(api.getInfo()).thenReturn(Observable.just(new BlockInfo(HEAD_BLOCK_NUM)));
        for (int i = 1; i < 11; i++) {
            Mockito.when(api.getBlock(i)).thenReturn(Observable.just(new Block(i)));
        }
        sut.getLastNBlocks(EXPPECTED_BLOCK_COUNT).subscribe();
        Mockito.verify(api, Mockito.times(EXPPECTED_BLOCK_COUNT)).getBlock(Mockito.anyInt());
        Mockito.verify(api, Mockito.atMost(1)).getInfo();
    }

    @Test
    public void blockCountCannotBeLargerThanHighestBlock() {
        Mockito.when(api.getInfo()).thenReturn(Observable.just(new BlockInfo(HEAD_BLOCK_NUM)));
        sut.getLastNBlocks(HEAD_BLOCK_NUM + 1).subscribe(new Consumer<List<Block>>() {
            @Override
            public void accept(List<Block> blocks) {

            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Assert.assertEquals(throwable.getMessage(), BlockRepositoryImpl.ILLEGAL_BLOCK_COUNT_ERROR);
            }
        });
    }
}