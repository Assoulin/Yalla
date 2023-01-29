package com.example.lec12.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast


val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)
