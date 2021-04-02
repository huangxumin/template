package other.mvvm.fragment

fun mvvmFragmentXml(
        packageName: String,
        activityClass: String,
        viewModelEnable: Boolean
) = if (viewModelEnable) {
    """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="viewModel"
            type="${packageName}.${activityClass}FragmentViewModel" />
    </data>
    
    <androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""
} else {
    """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="${packageName}.${activityClass}Fragment">
</androidx.constraintlayout.widget.ConstraintLayout>
        """
}