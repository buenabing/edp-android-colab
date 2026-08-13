package com.example.myapplication

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val contactNumber: String = "",
    val address: String = "",
    val username: String = "",
    val skills: List<String> = emptyList(),
    val newSkill: String = "", // Holds text currently typed in the "add skill" field
    val isPreview: Boolean = false // false = editing mode, true = preview mode
)
