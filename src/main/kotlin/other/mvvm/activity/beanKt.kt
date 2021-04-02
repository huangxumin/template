package other.mvvm.activity.src.app_package

fun beanKt(
        packageName: String,
        beanName: String,
) =
    """
package ${packageName}

package com.example.basemvvm.bean
import com.afanticar.base.base_adapter.BaseResponse

class ${beanName}(
     var xx: String = "",
     var xxx: Int = 0,
     var xxxx: String = ""
) : BaseResponse(false, false)
"""