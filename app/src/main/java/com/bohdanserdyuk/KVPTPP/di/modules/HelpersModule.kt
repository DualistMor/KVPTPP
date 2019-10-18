package com.bohdanserdyuk.KVPTPP.di.modules

import com.bohdanserdyuk.KVPTPP.view.helpers.FragmentGetActivityHelper
import dagger.Module
import dagger.Provides

@Module
class HelpersModule {
    @Provides
    fun provideFragmentGetActivityHelper(): FragmentGetActivityHelper {
        return FragmentGetActivityHelper()
    }
}