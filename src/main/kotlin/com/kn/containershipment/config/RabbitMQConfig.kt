package com.kn.containershipment.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuration class file to setup connection with the RabbitMQ
 *
 * TODO - check the queue name as I am not able to receive the messages from the queue
 */
@Configuration
@EnableRabbit
class RabbitMQConfig {
    private val EXCHANGE_NAME = "amq.topic"
    private val QUEUE = "rabbitmq"
    private val ROUTING_KEY = "shipment"

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(EXCHANGE_NAME)
    }

    @Bean
    fun queue(): Queue {
        return Queue(QUEUE)
    }

    @Bean
    fun queueToExchangeBinding(): Binding {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY)
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.virtualHost = "/"
        connectionFactory.host = "localhost"
        connectionFactory.username = "kn"

        connectionFactory.setPassword("kn")
        return connectionFactory
    }

    @Bean
    fun rabbitMQTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.setDefaultReceiveQueue(QUEUE)
        rabbitTemplate.messageConverter = jsonMessageConverter()
        rabbitTemplate.setReplyAddress(queue().name)
        rabbitTemplate.setReplyTimeout(60000)
        rabbitTemplate.setUseDirectReplyToContainer(false)
        return rabbitTemplate
    }

    @Bean
    fun jsonMessageConverter(): MessageConverter {
        val objectMapper = ObjectMapper()
        return Jackson2JsonMessageConverter(objectMapper)
    }

    @Bean
    fun rabbitListenerContainerFactory(): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setMessageConverter(jsonMessageConverter())
        factory.setConcurrentConsumers(1)
        factory.setMaxConcurrentConsumers(1)
        return factory
    }

    @Bean
    fun amqpAdmin(): AmqpAdmin {
        return RabbitAdmin(connectionFactory())
    }
}