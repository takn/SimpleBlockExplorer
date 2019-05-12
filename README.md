* EOS Simple block explorer

This sample app uses a simple MVVM architecture to fetch the ten most recent blocks from
api.eosnewyork.io

It is composed as follows:

*** Model     = BlockEntity
*** View      = BlockListFragment
*** ViewModel = BlockListViewModel

BlockListViewModel provides a LiveData observable to which the BlockListFragment subscribes to.
A simple button from the fragment invokes a block refresh to BlockListViewModel.

The data for BlockListViewModel is provided via the BlockRepository interface.
There are two implementations, the `BlockRepositoryImpl` in the main source set and `MockBlockRepository` 
in androidTest.

The main implementation uses Retrofit as the transport layer for fetching the data. Gson is used for
serialization and RxJava for marshalling. 

Mock implementation is provided to AndroidTest via a custom runner that provides `MockBlockExplorerApplication`
which in turns provides Mock fixtures via `MockResourceLocator`
It's understood that best practices call for using specific source sets to provide mock data, but this
simpler approach was used in the interest of time. 

BlockListViewModel subscribes to BlockRepository and then forwards that data to the view via LiveData.
Errors are handled by the viewModel. 

