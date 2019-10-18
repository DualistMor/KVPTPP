package com.bohdanserdyuk.KVPTPP.view.helpers

import android.app.Activity


class FragmentGetActivityHelper {
    /**
     * When inside a nested fragment and Activity gets recreated due to reasons like orientation
     * change, [android.support.v4.app.Fragment.getActivity] returns old Activity but the top
     * level parent fragment's [android.support.v4.app.Fragment.getActivity] returns current,
     * recreated Activity. Hence use this method in nested fragments instead of
     * android.support.v4.app.Fragment#getActivity()
     *
     * @param fragment
     * The current nested Fragment
     *
     * @return current Activity that fragment is hosted in
     */
    fun getActivity(fragment: androidx.fragment.app.Fragment?): Activity? {
        var fragment = fragment ?: return null
        while (fragment.parentFragment != null) {
            fragment = fragment.parentFragment!!
        }
        return fragment.activity
    }
}