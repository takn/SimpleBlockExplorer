package com.studio1r.simpleblockexplorer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;

class BlockRepositoryImpl implements BlockRepository {
    @NotNull
    @Override
    public Observable<List<Block>> getNBlocks(int startBlock, int blockCount) {
        return null;
    }
}
