package com.radiantmood.receptionist.ext

fun <T> MutableSet<T>.togglePresence(item: T) =
    if (contains(item)) {
        remove(item)
    } else add(item)