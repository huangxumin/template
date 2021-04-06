package other.mvvm.activity.src.app_package

fun beanKt(
        basePackageName: String,
        packageName: String,
        beanName: String,
) =
    """
package ${packageName}

import ${basePackageName}.base_adapter.BaseResponse

class ${beanName}(
     var xx: String = "",
     var xxx: Int = 0,
     var xxxx: String = ""
) : BaseResponse(false, false)
"""