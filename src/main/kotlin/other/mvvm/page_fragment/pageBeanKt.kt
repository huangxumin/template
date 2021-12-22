package other.mvvm.page_fragment

fun pageBeanKt(
        basePackageName: String,
        packageName: String,
        beanName: String,
) =
    """
package ${packageName}


class ${beanName}Bean(
     var xx: String = "",
) 
"""