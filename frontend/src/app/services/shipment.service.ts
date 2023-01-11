import { Shipment, TransportType } from "../models/shipment.mode";
import { Injectable, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root',
})
export class ShipmentService implements OnInit {

    private url = 'http://localhost:8080';

    constructor(private httpClient: HttpClient) { }

    ngOnInit(): void {
        this.httpClient
            .get(this.url + '/shipments')
            .subscribe(
                (response) => {
                    this.shipments = response as Shipment[];
                },
                (error) => {
                    // Todo
                    console.log('error - ', error);
                }
            );
    }

    private shipments: Shipment[] = [];

    getShipments(): Shipment[] {
        return this.shipments;
    }

    getShipmentById(shipmentId: Number): Shipment | null {
        return this.shipments.find(it => it.id == shipmentId) || null;
    }

}