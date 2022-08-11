package com.hous.hous_aos.util

import timber.log.Timber

/**
 * DebugTree를 상속받아 더 자세한 Log남기기*/
class TimberDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return "${element.fileName} : ${element.lineNumber} - ${element.methodName}"
    }
}