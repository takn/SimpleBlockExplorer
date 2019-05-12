package com.studio1r.simpleblockexplorer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MockBlockRepository implements BlockRepository {

    private final List<BlockEntity> mockBlocks = new ArrayList<>();

    public MockBlockRepository() {

    }

    private void populate() {
        for (int i = 0; i < 10; i++) {
            mockBlocks.add(new BlockEntity("ts" + i, "id" + i, i,
                    new ArrayList<BlockAction>(), "ps" + i, "p" + i, "sv" + i));
        }
    }

    @NotNull
    @Override
    public Observable<List<BlockEntity>> getLastNBlocks(int blockCount) {
        if (mockBlocks.size() == 0) {
            populate();
        }
        return Observable.just(mockBlocks);
    }
}
