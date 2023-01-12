package com.kn.containershipment.service

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.ExecutionPlanAction
import com.kn.containershipment.model.ShipmentPlan
import com.kn.containershipment.repository.ExecutionPlanRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class ExecutionPlanService(val templateService: TemplateService, val executionPlanRepository: ExecutionPlanRepository) {

    fun createPlan(shipmentPlan: ShipmentPlan): ExecutionPlan {
        val shipment = shipmentPlan.shipment
        val templateId = shipmentPlan.templateId

        if (shipment == null || templateId == 0L) {
            throw IllegalArgumentException("shipment or template value is incorrect");
        }

        val template = templateService.getTemplateById(templateId)

        val executionPlanActions: MutableList<ExecutionPlanAction> = mutableListOf()

        for (action in template.actions) {
            val planAction = ExecutionPlanAction(actionName = action.name, isExecuted = true, isNotify = false)
            executionPlanActions.add(planAction)
        }

        val executionPlan = ExecutionPlan(origin = shipment.origin, destination = shipment.destination, customerId = shipment.customerId,
                transportType = shipment.transportType, fragile = shipment.fragile, notifyCustomer = shipment.notifyCustomer, templateId = templateId,
                actions = executionPlanActions, temperature = shipment.temperatureRange)

        executionPlanRepository.save(executionPlan)

        return executionPlan
    }

}