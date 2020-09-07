export class TariffPreview {
    id: string;
    tariffName: string;
    tariffType: TariffType;
    price: number;
}

export class Tariff {
    id: string;
    tariffName: string = '';
    price: number;
    description: string = '';
    type: TariffType;
    creationTime: Date;
    changeTime: Date;

    public constructor() {}
}

// export class Tariff

export class TariffPerCall extends Tariff {
    callsAvailable: number;
}

export class TariffPerTime extends Tariff {
    timeAvailableDay: number;
}

export enum TariffType {
    PER_CALL = 'PER_CALL',
    PER_TIME = 'PER_TIME',
}
