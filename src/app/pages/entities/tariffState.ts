export class TariffState {}

export class TariffPerCallState extends TariffState {
    currentCallsAvailable: number;
    isBlocked: boolean;
}

export class TariffPerTimeState extends TariffState {
    startTime: Date;
    endTime: Date;
    isBlocked: boolean;
}
