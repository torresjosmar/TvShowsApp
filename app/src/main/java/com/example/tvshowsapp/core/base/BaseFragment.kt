package com.example.tvshowsapp.core.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import com.example.tvshowsapp.R


abstract class BaseFragment<T : ViewBinding> : Fragment() {

    lateinit var bindingView: T
    private var activity: AppCompatActivity? = null
    protected var TAG_SCREEN: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listenToObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val type = findParameterizedType(javaClass)
        val clazz = type.actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        bindingView = method.invoke(null, layoutInflater, container, false) as T
        TAG_SCREEN = "[" + javaClass.simpleName + "]"
        Log.i("SCREEN", "*********************")
        Log.i("SCREEN", TAG_SCREEN!!)
        Log.i("SCREEN", "*********************")
        return bindingView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    abstract fun init()

    abstract fun listenToObserver()

    private fun findParameterizedType(clazz: Class<*>): ParameterizedType {
        var currentClass: Class<*>? = clazz
        while (currentClass != null) {
            val genericSuperclass = currentClass.genericSuperclass
            if (genericSuperclass is ParameterizedType) {
                return genericSuperclass
            }
            currentClass = currentClass.superclass
        }
        throw IllegalStateException("No se encontró ParameterizedType en la jerarquía")
    }

    fun onNetWorkError(event: Unit){
        Toast.makeText(activity, resources.getString(R.string.txt_network_error), Toast.LENGTH_LONG).show()
    }
}