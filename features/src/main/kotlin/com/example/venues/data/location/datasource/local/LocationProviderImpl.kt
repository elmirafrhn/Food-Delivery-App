package com.example.venues.data.location.datasource.local

import com.example.core.coroutinesModule.DispatcherProvider
import com.example.venues.data.location.datasource.model.LocationItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationProviderImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val delayDuration: Long
) : LocationProvider {

    val fakeLocationList = listOf(
        LocationItem("60.169418", "24.931618"),
        LocationItem("60.169818", "24.932906"),
        LocationItem("60.170005", "24.935105"),
        LocationItem("60.169108", "24.936210"),
        LocationItem("60.168355", "24.934869"),
        LocationItem("60.167560", "24.932562"),
        LocationItem("60.168254", "24.931532"),
        LocationItem("60.169012", "24.930341"),
        LocationItem("60.170085", "24.929569")
    )

    override fun provideLocation(): Flow<LocationItem> = flow {
        var index = 0
        while (true) {
            emit(fakeLocationList[index])
            index = (index + 1) % fakeLocationList.size
            delay(delayDuration)
        }
    }.flowOn(dispatcherProvider.io())
}