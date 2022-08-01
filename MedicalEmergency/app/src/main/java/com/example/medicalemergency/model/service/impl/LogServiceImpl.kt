package com.example.medicalemergency.model.service.impl

import com.example.medicalemergency.model.service.LogService
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

class LogServiceImpl(): LogService {
    override fun logNonFatalCrash(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }
}