package com.kn.containershipment.controller

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.service.TemplateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Template controller class which provide one API endpoint to get all the templates in the DB
 */
@RestController
class TemplateController(val templateService: TemplateService) {

    /**
     * The method returns all the templates stored in the DB and it can accessed at "http::/localhost:80880/templates"
     */
    @GetMapping("/templates")
    fun getTemplates(): List<PlanTemplate> {
        return templateService.getTemplates();
    }
}