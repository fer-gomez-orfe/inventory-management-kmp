package org.montra.crudmuliplatform.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SparePartModel (
    val serial_number: String = "",
    val item_Eng: String = "",
    val item_Esp: String = "",
    val description: String = "",
    val part_number: String = "",
    val cantidad: Int = 0,
    val notas: String = "",
    val ult_actualizacion: String = ""
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            item_Eng,
            item_Esp,
            serial_number,
            part_number,
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }

    fun checkIfExist(query: String) : Boolean {
        val matchingSerials = listOf(
            serial_number,
            part_number
        )
        return  matchingSerials.any {
            it.equals(query, ignoreCase = true)
        }
    }
}