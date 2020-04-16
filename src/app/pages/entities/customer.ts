import {TariffState} from './tariffState';

export class CustomerPreview {
    id: string;
    login: string;
    balance: number;
    tariffId: string;
}

export class Customer {
    id: string;
    login: string;
    balance: number;
    tariffId: string;
    tariffName: string;
    tariffType: string;
    email: string;
    password: string;
    tariffState: TariffState;
    phoneNumber: string;
    creationTime: Date;
    changeTime: Date;
    newTariff: boolean;
}
