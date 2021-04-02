package other.mvvm.fragment
fun mvvmFragmentViewModel(
        packageName:String,
        fragmentClass:String
)="""
package ${packageName}

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.afanticar.base.viewmodel.BaseViewModel

class ${fragmentClass}FragmentViewModel @ViewModelInject constructor(
application: Application,repository ${fragmentClass}FragmentRepository):
BaseViewModel(application) {

}
"""