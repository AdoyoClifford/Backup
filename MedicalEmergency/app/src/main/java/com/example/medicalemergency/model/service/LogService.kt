package com.example.medicalemergency.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}