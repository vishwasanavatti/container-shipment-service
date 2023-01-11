import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Shipment, PlanTemplate, TransportType } from "../../models/shipment.mode";

@Component({
  selector: 'execution-plan-model',
  templateUrl: './execution-plan-model.component.html',
  styleUrls: ['./execution-plan-model.component.scss']
})
export class ExecutionPlanModelComponent implements OnInit {
  private url = 'http://localhost:8080';

  @Input()
  shipment: Shipment;

  public templates: PlanTemplate[] = [];

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient
      .get(this.url + '/templates')
      .subscribe(
        (response) => {
          this.templates = this.transform(response);
        },
        (error) => {
          // Todo
          console.log('error - ', error);
        }
      );
  }

  transform(responses: any): PlanTemplate[] {
    let temps = [];
    responses.forEach(element => {
      temps.push({
        id: element.id,
        name: element.name,
        selected: false
      })
    });

    return temps;
  }

  public selectTemplate(id: number): void {
    let template = this.templates.find(t => t.id === id);
    if (template === null) {
      return;
    }

    this.templates.forEach(t => t.selected = false);

    template.selected = true;
  }

}
