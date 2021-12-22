package other.mvvm.activity.src.app_package
fun mvvmFragmentRepository(
        packageName: String,
        activityClass: String,
        beanName: String,
        needPaging3Enable: Boolean,
        pagingSourceName: String
)=if (needPaging3Enable) {
    """
package ${packageName}
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ${packageName}.${beanName}
import com.afanticar.network.constants.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ${activityClass}FragmentRepository @Inject constructor() {

    fun getPagingData(): Flow<PagingData<${beanName}>> {
        return Pager(
            config = PagingConfig( pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = { ${pagingSourceName}() }
        ).flow
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

