package com.studio1r.simpleblockexplorer

import android.util.Log

class MockResourceLocator : ResourceLocator() {
    private val mockRepository: MockBlockRepository = MockBlockRepository()

    override fun getBlockRepository(): BlockRepository {
        Log.d("INIT", "getMockBlockRepository")
        return mockRepository
    }
}
