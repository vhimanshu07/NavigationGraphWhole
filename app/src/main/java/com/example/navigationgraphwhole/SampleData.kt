package com.example.navigationgraphwhole

import androidx.lifecycle.MutableLiveData

/**
 * Created by Himanshu Verma on 30/05/24.
 **/
object SampleData {
    var amount = MutableLiveData<Long>(100L)
    /**
     *
     * We have made it live data because whenever we make changes in settings fragment it should also reflect
     * in Send cash fragment
     *
     */
}