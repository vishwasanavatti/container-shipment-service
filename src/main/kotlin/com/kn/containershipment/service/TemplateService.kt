package com.kn.containershipment.service

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.repository.TemplateRepository
import org.springframework.stereotype.Service

@Service
class TemplateService(val templateRepository: TemplateRepository) {

    fun getTemplates(): List<PlanTemplate> {
        val templates = templateRepository.findAll()
        return templates.map { it }.toList()
    }

    fun getTemplateById(id : Long): PlanTemplate {
        val templates = templateRepository.findById(id)
        return templates.orElse(null)
    }
}