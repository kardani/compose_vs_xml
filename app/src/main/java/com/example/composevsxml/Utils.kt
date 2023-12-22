package com.example.composevsxml

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import kotlin.random.Random

fun generateRandomFullName(): String {
    val random = Random(System.currentTimeMillis())

    val firstNameLength = random.nextInt(4, 10) // Random length between 4 and 10 characters
    val lastNameLength = random.nextInt(4, 10)

    val firstName = generateRandomString(firstNameLength).capitalize(Locale.current)
    val lastName = generateRandomString(lastNameLength).capitalize(Locale.current)

    return "$firstName $lastName"
}

fun generateRandomString(length: Int, includeSpecialChar: Boolean = false, includeNumbers: Boolean = false): String {
    val random = Random(System.currentTimeMillis())
    var allowedChars = "abcdefghijklmnopqrstuvwxyz"

    allowedChars += if(includeSpecialChar) "!@#$%^&*()_+" else ""

    allowedChars += if(includeNumbers) "0123456789" else ""


    return (1..length)
        .map { allowedChars[random.nextInt(0, allowedChars.length)] }
        .joinToString("")
}
