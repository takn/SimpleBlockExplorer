package com.studio1r.simpleblockexplorer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BlockRepositoryImplTest {

    private BlockRepositoryImpl sut;

    @Before
    public void setUp() {
        sut = new BlockRepositoryImpl();
    }

    @Test
    public void startBlockMustBeValid() {
        try {
            sut.getNBlocks(0, 0);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), BlockRepositoryImpl.INVALID_BLOCK_ID);
        }
    }

    @Test
    public void blockCountCannotBeLargerThanStartBlock() {
        try {
            sut.getNBlocks(1, 3);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), BlockRepositoryImpl.INVALID_BLOCK_COUNT_REQUEST);
        }
    }
}