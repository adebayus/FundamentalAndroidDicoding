package com.sebade.dicodingsubmissongithubusertwo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class HostNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_navigation)
        supportActionBar?.title = "Github User"
    }

}