package other.mvvm.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API


val mvvmActivityTemplate
    get() = template {
        revision = 1
        name = "MVVM Activity"
        description = "适用于MVVM框架的Activity"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        lateinit var layoutName: StringParameter

        val activityClass = stringParameter {
            name = "Activity Name(只输入名字，不要包含Activity)"
            default = "Test"
            help = ""
            constraints = listOf(Constraint.NONEMPTY, Constraint.ACTIVITY)
        }

        layoutName = stringParameter {
            name = "activity Layout Name"
            default = "activity_main"
            help = "请输入activity布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(activityClass.value) }
        }


        val needPageFragment = booleanParameter {
            name = "是否需要创建带PageFragment的activity"
            default = true
        }



        val fragmentLayoutName = stringParameter {
            name = "fragment Layout Name"
            default = "fragment_main"
            help = "请输入fragment布局的名字"
            visible = { needPageFragment.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(activityClass.value) }
        }

        val beanName = stringParameter {
            name = "bean Name"
            default = "Test"
            help = "Bean类名"
            visible = { needPageFragment.value }
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${extractClassName(activityClass.value)}" }
        }

        val adapterName = stringParameter {
            name = "adapter Name"
            default = ""
            help = "Adapter类名"
            visible = { needPageFragment.value }
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${extractClassName(beanName.value)}Adapter" }
        }

        val adapterLayoutName = stringParameter {
            name = "Adapter Layout Name"
            default = ""
            help = "请输入布局的名字"
            visible = { needPageFragment.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${classToResource(beanName.value)}_item_layout" }
        }


//        val needPaging3 = booleanParameter {
//            name = "是否需要创建带paging3的activity(" +
//                    "包括adapter,bean,bottomLoadView,pagingSource,\n " +
//                    "remoteMediator)"
//            default = false
//        }


//
//        val pagingSourceName = stringParameter {
//            name = "PagingSource Name"
//            default = ""
//            help = "PagingSource类名"
//            visible = { needPaging3.value }
//            constraints = listOf(Constraint.NONEMPTY)
//            suggest = { "${extractClassName(beanName.value)}PagingSource" }
//        }


        val needViewModel = booleanParameter {
            name = "是否需要关联创建viewModel(包括repository)"
            default = true
        }

        val needRepository = booleanParameter {
            name = "是否需要关联创建Repository"
            default = true
        }

        val packageName = stringParameter {
            name = "Package name,当前使用的base文件夹是com.afanticar.base"
            visible = { !isNewModule }
            default = "com.afanticar"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        val basePackageName = "com.afanticar.base"



        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(needPageFragment),
            TextFieldWidget(fragmentLayoutName),
            TextFieldWidget(beanName),
            TextFieldWidget(adapterName),
            TextFieldWidget(adapterLayoutName),
//            TextFieldWidget(pagingSourceName),
            CheckBoxWidget(needViewModel),
            CheckBoxWidget(needRepository),
//            CheckBoxWidget(needPaging3),
        )

//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->
            mvvmActivityRecipe(
                data as ModuleTemplateData,
                activityClass.value,
                layoutName.value,
                packageName.value,
                basePackageName,
                needPageFragment.value,
                fragmentLayoutName.value,
                false,
                beanName.value,
                adapterName.value,
                adapterLayoutName.value,
//                pagingSourceName.value,

                "",

                needViewModel.value,
                needRepository.value
            )
        }
    }




