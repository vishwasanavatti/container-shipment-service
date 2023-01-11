package com.kn.containershipment.consumer

import com.kn.containershipment.model.Shipment
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class RabbitReceiver {

    private val logger: Logger = Logger.getLogger(RabbitReceiver::class.java.toString())

    @RabbitListener(queues = ["rabbitmq"])
    @RabbitHandler
    fun receive(shipment: Shipment) {
        println("print here")
        logger.info("shipment - $shipment")
        println(shipment)
    }
}