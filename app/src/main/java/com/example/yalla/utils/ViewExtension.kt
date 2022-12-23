package com.example.lec12.utils

import android.view.LayoutInflater
import android.view.View

val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)
