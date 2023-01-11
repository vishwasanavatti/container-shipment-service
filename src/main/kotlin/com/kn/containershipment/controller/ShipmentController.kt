package com.kn.containershipment.controller

import com.kn.containershipment.consumer.RabbitReceiver
import com.kn.containershipment.model.Shipment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ShipmentController {

    @GetMapping("/shipments")
    fun getShipments(rabbitReceiver: RabbitReceiver): MutableSet<Shipment> {
        return rabbitReceiver.shipments
    }

}