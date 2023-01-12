package com.kn.containershipment.model

data class ShipmentPlan(
        val shipment: Shipment? = null,

        val templateId: Long = 0
)