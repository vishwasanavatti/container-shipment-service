package com.kn.containershipment.controller

import com.kn.containershipment.consumer.RabbitReceiver
import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.model.ShipmentPlan
import com.kn.containershipment.service.ExecutionPlanService
import com.kn.containershipment.service.ShipmentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
class ShipmentController(val executionPlanService: ExecutionPlanService) {

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
    fun createExecutePlan(@RequestBody shipmentPlan: ShipmentPlan): ExecutionPlan {
        val executionPlan : ExecutionPlan
        try {
            executionPlan =  executionPlanService.createPlan(shipmentPlan)
        } catch (e: IllegalArgumentException) {
            // Check how to throw Bad Request Exception
            throw IllegalArgumentException("Error - $e")
        } catch (e : Exception) {
            throw InternalError("Something went wrong - $e")
        }

        return executionPlan
    }

}