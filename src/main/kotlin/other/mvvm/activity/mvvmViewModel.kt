package other.mvvm.activity

fun mvvmViewModel(
    basePackageName: String,
    packageName: String,
    activityClass: String,
    beanName: String,
    needPaging3Enable: Boolean
) = if (needPaging3Enable) {
    """
package $packageName

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import ${basePackageName}.viewmodel.BaseViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class ${activityClass}ActivityViewModel @ViewModelInject 
constructor(application: Application,val repository :${activityClass}ActivityRepository): BaseViewModel(application) {

    fun getData(): Flow<PagingData<${beanName}>> {
        return repository.getPagingData().cachedIn(viewModelScope)
    }

}
"""
} else {

    """
package $packageName

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.afanticar.common.common_page_new_utils.CommonPageViewModel
import com.afanticar.common.common_page_new_utils.bean.CommonPageStatusBean
import com.afanticar.common.net.execute
import com.afanticar.common.net.pageExecute
import ${basePackageName}.viewmodel.BaseViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class ${activityClass}ViewModel constructor(application: Application): CommonPageViewModel(application) {


    val repository: ${activityClass}Repository by lazy{${activityClass}Repository()}

    val m${activityClass}Data = MutableLiveData<CommonPageStatusBean<${beanName}Bean>>()

    @InternalCoroutinesApi
    fun get${activityClass}Data(loadStatus: LoadStatusBean) {

        viewModelScope.launch {
         
               val date= repository.get${activityClass}Data(loadStatus.current, loadStatus.pageSize)
               m${activityClass}Data.postValue(date)
          
        }

    }
    
}
"""
}