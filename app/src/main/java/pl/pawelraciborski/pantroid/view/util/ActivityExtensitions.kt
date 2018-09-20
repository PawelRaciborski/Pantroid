package pl.pawelraciborski.pantroid.view.util

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by Pawel Raciborski on 20.09.2018.
 */
inline fun FragmentManager.inTransaction(
        function: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().function().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun Intent.getLongExtraOrNull(name: String) =
        if (hasExtra(name)) {
            getLongExtra(name, -1L)
        } else {
            null
        }