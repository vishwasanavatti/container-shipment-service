package com.kn.containershipment.model

/**
 * This class model is used as payload for the {@link ExecutionPlan}
 */
data class ShipmentPlan(
        val shipment: Shipment? = null,

        val templateId: Long = 0
)