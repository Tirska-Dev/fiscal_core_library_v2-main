package me.ugreek85.utils


object DocumentCopyUtil {

    fun <T, B: T> copyList(clazz: Class<T>, list: List<T>?, factory: () -> B): List<B>?
    {
        return if(list == null) null
        else {
            val newList = mutableListOf<B>()
            list.forEach { item ->

                val newItem = factory()
                CopyObjectUtil.copyByInterface(clazz, item, newItem)
                newList.add(newItem)
            }
            newList
        }
    }

    fun <T, B: T> copyArrayList(clazz: Class<T>, list: List<T>?, factory: () -> B): ArrayList<B>?
    {
        return if(list == null) null
        else {
            val newList = ArrayList<B>()
            list.forEach { item ->

                val newItem = factory()
                CopyObjectUtil.copyByInterface(clazz, item, newItem)
                newList.add(newItem)
            }
            newList
        }
    }

    fun <T> copyObject(clazz: Class<T>, source: T?, factory: () -> T): T?
    {
        return if(source == null) null
        else factory().apply {
            CopyObjectUtil.copyByInterface(clazz, source, this)
        }
    }

    fun <T, B: T> copyObject2(clazz: Class<T>, source: T?, factory: () -> B): B?
    {
        return if(source == null) null
        else factory().apply {
            CopyObjectUtil.copyByInterface(clazz, source, this)
        }
    }
}