import { Component, Input, OnInit } from '@angular/core';
import { ExecutionPlan } from 'src/app/models/shipment.mode';

@Component({
  selector: 'execution-plan-component',
  templateUrl: './execution-plan-component.component.html',
  styleUrls: ['./execution-plan-component.component.scss']
})
export class ExecutionPlanComponentComponent implements OnInit {

  @Input()
  executionPlan: ExecutionPlan;

  constructor() { }

  ngOnInit(): void {
  }

}
