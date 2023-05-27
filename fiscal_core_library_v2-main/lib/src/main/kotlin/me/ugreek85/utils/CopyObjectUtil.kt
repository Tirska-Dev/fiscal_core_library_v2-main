package me.ugreek85.utils

import java.lang.Exception
import java.lang.reflect.Method
import java.lang.reflect.Modifier

object CopyObjectUtil {

    private data class SetterAndGetterPair(
        var setter: Method? = null,
        var getter: Method? = null
    )

    fun <T> copyByInterface(interfaceOfObject: Class<T>, from: T, to: T)
    {
        val mapOfFieldMethods = fillMapOfFieldMethods(interfaceOfObject.methods)
        copyFieldValues(mapOfFieldMethods, from, to)
    }

    private fun <T> copyFieldValues(mapOfFieldMethods: Map<String, SetterAndGetterPair>, from: T, to: T)
    {
        mapOfFieldMethods.forEach { (_, pairOfMethods) ->
            if(pairOfMethods.setter != null && pairOfMethods.getter != null)
            {
                val value = pairOfMethods.getter!!.invoke(from)
                pairOfMethods.setter!!.invoke(to, value)
            }
        }
    }

    private fun fillMapOfFieldMethods(methods: Array<Method>): Map<String, SetterAndGetterPair>
    {
        val map = mutableMapOf<String, SetterAndGetterPair>()

        methods.forEach { method ->

            if(isSetterOrGetterMethod(method) && isMethodPublic(method))
            {
                val fieldName = getFieldName(method)
                if(map.containsKey(fieldName))
                {
                    map[fieldName]?.apply {
                        putMethodToPair(this, method)
                    }
                }
                else
                {
                    map[fieldName] = SetterAndGetterPair().apply {
                        putMethodToPair(this, method)
                    }
                }

            }
        }

        return map
    }

    private fun putMethodToPair(pair: SetterAndGetterPair, method: Method)
    {
        if(isGetter(method)) pair.getter = method
        else pair.setter = method
    }

    private fun isSetterOrGetterMethod(method: Method): Boolean
    {
        if(method.name.length <= 3) return false
        return isSetter(method) || isGetter(method)
    }

    private fun isSetter(method: Method): Boolean
    {
        return method.name.startsWith("set")
    }

    private fun isGetter(method: Method): Boolean
    {
        return method.name.startsWith("get") || method.name.startsWith("is")
    }

    private fun getMethodPrefixLength(method: Method): Int
    {
        arrayOf( "get", "is", "set").forEach {
            if(method.name.startsWith(it)) return it.length;
        }

        return 0;
    }


    private fun isMethodPublic(method: Method) : Boolean
    {
        return Modifier.isPublic(method.modifiers)
    }

    private fun getFieldName(method: Method): String
    {
        return method.name.substring(getMethodPrefixLength(method))
    }
}