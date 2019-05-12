package com.studio1r.simpleblockexplorer;

import android.app.Application;

public class MockBlockExplorerApplication extends Application implements ResourceProvider {
    private ResourceLocator resourceLocator;

    public ResourceLocator getResourceLocator() {
        if (resourceLocator == null) {
            resourceLocator = new MockResourceLocator();
        }
        return resourceLocator;
    }
}
