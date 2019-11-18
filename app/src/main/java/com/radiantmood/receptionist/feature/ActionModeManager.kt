package com.radiantmood.receptionist.feature

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import com.radiantmood.receptionist.core.HomeActivity
import com.radiantmood.receptionist.di.PerFragment
import javax.inject.Inject

@PerFragment
class ActionModeManager @Inject constructor() {

    private var actionMode: ActionMode? = null

    val isActionModeEnabled: Boolean get() = actionMode != null

    fun finishActionMode(fragment: Fragment) {
        actionMode = null
        (fragment.activity as? HomeActivity)?.toolbarVisibility = View.VISIBLE
    }

    fun initActionMode(fragment: Fragment, callback: ActionMode.Callback) {
        actionMode = (fragment.activity as? AppCompatActivity)?.startSupportActionMode(callback)
        (fragment.activity as? HomeActivity)?.toolbarVisibility = View.GONE
    }

    fun setTitle(title: String) {
        actionMode?.title = title
    }
}