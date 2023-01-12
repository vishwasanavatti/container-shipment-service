import { Shipment, TransportType } from "../models/shipment.mode";
import { Injectable, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root',
})
export class ShipmentService {

    private url = 'http://localhost:8080';

    private shipments: Shipment[] = [];

    constructor(private httpClient: HttpClient) { }

    fetchShipments(): Promise<Shipment[]> {
        return this.httpClient
            .get<Shipment[]>(this.url + '/shipments').toPromise();
    }

    async getShipments(): Promise<Shipment[]> {
        this.shipments = await this.fetchShipments();
        return this.shipments;
    }

    getShipmentById(shipmentId: Number): Shipment | null {
        return this.shipments.find(it => it.id == shipmentId) || null;
    }

}