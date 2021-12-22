package other.mvvm.page_fragment
fun pageFragmentRepository(
        packageName: String,
        activityClass: String,
        beanName: String,
        needPaging3Enable: Boolean,
        pagingSourceName: String
)=if (needPaging3Enable) {
    """
package ${packageName}

import javax.inject.Inject
import ${packageName}.${beanName}Bean
import com.afanticar.network.bean.CommonPageBean

class ${activityClass}FragmentRepository @Inject constructor() {

    suspend fun 网络请求(page: Int, size: Int): CommonPageBean<${beanName}Bean> {
        return XXXService.create().网络请求(page, size)
    }
}
"""
} else {

    """
package ${packageName}
import javax.inject.Inject

class ${activityClass}FragmentRepository @Inject constructor() {



}
"""

}

