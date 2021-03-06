# EOS Simple block explorer

This sample app uses a simple MVVM architecture to fetch the ten most recent blocks from
api.eosnewyork.io

It is composed as follows:

* Model     = BlockEntity
* View      = BlockListFragment
* ViewModel = BlockListViewModel

BlockListViewModel provides a LiveData observable to which the BlockListFragment subscribes.
A simple button from the fragment invokes a block refresh to BlockListViewModel.

The data for BlockListViewModel is provided via the BlockRepository interface.
There are two implementations, the `BlockRepositoryImpl` in the main source set and `MockBlockRepository` 
in androidTest.

The main implementation uses Retrofit as the transport layer for fetching the data. Gson is used for
serialization and RxJava for marshalling. 

Mock implementation is provided to AndroidTest via a custom runner that provides `MockBlockExplorerApplication`
which in turn provides Mock fixtures via `MockResourceLocator`
It's understood that best practices call for using specific source sets to provide mock data, but this
simpler approach was used in the interest of time. 

BlockListViewModel subscribes to BlockRepository and then forwards that data to the view via LiveData.
Errors are handled by the viewModel. 


#### TODOS:


With sufficient time unified reports would be an improvement.
something like https://engineering.rallyhealth.com/android/code-coverage/testing/2018/06/04/android-code-coverage.html

I ran into a bit of trouble configuring jUnit reporting, it seems things have changed and the test
dsl is no longer available. Some research/reading about working with jUnit and androidJUnit options
is in order. Coverage reporting is missing for jUnit tests. 

Instrumented tests should have a bit more coverage, only a couple of simple tests are in place with 
the purpose of demonstrating an understanding of how to work with them. 
Only a few fields are displayed for raw 'detail' data.

Each block info is fetched using an RxJava range operator. 
This displays each block info from lowest (oldest) to highest (newest). 
The order is reversed once the full list is populated, but this is noticeable in the UI when it happens, so it's not ideal. 



