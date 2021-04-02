package other.mvvm.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.activity.src.app_package.mvvmFragmentRepository


fun RecipeExecutor.mvvmFragmentRecipe(
        moduleData: ModuleTemplateData,
        fragmentClass: String,
        layoutName: String,
        packageName: String,
        viewModelEnable: Boolean,
        repositoryEnable: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//            moduleData = moduleData,
//            fragmentClass = "${fragmentClass}Activity",
//            fragmentTitle = fragmentClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateActivityTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmFragment = mvvmFragmentKt(projectData.applicationPackage,
            fragmentClass, layoutName, packageName,viewModelEnable)
    // 保存Activity
    save(mvvmFragment, srcOut.resolve("${fragmentClass}Fragment.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmFragmentXml(packageName, fragmentClass,viewModelEnable), resOut.resolve("layout/${layoutName}.xml"))
    if (viewModelEnable) {
        // 保存viewmodel
        save(mvvmFragmentViewModel(packageName, fragmentClass), srcOut.resolve("${fragmentClass}FragmentViewModel.${ktOrJavaExt}"))

        if (repositoryEnable) {
            // 保存repository
            save(mvvmFragmentRepository(packageName, fragmentClass), srcOut.resolve("${fragmentClass}Repository.${ktOrJavaExt}"))
        }
    }

}