package com.example.myapplication.data.network.dto

import com.example.myapplication.domain.Message
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import java.time.Instant

fun MessageDto.toDomain(): Message = Message(
    id = id ?: "",
    sender = sender ?: "Unknown",
    text = text ?: "",
    createdAt = run {
        val primitive = createdAt?.jsonPrimitive
        primitive?.longOrNull ?: try {
            primitive?.contentOrNull?.let { Instant.parse(it).toEpochMilli() } ?: 0L
        } catch (e: Exception) {
            0L
        }
    }
)

fun List<MessageDto>.toDomain(): List<Message> =
    map { it.toDomain() }
