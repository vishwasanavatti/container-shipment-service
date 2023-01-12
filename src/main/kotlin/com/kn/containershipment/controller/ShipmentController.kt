package com.kn.containershipment.controller

import com.kn.containershipment.consumer.RabbitReceiver
import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.model.ShipmentPlan
import com.kn.containershipment.service.ExecutionPlanService
import com.kn.containershipment.service.ShipmentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ShipmentController {

//    @GetMapping("/shipments")
//    fun getShipments(rabbitReceiver: RabbitReceiver): MutableSet<Shipment> {
//        return rabbitReceiver.shipments
//    }

    @GetMapping("/shipments")
    fun getShipments(shipmentService: ShipmentService): Set<Shipment> {
        return shipmentService.getShipments()
    }

    @PostMapping("/shipment-plan")
    @ResponseStatus(HttpStatus.CREATED)
    fun createExecutePlan(@RequestBody shipmentPlan: ShipmentPlan, executionPlanService: ExecutionPlanService): List<ExecutionPlan>  {
        return executionPlanService.createPlan(shipmentPlan);
    }

}