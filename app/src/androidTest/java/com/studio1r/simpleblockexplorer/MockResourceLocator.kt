package com.studio1r.simpleblockexplorer

class MockResourceLocator : ResourceLocator() {
    private val mockRepository: MockBlockRepository = MockBlockRepository()

    override fun getBlockRepository(): BlockRepository {
        return mockRepository
    }
}
