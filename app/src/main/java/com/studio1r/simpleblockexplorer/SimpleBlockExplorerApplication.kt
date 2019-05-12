package com.studio1r.simpleblockexplorer

import android.app.Application

class SimpleBlockExplorerApplication : Application(), ResourceProvider {
    override val resourceLocator: ResourceLocator = ResourceLocator()


}
