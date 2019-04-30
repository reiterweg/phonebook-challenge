export interface IContact {
    id?: number;
    firstName?: string;
    lastName?: string;
    phone?: string;
}

export class Contact implements IContact {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public phone?: string
    ) {
    }
}
