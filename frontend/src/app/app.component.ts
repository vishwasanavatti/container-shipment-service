import { Component, OnInit } from '@angular/core';
import { ExecutionPlan, Shipment, TransportType } from "./models/shipment.mode";
import { ShipmentService } from "./services/shipment.service";

/**
 * I emphasize on writing unit tests but could not add them due to two days time constraints
 */
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {

  AIR: TransportType = TransportType.AIR;
  ROAD: TransportType = TransportType.ROAD;
  SEA: TransportType = TransportType.SEA;

  shipments: Shipment[] = [];
  selectedShipment: Shipment;
  executionPlan: ExecutionPlan;

  constructor(private shipmentService: ShipmentService) {
  }

  /**
   * Make this method async as shipments are fetched from the server
   */
  async ngOnInit(): Promise<void> {
    this.shipments = await this.shipmentService.getShipments();
  }

  /**
   * This method is used to set the 'selectedShipment' when checkbox is checked
   * 
   * @param id 
   * @param event 
   */
  public onSelect(id: number, event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.checked) this.selectedShipment = this.shipments.find(s => s.id === id);
    else this.selectedShipment = null;
  }

  /**
   * This method sets the 'executionPlan' emitted from `execution-plan-model`
   * 
   * @param executionPlan 
   */
  public setExecutionPlan(executionPlan: ExecutionPlan): void {
    this.executionPlan = executionPlan;
  }

}
