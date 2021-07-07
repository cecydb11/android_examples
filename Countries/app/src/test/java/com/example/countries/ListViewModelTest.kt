package com.example.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countries.Model.CountriesService
import com.example.countries.Model.Country
import com.example.countries.ViewModel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {

    @get: Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countriesService: CountriesService

    @InjectMocks
    var listViewModel = ListViewModel()

    private var testSingle: Single<List<Country>>? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCountriesSuccess(){
        val country = Country("countryName", "capital", "url")
        val countriesList = arrayListOf(country)

        //This is what the mock test will return.
        testSingle = Single.just(countriesList)

        //We create a test for the countries service, we return our testSingle (mock data).
        `when`(countriesService.getCountries()).thenReturn(testSingle)

        listViewModel.refresh()

        //We make sure the expected result is what we want.
        assertEquals(1, listViewModel.countries.value?.size)
        assertEquals(false, listViewModel.countryLoadError.value)
        assertEquals(false, listViewModel.loading.value)

    }

    @Test
    fun getCountriesFailure(){

        //This is what the mock test will return.
        testSingle = Single.error(Throwable())

        //We create a test for the countries service, we return our testSingle (mock error).
        `when`(countriesService.getCountries()).thenReturn(testSingle)

        listViewModel.refresh()

        //We make sure the expected result is what we want.
        assertEquals(true, listViewModel.countryLoadError.value)
        assertEquals(false, listViewModel.loading.value)

    }

    @Before
    fun setUpRxSchedulers(){
        val immediate = object: Scheduler() {
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
               return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }

        }

        RxJavaPlugins.setInitIoSchedulerHandler{scheduler -> immediate}
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate}
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduleerr -> immediate}
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}