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

/**
 * Shipment controller class which provide two API endpoints
 */
@RestController
class ShipmentController(val executionPlanService: ExecutionPlanService) {

    /**
     * This method is commented as I am unable to get the shipments from the queue
     */
//    @GetMapping("/shipments")
//    fun getShipments(rabbitReceiver: RabbitReceiver): MutableSet<Shipment> {
//        return rabbitReceiver.shipments
//    }

    /**
     * This method provides the dummy list of shipments and can be accessed at "http::/localhost:80880/shipments"
     *
     * This is a workaround as I have trouble getting shipments from queuing system
     */
    @GetMapping("/shipments")
    fun getShipments(shipmentService: ShipmentService): Set<Shipment> {
        return shipmentService.getShipments()
    }

    /**
     * This method execute the shipment plan. It creates the execution plan returns it.
     *
     * @param - {@link ShipmentPlan} is the payload for this endpoint
     */
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