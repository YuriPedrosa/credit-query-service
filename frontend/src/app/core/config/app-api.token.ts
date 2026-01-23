import { InjectionToken } from '@angular/core';

export const APP_API_TOKEN = new InjectionToken<{ API: string }>('APP_API');

export interface AppConfig {
    apiUrl: string;
}

export { APP_API, loadEnv } from './api.config';

