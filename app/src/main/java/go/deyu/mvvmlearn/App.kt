package go.deyu.mvvmlearn

import android.app.Application
import go.deyu.mvvmlearn.data.NotesRepository
import go.deyu.mvvmlearn.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(applicationModule)
        }
    }
}