package com.kn.containershipment.controller

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.service.TemplateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController(val templateService: TemplateService) {

    @GetMapping("/templates")
    fun getTemplates(): List<PlanTemplate> {
        return templateService.getTemplates();
    }
}