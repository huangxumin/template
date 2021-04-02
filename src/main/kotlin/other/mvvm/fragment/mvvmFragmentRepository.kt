package other.mvvm.activity.src.app_package
fun mvvmFragmentRepository(
        packageName:String,
        activityClass:String
)="""
package ${packageName}
import javax.inject.Inject

class ${activityClass}FragmentRepository @Inject constructor() {


}
"""