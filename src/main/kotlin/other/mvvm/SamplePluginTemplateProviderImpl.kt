package other.mvvm
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.mvvm.activity.mvvmActivityTemplate
import other.mvvm.fragment.mvvmFragmentTemplate
import other.mvvm.page_fragment.pageFragmentTemplate
class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
            // activity的模板
            mvvmActivityTemplate,
            // fragment的模板
            mvvmFragmentTemplate,
            // fragment的模板
            pageFragmentTemplate
    )
}