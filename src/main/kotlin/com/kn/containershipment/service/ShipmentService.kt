package com.kn.containershipment.service

import com.kn.containershipment.model.Shipment
import com.kn.containershipment.model.TemperatureRange
import com.kn.containershipment.model.TransportType

class ShipmentService {
    fun getShipments() : Set<Shipment> {
        val shipments: MutableSet<Shipment> = mutableSetOf()
        val temperatureRange = TemperatureRange(id = 1, min = -20, max = -10)

        val shipment1 = Shipment(id = 1, origin = "Frankfurt", destination = "Hamburg", customerId = "512351", createdDate = 1672873600,
        fragile = true, notifyCustomer = true, transportType = TransportType.ROAD, temperatureRange = temperatureRange)
        val shipment2 = Shipment(id = 2, origin = "Frankfurt", destination = "Hamburg", customerId = "512351", createdDate = 1672873600,
                fragile = false, notifyCustomer = true, transportType = TransportType.AIR, temperatureRange = temperatureRange)
        val shipment3 = Shipment(id = 3, origin = "Frankfurt", destination = "Hamburg", customerId = "512351", createdDate = 1672873600,
                fragile = true, notifyCustomer = false, transportType = TransportType.SEA, temperatureRange = temperatureRange)

        shipments.addAll(listOf(shipment1, shipment2, shipment3))
        return shipments.toSet()
    }
}