package com.udacoding.driverojol.ui.request.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.udacoding.driverojol.R

/**
 * A simple [Fragment] subclass.
 */
class CompleteBooking : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_booking, container, false)
    }


}
