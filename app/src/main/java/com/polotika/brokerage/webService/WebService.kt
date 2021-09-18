package com.polotika.brokerage.webService

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.polotika.brokerage.pojo.models.Event
import javax.inject.Inject

class WebService @Inject constructor(private val context: Context) {

    fun forgetPasswordRequest(email: String): Event {

        var event: Event = Event.Failed
        val request = StringRequest(Request.Method.GET, email, {
            event = Event.Success
        }, {
            event = Event.Failed
        })

        Volley.newRequestQueue(context).add(request)
        return event
    }
}