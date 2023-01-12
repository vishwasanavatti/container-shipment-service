

export interface Shipment {
    id: Number,

    origin: String,

    destination?: String,

    customerId: String,

    createdDate: Number,

    fragile: Boolean,

    notifyCustomer: Boolean,

    transportType: TransportType,
    temperatureRange: TemperatureRange
}

export enum TransportType {
    AIR,
    SEA,
    ROAD
}

export interface TemperatureRange {
    id: Number,
    min: Number,
    max: Number
}

export interface PlanTemplate {
    id: Number,
    name: string,
    selected: boolean
}

export interface ExecutionPlan {
    id: Number,
    origin: String,

    destination?: String,

    customerId: String,

    createdDate: Number,

    fragile: Boolean,

    notifyCustomer: Boolean,

    transportType: TransportType,
    temperatureRange: TemperatureRange,
    actions: ExecutionPlanAction[]
}

export interface ExecutionPlanAction {
    id: number,
    actionName: String,
    isExecuted: Boolean,
    isNotify: Boolean,
}