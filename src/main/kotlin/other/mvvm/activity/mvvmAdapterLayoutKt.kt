package other.mvvm.activity.res.layout

fun mvvmAdapterLayoutKt(
        packageName: String,
        beanName: String
)=
    """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="$beanName"
            type="${packageName}.${beanName}" />
    </data>
    
<androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    //布局
    
    
    
</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""