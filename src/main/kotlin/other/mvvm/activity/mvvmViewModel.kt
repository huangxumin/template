package other.mvvm.activity.src.app_package

fun mvvmViewModel(
        basePackageName: String,
        packageName: String,
        activityClass: String,
        beanName: String,
        needPaging3Enable: Boolean
) = if (needPaging3Enable) {
    """
package ${packageName}

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import ${basePackageName}.viewmodel.BaseViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ${activityClass}ActivityViewModel @ViewModelInject 
constructor(application: Application,val repository ${activityClass}ActivityRepository): BaseViewModel(application) {

    fun getData(): Flow<PagingData<${beanName}>> {
        return repository.getPagingData().cachedIn(viewModelScope)
    }



}
"""
} else {

    """
package ${packageName}

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import ${basePackageName}.viewmodel.BaseViewModel

class ${activityClass}ActivityViewModel @ViewModelInject 
constructor(application: Application,repository ${activityClass}ActivityRepository): BaseViewModel(application) {


}
"""
}