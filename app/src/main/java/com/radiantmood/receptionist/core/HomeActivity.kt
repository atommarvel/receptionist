package com.radiantmood.receptionist.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.radiantmood.receptionist.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var toolbarVisibility: Int
        get() = toolbar.visibility
        set(value) {
            toolbar.visibility = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
    }
}
