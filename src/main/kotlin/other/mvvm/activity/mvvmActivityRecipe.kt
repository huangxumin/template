package other.mvvm.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.activity.src.app_package.*
import other.mvvm.page_fragment.*


fun RecipeExecutor.mvvmActivityRecipe(
        moduleData: ModuleTemplateData,
        activityClass: String,
        layoutName: String,
        packageName: String,
        basePackageName: String,
        needPageFragment: Boolean,
        fragmentLayoutName: String,
        needPaging3Enable: Boolean,
        beanName: String,
        adapterName: String,
        adapterLayoutName: String,
        pagingSourceName: String,
        viewModelEnable: Boolean,
        repositoryEnable: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//            moduleData = moduleData,
//            activityClass = "${activityClass}Activity",
//            activityTitle = activityClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateActivityTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmActivity = mvvmAcitivityKt(basePackageName,
            activityClass, layoutName, packageName,beanName,viewModelEnable,needPaging3Enable)
    // 保存Activity
    save(mvvmActivity, srcOut.resolve("${activityClass}Activity.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmActivityXml(packageName, activityClass,beanName,adapterName,viewModelEnable,needPaging3Enable), resOut.resolve("layout/${layoutName}.xml"))

    // 保存viewmodel
    if (viewModelEnable||needPaging3Enable) {
        save(mvvmViewModel(basePackageName,packageName, activityClass,beanName,needPaging3Enable), srcOut.resolve("${activityClass}ActivityViewModel.${ktOrJavaExt}"))
        if (repositoryEnable) {
            //repository
            save(mvvmRepository(packageName, activityClass,beanName,needPaging3Enable,pagingSourceName), srcOut.resolve("${activityClass}ActivityRepository.${ktOrJavaExt}"))
        }
    }

    if(needPageFragment){

        val mvvmFragment = pageFragmentKt(basePackageName,
            activityClass, fragmentLayoutName, packageName,beanName,viewModelEnable,needPaging3Enable,adapterName)
        // 保存Activity
        save(mvvmFragment, srcOut.resolve("${activityClass}Fragment.${ktOrJavaExt}"))
        // 保存xml
        save(pageFragmentXml(packageName, activityClass,beanName,adapterName,viewModelEnable,needPaging3Enable),
            resOut.resolve("layout/${fragmentLayoutName}.xml"))

        // 保存adapter bean item_layout
        save(pageBeanKt(basePackageName,packageName,beanName), srcOut.resolve("${beanName}Bean.${ktOrJavaExt}"))
        save(pageAdapterKt(basePackageName,packageName,beanName,adapterName,adapterLayoutName), srcOut.resolve("${adapterName}.${ktOrJavaExt}"))
        save(pageAdapterLayoutKt(packageName,beanName), resOut.resolve("layout/${adapterLayoutName}.xml"))
    }

    if(needPaging3Enable){
//        save(beanKt(basePackageName, packageName, beanName), srcOut.resolve("${beanName}.${ktOrJavaExt}"))
//        save(mvvmAdapterKt(basePackageName,packageName,beanName,adapterName,adapterLayoutName), srcOut.resolve("${adapterName}.${ktOrJavaExt}"))
//        save(mvvmAdapterLayoutKt(packageName,beanName), resOut.resolve("layout/${adapterLayoutName}.xml"))
//        save(beanPagingSourceKt(packageName,beanName,pagingSourceName), srcOut.resolve("${pagingSourceName}.${ktOrJavaExt}"))

    }

}