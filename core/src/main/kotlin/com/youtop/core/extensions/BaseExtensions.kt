package com.youtop.core.extensions

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

fun <T> SharedFlow<T>.asMutable() = this as MutableSharedFlow