package com.studio1r.simpleblockexplorer;

import android.app.Application;

public class SimpleBlockExplorerApplication extends Application implements ResourceProvider {
    private ResourceLocator resourceLocator;

    public ResourceLocator getResourceLocator() {
        if (resourceLocator == null) {
            resourceLocator = new ResourceLocator();
        }
        return resourceLocator;
    }
}
