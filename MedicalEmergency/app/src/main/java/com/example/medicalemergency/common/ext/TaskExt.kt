package com.example.medicalemergency.common.ext

import com.example.medicalemergency.model.Task


fun Task?.hasDueDate(): Boolean {
    return this?.dueDate.orEmpty().isNotBlank()
}

fun Task?.hasDueTime(): Boolean {
    return this?.dueTime.orEmpty().isNotBlank()
}