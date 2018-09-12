export interface ICircuitBreakerDrive {
    id?: number;
    cbDrive?: string;
}

export class CircuitBreakerDrive implements ICircuitBreakerDrive {
    constructor(public id?: number, public cbDrive?: string) {}
}
