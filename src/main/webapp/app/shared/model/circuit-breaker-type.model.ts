export interface ICircuitBreakerType {
    id?: number;
    cbType?: string;
}

export class CircuitBreakerType implements ICircuitBreakerType {
    constructor(public id?: number, public cbType?: string) {}
}
